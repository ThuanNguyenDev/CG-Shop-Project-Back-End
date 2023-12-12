package com.example.case_study_m4.dto.response.order;

import lombok.Data;

import java.sql.Date;

@Data
public class OrderCreateResponseDTO {
    private Integer id;
    private Integer userId;
    private Date orderDate;
}
