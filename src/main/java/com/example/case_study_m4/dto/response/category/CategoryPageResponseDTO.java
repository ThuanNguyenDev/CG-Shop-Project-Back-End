package com.example.case_study_m4.dto.response.category;

import lombok.Data;

import java.util.List;

@Data
public class CategoryPageResponseDTO<CategoryResponseDTO> {
    private List<CategoryResponseDTO> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
