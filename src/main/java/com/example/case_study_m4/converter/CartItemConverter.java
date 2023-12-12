package com.example.case_study_m4.converter;

import com.example.case_study_m4.dto.request.CartItemRequestDTO;
import com.example.case_study_m4.dto.response.CartItemCreateResponseDTO;
import com.example.case_study_m4.dto.response.CartItemResponseDTO;
import com.example.case_study_m4.entity.CartItem;
import com.example.case_study_m4.entity.Product;
import com.example.case_study_m4.entity.ShoppingCart;
import com.example.case_study_m4.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CartItemConverter {
//    @Autowired
//    private ProductRepository productRepository;

    public CartItem convertToEntity(CartItemRequestDTO cartItemRequest) {
        CartItem cartItem = new CartItem();
//        ShoppingCart shoppingCart = new ShoppingCart();
//        shoppingCart.setId(cartItemRequest.getShoppingCartId());

        Product product = new Product();
        product.setId(cartItemRequest.getProductId());

//        cartItem.setShoppingCart(shoppingCart);
//        cartItem.setProduct(product);
        cartItem.setQuantity(cartItemRequest.getQuantity());

//        Product foundProduct = productRepository.findById(cartItemRequest.getProductId())
//                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
//        cartItem.setPrice(foundProduct.getPrice());
//        cartItem.setSubtotalPrice(foundProduct.getPrice()* cartItemRequest.getQuantity());
        return cartItem;

    }

    public List<CartItem> convertToEntityList(List<CartItemRequestDTO> cartItemRequestDTOList) {
        List<CartItem> cartItems = new ArrayList<>();
        for ( CartItemRequestDTO cartItemRequestDTO : cartItemRequestDTOList) {
            cartItems.add(convertToEntity(cartItemRequestDTO));
        }
        return cartItems;
    }

    public CartItemResponseDTO convertToDTO(CartItem savedCartItem) {
        CartItemResponseDTO cartItemResponseDTO = new CartItemResponseDTO();
        cartItemResponseDTO.setId(savedCartItem.getId());
        cartItemResponseDTO.setShoppingCartId(savedCartItem.getShoppingCart().getId());
        cartItemResponseDTO.setProductId(savedCartItem.getProduct().getId());
        cartItemResponseDTO.setQuantity(savedCartItem.getQuantity());

//        Product foundProduct = productRepository.findById(savedCartItem.getProduct().getId())
//                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        cartItemResponseDTO.setProductName(savedCartItem.getProduct().getName());
        cartItemResponseDTO.setProductPrice(savedCartItem.getProduct().getPrice());

        cartItemResponseDTO.setSubtotalPrice(savedCartItem.getQuantity()*savedCartItem.getPrice());
        return  cartItemResponseDTO;
    }

    public List<CartItemResponseDTO> convertToDTOs(List<CartItem> cartItems) {
        List<CartItemResponseDTO> cartItemResponseDTOS = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            cartItemResponseDTOS.add(convertToDTO(cartItem));
        }
        return cartItemResponseDTOS;
    }


    public CartItemCreateResponseDTO convertToCreateDTO(CartItem createCartItem) {
        CartItemCreateResponseDTO cartItemResponseDTO = new CartItemCreateResponseDTO();
//        cartItemResponseDTO.setId(createCartItem.getId());
//        cartItemResponseDTO.setShoppingCartId(createCartItem.getShoppingCart().getId());
//        cartItemResponseDTO.setProductId(createCartItem.getProduct().getId());
        cartItemResponseDTO.setQuantity(createCartItem.getQuantity());

//        Product foundProduct = productRepository.findById(createCartItem.getProduct().getId()).orElseThrow(() -> new EntityNotFoundException("Product not found"));

        cartItemResponseDTO.setProductName(createCartItem.getProduct().getName());
        cartItemResponseDTO.setProductPrice(createCartItem.getProduct().getPrice());

        cartItemResponseDTO.setSubtotalPrice(createCartItem.getQuantity()*createCartItem.getPrice());
        return  cartItemResponseDTO;
    }

}
