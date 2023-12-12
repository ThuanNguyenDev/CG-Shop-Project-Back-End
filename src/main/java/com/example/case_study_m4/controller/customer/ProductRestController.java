package com.example.case_study_m4.controller.customer;
import com.example.case_study_m4.dto.response.category.CategoryResponseDTO;
import com.example.case_study_m4.dto.response.product.CommonResponseDTO;
import com.example.case_study_m4.dto.response.product.PageResponseDTO;
import com.example.case_study_m4.dto.response.product.ProductCustomerResponseDTO;
import com.example.case_study_m4.dto.response.product.ProductResponseDTO;
import com.example.case_study_m4.service.CategoryService;
import com.example.case_study_m4.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user/product")
@CrossOrigin(origins = "*")
public class ProductRestController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;


    @GetMapping("/get-product/{id}")
    public ResponseEntity<ProductCustomerResponseDTO> getProductById(@PathVariable Integer id) {
        ProductCustomerResponseDTO product = productService.getProductDetailById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByCategoryId(@PathVariable Integer categoryId) {

        CategoryResponseDTO category = categoryService.getCategoryById(categoryId);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<ProductResponseDTO> products = productService.getProductsByCategoryId(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/show")
    public ResponseEntity<CommonResponseDTO<ProductResponseDTO>> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int size,
            @RequestParam(defaultValue = "name") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        CommonResponseDTO<ProductResponseDTO> productPage = productService.getAllProducts(pageable);

        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<CommonResponseDTO<ProductResponseDTO>> searchProducts(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "price") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        CommonResponseDTO<ProductResponseDTO> commonResponseDTO = productService.searchProducts(name,pageable);
        return new ResponseEntity<>(commonResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/show-product-by-price")
    public ResponseEntity<CommonResponseDTO<ProductResponseDTO>> filterProductsByPrice(
            @RequestParam(name = "minPrice", required = false) Integer minPrice,
            @RequestParam(name = "maxPrice", required = false) Integer maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(defaultValue = "price") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        CommonResponseDTO<ProductResponseDTO> filteredResults = productService.filterProductsByPrice(minPrice, maxPrice, pageable);



        return new ResponseEntity<>(filteredResults, HttpStatus.OK);
    }

    @GetMapping("/show-products")
    public ResponseEntity<CommonResponseDTO<ProductResponseDTO>> showProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(defaultValue = "price") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {

        Sort sort;

        if ("asc".equalsIgnoreCase(sortOrder)) {
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }

        Pageable pageable = PageRequest.of(page, size, sort);
        CommonResponseDTO<ProductResponseDTO> commonResponseDTO = productService.getAllProducts(pageable);
        return new ResponseEntity<>(commonResponseDTO, HttpStatus.OK);
    }




}
