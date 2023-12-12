package com.example.case_study_m4.controller.customer;

import com.example.case_study_m4.config.security.JwtTokenUtil;
import com.example.case_study_m4.dto.request.order.OrderCreateRequestDTO;
import com.example.case_study_m4.dto.response.order.OrderResponseDTO;
import com.example.case_study_m4.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("")
    public ResponseEntity<OrderResponseDTO> createOrder(HttpServletRequest request) throws Exception {
        String token = request.getHeader("Authorization");
        String username = jwtTokenUtil.getUsernameFromJWT(token);
        OrderResponseDTO createdOrder = orderService.createOrder(username);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }
}
