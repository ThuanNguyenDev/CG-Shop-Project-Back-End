package com.example.case_study_m4.repository;

import com.example.case_study_m4.entity.Order;
import com.example.case_study_m4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
