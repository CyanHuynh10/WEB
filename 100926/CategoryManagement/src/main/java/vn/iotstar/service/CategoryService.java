package vn.iotstar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.iotstar.entity.Category;

public interface CategoryService {

    Category save(Category category);

    List<Category> findAll();

    Page<Category> findAll(Pageable pageable);

    Optional<Category> findById(Long id);

    void deleteById(Long id);

    Page<Category> searchByName(String keyword, Pageable pageable);
}