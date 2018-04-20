package com.example.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "Detail")
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ord_id", nullable = false)
    @JsonIgnore
    private Orders dOrders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pr_id", nullable = false)
    @JsonIgnore
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    public Detail() {
    }

    public Detail(Orders dOrders, Product product, int quantity) {
        this.dOrders = dOrders;
        this.product = product;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Orders getdOrders() {
        return dOrders;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setdOrders(Orders dOrders) {
        this.dOrders = dOrders;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "order=" + dOrders +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
