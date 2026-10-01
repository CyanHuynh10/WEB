package vn.iot.ltweb_midterm.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.iot.ltweb_midterm.config.JPAConfig_24110064;
import vn.iot.ltweb_midterm.entity.OrderItem_24110064;

public class OrderItemRepository_24110064 {

    public void insert(OrderItem_24110064 orderItem) {
        EntityManager enma = JPAConfig_24110064.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.persist(orderItem);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }
}
