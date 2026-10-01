package vn.iot.ltweb_midterm.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iot.ltweb_midterm.config.JPAConfig_24110064;
import vn.iot.ltweb_midterm.entity.Rating_24110064;

import java.util.List;

public class RatingRepository_24110064 {

    public List<Rating_24110064> findByBookId(Integer bookId) {
        EntityManager em = JPAConfig_24110064.getEntityManager();
        try {
            TypedQuery<Rating_24110064> query = em.createQuery(
                "SELECT r FROM Rating_24110064 r JOIN FETCH r.user WHERE r.book.bookid = :bookId", Rating_24110064.class);
            query.setParameter("bookId", bookId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void save(Rating_24110064 rating) {
        EntityManager em = JPAConfig_24110064.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(rating); // use merge to handle both insert and update for composite key easily
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) trans.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
