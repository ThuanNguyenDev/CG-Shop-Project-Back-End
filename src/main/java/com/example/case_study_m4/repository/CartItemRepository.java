package com.example.case_study_m4.repository;

import com.example.case_study_m4.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    List<CartItem> findByShoppingCartId(Integer shoppingCartId);
}