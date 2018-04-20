package com.example.project.controller;

import com.example.project.entity.Detail;
import com.example.project.repository.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/details")
public class DetailsController {

    @Autowired
    DetailRepository detailRepository;

    @GetMapping
    public List<Detail> getDetailsList(){
        return detailRepository.findAll();
    }
}
