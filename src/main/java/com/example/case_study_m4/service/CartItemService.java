package com.example.case_study_m4.service;

import com.example.case_study_m4.dto.request.CartItemRequestDTO;
import com.example.case_study_m4.dto.request.UpdateCartItemQuantityRequestDTO;
import com.example.case_study_m4.dto.response.CartItemCreateResponseDTO;
import com.example.case_study_m4.dto.response.CartItemResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartItemService {
    CartItemCreateResponseDTO createCartItem(Integer userId,
                                             CartItemRequestDTO cartItemRequest);
    CartItemResponseDTO getCartItemById(Integer cartItemId ,
                                        Integer userId);
    CartItemResponseDTO updateCartItem(Integer userId,
                                       Integer cartItemId,
                                       CartItemRequestDTO updatedCartItem);


    List<CartItemResponseDTO> getCartItemsListByUserID(Integer userId);


    CartItemCreateResponseDTO addToCart(CartItemRequestDTO cartItemRequest,
                                        String userName);

    List<CartItemResponseDTO> getCartItemsByUserName(String username);

    CartItemResponseDTO updateCartItem(int cartItemId,
                                       UpdateCartItemQuantityRequestDTO cartItemRequest,
                                       String username) throws Exception;

    void deleteCartItem(int cartItemId, String username) throws Exception;
}
