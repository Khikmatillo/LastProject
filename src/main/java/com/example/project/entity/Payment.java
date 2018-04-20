package com.example.project.entity;//package com.marokand.onlineshopping.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
//@Table(name = "Payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "time")
    private Date date;

    @Column(name = "amount")
    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inv_id")
    @JsonIgnore
    private Invoice invoice;

    public Payment() {
    }

    public Payment(Date date, Double amount, Invoice invoice) {
        this.date = date;
        this.amount = amount;
        this.invoice = invoice;
    }

//    public Payment(int id, Date date, Double amount, Invoice invoice) {
//        this.id = id;
//        this.date = date;
//        this.amount = amount;
//        this.invoice = invoice;
//    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", invoice=" + invoice +
                '}';
    }
}
