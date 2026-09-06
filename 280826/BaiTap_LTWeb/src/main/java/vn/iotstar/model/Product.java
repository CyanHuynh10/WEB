package vn.iotstar.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "Product")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name")
    private String productName;

    private String description;
    private double price;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "created_date")
    private Timestamp createdDate;

    // Quan hệ N-1 với Category
    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category category;

    public Product() {
        this.createdDate = new Timestamp(System.currentTimeMillis());
    }

    // Getters and Setters
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public Timestamp getCreatedDate() { return createdDate; }
    public void setCreatedDate(Timestamp createdDate) { this.createdDate = createdDate; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}