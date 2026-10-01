package vn.iot.ltweb_midterm.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iot.ltweb_midterm.config.JPAConfig_24110064;
import vn.iot.ltweb_midterm.entity.Book_24110064;

import java.util.List;

public class BookRepository_24110064 {

    public List<Book_24110064> findAll(int offset, int limit) {
        EntityManager em = JPAConfig_24110064.getEntityManager();
        try {
            TypedQuery<Book_24110064> query = em.createQuery("SELECT b FROM Book_24110064 b ORDER BY b.bookid DESC", Book_24110064.class);
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            // Fetch authors to avoid lazy loading issues later
            List<Book_24110064> books = query.getResultList();
            for (Book_24110064 b : books) {
                b.getAuthors().size();
            }
            return books;
        } finally {
            em.close();
        }
    }

    public long countAll() {
        EntityManager em = JPAConfig_24110064.getEntityManager();
        try {
            return em.createQuery("SELECT COUNT(b) FROM Book_24110064 b", Long.class).getSingleResult();
        } finally {
            em.close();
        }
    }

    public Book_24110064 findById(Integer id) {
        EntityManager em = JPAConfig_24110064.getEntityManager();
        try {
            Book_24110064 b = em.find(Book_24110064.class, id);
            if (b != null) {
                b.getAuthors().size(); // init lazy
            }
            return b;
        } finally {
            em.close();
        }
    }

    public void save(Book_24110064 book) {
        EntityManager em = JPAConfig_24110064.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            if (book.getBookid() == null) {
                em.persist(book);
            } else {
                em.merge(book);
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
            Book_24110064 book = em.find(Book_24110064.class, id);
            if (book != null) {
                em.remove(book);
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
