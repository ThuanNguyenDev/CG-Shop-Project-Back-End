package com.example.case_study_m4.service;

import com.example.case_study_m4.dto.request.order.OrderItemRequestDTO;
import com.example.case_study_m4.dto.response.CartItemResponseDTO;
import com.example.case_study_m4.dto.response.order.OrderItemResponseDTO;
import com.example.case_study_m4.dto.response.order.OrderResponseDTO;
import com.example.case_study_m4.entity.OrderItem;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface OrderItemService {
    OrderItemResponseDTO createOrderItem(OrderItemRequestDTO orderItemRequest);

    OrderItemResponseDTO updateOrderItem(Integer orderItemId,
                                         OrderItemRequestDTO updatedOrderItem);

    List<OrderItemResponseDTO> getOrderItemsInOrder(Integer orderId);

    List<OrderItemResponseDTO> getAllOderItemsByOrder(Integer userID,
                                                      Integer orderId);

    OrderItemResponseDTO convertCartItemToOrderItemAndDelete(Integer cartItemId);


    OrderItem transferCartItemToOrderItem(int cartItemId);
}
