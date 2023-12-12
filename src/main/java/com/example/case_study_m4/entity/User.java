package com.example.case_study_m4.entity;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

    public class User {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @NotBlank(message = "Username is required")
        @Size(min = 5, max = 50, message = "Username must be between 5 and 50 characters")
        @Column(name = "username", unique = true, nullable = false)
        private String username;

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters")
        @Column(name = "password", nullable = false)
        private String password;

        @Email(message = "Invalid email address")
        @NotBlank(message = "Email is required")
        @Column(name = "email", unique = true, nullable = false)
        private String email;

        @Pattern(regexp = "^[0-9]*$", message = "Phone number should only contain digits")
        @Size(min = 10, max = 15, message = "Phone number length should be between 10 and 15 digits")
        @Column(name="phone_number")
        private String phoneNumber;

        @NotBlank(message = "Address is required")
        @Size(max = 255, message = "Address should not exceed 255 characters")
        @Column(name="address")
        private String address;

        @OneToOne(mappedBy = "user")
        private ShoppingCart shoppingCart;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
         private List<Order> orders ;

//
//        @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
//        @JoinTable(
//            name="users_roles",
//            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="id")},
//            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="id")})
//    private List<Role> roles = new ArrayList<>();

         @Column(name = "role")
         private String role;

}
