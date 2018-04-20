package com.example.project.controller;

import com.example.project.entity.Invoice;
import com.example.project.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoicesController {

    @Autowired
    InvoiceRepository invoiceRepository;

    @GetMapping
    public List<Invoice> getInvoicesList(){
        return invoiceRepository.findAll();
    }

//    @PostMapping
//    public Invoice addNewInvoive(){
//
//    }

    @GetMapping("{id}")
    public Invoice getInvoiceDetailsById(@PathVariable String id){
        int iid = Integer.parseInt(id);
        return invoiceRepository.findById(iid);
    }
}
