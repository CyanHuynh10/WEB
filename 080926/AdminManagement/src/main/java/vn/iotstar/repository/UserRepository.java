package vn.iotstar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iotstar.entity.User;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Tìm kiếm User theo tên đăng nhập hoặc họ tên (không phân biệt hoa thường)
    List<User> findByUsernameContainingIgnoreCaseOrFullnameContainingIgnoreCase(String username, String fullname);
    
    // Hàm này hỗ trợ kiểm tra xem username đã tồn tại chưa khi Add/Edit
    Optional<User> findByUsername(String username);
}