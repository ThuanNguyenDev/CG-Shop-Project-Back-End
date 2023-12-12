package com.example.case_study_m4.service.impl;

import com.example.case_study_m4.converter.OrderConverter;
import com.example.case_study_m4.dto.request.order.OrderCreateRequestDTO;
import com.example.case_study_m4.dto.response.order.OrderCreateResponseDTO;
import com.example.case_study_m4.dto.response.order.OrderResponseDTO;
import com.example.case_study_m4.entity.*;
import com.example.case_study_m4.repository.OrderRepository;
import com.example.case_study_m4.repository.ShoppingCartRepository;
import com.example.case_study_m4.repository.UserRepository;
import com.example.case_study_m4.service.OrderItemService;
import com.example.case_study_m4.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderConverter orderConverter;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private OrderItemService orderItemService;


    @Override
    public List<OrderResponseDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orderConverter.convertToDTOs(orders);
    }

    @Override
    public OrderResponseDTO getOrderById(Integer orderId) {
        Order optionalOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        if (optionalOrder!=null) {
            return orderConverter.convertToDTO(optionalOrder);
        } else {

          return null;
        }
    }



    @Override
    public void deleteOrder(Integer orderId) {
        Order deleteOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        if (deleteOrder != null) {
            orderRepository.delete(deleteOrder);
        }
    }



    @Override
    public OrderResponseDTO createOrder(int cartId) throws Exception {

        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Shopping Cart not found"));

        if (shoppingCart.getCartItems() == null) {
            throw new Exception("Cart Item not found");
        }

        User foundUser = userRepository.findById(shoppingCart.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Order order = new Order();
        LocalDateTime now = LocalDateTime.now();
        order.setOrderDate(now);

        order.setUser(foundUser);
        int totalAmount = 0;

        List<OrderItem> orderItemList = new ArrayList<>();

        for (CartItem cartItem : shoppingCart.getCartItems()) {
            int itemTotal = cartItem.getPrice() * cartItem.getQuantity();
            totalAmount += itemTotal;
            orderItemList.add(orderItemService.transferCartItemToOrderItem(cartItem.getId()));
        }

        for (OrderItem orderItem : orderItemList) {
            orderItem.setOrder(order);
        }

        order.setOrderItems(orderItemList);
        order.setTotalPrice(totalAmount);

        Order savedOrder = orderRepository.save(order);
        return orderConverter.convertToDTO(savedOrder);

    }

    @Override
    public OrderResponseDTO createOrder(String username) throws Exception {

        User foundUser = userRepository.findByUsername(username);

        ShoppingCart shoppingCart = shoppingCartRepository.findById(foundUser.getShoppingCart().getId())
                .orElseThrow(() -> new EntityNotFoundException("Shopping Cart not found"));

        if (shoppingCart.getCartItems() == null) {
            throw new Exception("Cart Item not found");
        }

        Order order = new Order();
        LocalDateTime now = LocalDateTime.now();
        order.setOrderDate(now);

        order.setUser(foundUser);
        int totalAmount = 0;

        List<OrderItem> orderItemList = new ArrayList<>();

        for (CartItem cartItem : shoppingCart.getCartItems()) {
            int itemTotal = cartItem.getPrice() * cartItem.getQuantity();
            totalAmount += itemTotal;
            orderItemList.add(orderItemService.transferCartItemToOrderItem(cartItem.getId()));
        }

        for (OrderItem orderItem : orderItemList) {
            orderItem.setOrder(order);
        }

        order.setOrderItems(orderItemList);
        order.setTotalPrice(totalAmount);

        Order savedOrder = orderRepository.save(order);
        return orderConverter.convertToDTO(savedOrder);
    }

}
