package vn.iotstar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iotstar.entity.User;
import vn.iotstar.repository.UserRepository;
import vn.iotstar.service.UserService;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public List<User> searchUser(String keyword) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            // Tìm theo cả username hoặc fullname
            return userRepo.findByUsernameContainingIgnoreCaseOrFullnameContainingIgnoreCase(keyword, keyword);
        }
        return userRepo.findAll();
    }

    @Override
    public boolean isUsernameExists(String username) {
        return userRepo.findByUsername(username).isPresent();
    }
}