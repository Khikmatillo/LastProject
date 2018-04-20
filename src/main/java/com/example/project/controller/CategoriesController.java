package com.example.project.controller;

import com.example.project.entity.Category;
import com.example.project.repository.CategoryRepository;
import com.example.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public List<Category> getAllCategoryList(){
        return categoryRepository.findAll();
    }

    @GetMapping("/{pr_id}")
    public Category getCategoryByProductId(@PathVariable int pr_id){
        return productRepository.findById(pr_id).getCategory();
    }
}
