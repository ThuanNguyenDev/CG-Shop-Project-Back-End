package com.example.case_study_m4.entity;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private Integer price;

    @Column(name = "image_url")
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
    private List<CartItem> cartItems;





}
