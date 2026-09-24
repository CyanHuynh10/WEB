package vn.iotstar.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.iotstar.springsecurity.service.UserService;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String showRegister() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String email, 
                               @RequestParam String password, @RequestParam String fullName, Model model) {
        try {
            userService.registerUser(username, email, password, fullName);
            return "redirect:/verify-otp?email=" + email;
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "auth/register";
        }
    }

    @GetMapping("/verify-otp")
    public String showVerifyOtp(@RequestParam(required = false) String email, Model model) {
        model.addAttribute("email", email);
        return "auth/verify-otp";
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String email, @RequestParam String otpCode, @RequestParam String type, Model model) {
        boolean verified = userService.verifyOtp(email, otpCode, type);
        if (verified) {
            if ("REGISTER".equals(type)) {
                return "redirect:/login?registered=true";
            } else if ("FORGOT_PASSWORD".equals(type)) {
                return "redirect:/reset-password?email=" + email;
            }
        }
        model.addAttribute("error", "Invalid or expired OTP");
        model.addAttribute("email", email);
        return "auth/verify-otp";
    }

    @GetMapping("/forgot-password")
    public String showForgotPassword() {
        return "auth/forgot-password";
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email, Model model) {
        try {
            userService.forgotPassword(email);
            model.addAttribute("type", "FORGOT_PASSWORD");
            return "redirect:/verify-otp?email=" + email;
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "auth/forgot-password";
        }
    }

    @GetMapping("/reset-password")
    public String showResetPassword(@RequestParam String email, Model model) {
        model.addAttribute("email", email);
        return "auth/reset-password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String email, @RequestParam String password, Model model) {
        try {
            userService.resetPassword(email, password);
            return "redirect:/login?reset=true";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("email", email);
            return "auth/reset-password";
        }
    }
}