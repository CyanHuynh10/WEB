package vn.iot.ltweb_midterm.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.iot.ltweb_midterm.config.JPAConfig_24110064;
import vn.iot.ltweb_midterm.entity.Book_24110064;
import vn.iot.ltweb_midterm.entity.Order_24110064;
import vn.iot.ltweb_midterm.entity.OrderItem_24110064;
import vn.iot.ltweb_midterm.entity.User_24110064;
import vn.iot.ltweb_midterm.model.CartItem_24110064;
import vn.iot.ltweb_midterm.model.Cart_24110064;
import vn.iot.ltweb_midterm.repository.OrderRepository_24110064;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderService_24110064 {

    private final OrderRepository_24110064 orderRepository = new OrderRepository_24110064();

    public Order_24110064 placeOrder(User_24110064 user, Cart_24110064 cart, String name, String phoneStr, String address) throws Exception {
        EntityManager enma = JPAConfig_24110064.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        
        try {
            trans.begin();
            
            Order_24110064 order = new Order_24110064();
            order.setUser(user);
            order.setOrderDate(new Date());
            order.setPaymentMethod("COD");
            order.setStatus("NEW");
            order.setCustomerName(name);
            order.setPhone(Integer.parseInt(phoneStr));
            order.setShippingAddress(address);
            
            double totalAmount = 0;
            List<OrderItem_24110064> orderItems = new ArrayList<>();
            
            for (CartItem_24110064 cItem : cart.getItems()) {
                // Reload book to check latest stock
                Book_24110064 book = enma.find(Book_24110064.class, cItem.getBook().getBookid());
                if (book == null) {
                    throw new Exception("Sản phẩm không tồn tại: " + cItem.getBook().getTitle());
                }
                if (book.getQuantity() < cItem.getQuantity()) {
                    throw new Exception("Sản phẩm " + book.getTitle() + " không đủ số lượng tồn kho.");
                }
                
                OrderItem_24110064 orderItem = new OrderItem_24110064();
                orderItem.setOrder(order);
                orderItem.setBook(book);
                orderItem.setQuantity(cItem.getQuantity());
                orderItem.setUnitPrice(cItem.getUnitPrice());
                orderItem.setSubtotal(cItem.getSubtotal());
                orderItems.add(orderItem);
                
                totalAmount += cItem.getSubtotal();
                
                // Reduce stock
                book.setQuantity(book.getQuantity() - cItem.getQuantity());
                enma.merge(book);
            }
            
            order.setTotalAmount(totalAmount);
            order.setOrderItems(orderItems);
            
            enma.persist(order);
            
            trans.commit();
            return order;
        } catch (Exception e) {
            if (trans.isActive()) {
                trans.rollback();
            }
            throw e;
        } finally {
            enma.close();
        }
    }
    
    public List<Order_24110064> getOrdersByUser(Integer userId, String status) {
        if (status == null || status.isEmpty() || "ALL".equals(status)) {
            return orderRepository.findByUserId(userId);
        }
        return orderRepository.findByUserIdAndStatus(userId, status);
    }
    
    public Order_24110064 getOrderDetails(Integer orderId) {
        return orderRepository.findByIdWithItems(orderId);
    }
}
