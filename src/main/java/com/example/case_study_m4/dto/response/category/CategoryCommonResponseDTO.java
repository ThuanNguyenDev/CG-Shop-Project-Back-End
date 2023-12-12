package com.example.case_study_m4.dto.response.category;

import lombok.Data;
@Data
public class CategoryCommonResponseDTO<CategoryResponseDTO> {
    private CategoryPageResponseDTO<CategoryResponseDTO> data;
    private boolean success;
    private String message;
}
