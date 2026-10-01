package vn.iot.ltweb_midterm.service;

import vn.iot.ltweb_midterm.entity.Book_24110064;
import vn.iot.ltweb_midterm.model.CartItem_24110064;
import vn.iot.ltweb_midterm.model.Cart_24110064;
import vn.iot.ltweb_midterm.repository.BookRepository_24110064;

public class CartService_24110064 {

    private final BookRepository_24110064 bookRepository = new BookRepository_24110064();

    public boolean addToCart(Cart_24110064 cart, Integer bookId, int quantity) throws Exception {
        if (quantity <= 0) {
            throw new Exception("Số lượng phải lớn hơn 0");
        }
        
        Book_24110064 book = bookRepository.findById(bookId);
        if (book == null) {
            throw new Exception("Không tìm thấy sách");
        }
        
        // Find existing quantity in cart
        int existingQuantity = 0;
        for (CartItem_24110064 item : cart.getItems()) {
            if (item.getBook().getBookid().equals(bookId)) {
                existingQuantity = item.getQuantity();
                break;
            }
        }
        
        if (existingQuantity + quantity > book.getQuantity()) {
            throw new Exception("Số lượng sách trong giỏ vượt quá tồn kho hiện tại.");
        }
        
        CartItem_24110064 item = new CartItem_24110064(book, quantity);
        cart.addItem(item);
        return true;
    }

    public void updateCart(Cart_24110064 cart, Integer bookId, int quantity) throws Exception {
        if (quantity <= 0) {
            throw new Exception("Số lượng không hợp lệ");
        }
        
        Book_24110064 book = bookRepository.findById(bookId);
        if (book != null && quantity > book.getQuantity()) {
            throw new Exception("Số lượng yêu cầu vượt quá tồn kho.");
        }
        
        cart.updateQuantity(bookId, quantity);
    }
    
    public void removeFromCart(Cart_24110064 cart, Integer bookId) {
        cart.removeItem(bookId);
    }
}
