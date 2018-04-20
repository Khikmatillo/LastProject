package com.example.project.entity;//package com.marokand.onlineshopping.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ord_id")
    @JsonIgnore
    private Orders orders;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "issued")
    private Date issuedDate;

    @Column(name = "due")
    private Date dueDate;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
//    @JsonIgnore
    private List<Payment> payments = new ArrayList<>();

    public Invoice() {
    }

    public Invoice(Orders orders, Double amount, Date issuedDate, Date dueDate) {
        this.orders = orders;
        this.amount = amount;
        this.issuedDate = issuedDate;
        this.dueDate = dueDate;
    }
//
//    public Invoice(int id, Orders orders, Double amount, Date issuedDate, Date dueDate) {
//        this.id = id;
//        this.orders = orders;
//        this.amount = amount;
//        this.issuedDate = issuedDate;
//        this.dueDate = dueDate;
//    }

    public int getId() {
        return id;
    }

    public Orders getOrders() {
        return orders;
    }

    public Double getAmount() {
        return amount;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", orders=" + orders +
                ", amount=" + amount +
                ", issuedDate=" + issuedDate +
                ", dueDate=" + dueDate +
                ", payments=" + payments +
                '}';
    }
}

