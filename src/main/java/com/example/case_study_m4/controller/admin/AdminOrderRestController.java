package com.example.case_study_m4.controller.admin;

import com.example.case_study_m4.dto.request.order.OrderCreateRequestDTO;
import com.example.case_study_m4.dto.response.order.OrderCreateResponseDTO;
import com.example.case_study_m4.dto.response.order.OrderItemResponseDTO;
import com.example.case_study_m4.dto.response.order.OrderResponseDTO;
import com.example.case_study_m4.service.OrderItemService;
import com.example.case_study_m4.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/order")
@CrossOrigin(origins = "*")
public class AdminOrderRestController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;


    @GetMapping("/show")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<OrderResponseDTO> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Integer orderId) {
        OrderResponseDTO order = orderService.getOrderById(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/{orderId}/order-items")
    public ResponseEntity<List<OrderItemResponseDTO>> getOrderItemsInOrder(@PathVariable Integer orderId) {
        List<OrderItemResponseDTO> orderItems = orderItemService.getOrderItemsInOrder(orderId);
        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }
}
