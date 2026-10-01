package vn.iot.ltweb_midterm.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iot.ltweb_midterm.config.JPAConfig_24110064;
import vn.iot.ltweb_midterm.entity.User_24110064;

public class UserRepository_24110064 {

    public User_24110064 findByEmail(String email) {
        EntityManager em = JPAConfig_24110064.getEntityManager();
        try {
            TypedQuery<User_24110064> query = em.createQuery("SELECT u FROM User_24110064 u WHERE u.email = :email", User_24110064.class);
            query.setParameter("email", email);
            return query.getResultStream().findFirst().orElse(null);
        } finally {
            em.close();
        }
    }

    public void save(User_24110064 user) {
        EntityManager em = JPAConfig_24110064.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            if (user.getId() == null) {
                em.persist(user);
            } else {
                em.merge(user);
            }
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) trans.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
