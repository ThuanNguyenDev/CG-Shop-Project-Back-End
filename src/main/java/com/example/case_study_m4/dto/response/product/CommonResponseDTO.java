package com.example.case_study_m4.dto.response.product;

import com.example.case_study_m4.dto.response.product.PageResponseDTO;
import lombok.Data;

@Data
public class CommonResponseDTO<ProductResponseDTO> {
    private PageResponseDTO<ProductResponseDTO> data;
    private boolean success;
    private String message;
}

