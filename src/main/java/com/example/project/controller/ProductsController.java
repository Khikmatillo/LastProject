package com.example.project.controller;

import com.example.project.entity.Product;
import com.example.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public List<Product> getProductsList(){
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductDetailsById(@PathVariable String id){
        int pid = Integer.parseInt(id);
        return productRepository.findById(pid);
    }
}
