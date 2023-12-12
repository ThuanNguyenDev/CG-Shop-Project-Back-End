package com.example.case_study_m4.dto.response;

import lombok.Data;

@Data
public class CartItemCreateResponseDTO {
//    private Integer id;
//    private Integer shoppingCartId;
//    private Integer productId;
    private String productName;
    private Integer productPrice;
    private Integer quantity;
    private Integer subtotalPrice;
}
