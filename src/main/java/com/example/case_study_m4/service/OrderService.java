package com.example.case_study_m4.service;

import com.example.case_study_m4.dto.response.order.OrderResponseDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface OrderService {

    List<OrderResponseDTO> getAllOrders();
    OrderResponseDTO getOrderById(Integer orderId);
    void deleteOrder(Integer orderId);

    OrderResponseDTO createOrder (int cartId) throws Exception;

    OrderResponseDTO createOrder (String username) throws Exception;

}
