package com.example.case_study_m4.service.impl;

import com.example.case_study_m4.converter.CartItemConverter;
import com.example.case_study_m4.dto.request.CartItemRequestDTO;
import com.example.case_study_m4.dto.request.UpdateCartItemQuantityRequestDTO;
import com.example.case_study_m4.dto.response.CartItemCreateResponseDTO;
import com.example.case_study_m4.dto.response.CartItemResponseDTO;
import com.example.case_study_m4.entity.CartItem;
import com.example.case_study_m4.entity.Product;
import com.example.case_study_m4.entity.ShoppingCart;
import com.example.case_study_m4.entity.User;
import com.example.case_study_m4.repository.CartItemRepository;
import com.example.case_study_m4.repository.ProductRepository;
import com.example.case_study_m4.repository.ShoppingCartRepository;
import com.example.case_study_m4.repository.UserRepository;
import com.example.case_study_m4.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CartItemConverter cartItemConverter;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ProductRepository productRepository;


    @Override
    public CartItemCreateResponseDTO createCartItem(Integer userID,CartItemRequestDTO cartItemRequest) {

        CartItem cartItem = cartItemConverter.convertToEntity(cartItemRequest);
        User foundUser = userRepository.findById(userID).orElseThrow(() -> new EntityNotFoundException("User not found"));
        ShoppingCart foundShoppingCart = shoppingCartRepository.findById(foundUser.getShoppingCart().getId()).orElseThrow(() -> new EntityNotFoundException("Shopping Cart not found"));

        Product foundProduct = productRepository.findById(cartItemRequest.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        cartItem.setPrice(foundProduct.getPrice());
        cartItem.setSubtotalPrice(foundProduct.getPrice()* cartItemRequest.getQuantity());
        cartItem.setShoppingCart(foundShoppingCart);
        CartItem savedCartItem = cartItemRepository.save(cartItem);
        return cartItemConverter.convertToCreateDTO(savedCartItem);
    }

    @Override
    public CartItemResponseDTO getCartItemById(Integer cartItemId, Integer userID) {
        User foundUser = userRepository.findById(userID).orElseThrow(() -> new EntityNotFoundException("User not found"));
        ShoppingCart shoppingCart = shoppingCartRepository.findById(foundUser.getShoppingCart().getId()).orElseThrow(() -> new EntityNotFoundException("Shopping Cart not found"));
        CartItem foundCartItem = null;
        for (CartItem cartItem : shoppingCart.getCartItems()) {
            if (cartItem.getId()==cartItemId) {
               foundCartItem = cartItem;
                return cartItemConverter.convertToDTO(foundCartItem);
            }
        }
        return null;
    }

    @Override
    public CartItemResponseDTO updateCartItem(Integer userId,Integer cartItemId, CartItemRequestDTO updatedCartItem) {
        User foundUser = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        ShoppingCart shoppingCart = shoppingCartRepository.findById(foundUser.getShoppingCart().getId()).orElseThrow(() -> new EntityNotFoundException("Shopping Cart not found"));

        for (CartItem cartItem : shoppingCart.getCartItems()) {
            if (cartItem.getId()==cartItemId) {
                cartItem = cartItemConverter.convertToEntity(updatedCartItem);
                cartItemRepository.save(cartItem);
                return cartItemConverter.convertToDTO(cartItem);
            }
        }
        return null;
    }



    @Override
    public List<CartItemResponseDTO> getCartItemsListByUserID(Integer userId) {
        User foundUser = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        ShoppingCart foundShoppingCart = shoppingCartRepository.findById(foundUser.getShoppingCart().getId()).orElseThrow(() -> new EntityNotFoundException("Shopping Cart not found"));
        List<CartItemResponseDTO> cartItemResponseDTOList = new ArrayList<>();
        for (CartItem cartItem : foundShoppingCart.getCartItems()) {
            cartItemResponseDTOList.add(cartItemConverter.convertToDTO(cartItem));
        }
        return cartItemResponseDTOList;
    }


    @Override
    public CartItemCreateResponseDTO addToCart(CartItemRequestDTO cartItemRequest, String userName) {
        User user = userRepository.findByUsername(userName);

        ShoppingCart foundShoppingCart = shoppingCartRepository.findById(user.getShoppingCart().getId())
                .orElseThrow(() -> new EntityNotFoundException("Shopping Cart not found"));

        CartItem cartItem = cartItemConverter.convertToEntity(cartItemRequest);

        Product foundProduct = productRepository.findById(cartItemRequest.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));


        cartItem.setShoppingCart(foundShoppingCart);

        cartItem.setProduct(foundProduct);
        cartItem.setPrice(foundProduct.getPrice());
        cartItem.setSubtotalPrice(foundProduct.getPrice()* cartItemRequest.getQuantity());
        cartItemRepository.save(cartItem);
        return cartItemConverter.convertToCreateDTO(cartItem);
    }

    @Override
    public List<CartItemResponseDTO> getCartItemsByUserName(String username) {
        User user = userRepository.findByUsername(username);

        ShoppingCart foundShoppingCart = shoppingCartRepository.findById(user.getShoppingCart().getId())
                .orElseThrow(() -> new EntityNotFoundException("Shopping Cart not found"));
        List<CartItemResponseDTO> cartItemResponseDTOList = new ArrayList<>();
        for (CartItem cartItem : foundShoppingCart.getCartItems()) {
            cartItemResponseDTOList.add(cartItemConverter.convertToDTO(cartItem));
        }
        return cartItemResponseDTOList;
    }

    @Override
    public CartItemResponseDTO updateCartItem(int cartItemId, UpdateCartItemQuantityRequestDTO cartItemRequest, String username) throws Exception {

        if ( cartItemRequest==null) {
            throw new Exception(" Request not found");
        }
        User user = userRepository.findByUsername(username);

        ShoppingCart foundShoppingCart = shoppingCartRepository.findById(user.getShoppingCart().getId())
                .orElseThrow(() -> new EntityNotFoundException("Shopping Cart not found"));

        List<CartItem> cartItemList = foundShoppingCart.getCartItems();
        CartItem foundCartItem = null;

        if (cartItemList.isEmpty()) {
            throw new Exception(" Cart Items not found");
        } else {
            for (CartItem cartItem : cartItemList) {
                if (cartItem.getId() == cartItemId) {
                    foundCartItem = cartItem;
                    break;
                }
            }


            int updateQuantity = cartItemRequest.getQuantity();

            if(updateQuantity < 0) {
                throw new Exception (" Quantity not valid");
            }

            assert foundCartItem != null;
            foundCartItem.setQuantity(updateQuantity);
            foundCartItem.setSubtotalPrice(updateQuantity*foundCartItem.getPrice());
            cartItemRepository.save(foundCartItem);
        }

        return cartItemConverter.convertToDTO(foundCartItem);

    }

    @Override
    public void deleteCartItem(int cartItemId, String username) throws Exception {

        User user = userRepository.findByUsername(username);

        ShoppingCart foundShoppingcart = shoppingCartRepository.findById(user.getShoppingCart().getId())
                .orElseThrow(() -> new EntityNotFoundException("Shopping Cart not found"));
        List<CartItem> cartItemList = foundShoppingcart.getCartItems();
        CartItem foundCartItem = null;

        if (cartItemList.isEmpty()) {
            throw new Exception(" Cart Items not found");
        } else {
            for (CartItem cartItem : cartItemList) {
                if (cartItem.getId()==cartItemId) {
                    foundCartItem = cartItem;
                    break;
                }
            }

            if (foundCartItem != null) {
                cartItemRepository.delete(foundCartItem);
            }

        }

    }
}
