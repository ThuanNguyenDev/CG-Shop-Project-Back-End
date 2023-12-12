package com.example.case_study_m4.controller.customer;

import com.example.case_study_m4.dto.response.category.CategoryResponseDTO;
import com.example.case_study_m4.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user/category")
@CrossOrigin(origins = "*")
public class CategoryRestController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/show")
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        List<CategoryResponseDTO> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }


    @GetMapping("/productCount/{categoryId}")
    public ResponseEntity<Integer> getProductCountForCategory(@PathVariable Integer categoryId) {
        Integer productCount = categoryService.getProductCountForCategory(categoryId);

        if (productCount != null) {
            return new ResponseEntity<>(productCount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
