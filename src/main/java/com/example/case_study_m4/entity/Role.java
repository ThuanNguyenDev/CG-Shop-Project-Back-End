package com.example.case_study_m4.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false, unique=true)
    private String name;

//    @ManyToMany(mappedBy="roles",fetch = FetchType.LAZY)
//    private List<User> users;
}
