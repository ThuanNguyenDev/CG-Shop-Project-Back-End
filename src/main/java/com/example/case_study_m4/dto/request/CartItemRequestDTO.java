package com.example.case_study_m4.dto.request;

import lombok.Data;

@Data
public class CartItemRequestDTO {
    private int productId;
    private int quantity;
}
