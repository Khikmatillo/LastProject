package com.example.project.admin.controller;

import com.example.project.admin.common.CommonSearchById;
import com.example.project.admin.common.CommonsService;
import com.example.project.entity.Product;
import com.example.project.repository.CategoryRepository;
import com.example.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("admin/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    private String commonReturn(Model model){
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("categs", categoryRepository.findAll());
        model.addAttribute("product", new Product());
        return "adminIndex";
    }

    @GetMapping
    public String listProducts(Model model){
        return commonReturn(model);
    }

    @PostMapping
    public String createNewProduct(@Valid Product product, Model model){
        productRepository.save(product);
        return commonReturn(model);
    }

    @GetMapping("/id")
    public String findInvoiceById(@RequestParam(value = "prid") String prid, Model model){
        CommonsService serachbyId = new CommonSearchById();
        return serachbyId.searchById(prid, "products", productRepository, model);
    }

    @PostMapping("/put")
    public String updateProduct(@RequestParam(value = "prid") int prid, @Valid Product pr, Model model){
        Product product = productRepository.findById(prid);
        product.setAllAttributes(pr.getName(), pr.getDescription(), pr.getPrice(), pr.getPhoto(), pr.getCategory());
        productRepository.save(product);
        return commonReturn(model);
    }

    @PostMapping("/delete")
    public String deleteProduct(@RequestParam(value = "prid") int prid, Model model){
        productRepository.delete(productRepository.findById(prid));
        return commonReturn(model);
    }
}
