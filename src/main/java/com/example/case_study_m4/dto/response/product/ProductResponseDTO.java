package com.example.case_study_m4.dto.response.product;

import com.example.case_study_m4.entity.Category;
import lombok.Data;

@Data
public class ProductResponseDTO {
    private Integer id;
    private String image;
    private String name;
    private Integer price;
    private Category category;
}
