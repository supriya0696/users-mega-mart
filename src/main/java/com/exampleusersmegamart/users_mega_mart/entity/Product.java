package com.exampleusersmegamart.users_mega_mart.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="product_name")
    private String productName;

    @Column(name="category")
    private String category;

    @Column(name="price")
    private String price;

    @Column(name="status")
    private String status;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    public Product(String productName, String category, String price, String status) {
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.status = status;
    }

    public Product() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" +id +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                ", price='" + price + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
