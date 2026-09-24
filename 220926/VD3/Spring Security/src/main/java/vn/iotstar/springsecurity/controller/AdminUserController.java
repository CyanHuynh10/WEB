package vn.iotstar.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.iotstar.springsecurity.entity.User;
import vn.iotstar.springsecurity.repository.UserRepository;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String listUsers(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<User> userPage = userRepository.findAll(PageRequest.of(page, 5));
        model.addAttribute("userPage", userPage);
        return "admin/users";
    }
}