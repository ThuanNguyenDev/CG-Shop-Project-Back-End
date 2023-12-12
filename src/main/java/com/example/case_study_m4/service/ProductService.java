package com.example.case_study_m4.service;

import com.example.case_study_m4.dto.request.product.ProductRequestDTO;
import com.example.case_study_m4.dto.response.product.CommonResponseDTO;
import com.example.case_study_m4.dto.response.product.PageResponseDTO;
import com.example.case_study_m4.dto.response.product.ProductCustomerResponseDTO;
import com.example.case_study_m4.dto.response.product.ProductResponseDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface ProductService {
    ProductResponseDTO createProduct(ProductRequestDTO productDTO);
    CommonResponseDTO<ProductResponseDTO> getAllProducts(Pageable pageable);
    ProductResponseDTO updateProduct(Integer id,ProductRequestDTO productRequestDTO);
    boolean deleteProduct(Integer id);
    CommonResponseDTO<ProductResponseDTO> searchProducts(String name,Pageable pageable);
    List<ProductResponseDTO> getProductsByCategoryId(Integer categoryId);
    ProductResponseDTO getProductById(Integer id);
    Long countProducts();
    ProductCustomerResponseDTO getProductDetailById(Integer id);
    CommonResponseDTO<ProductResponseDTO> filterProductsByPrice(Integer minPrice, Integer maxPrice, Pageable pageable);

}
