package vn.iotstar.service;

import vn.iotstar.model.User;

public interface UserService {
    User login(String username, String password);
    User get(String username);
    User getByEmail(String email);
    boolean register(User user);
    void update(User user);
    boolean verifyOtp(String email, String otp);
    boolean resetPassword(String email, String otp, String newPassword);
}