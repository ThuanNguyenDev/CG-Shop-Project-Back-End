package com.example.case_study_m4.controller.customer;

import com.example.case_study_m4.config.security.JwtTokenUtil;
import com.example.case_study_m4.dto.request.CartItemRequestDTO;
import com.example.case_study_m4.dto.request.UpdateCartItemQuantityRequestDTO;
import com.example.case_study_m4.dto.response.CartItemCreateResponseDTO;
import com.example.case_study_m4.dto.response.CartItemResponseDTO;
import com.example.case_study_m4.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/user/cart")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CartItemService cartItemService;


    @PostMapping("/cart-item")
    public ResponseEntity<CartItemCreateResponseDTO> addToCart(
            @RequestBody CartItemRequestDTO cartItemRequest,
            HttpServletRequest request) {
//        UserDetails userDetails = request.getAttribute("userDetails")
        String token = request.getHeader("Authorization");
        String username = jwtTokenUtil.getUsernameFromJWT(token);
        CartItemCreateResponseDTO createdCartItem = cartItemService.addToCart(cartItemRequest, username);
        return new ResponseEntity<>(createdCartItem, HttpStatus.CREATED);
    }

    @GetMapping("/cart-items")
    public ResponseEntity<List<CartItemResponseDTO>> getCartItemsByCart(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        String username = jwtTokenUtil.getUsernameFromJWT(token);

        List<CartItemResponseDTO> cartItemResponseDTOS = cartItemService.getCartItemsByUserName(username);

        return new ResponseEntity<>(cartItemResponseDTOS, HttpStatus.OK);
    }

    @PutMapping("/cart-items/{cartItemId}")
    public ResponseEntity<CartItemResponseDTO> updateCartItem(
            @PathVariable("cartItemId")  int cartItemId,
            @RequestBody UpdateCartItemQuantityRequestDTO cartItemRequest,
            HttpServletRequest request) throws Exception {

        String token = request.getHeader("Authorization");

        String username = jwtTokenUtil.getUsernameFromJWT(token);

        CartItemResponseDTO updatedCartItem = cartItemService.updateCartItem(cartItemId, cartItemRequest, username);

        return new ResponseEntity<>(updatedCartItem, HttpStatus.OK);
    }



    @DeleteMapping("/cart-items/{cartItemId}")
    public ResponseEntity<Void> deleteCartItem(
            @PathVariable("cartItemId") int cartItemId,
            HttpServletRequest request) throws Exception {

        String token = request.getHeader("Authorization");

        String username = jwtTokenUtil.getUsernameFromJWT(token);

        cartItemService.deleteCartItem(cartItemId, username);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
