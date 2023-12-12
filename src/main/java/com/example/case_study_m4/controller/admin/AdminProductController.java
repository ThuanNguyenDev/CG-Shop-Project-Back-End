package com.example.case_study_m4.controller.admin;
import com.example.case_study_m4.config.security.JwtTokenUtil;
import com.example.case_study_m4.dto.request.product.ProductRequestDTO;
import com.example.case_study_m4.dto.response.category.CategoryResponseDTO;
import com.example.case_study_m4.dto.response.product.CommonResponseDTO;
import com.example.case_study_m4.dto.response.product.PageResponseDTO;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/admin/product")
@CrossOrigin(origins = "*")
public class AdminProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;



    @GetMapping("/getProduct/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Integer id) {
        ProductResponseDTO product = productService.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productDTO) {
            ProductResponseDTO newProductDTO = productService.createProduct(productDTO);
            return new ResponseEntity<>(newProductDTO, HttpStatus.CREATED);
    }


    @PutMapping ("/update/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Integer id, @RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO updatedProductDTO = productService.updateProduct(id,productRequestDTO);
        if (updatedProductDTO != null) {
            return new ResponseEntity<>(updatedProductDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        boolean deleted = productService.deleteProduct(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    

    @GetMapping("/showAllProductByCateID/{categoryId}")
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
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        CommonResponseDTO<ProductResponseDTO> commonResponseDTO = productService.getAllProducts(pageable);
        return new ResponseEntity<>(commonResponseDTO, HttpStatus.OK);
    }


    @GetMapping("/count")
    public ResponseEntity<Long> countProducts() {
        try {
            Long totalProducts = productService.countProducts();
            return new ResponseEntity<>(totalProducts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<CommonResponseDTO<ProductResponseDTO>> searchProducts(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        CommonResponseDTO<ProductResponseDTO> commonResponseDTO = productService.searchProducts(name,pageable);

        return new ResponseEntity<>(commonResponseDTO, HttpStatus.OK);
    }
}
