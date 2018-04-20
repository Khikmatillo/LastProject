package com.example.project.controller;

import com.example.project.entity.*;
import com.example.project.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DetailRepository detailRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<Orders> getOrdersList(@AuthenticationPrincipal UserDetails currentUser){
        String email = currentUser.getUsername();
        User user = userRepository.findByEmail(email);

        List<Orders> orders = new ArrayList<>();
        List<Customer> customers = user.getCustomers();
        for(Customer cust : customers){
            orders.addAll(cust.getOrders());
        }

        return orders;
    }

    @GetMapping("/{id}")
    public Orders getOrderDetailsById(@PathVariable int id, @AuthenticationPrincipal UserDetails currentUser){
        String email = currentUser.getUsername();
        User user = userRepository.findByEmail(email);
        List<Customer> customers = user.getCustomers();
        for(Customer customer : customers){
            for(Orders tempOrder : customer.getOrders()){
                if(tempOrder.getId() == id){
                    return tempOrder;
                }
            }
        }

        return null;
    }

    @PostMapping
    public ObjectNode makeOrder(@RequestBody ObjectNode json){
        System.out.println("In Controller function");
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();

        int cust_id = json.get("cust_id").asInt();
        int pr_id = json.get("pr_id").asInt();
        int qty = json.get("qty").asInt();
        Date date = new Date();
        LocalDateTime localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDate = localDate.plusDays(2);
        Date dueDate = Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());


        Customer customer = customerRepository.findById(cust_id);
        Orders orders = orderRepository.save(new Orders(date, customer));
        Product product = productRepository.findById(pr_id);
        Detail detail = detailRepository.save(new Detail(orders, product, qty));
        Invoice invoice = invoiceRepository.save(new Invoice(orders, product.getPrice() * detail.getQuantity(), date, dueDate));

        node.put("status", "ok");
        node.put("inv_id", invoice.getId());

        return node;
    }
}
