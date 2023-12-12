package com.example.case_study_m4.entity;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name="cart_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @JoinColumn(name="quantity")
    private Integer quantity;

    @JoinColumn(name="price")
    private Integer price;

    @JoinColumn(name="sub_total")
    private Integer subtotalPrice;

}
