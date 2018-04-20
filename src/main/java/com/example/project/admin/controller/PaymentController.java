package com.example.project.admin.controller;


import com.example.project.admin.common.CommonSearchById;
import com.example.project.admin.common.CommonsService;
import com.example.project.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/payments")
public class PaymentController {

    @Autowired
    PaymentRepository paymentRepository;

    @GetMapping
    public String listPayments(Model model){
        model.addAttribute("payments", paymentRepository.findAll());
        return "adminIndex";
    }

    @GetMapping("/id")
    public String findInvoiceById(@RequestParam(value = "payid") String payid, Model model){
        CommonsService serachbyId = new CommonSearchById();
        return serachbyId.searchById(payid, "payments", paymentRepository, model);
    }
}
