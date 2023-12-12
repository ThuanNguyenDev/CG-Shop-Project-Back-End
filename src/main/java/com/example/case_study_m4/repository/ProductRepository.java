package com.example.case_study_m4.repository;


import com.example.case_study_m4.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer>, CrudRepository<Product, Integer>  {

    List<Product> findByCategoryId(Integer categoryId);
    Page<Product> findByNameLike(String s,Pageable pageable);
    Integer countByCategoryId(Integer categoryId);

    Page<Product> findByPriceBetween(Integer minPrice, Integer maxPrice, Pageable pageable);

    Page<Product> findByPriceGreaterThanEqual(Integer minPrice, Pageable pageable);

    Page<Product> findByPriceLessThanEqual(Integer maxPrice, Pageable pageable);
}
