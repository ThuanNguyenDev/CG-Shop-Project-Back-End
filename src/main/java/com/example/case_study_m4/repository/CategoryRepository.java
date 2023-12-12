package com.example.case_study_m4.repository;

import com.example.case_study_m4.entity.Category;
import com.example.case_study_m4.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer>, PagingAndSortingRepository<Category, Integer> {

    Page<Category> findByNameLike (String s,Pageable pageable);

}
