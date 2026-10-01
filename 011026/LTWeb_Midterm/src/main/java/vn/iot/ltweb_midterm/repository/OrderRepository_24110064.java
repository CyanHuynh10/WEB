package vn.iot.ltweb_midterm.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iot.ltweb_midterm.config.JPAConfig_24110064;
import vn.iot.ltweb_midterm.entity.Order_24110064;

import java.util.List;

public class OrderRepository_24110064 {

    public void insert(Order_24110064 order) {
        EntityManager enma = JPAConfig_24110064.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.persist(order);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    public void update(Order_24110064 order) {
        EntityManager enma = JPAConfig_24110064.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.merge(order);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    public Order_24110064 findById(Integer id) {
        EntityManager enma = JPAConfig_24110064.getEntityManager();
        try {
            return enma.find(Order_24110064.class, id);
        } finally {
            enma.close();
        }
    }
    
    public Order_24110064 findByIdWithItems(Integer id) {
        EntityManager enma = JPAConfig_24110064.getEntityManager();
        try {
            TypedQuery<Order_24110064> query = enma.createQuery("SELECT o FROM Order_24110064 o LEFT JOIN FETCH o.orderItems oi LEFT JOIN FETCH oi.book WHERE o.orderId = :id", Order_24110064.class);
            query.setParameter("id", id);
            return query.getResultStream().findFirst().orElse(null);
        } finally {
            enma.close();
        }
    }

    public List<Order_24110064> findByUserId(Integer userId) {
        EntityManager enma = JPAConfig_24110064.getEntityManager();
        try {
            TypedQuery<Order_24110064> query = enma.createQuery("SELECT o FROM Order_24110064 o WHERE o.user.id = :userId ORDER BY o.orderDate DESC", Order_24110064.class);
            query.setParameter("userId", userId);
            return query.getResultList();
        } finally {
            enma.close();
        }
    }
    
    public List<Order_24110064> findByUserIdAndStatus(Integer userId, String status) {
        EntityManager enma = JPAConfig_24110064.getEntityManager();
        try {
            TypedQuery<Order_24110064> query = enma.createQuery("SELECT o FROM Order_24110064 o WHERE o.user.id = :userId AND o.status = :status ORDER BY o.orderDate DESC", Order_24110064.class);
            query.setParameter("userId", userId);
            query.setParameter("status", status);
            return query.getResultList();
        } finally {
            enma.close();
        }
    }
}
