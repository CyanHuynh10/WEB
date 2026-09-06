package vn.iotstar.dao;

import java.util.List;
import vn.iotstar.model.Product;

public interface ProductDao {
    void insert(Product product);
    void update(Product product);
    void delete(int id) throws Exception;
    Product findById(int id);
    List<Product> findAll();
    List<Product> findAll(int page, int pageSize);
    List<Product> findTop10();
    int count();
}