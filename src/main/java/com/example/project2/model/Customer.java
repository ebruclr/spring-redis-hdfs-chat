package com.example.project2.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customers")
// Lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64)
    private String name;

    @Column(length = 128)
    private String address;

    @Column(length = 20)
    private String phone;

}
