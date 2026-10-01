package vn.iot.ltweb_midterm.service;

import vn.iot.ltweb_midterm.entity.User_24110064;
import vn.iot.ltweb_midterm.repository.UserRepository_24110064;
import java.util.Date;

public class UserService_24110064 {

    private UserRepository_24110064 userRepo = new UserRepository_24110064();

    public User_24110064 login(String email, String password) {
        User_24110064 user = userRepo.findByEmail(email);
        if (user != null && user.getPasswd().equals(password)) {
            user.setLast_login(new Date());
            userRepo.save(user);
            return user;
        }
        return null;
    }

    public boolean register(String email, String password, String fullname) {
        if (userRepo.findByEmail(email) != null) {
            return false;
        }
        User_24110064 user = new User_24110064();
        user.setEmail(email);
        user.setPasswd(password);
        user.setFullname(fullname);
        user.setSignup_date(new Date());
        user.setIs_admin(false);
        userRepo.save(user);
        return true;
    }
    
    public boolean checkEmailExist(String email) {
        return userRepo.findByEmail(email) != null;
    }
}
