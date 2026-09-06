package vn.iotstar.dao.impl;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iotstar.config.JPAConfig;
import vn.iotstar.dao.CategoryDao;
import vn.iotstar.model.Category;

public class CategoryDaoImpl implements CategoryDao {

    @Override
    public void insert(Category category) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.persist(category); // JPA Insert
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally { enma.close(); }
    }

    @Override
    public void update(Category category) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.merge(category); // JPA Update
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally { enma.close(); }
    }

    @Override
    public void delete(int id) throws Exception {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            Category category = enma.find(Category.class, id); // Tìm entity
            if (category != null) {
                enma.remove(category); // JPA Delete
            } else {
                throw new Exception("Không tìm thấy Category");
            }
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally { enma.close(); }
    }

    @Override
    public Category findById(int id) {
        EntityManager enma = JPAConfig.getEntityManager();
        Category category = enma.find(Category.class, id); // JPA Find By ID
        enma.close();
        return category;
    }

    @Override
    public Category findByCategoryname(String name) {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT c FROM Category c WHERE c.name = :name";
        try {
            TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        } finally { enma.close(); }
    }

    @Override
    public List<Category> findAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Category> query = enma.createQuery("SELECT c FROM Category c", Category.class);
        List<Category> list = query.getResultList();
        enma.close();
        return list;
    }

    @Override
    public List<Category> searchByName(String keyword) {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT c FROM Category c WHERE c.name LIKE :keyword";
        TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
        query.setParameter("keyword", "%" + keyword + "%");
        List<Category> list = query.getResultList();
        enma.close();
        return list;
    }

    @Override
    public List<Category> findAll(int page, int pagesize) {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Category> query = enma.createQuery("SELECT c FROM Category c", Category.class);
        query.setFirstResult((page - 1) * pagesize);
        query.setMaxResults(pagesize);
        List<Category> list = query.getResultList();
        enma.close();
        return list;
    }

    @Override
    public int count() {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT COUNT(c) FROM Category c";
        jakarta.persistence.Query query = enma.createQuery(jpql);
        return ((Long) query.getSingleResult()).intValue();
    }
}