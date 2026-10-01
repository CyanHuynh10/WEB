package vn.iot.ltweb_midterm.model;

import java.util.ArrayList;
import java.util.List;

public class Cart_24110064 {
    private List<CartItem_24110064> items;

    public Cart_24110064() {
        items = new ArrayList<>();
    }

    public List<CartItem_24110064> getItems() {
        return items;
    }

    public void setItems(List<CartItem_24110064> items) {
        this.items = items;
    }

    public void addItem(CartItem_24110064 item) {
        for (CartItem_24110064 currentItem : items) {
            if (currentItem.getBook().getBookid().equals(item.getBook().getBookid())) {
                currentItem.setQuantity(currentItem.getQuantity() + item.getQuantity());
                return;
            }
        }
        items.add(item);
    }

    public void removeItem(Integer bookId) {
        items.removeIf(item -> item.getBook().getBookid().equals(bookId));
    }

    public void updateQuantity(Integer bookId, int quantity) {
        for (CartItem_24110064 item : items) {
            if (item.getBook().getBookid().equals(bookId)) {
                item.setQuantity(quantity);
                return;
            }
        }
    }

    public double getTotal() {
        double total = 0;
        for (CartItem_24110064 item : items) {
            total += item.getSubtotal();
        }
        return total;
    }
    
    public int getTotalQuantity() {
        int total = 0;
        for (CartItem_24110064 item : items) {
            total += item.getQuantity();
        }
        return total;
    }

    public void clear() {
        items.clear();
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
}
