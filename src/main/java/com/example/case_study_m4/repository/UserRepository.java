package com.example.case_study_m4.repository;

import com.example.case_study_m4.entity.Product;
import com.example.case_study_m4.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer>, CrudRepository<User, Integer> {
    List<User> findByUsernameLikeIgnoreCase(String searchText);

    Page<User> findAll( Pageable pageable);
    User findByUsername(String username);

    List<String> findRolesByUsername(String username);


}
