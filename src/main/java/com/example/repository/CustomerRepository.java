package com.example.repository;

import com.example.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
//    @Query("ALTER TABLE Customer AUTO_INCREMENT=150")    // 추가.
//    List<Customer> findAllOrderByName();              // 추가.

//    @Modifying
//    @Query(value = "ALTER TABLE customers AUTO_INCREMENT=0;", nativeQuery = true)
//    void addDeletedColumn();    // 안 먹힘.

//    @Modifying
//    @Query(value = "ALTER TABLE customers AUTO_INCREMENT=200;", nativeQuery = true)
//    void addDeletedColumn();

    @Query("SELECT x FROM Customer x ORDER BY x.firstName, x.lastName")    // 원래 "SELECT x FROM Customer x ORDER BY x.firstName, x.lastName"
    List<Customer> findAll();   // 원래 findAllOrderByName();

//    @Query("SELECT x FROM Customer x ORDER BY x.firstName, x.lastName")    // 원래 "SELECT x FROM Customer x ORDER BY x.firstName, x.lastName"
//    Page<Customer> findAllOrderByName(Pageable pageable);
    //Pageable pageable = PageRequest.of(0, 10);    // 추가.

    @Query("SELECT x FROM Customer x WHERE x.firstName = :firstName ORDER BY x.id")
    List<Customer> findUser(@Param("firstName") String firstName);   // 검색 기능

//    @Modifying
//    @Query("ALTER TABLE customers AUTO_INCREMENT=200")
//    Customer create(Customer customer);
    //void delete(Integer id);   // CustomerService 에러 때문에 생성.
    //Customer findOne(Integer id);   // CustomerService 에러 때문에 생성.

    //<S extends Customer> Optional<S> findOne(Integer id);
}