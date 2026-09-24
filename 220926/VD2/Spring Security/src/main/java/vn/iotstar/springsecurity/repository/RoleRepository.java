package vn.iotstar.springsecurity.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.iotstar.springsecurity.entity.Role;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    java.util.List<Role> findByNameIgnoreCase(String name);
}
