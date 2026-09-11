package vn.iotstar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iotstar.entity.Category;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Tìm kiếm Category theo tên (không phân biệt hoa thường)
    List<Category> findByNameContainingIgnoreCase(String name);
}