package com.example.case_study_m4.dto.request.product;
import lombok.Data;

@Data
public class ProductRequestDTO {
    private String name;
    private Integer price;
    private Integer categoryId;
    private String image;
}
