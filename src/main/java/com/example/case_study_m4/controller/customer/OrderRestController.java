package com.example.case_study_m4.controller.customer;

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
@RequestMapping("/api/user/order")
@CrossOrigin(origins = "*")
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;


    @PostMapping("/{userID}/create")
    public ResponseEntity<OrderResponseDTO> createOrder(@PathVariable Integer userID) throws Exception {
        OrderResponseDTO createdOrder = orderService.createOrder(userID);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }


    @GetMapping("/{userID}/get-order/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Integer orderId) {
        OrderResponseDTO order = orderService.getOrderById(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }



    @DeleteMapping("/{userID}/delete/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/{userID}/{orderId}")
    public ResponseEntity<List<OrderItemResponseDTO>> getOrderItemsInOrder(@PathVariable Integer orderId) {
        List<OrderItemResponseDTO> orderItems = orderItemService.getOrderItemsInOrder(orderId);
        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }
}
