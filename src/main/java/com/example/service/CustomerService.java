package com.example.service;

import com.example.domain.Customer;
import com.example.domain.SearchName;
import com.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@Transactional
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    // 혹시 몰라.
    PageRequest pageRequest;

    public List<Customer> findAll() {
        return customerRepository.findAll();   // 원래 findAllOrderByName();
    }

//    public Page<Customer> findAll(Pageable pageable) {
//        return customerRepository.findAllOrderByName(pageable);   // (pageable);
//    }

    public Customer findOne(Integer id) {    // findOne이라는 함수 CustomerController에서 사용. (Integer id)
        return customerRepository.getOne(id);   // 고침. 저번에도 에러 떴던 곳.   return customerRepository.findOne(id);
    }

    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

    public void delete(Integer id) {   // public void delete(Integer id)
        customerRepository.deleteById(id);   // 고침.  customerRepository.delete(id);
    }

    public List<Customer> searchName(SearchName search) {
        return customerRepository.findUser(search.getSearch());
    }

    // 페이징 위해서.---------------------
//    private int page;
//    private int size;
//    private Sort.Direction direction;
//
//    public org.springframework.data.domain.PageRequest of() {
//        return org.springframework.data.domain.PageRequest.of(page -1, size, direction, "id");
//    }
    //--------------------------------

    //--------------------------------
//    public Page<Customer> getCustomerList(Pageable pageable) {
//        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() -1);
//        pageable = PageRequest.of(page, 10);
//        return customerRepository.findAll(pageable);
//    }
    //-------------------------------------------------------


}