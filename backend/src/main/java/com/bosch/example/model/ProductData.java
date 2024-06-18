package com.bosch.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ProductData")
public class ProductData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name")
    private String productName;

    @Column(name = "Value")
    private String productValue;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private UserData productUser;

    public ProductData(String productName, String productValue, UserData productUser) {
        this.productName = productName;
        this.productValue = productValue;
        this.productUser = productUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductValue() {
        return productValue;
    }

    public void setProductValue(String productValue) {
        this.productValue = productValue;
    }

    public UserData getProductUser() {
        return productUser;
    }

    public void setProductUser(UserData productUser) {
        this.productUser = productUser;
    }

}
