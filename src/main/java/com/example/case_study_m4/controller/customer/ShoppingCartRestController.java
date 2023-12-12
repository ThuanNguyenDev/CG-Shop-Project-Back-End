package com.example.case_study_m4.controller.customer;


import com.example.case_study_m4.dto.response.CartItemResponseDTO;
import com.example.case_study_m4.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/shopping-carts")
public class ShoppingCartRestController {

    @Autowired
    private CartItemService cartItemService;



    @GetMapping("/{userId}/cart-items")
    public ResponseEntity<List<CartItemResponseDTO>> getCartItemsListByUserID(@PathVariable Integer userId) {
        List<CartItemResponseDTO> cartItemResponseDTOS = cartItemService.getCartItemsListByUserID(userId);
        return new ResponseEntity<>(cartItemResponseDTOS, HttpStatus.OK);
    }


}
