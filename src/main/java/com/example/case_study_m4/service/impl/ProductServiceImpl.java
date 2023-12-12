package com.example.case_study_m4.service.impl;

import com.example.case_study_m4.converter.ProductConverter;
import com.example.case_study_m4.dto.request.product.ProductRequestDTO;
import com.example.case_study_m4.dto.response.product.CommonResponseDTO;
import com.example.case_study_m4.dto.response.product.PageResponseDTO;
import com.example.case_study_m4.dto.response.product.ProductCustomerResponseDTO;
import com.example.case_study_m4.dto.response.product.ProductResponseDTO;
import com.example.case_study_m4.entity.Product;
import com.example.case_study_m4.repository.ProductRepository;
import com.example.case_study_m4.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductConverter productConverter;

    @Override
    public ProductResponseDTO getProductById(Integer id) {
      Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
      ProductResponseDTO productDTO = productConverter.fromProductToProductDTO(product);
      return productDTO;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productDTO) {
        Product product = productConverter.fromProductDTOToProduct(productDTO);
        Product savedProduct = productRepository.save(product);
        return productConverter.fromProductToProductDTO(savedProduct);
    }

    @Override
    public ProductResponseDTO updateProduct(Integer id,ProductRequestDTO productRequestDTO) {

        if (!productRepository.existsById(id)) {
            return null;
        }

        Product productToUpdate = productConverter.fromProductDTOToProduct(productRequestDTO);
        productToUpdate.setId(id);
        Product updatedProduct = productRepository.save(productToUpdate);
        ProductResponseDTO productResponseDTO = productConverter.fromProductToProductDTO(updatedProduct);
        return productResponseDTO;
    }

    @Override
    public boolean deleteProduct(Integer id) {
        if (!productRepository.existsById(id)) {
            return false;
        }

        productRepository.deleteById(id);
        return true;
    }

    @Override
    public CommonResponseDTO<ProductResponseDTO> searchProducts(String name, Pageable pageable) {

        Page<Product> products = productRepository.findByNameLike("%" + name + "%",pageable);

        List<Product> productList = products.toList();
        List<ProductResponseDTO> productResponseDTOS = productConverter.fromProductListToProductDTOList(productList);

        PageResponseDTO<ProductResponseDTO> pageResponseDTO = new PageResponseDTO<>();
        pageResponseDTO.setContent(productResponseDTOS);
        pageResponseDTO.setPage(products.getNumber());
        pageResponseDTO.setSize(products.getSize());
        pageResponseDTO.setTotalElements(products.getTotalElements());
        pageResponseDTO.setTotalPages(products.getTotalPages());

        CommonResponseDTO<ProductResponseDTO> commonResponseDTO = new CommonResponseDTO<>();
        commonResponseDTO.setData(pageResponseDTO);
        commonResponseDTO.setSuccess(true);
        commonResponseDTO.setMessage("Products retrieved successfully.");
        return commonResponseDTO;

    }


    @Override
    public List<ProductResponseDTO> getProductsByCategoryId(Integer categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);
       List<ProductResponseDTO> result = productConverter.fromProductListToProductDTOList(products);
       return result;
    }
    @Override
    public CommonResponseDTO<ProductResponseDTO> getAllProducts(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);

        List<ProductResponseDTO> productDTOs = productPage
                .getContent()
                .stream()
                .map(productConverter::fromProductToProductDTO)
                .collect(Collectors.toList());

        PageResponseDTO<ProductResponseDTO> pageResponseDTO = new PageResponseDTO<>();
        pageResponseDTO.setContent(productDTOs);
        pageResponseDTO.setPage(productPage.getNumber());
        pageResponseDTO.setSize(productPage.getSize());
        pageResponseDTO.setTotalElements(productPage.getTotalElements());
        pageResponseDTO.setTotalPages(productPage.getTotalPages());

        CommonResponseDTO commonResponseDTO = new CommonResponseDTO<>();
        commonResponseDTO.setData(pageResponseDTO);
        commonResponseDTO.setSuccess(true);
        commonResponseDTO.setMessage("Products retrieved successfully.");

        return commonResponseDTO;
    }


    @Override
    public Long countProducts() {
        return productRepository.count();
    }

    @Override
    public ProductCustomerResponseDTO getProductDetailById(Integer id) {

        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        ProductCustomerResponseDTO productDTO = productConverter.fromProductToProductDetailDTO(product);
        return productDTO;
    }

    @Override
    public CommonResponseDTO<ProductResponseDTO> filterProductsByPrice(Integer minPrice, Integer maxPrice, Pageable pageable) {

            Page<Product> productsPage;
            if (minPrice != null && maxPrice != null) {
                productsPage = productRepository.findByPriceBetween(minPrice, maxPrice, pageable);
            } else if (minPrice != null) {
                productsPage = productRepository.findByPriceGreaterThanEqual(minPrice, pageable);
            } else if (maxPrice != null) {
                productsPage = productRepository.findByPriceLessThanEqual(maxPrice, pageable);
            } else {
                productsPage = productRepository.findAll(pageable);
            }


            List<ProductResponseDTO> productDTOs = productsPage.getContent().stream()
                    .map(productConverter::fromProductToProductDTO)
                    .collect(Collectors.toList());


            PageResponseDTO<ProductResponseDTO> responseDTO = new PageResponseDTO<>();
            responseDTO.setContent(productDTOs);
            responseDTO.setPage(productsPage.getNumber());
            responseDTO.setSize(productsPage.getSize());
            responseDTO.setTotalPages(productsPage.getTotalPages());
            responseDTO.setTotalElements(productsPage.getTotalElements());

        CommonResponseDTO<ProductResponseDTO> commonResponseDTO = new CommonResponseDTO<>();
        commonResponseDTO.setData(responseDTO);
        commonResponseDTO.setSuccess(true);
        commonResponseDTO.setMessage("Filtered products retrieved successfully.");

            return commonResponseDTO;

    }



}

