package com.example.case_study_m4.dto.response.product;

import lombok.Data;

import java.util.List;

@Data
public class PageResponseDTO<ProductResponseDTO> {
    private List<ProductResponseDTO> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;

}
