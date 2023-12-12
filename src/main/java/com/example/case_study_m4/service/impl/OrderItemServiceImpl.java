package com.example.case_study_m4.service.impl;

import com.example.case_study_m4.converter.OrderConverter;
import com.example.case_study_m4.converter.OrderItemConverter;
import com.example.case_study_m4.dto.request.order.OrderItemRequestDTO;
import com.example.case_study_m4.dto.response.CartItemResponseDTO;
import com.example.case_study_m4.dto.response.order.OrderItemResponseDTO;
import com.example.case_study_m4.dto.response.order.OrderResponseDTO;
import com.example.case_study_m4.entity.*;
import com.example.case_study_m4.repository.*;
import com.example.case_study_m4.service.OrderItemService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemConverter orderItemConverter;
    @Autowired
    private OrderConverter orderConverter;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private  UserRepository userRepository;



    @Override
    public OrderItemResponseDTO createOrderItem(OrderItemRequestDTO orderItemRequest) {
        OrderItem orderItem = orderItemConverter.convertToEntity(orderItemRequest);
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return orderItemConverter.convertToDTO(savedOrderItem);
    }


    @Override
    public OrderItemResponseDTO updateOrderItem(Integer orderItemId, OrderItemRequestDTO updatedOrderItem) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new EntityNotFoundException("OrderItem not found"));

        OrderItem updatedOrderItemEntity = orderItemRepository.save(orderItem);
        return orderItemConverter.convertToDTO(updatedOrderItemEntity);
    }



    @Override
    public List<OrderItemResponseDTO> getOrderItemsInOrder(Integer orderId) {
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);
        return orderItemConverter.convertToDTOs(orderItems);
    }

    @Override
    public List<OrderItemResponseDTO> getAllOderItemsByOrder(Integer userID,Integer orderId) {
        User foundUser = userRepository.findById(userID).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Order foundOrder = null;
        for (Order order : foundUser.getOrders()) {
            if(order.getId()==orderId) {
                foundOrder = order;
                return orderItemConverter.convertToDTOs(foundOrder.getOrderItems());
            }
        }
        return null;

    }

    public OrderItemResponseDTO convertCartItemToOrderItemAndDelete(Integer cartItemId) {

        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new EntityNotFoundException("CartItem not found"));

        if (cartItem != null) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setSubTotalPrice(cartItem.getSubtotalPrice());
            orderItemRepository.save(orderItem);
//          cartItemRepository.deleteById(cartItemId);
            OrderItemResponseDTO orderItemResponseDTO = orderItemConverter.convertToDTO(orderItem);
            return orderItemResponseDTO;
        }

    return null;
    }



    @Override
    public OrderItem transferCartItemToOrderItem(int cartItemId) {

        CartItem cartItem = cartItemRepository.findById(cartItemId).
                orElseThrow(() -> new EntityNotFoundException("CartItem not found"));

        if (cartItem != null) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setSubTotalPrice(cartItem.getSubtotalPrice());
            orderItemRepository.save(orderItem);
            cartItemRepository.deleteById(cartItemId);
            return orderItem;
        }

        return null;

    }
}

