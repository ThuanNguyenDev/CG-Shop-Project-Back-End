package com.example.case_study_m4.converter;

import com.example.case_study_m4.dto.request.order.OrderCreateRequestDTO;
import com.example.case_study_m4.dto.request.order.OrderRequestDTO;
import com.example.case_study_m4.dto.response.order.OrderCreateResponseDTO;
import com.example.case_study_m4.dto.response.order.OrderResponseDTO;
import com.example.case_study_m4.entity.Order;
import com.example.case_study_m4.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderConverter {

    @Autowired
    private OrderItemConverter orderItemConverter;

    public Order convertToEntity(OrderRequestDTO orderRequest) {
        if (orderRequest == null) {
            return null;
        }
        Order order = new Order();
        User user = new User();
        user.setId(orderRequest.getUserId());

        order.setUser(user);
//        order.setOrderDate(orderRequest.getOrderDate());
        order.setOrderItems(orderItemConverter.convertToEntityList(orderRequest.getOrderItems()));
        return order;
    }

    public OrderResponseDTO convertToDTO(Order order) {
        if (order == null) {
            return null;
        }

        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setId(order.getId());
        orderResponseDTO.setUserId(order.getUser().getId());
        orderResponseDTO.setOrderDate(order.getOrderDate());
        orderResponseDTO.setOrderItems(orderItemConverter.convertToDTOs(order.getOrderItems()));
        orderResponseDTO.setTotalPrice(order.getTotalPrice());

        return orderResponseDTO;
    }

    public List<OrderResponseDTO> convertToDTOs(List<Order> orders) {
        List<OrderResponseDTO> orderResponseDTOList = new ArrayList<>();
        for (Order order: orders) {
            orderResponseDTOList.add(convertToDTO(order));
        }
        return orderResponseDTOList;
    }


    public Order convertToCreateRequestEntity(OrderCreateRequestDTO orderRequest) {
        Order order = new Order();
        User user = new User();
//        user.setId(orderRequest.getUserId());
        order.setUser(user);
//        order.setOrderDate(orderRequest.getOrderDate());
        return order;

    }

    public OrderCreateResponseDTO convertToCreateResponseDTO(Order savedOrder) {
        OrderCreateResponseDTO orderCreateResponseDTO = new OrderCreateResponseDTO();
        orderCreateResponseDTO.setId(savedOrder.getId());
        orderCreateResponseDTO.setUserId(savedOrder.getUser().getId());
//        orderCreateResponseDTO.setOrderDate(savedOrder.getOrderDate());
        return orderCreateResponseDTO;
    }
}
