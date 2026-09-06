package vn.iotstar.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import vn.iotstar.config.JPAConfig;
import vn.iotstar.dao.ProductDao;
import vn.iotstar.model.Product;

public class ProductDaoImpl implements ProductDao {
    @Override
    public void insert(Product product) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.persist(product);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally { enma.close(); }
    }

    @Override
    public void update(Product product) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.merge(product);
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
            Product product = enma.find(Product.class, id);
            if (product != null) {
                enma.remove(product);
            } else {
                throw new Exception("Không tìm thấy sản phẩm");
            }
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally { enma.close(); }
    }

    @Override
    public Product findById(int id) {
        EntityManager enma = JPAConfig.getEntityManager();
        Product product = enma.find(Product.class, id);
        enma.close();
        return product;
    }

    @Override
    public List<Product> findAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Product> query = enma.createQuery("SELECT p FROM Product p", Product.class);
        List<Product> list = query.getResultList();
        enma.close();
        return list;
    }

    @Override
    public List<Product> findAll(int page, int pageSize) {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Product> query = enma.createQuery("SELECT p FROM Product p", Product.class);
        query.setFirstResult((page - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<Product> list = query.getResultList();
        enma.close();
        return list;
    }

    @Override
    public List<Product> findTop10() {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Product> query = enma.createQuery("SELECT p FROM Product p ORDER BY p.createdDate DESC", Product.class);
        query.setMaxResults(10);
        List<Product> list = query.getResultList();
        enma.close();
        return list;
    }

    @Override
    public int count() {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT count(p) FROM Product p";
        jakarta.persistence.Query query = enma.createQuery(jpql);
        Long count = (Long) query.getSingleResult();
        enma.close();
        return count.intValue();
    }
}