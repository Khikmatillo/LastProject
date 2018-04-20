package com.example.project.controller;

import com.example.project.entity.*;
import com.example.project.repository.InvoiceRepository;
import com.example.project.repository.PaymentRepository;
import com.example.project.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentsController {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<Payment> getPaymentsList(@AuthenticationPrincipal UserDetails currentUser){
        String email = currentUser.getUsername();
        User user = userRepository.findByEmail(email);
        List<Customer> customers = user.getCustomers();
        List<Payment> payments = new ArrayList<>();

        for(Customer customer : customers){
            for(Orders orders : customer.getOrders()){
                for(Invoice invoice : orders.getInvoices()){
                    payments.addAll(invoice.getPayments());
                }
            }
        }

        return payments;
    }

    @GetMapping("/{id}")
    public Payment getPaymentDetailsById(@PathVariable int id, @AuthenticationPrincipal UserDetails currentUser){
        String email = currentUser.getUsername();
        User user = userRepository.findByEmail(email);
        List<Customer> customers = user.getCustomers();

        for(Customer customer : customers){
            for(Orders orders : customer.getOrders()){
                for(Invoice invoice : orders.getInvoices()){
                    for(Payment payment : invoice.getPayments()){
                        if(payment.getId() == id){
                            return payment;
                        }
                    }
                }
            }
        }

        return null;
    }

    @PostMapping
    public ObjectNode makePayment(@RequestBody ObjectNode json, @AuthenticationPrincipal UserDetails currentUser){
        int inv_id = Integer.parseInt(json.get("inv_id").toString());
        double amount = Double.parseDouble(json.get("amount").toString());

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();

        String email = currentUser.getUsername();
        User user = userRepository.findByEmail(email);
        List<Customer> customers = user.getCustomers();
        for(Customer customer : customers){
            for(Orders orders : customer.getOrders()){
                for(Invoice invoice : orders.getInvoices()){
                    if(invoice.getId() == inv_id){
                        double paidamount = amount;
                        for(Payment temp : invoice.getPayments()){
                            paidamount += temp.getAmount();
                        }

                        Payment payment = paymentRepository.save(new Payment(new Date(), amount, invoice));
                        node.put("status", "ok");
                        node.put("id", payment.getId());
                        node.put("paid", payment.getAmount());
                        node.put("remaining", invoice.getAmount() - paidamount);
                        node.put("date", payment.getDate().toString());
                        return node;
                    }
                }
            }
        }
        return null;
    }
}
