package com.example.project.entity;//package com.marokand.onlineshopping.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cust_id", nullable = false)
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "dOrders", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnore
    private List<Detail> details = new ArrayList<>();

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnore
    private List<Invoice> invoices = new ArrayList<>();

    public Orders() {

    }

    public Orders(Date date, Customer customer) {
        this.date = date;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", date=" + date +
                ", customer=" + customer +
//                ", details=" + details +
                '}';
    }
}
