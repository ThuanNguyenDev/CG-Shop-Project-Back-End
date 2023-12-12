package com.example.case_study_m4.converter;

import com.example.case_study_m4.dto.request.category.CategoryRequestDTO;
import com.example.case_study_m4.dto.response.category.CategoryResponseDTO;
import com.example.case_study_m4.dto.response.product.ProductListResponseDTO;
import com.example.case_study_m4.entity.Category;
import com.example.case_study_m4.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryConverter {

    @Autowired
    private ProductConverter productConverter;
    public Category fromCategoryDTOToCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category = new Category();
        category.setName(categoryRequestDTO.getName());
        category.setImage(categoryRequestDTO.getImage());
        return category;
    }

    public CategoryResponseDTO fromCategoryToCategoryDTO(Category category) {
       CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
       categoryResponseDTO.setId(category.getId());
       categoryResponseDTO.setName(category.getName());
       categoryResponseDTO.setImage(category.getImage());

       List<Product> productList = (category.getProducts());
       List<ProductListResponseDTO> productDTOS = productConverter.fromCateProductListToProductDTOList(productList);
       categoryResponseDTO.setProducts(productDTOS);
       return categoryResponseDTO;
    }

    public List<CategoryResponseDTO> fromCategoryListToCategoryDTOList(List<Category> categoryList) {
        List<CategoryResponseDTO> categoryResponseDTOList = new ArrayList<>();
        for (Category category : categoryList) {
            categoryResponseDTOList.add(fromCategoryToCategoryDTO(category));
        }
        return  categoryResponseDTOList;
    }


}

