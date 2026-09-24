package vn.iotstar.springsecurity.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.iotstar.springsecurity.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailIgnoreCase(String email);
    java.util.List<User> findByUsernameIgnoreCase(String username);
    java.util.List<User> findByUsernameOrEmail(String username, String email);
}
