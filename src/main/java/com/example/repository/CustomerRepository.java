package com.example.repository;

import com.example.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("SELECT x FROM Customer x ORDER BY x.firstName, x.lastName")
    List<Customer> findAll();

    // 검색 기능
    @Query("SELECT x FROM Customer x WHERE x.firstName = :firstName ORDER BY x.id")
    List<Customer> findUser(@Param("firstName") String firstName);
}