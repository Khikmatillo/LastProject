package com.example.project.controller;

import com.example.project.entity.Customer;
import com.example.project.repository.CustomerRepository;
import com.example.project.repository.UserRepository;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<Customer> getCustomersList(){
        return customerRepository.findAll();
    }

    @PostMapping
    public Customer registerNewCustomer(@RequestBody ObjectNode json){
        String name = json.get("name").textValue();
        String country = json.get("country").textValue();
        String address = json.get("address").textValue();
        String phone = json.get("phone").textValue();
        int user_id = json.get("user_id").asInt();

//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        Customer customer =

        return customerRepository.save(new Customer(name, country, address, phone, userRepository.findById(user_id)));
    }

//    @GetMapping("{id}")
//    public Optional<Customer> getCustomerDetailsById(@PathVariable String id){
//        int cid = Integer.parseInt(id);
//        return customerRepository.findById(cid);
//    }




//    @GetMapping("{name}")
//    public Customer getCustomerDetailsByName(@PathVariable String name){
////        int cid = Integer.parseInt(id);
//        return customerRepository.findByEmail(name);
//    }
}
