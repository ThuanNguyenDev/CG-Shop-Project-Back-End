package com.example.case_study_m4.service;

import com.example.case_study_m4.dto.response.category.CategoryCommonResponseDTO;
import com.example.case_study_m4.dto.response.category.CategoryPageResponseDTO;
import com.example.case_study_m4.dto.request.category.CategoryRequestDTO;
import com.example.case_study_m4.dto.response.category.CategoryResponseDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    CategoryResponseDTO createCategory(CategoryRequestDTO categoryDTO);
    List<CategoryResponseDTO> getAllCategories();
    CategoryCommonResponseDTO<CategoryResponseDTO> getAllCategory(Pageable pageable);
    CategoryResponseDTO updateCategory(Integer id,
                                       CategoryRequestDTO categoryRequestDTO);
    boolean deleteCategory(Integer id);
    CategoryResponseDTO getCategoryById(Integer categoryId);
    Integer getProductCountForCategory(Integer categoryId);
    CategoryCommonResponseDTO<CategoryResponseDTO> searchCategory(String name,
                                                                  Pageable pageable);
}
