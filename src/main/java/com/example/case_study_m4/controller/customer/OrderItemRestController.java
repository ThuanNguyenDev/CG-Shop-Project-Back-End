package com.example.case_study_m4.controller.customer;

import com.example.case_study_m4.dto.request.order.OrderItemRequestDTO;
import com.example.case_study_m4.dto.response.order.OrderItemResponseDTO;
import com.example.case_study_m4.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/order-item")
@CrossOrigin(origins = "*")
public class OrderItemRestController {

    @Autowired
    private OrderItemService orderItemService;

    @PostMapping("/create")
    public ResponseEntity<OrderItemResponseDTO> createOrderItem(@RequestBody OrderItemRequestDTO orderItemRequest) {
        OrderItemResponseDTO createdOrderItem = orderItemService.createOrderItem(orderItemRequest);
        return new ResponseEntity<>(createdOrderItem, HttpStatus.CREATED);
    }



    @PutMapping("/{orderItemId}")
    public ResponseEntity<OrderItemResponseDTO> updateOrderItem(@PathVariable Integer orderItemId, @RequestBody OrderItemRequestDTO updatedOrderItem) {
        OrderItemResponseDTO orderItem = orderItemService.updateOrderItem(orderItemId, updatedOrderItem);
        return new ResponseEntity<>(orderItem, HttpStatus.OK);
    }



    @GetMapping("/{userId}/order/{orderId}/order-items")
    public ResponseEntity<List<OrderItemResponseDTO>> getOrderItemList(@PathVariable Integer userId ,@PathVariable Integer orderId) {
        List<OrderItemResponseDTO> orderItemResponseDTOS = orderItemService.getAllOderItemsByOrder(userId,orderId);
        return new ResponseEntity<>(orderItemResponseDTOS, HttpStatus.OK);
    }


}
