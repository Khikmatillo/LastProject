package com.example.project.admin.controller;

import com.example.project.admin.common.CommonSearchById;
import com.example.project.admin.common.CommonsService;
import com.example.project.entity.Customer;
import com.example.project.repository.CustomerRepository;
import com.example.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("admin/customers")
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserRepository userRepository;

    private String commonReturn(Model model){
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("customer", new Customer());
        return "adminIndex";
    }

    @GetMapping
    public String listCustomers(Model model){
        return commonReturn(model);
    }

    @PostMapping
    public String createNewCustomer(@Valid Customer customer, Model model){
        customerRepository.save(customer);
        return commonReturn(model);
    }

    @GetMapping("/id")
    public String findInvoiceById(@RequestParam(value = "custid") String custid, Model model){
        CommonsService serachbyId = new CommonSearchById();
        return serachbyId.searchById(custid, "customers", customerRepository, model);
    }
}
