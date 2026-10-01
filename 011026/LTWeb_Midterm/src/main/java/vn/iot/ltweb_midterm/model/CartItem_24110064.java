package vn.iot.ltweb_midterm.model;

import vn.iot.ltweb_midterm.entity.Book_24110064;

public class CartItem_24110064 {
    private Book_24110064 book;
    private int quantity;
    private double unitPrice;

    public CartItem_24110064() {}

    public CartItem_24110064(Book_24110064 book, int quantity) {
        this.book = book;
        this.quantity = quantity;
        this.unitPrice = book.getPrice() != null ? book.getPrice() : 0.0;
    }

    public Book_24110064 getBook() {
        return book;
    }

    public void setBook(Book_24110064 book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getSubtotal() {
        return this.unitPrice * this.quantity;
    }
}
