package vn.iotstar.springsecurity.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.iotstar.springsecurity.entity.Role;
import vn.iotstar.springsecurity.entity.User;
import vn.iotstar.springsecurity.repository.RoleRepository;
import vn.iotstar.springsecurity.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Role userRole = roleRepository.findByNameIgnoreCase("ROLE_USER").stream().findFirst()
                    .orElseGet(() -> {
                        Role r = new Role();
                        r.setName("ROLE_USER");
                        return roleRepository.save(r);
                    });
                    
            Role adminRole = roleRepository.findByNameIgnoreCase("ROLE_ADMIN").stream().findFirst()
                    .orElseGet(() -> {
                        Role r = new Role();
                        r.setName("ROLE_ADMIN");
                        return roleRepository.save(r);
                    });

            if (userRepository.findByUsernameIgnoreCase("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setEmail("admin@gmail.com");
                admin.setPassword(passwordEncoder.encode("123456"));
                admin.setFullName("System Administrator");
                admin.setRole(adminRole);
                admin.setEnabled(true);
                userRepository.save(admin);
            }
            
            if (userRepository.findByUsernameIgnoreCase("user01").isEmpty()) {
                User user = new User();
                user.setUsername("user01");
                user.setEmail("user01@gmail.com");
                user.setPassword(passwordEncoder.encode("123456"));
                user.setFullName("Nguyễn Hữu Trung");
                user.setRole(userRole);
                user.setEnabled(true);
                userRepository.save(user);
            }
        };
    }
}
