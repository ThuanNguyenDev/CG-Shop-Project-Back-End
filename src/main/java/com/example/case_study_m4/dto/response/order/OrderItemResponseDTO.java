package com.example.case_study_m4.dto.response.order;

import lombok.Data;

@Data
public class    OrderItemResponseDTO {
//    private Integer id;
//    private Integer orderId;
//    private Integer productId;
    private String productName;
    private Integer quantity;
    private Integer price;
    private Integer subTotalPrice;
}
