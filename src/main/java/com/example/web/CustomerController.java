package com.example.web;

import com.example.domain.Customer;
import com.example.domain.SearchName;
import com.example.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Map.of;

@Controller
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @ModelAttribute
    CustomerForm setUpForm() {
        return new CustomerForm();
    }

    @ModelAttribute
    SearchNameForm setUpSearchForm() {
        return new SearchNameForm();
    }

    @GetMapping
    String list(Model model) {
        List<Customer> customers = customerService.findAll();
//        List<Customer> newCustomers = null;
//        int cnt = 0;
//
//        for (Customer customer : customers) {
//            newCustomers.add(new Customer(
//                    ++cnt,
//                    customer.getFirstName(),
//                    customer.getLastName()
//            ));
//        }
//
//        customers.forEach(x -> {
//                System.out.println(x);
//        });
//
//        System.out.println("\n\n\n");
        model.addAttribute("customers", customers);
        return "customers/list";
    }

    @PostMapping(path = "create")   // error가 난다.
    String create(@Validated CustomerForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return list(model);
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(form, customer);
        customerService.create(customer);
        return "redirect:/customers";
    }

    @GetMapping(path = "edit", params = "form")
    String editForm(@RequestParam Integer id, CustomerForm form) {
        Customer customer = customerService.findOne(id);
        BeanUtils.copyProperties(customer, form);
        return "customers/edit";
    }

    @PostMapping(path = "edit")
    String edit(@RequestParam Integer id, @Validated CustomerForm form, BindingResult result) {
        if (result.hasErrors()) {
            return editForm(id, form);
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(form, customer);
        customer.setId(id);
        customerService.update(customer);
        return "redirect:/customers";  // return "redirect:/customers";  끝에 s.
    }

    @GetMapping(path = "edit", params = "goToTop")
    String goToTop() {
        return "redirect:/customers";
    }

    @PostMapping(path = "delete")
    String delete(@RequestParam Integer id) {
        customerService.delete(id);     // customerService.delete(id);
        return "redirect:/customers";
    }

    // 사용자 검색
    @PostMapping(path = "search")
    String search(@Validated SearchNameForm form, BindingResult result, Model model) {
        SearchName search = new SearchName();
        BeanUtils.copyProperties(form, search);
        // For testing.. customerService.searchName(search);

        List<Customer> customers = customerService.searchName(search);
        model.addAttribute("customers", customers);

        return "customers/list";
    }

    // 페이징 처리를 위해 PageRequest 써 보기.
//    @GetMapping(path = "paging/{page}/{size}/{sort}")
//    Page<Customer> findAll(final PageRequest pageable) {
//        return customerService.findAll(PageRequest of()).map(Customer::new);   // PageRequest(page -1, 10, Sort.Direction.DESC, "id");
//    }
//
//    @GetMapping(path = "paging/{page}/{size}/{sort}")
//    PageRequest goToPage(int page) {
//        PageRequest pageRequest = new PageRequest(page -1, 10, "id", Sort.Direction.DESC);
//        return customerService.goToPage(pageRequest);   // PageRequest(no -1, 10, Sort.Direction.DESC, "id");
//    }

    @GetMapping(path = "pages")
    public String customerView(@PageableDefault Pageable pageable) {
        return "customers/pages";
    }
}
