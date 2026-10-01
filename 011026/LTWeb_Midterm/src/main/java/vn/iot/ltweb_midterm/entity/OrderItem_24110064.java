package vn.iot.ltweb_midterm.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_items")
public class OrderItem_24110064 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order_24110064 order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookid", nullable = false)
    private Book_24110064 book;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false, columnDefinition = "decimal(6,2)")
    private Double unitPrice;

    @Column(name = "subtotal", nullable = false, columnDefinition = "decimal(18,2)")
    private Double subtotal;

    public OrderItem_24110064() {
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Order_24110064 getOrder() { return order; }
    public void setOrder(Order_24110064 order) { this.order = order; }

    public Book_24110064 getBook() { return book; }
    public void setBook(Book_24110064 book) { this.book = book; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(Double unitPrice) { this.unitPrice = unitPrice; }

    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
}
