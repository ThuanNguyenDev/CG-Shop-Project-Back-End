package com.example.case_study_m4.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="shopping_carts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "shoppingCart",fetch = FetchType.LAZY)
    private List<CartItem> cartItems;


}
