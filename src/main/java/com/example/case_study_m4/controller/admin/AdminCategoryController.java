package com.example.case_study_m4.controller.admin;

import com.example.case_study_m4.dto.request.category.CategoryRequestDTO;
import com.example.case_study_m4.dto.response.category.CategoryCommonResponseDTO;
import com.example.case_study_m4.dto.response.category.CategoryPageResponseDTO;
import com.example.case_study_m4.dto.response.category.CategoryResponseDTO;
import com.example.case_study_m4.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/category")
@CrossOrigin(origins = "*")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/show")
    public ResponseEntity<CategoryCommonResponseDTO<CategoryResponseDTO>> getCategoryList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        CategoryCommonResponseDTO<CategoryResponseDTO> categoryPage = categoryService.getAllCategory(pageable);



        return new ResponseEntity<>(categoryPage, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<CategoryResponseDTO> addNewCategory(@RequestBody CategoryRequestDTO categoryDTO) {
        CategoryResponseDTO newCategoryDTO = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(newCategoryDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable Integer id, @RequestBody CategoryRequestDTO categoryRequestDTO) {
        CategoryResponseDTO updatedCategoryDTO = categoryService.updateCategory(id,categoryRequestDTO);
        if (updatedCategoryDTO != null) {
            return new ResponseEntity<>(updatedCategoryDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        boolean deleted = categoryService.deleteCategory(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getCate/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Integer id) {
        CategoryResponseDTO categoryResponseDTO = categoryService.getCategoryById(id);
        if (categoryResponseDTO != null) {
            return new ResponseEntity<>(categoryResponseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<CategoryCommonResponseDTO<CategoryResponseDTO>> searchCategory(
            @RequestParam(name = "name") String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        CategoryCommonResponseDTO<CategoryResponseDTO> searchResults = categoryService.searchCategory(name,pageable);
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }

}
