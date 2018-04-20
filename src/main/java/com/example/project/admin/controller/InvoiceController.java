package com.example.project.admin.controller;

import com.example.project.admin.common.CommonSearchById;
import com.example.project.admin.common.CommonsService;
import com.example.project.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/invoices")
public class InvoiceController {

    @Autowired
    InvoiceRepository invoiceRepository;

    @GetMapping
    public String listInvoices(Model model){
        model.addAttribute("invoices", invoiceRepository.findAll());
        return "adminIndex";
    }

    @GetMapping("/id")
    public String findInvoiceById(@RequestParam(value = "invid") String invid, Model model){
        CommonsService serachbyId = new CommonSearchById();
        return serachbyId.searchById(invid, "invoices", invoiceRepository, model);
    }
}
