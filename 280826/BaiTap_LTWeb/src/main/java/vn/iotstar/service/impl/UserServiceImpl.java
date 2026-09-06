package vn.iotstar.service.impl;

import java.sql.Timestamp;
import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.model.User;
import vn.iotstar.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public User login(String username, String password) {
        User user = this.get(username);
        // Chỉ đăng nhập thành công nếu đúng mật khẩu VÀ tài khoản đã kích hoạt (status == true)
        if (user != null && password.equals(user.getPassword()) && user.isStatus()) {
            return user;
        }
        return null;
    }

    @Override
    public User get(String username) {
        return userDao.get(username);
    }

    @Override
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    public boolean register(User user) {
        if (userDao.checkExistUsername(user.getUserName()) || userDao.checkExistEmail(user.getEmail())) {
            return false;
        }
        userDao.insert(user);
        return true;
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public boolean verifyOtp(String email, String otp) {
        User user = userDao.getByEmail(email);
        if (user != null && user.getOtpCode() != null && user.getOtpCode().equals(otp)) {
            if (user.getOtpExpiry() != null && user.getOtpExpiry().after(new Timestamp(System.currentTimeMillis()))) {
                user.setStatus(true);
                user.setOtpCode(null);
                user.setOtpExpiry(null);
                userDao.update(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean resetPassword(String email, String otp, String newPassword) {
        User user = userDao.getByEmail(email);
        if (user != null && user.getOtpCode() != null && user.getOtpCode().equals(otp)) {
            if (user.getOtpExpiry() != null && user.getOtpExpiry().after(new Timestamp(System.currentTimeMillis()))) {
                user.setPassword(newPassword);
                user.setOtpCode(null);
                user.setOtpExpiry(null);
                userDao.update(user);
                return true;
            }
        }
        return false;
    }
}