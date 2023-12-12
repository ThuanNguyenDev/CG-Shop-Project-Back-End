package com.example.case_study_m4.dto.response.product;

import lombok.Data;

@Data
public class ProductCustomerResponseDTO {
    private String image;
    private String name;
    private Integer price;
    private String categoryName;
}
