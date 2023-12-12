package com.example.case_study_m4.dto.request.order;
import lombok.Data;
import java.sql.Date;
import java.util.List;

@Data
public class OrderRequestDTO {
    private Integer userId;
//    private Date orderDate;
    private List<OrderItemRequestDTO> orderItems;
}
