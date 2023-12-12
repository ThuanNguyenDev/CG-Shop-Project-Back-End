package com.example.case_study_m4.dto.request.order;

import lombok.Data;

@Data
public class OrderItemRequestDTO {
    private Integer orderId;
    private Integer productId;
    private Integer quantity;
    private Integer price;
}
