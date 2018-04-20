package com.example.project.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Product")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "photo")
    private String photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Detail> orderDetails = new ArrayList<>();

    public Product() {
    }

    public Product(String name, String description, Double price, String photo, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.photo = photo;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getPhoto() {
        return photo;
    }

    public Category getCategory() {
        return category;
    }

    public List<Detail> getOrderDetails() {
        return orderDetails;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setOrderDetails(List<Detail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void setAllAttributes(String name, String description, Double price, String photo, Category category){
        this.name = name;
        this.description = description;
        this.price = price;
        this.photo = photo;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", photo='" + photo + '\'' +
                ", category=" + category +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
