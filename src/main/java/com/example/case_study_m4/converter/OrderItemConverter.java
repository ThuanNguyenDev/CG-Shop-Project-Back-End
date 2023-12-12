package com.example.case_study_m4.converter;

import com.example.case_study_m4.dto.request.order.OrderItemRequestDTO;
import com.example.case_study_m4.dto.response.order.OrderItemResponseDTO;
import com.example.case_study_m4.entity.Order;
import com.example.case_study_m4.entity.OrderItem;
import com.example.case_study_m4.entity.Product;
import com.example.case_study_m4.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderItemConverter {


    public OrderItem convertToEntity(OrderItemRequestDTO orderItemRequestDTO) {
        OrderItem orderItem = new OrderItem();
        Order order = new Order();
        order.setId(orderItemRequestDTO.getOrderId());
        orderItem.setOrder(order);

        Product product = new Product();
        product.setId(orderItemRequestDTO.getProductId());
        orderItem.setProduct(product);

        orderItem.setQuantity(orderItemRequestDTO.getQuantity());
//        orderItem.setPrice(orderItemRequestDTO.getPrice());
        return orderItem;
    }



    public OrderItemResponseDTO convertToDTO(OrderItem orderItem) {
        OrderItemResponseDTO orderItemResponseDTO = new OrderItemResponseDTO();
//        orderItemResponseDTO.setId(orderItem.getId());
//        orderItemResponseDTO.setOrderId(orderItem.getOrder().getId());
//        orderItemResponseDTO.setProductId(orderItem.getProduct().getId());
        orderItemResponseDTO.setProductName(orderItem.getProduct().getName());
        orderItemResponseDTO.setQuantity(orderItem.getQuantity());

//        Product foundProduct = productRepository.findById(orderItem.getProduct().getId()).orElseThrow(() -> new EntityNotFoundException("OrderItem not found"));

        orderItemResponseDTO.setPrice(orderItem.getPrice());
        orderItemResponseDTO.setSubTotalPrice(orderItem.getQuantity()*orderItem.getPrice());
        return orderItemResponseDTO;
    }


    public List<OrderItemResponseDTO> convertToDTOs(List<OrderItem> orderItemList) {
        List<OrderItemResponseDTO> list = new ArrayList<>();
        for (OrderItem orderItem : orderItemList) {
            list.add(convertToDTO(orderItem));
        }
        return list;
    }

    public List<OrderItem> convertToEntityList(List<OrderItemRequestDTO> orderItems) {
        List<OrderItem> orderItemList = new ArrayList<>();
        for (OrderItemRequestDTO orderItemRequestDTO:orderItems) {
            orderItemList.add(convertToEntity(orderItemRequestDTO));
        }
        return orderItemList;
    }
}
