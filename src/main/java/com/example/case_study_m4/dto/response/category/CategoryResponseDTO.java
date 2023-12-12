package com.example.case_study_m4.dto.response.category;


import com.example.case_study_m4.dto.response.product.ProductListResponseDTO;
import lombok.Data;
import java.util.List;

@Data
public class CategoryResponseDTO {
    private Integer id;
    private String image;
    private String name;
    private List<ProductListResponseDTO> products;
}
