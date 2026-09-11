package vn.iotstar.service;

import vn.iotstar.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    void deleteById(Long id);
    // Hàm xử lý logic tìm kiếm User
    List<User> searchUser(String keyword);
    // Kiểm tra username trùng lặp
    boolean isUsernameExists(String username);
}