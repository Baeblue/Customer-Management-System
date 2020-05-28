package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // (strategy = GenerationType.IDENTITY) 추가해봄.
    // /create 안 되고
    // Sequence “HIBERNATE_SEQUENCE” not found; SQL statement 문제라서.
    private Integer id;
    private String firstName;
    private String lastName;
}
