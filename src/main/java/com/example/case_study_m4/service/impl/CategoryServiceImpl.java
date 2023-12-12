package com.example.case_study_m4.service.impl;

import com.example.case_study_m4.converter.CategoryConverter;
import com.example.case_study_m4.dto.response.category.CategoryCommonResponseDTO;
import com.example.case_study_m4.dto.response.category.CategoryPageResponseDTO;
import com.example.case_study_m4.dto.request.category.CategoryRequestDTO;
import com.example.case_study_m4.dto.response.category.CategoryResponseDTO;
import com.example.case_study_m4.entity.Category;
import com.example.case_study_m4.repository.CategoryRepository;
import com.example.case_study_m4.repository.ProductRepository;
import com.example.case_study_m4.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryConverter categoryConverter;

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryDTO) {
        Category category = categoryConverter.fromCategoryDTOToCategory(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return categoryConverter.fromCategoryToCategoryDTO(savedCategory);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        List<CategoryResponseDTO> categoryDTOList = categoryConverter.fromCategoryListToCategoryDTOList(categories);
        return categoryDTOList;
    }

    @Override
    public CategoryResponseDTO updateCategory(Integer id,CategoryRequestDTO categoryRequestDTO) {

        if (!categoryRepository.existsById(id)) {
            return null;
        }
        Category categoryToUpdate = categoryConverter.fromCategoryDTOToCategory(categoryRequestDTO);
        categoryToUpdate.setId(id);
        Category updatedCategory = categoryRepository.save(categoryToUpdate);
        CategoryResponseDTO categoryResponseDTO = categoryConverter.fromCategoryToCategoryDTO(updatedCategory);
        return categoryResponseDTO;
    }

    @Override
    public boolean deleteCategory(Integer id) {

        if (!categoryRepository.existsById(id)) {
            return false;
        }
        categoryRepository.deleteById(id);
        return true;
    }

    @Override
    public CategoryResponseDTO getCategoryById(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found"));
        CategoryResponseDTO categoryDTO = categoryConverter.fromCategoryToCategoryDTO(category);
        return categoryDTO;

    }

    @Override
    public Integer getProductCountForCategory(Integer categoryId) {
        Integer productCount = productRepository.countByCategoryId(categoryId);

        return productCount;
    }

    @Override
    public CategoryCommonResponseDTO<CategoryResponseDTO> searchCategory(String name, Pageable pageable) {

        Page<Category> categories = categoryRepository.findByNameLike("%" + name + "%", pageable);

        List<CategoryResponseDTO> categoryDTOs = categories
                .getContent()
                .stream()
                .map(categoryConverter::fromCategoryToCategoryDTO)
                .collect(Collectors.toList());

        CategoryPageResponseDTO<CategoryResponseDTO> pageResponseDTO = new CategoryPageResponseDTO<>();
        pageResponseDTO.setContent(categoryDTOs);
        pageResponseDTO.setPage(categories.getNumber());
        pageResponseDTO.setSize(categories.getSize());
        pageResponseDTO.setTotalElements(categories.getTotalElements());
        pageResponseDTO.setTotalPages(categories.getTotalPages());

        CategoryCommonResponseDTO<CategoryResponseDTO> commonResponseDTO = new CategoryCommonResponseDTO<>();
        commonResponseDTO.setData(pageResponseDTO);
        commonResponseDTO.setSuccess(true);
        commonResponseDTO.setMessage("Search results retrieved successfully.");

        return commonResponseDTO;
    }

    @Override
    public CategoryCommonResponseDTO<CategoryResponseDTO> getAllCategory(Pageable pageable) {
        Page<Category> categoryPage = categoryRepository.findAll(pageable);

        List<CategoryResponseDTO> categoryDTOs = categoryPage
                .getContent()
                .stream()
                .map(categoryConverter::fromCategoryToCategoryDTO)
                .collect(Collectors.toList());

        CategoryPageResponseDTO<CategoryResponseDTO> pageResponseDTO = new CategoryPageResponseDTO<>();
        pageResponseDTO.setContent(categoryDTOs);
        pageResponseDTO.setPage(categoryPage.getNumber());
        pageResponseDTO.setSize(categoryPage.getSize());
        pageResponseDTO.setTotalElements(categoryPage.getTotalElements());
        pageResponseDTO.setTotalPages(categoryPage.getTotalPages());

        CategoryCommonResponseDTO<CategoryResponseDTO> commonResponseDTO = new CategoryCommonResponseDTO<>();
        commonResponseDTO.setData(pageResponseDTO);
        commonResponseDTO.setSuccess(true);
        commonResponseDTO.setMessage("Categories retrieved successfully.");

        return commonResponseDTO;
    }

}

