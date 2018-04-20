package com.example.project.admin.controller;

import com.example.project.admin.common.CommonSearchById;
import com.example.project.admin.common.CommonsService;
import com.example.project.entity.Category;
import com.example.project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping
    public String listCategories(Model model){
        model.addAttribute("categories", categoryRepository.findAll());
        return "adminIndex";
    }

    @GetMapping("/id")
    public String findInvoiceById(@RequestParam(value = "catid") String catid, Model model){
        CommonsService serachbyId = new CommonSearchById();
        return serachbyId.searchById(catid, "categories", categoryRepository, model);
    }

    @PostMapping
    public String createNewCategory(@RequestParam(value = "catname") String catname, Model model){
        if(!catname.isEmpty())
            categoryRepository.save(new Category(catname));
        model.addAttribute("categories", categoryRepository.findAll());
        return "adminIndex";
    }

    @PostMapping("/put")
    public String updateCategory(@RequestParam(value = "catid") int catid, @RequestParam(value = "catname") String catname, Model model){
        Category category = categoryRepository.findById(catid);
        if(!catname.isEmpty()) {
            category.setName(catname);
            categoryRepository.save(category);
        }
        model.addAttribute("categories", categoryRepository.findAll());
        return "adminIndex";
    }

    @PostMapping("/delete")
    public String deleteCategory(@RequestParam(value = "catid") int catid, Model model){
        categoryRepository.delete(categoryRepository.findById(catid));
        model.addAttribute("categories", categoryRepository.findAll());
        return "adminIndex";
    }
}
