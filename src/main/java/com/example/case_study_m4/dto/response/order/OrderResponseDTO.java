package com.example.case_study_m4.dto.response.order;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDTO {
    private Integer id;
    private Integer userId;
    private LocalDateTime orderDate;
    private List<OrderItemResponseDTO> orderItems;
    private Integer totalPrice;
}
