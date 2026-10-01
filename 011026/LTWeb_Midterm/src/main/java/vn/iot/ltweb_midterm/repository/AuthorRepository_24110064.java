package vn.iot.ltweb_midterm.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iot.ltweb_midterm.config.JPAConfig_24110064;
import vn.iot.ltweb_midterm.entity.Author_24110064;

import java.util.List;

public class AuthorRepository_24110064 {

    public List<Author_24110064> findAll(int offset, int limit) {
        EntityManager em = JPAConfig_24110064.getEntityManager();
        try {
            TypedQuery<Author_24110064> query = em.createQuery("SELECT a FROM Author_24110064 a ORDER BY a.author_id DESC", Author_24110064.class);
            if (limit > 0) {
                query.setFirstResult(offset);
                query.setMaxResults(limit);
            }
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Author_24110064> findAll() {
        return findAll(0, -1);
    }

    public long countAll() {
        EntityManager em = JPAConfig_24110064.getEntityManager();
        try {
            return em.createQuery("SELECT COUNT(a) FROM Author_24110064 a", Long.class).getSingleResult();
        } finally {
            em.close();
        }
    }

    public Author_24110064 findById(Integer id) {
        EntityManager em = JPAConfig_24110064.getEntityManager();
        try {
            return em.find(Author_24110064.class, id);
        } finally {
            em.close();
        }
    }

    public void save(Author_24110064 author) {
        EntityManager em = JPAConfig_24110064.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            if (author.getAuthor_id() == null) {
                em.persist(author);
            } else {
                em.merge(author);
            }
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) trans.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void delete(Integer id) {
        EntityManager em = JPAConfig_24110064.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            Author_24110064 author = em.find(Author_24110064.class, id);
            if (author != null) {
                em.remove(author);
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
