package com.example.case_study_m4.converter;

import com.example.case_study_m4.dto.request.product.ProductRequestDTO;
import com.example.case_study_m4.dto.response.product.ProductCustomerResponseDTO;
import com.example.case_study_m4.dto.response.product.ProductListResponseDTO;
import com.example.case_study_m4.dto.response.product.ProductResponseDTO;
import com.example.case_study_m4.entity.Category;
import com.example.case_study_m4.entity.Product;
import com.example.case_study_m4.repository.CategoryRepository;
import com.example.case_study_m4.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductConverter {
//    @Autowired
//    private CategoryRepository categoryRepository;

    public Product fromProductDTOToProduct(ProductRequestDTO productRequestDTO){
        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        Category category = new Category();
        category.setId(productRequestDTO.getCategoryId());
        product.setCategory(category);
        product.setImage(productRequestDTO.getImage());
        return product;
    }

    public List<Product> fromListDTOtoListProduct(List<ProductRequestDTO> productRequestDTOList) {
        List<Product> productList = new ArrayList<>();
        for(ProductRequestDTO productRequestDTO:productRequestDTOList) {
            productList.add(fromProductDTOToProduct(productRequestDTO));
        }
        return productList;
    }

    public ProductResponseDTO fromProductToProductDTO(Product product) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(product.getId());
        productResponseDTO.setImage(product.getImage());
        productResponseDTO.setName(product.getName());
        productResponseDTO.setPrice(product.getPrice());
        Category category = new Category();
        category.setId(product.getCategory().getId());
        category.setName(product.getCategory().getName());
        productResponseDTO.setCategory(category);
        return productResponseDTO;
    }

    public List<ProductResponseDTO> fromProductListToProductDTOList(List<Product> productList) {
        List<ProductResponseDTO> productResponseDTOList = new ArrayList<>();
        for (Product product: productList) {
            productResponseDTOList.add(fromProductToProductDTO(product));
        }
        return productResponseDTOList;
    }


    public ProductListResponseDTO fromProductListToProductDTO(Product product) {
        ProductListResponseDTO productListResponseDTO = new ProductListResponseDTO();
        productListResponseDTO.setId(product.getId());
        productListResponseDTO.setName(product.getName());
        productListResponseDTO.setPrice(product.getPrice());
        return productListResponseDTO;
        }

    public List<ProductListResponseDTO> fromCateProductListToProductDTOList(List<Product> productList) {
        List<ProductListResponseDTO> productListResponseDTOS = new ArrayList<>();
        for (Product product: productList) {
           productListResponseDTOS.add(fromProductListToProductDTO(product));
        }
        return productListResponseDTOS;
        }

    public ProductCustomerResponseDTO fromProductToProductDetailDTO(Product product) {

        ProductCustomerResponseDTO productResponseDTO = new ProductCustomerResponseDTO();
        productResponseDTO.setImage(product.getImage());
        productResponseDTO.setName(product.getName());
        productResponseDTO.setPrice(product.getPrice());

//        Category foundCategory = categoryRepository.findById(product.getCategory().getId())
//                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        productResponseDTO.setCategoryName(product.getCategory().getName());
        return productResponseDTO;
    }
}





