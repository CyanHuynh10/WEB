package vn.iot.ltweb_midterm.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iot.ltweb_midterm.service.UserService_24110064;
import vn.iot.ltweb_midterm.util.MailUtil_24110064;

import java.io.IOException;
import java.util.Random;

@WebServlet("/register")
public class RegisterController_24110064 extends HttpServlet {
    private UserService_24110064 userService = new UserService_24110064();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();

        if ("send_otp".equals(action)) {
            String email = req.getParameter("email");
            String fullname = req.getParameter("fullname");
            String password = req.getParameter("password");
            
            if (userService.checkEmailExist(email)) {
                req.setAttribute("error", "Email đã tồn tại!");
                req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
                return;
            }

            // Generate OTP
            String otp = String.format("%06d", new Random().nextInt(999999));
            session.setAttribute("otp", otp);
            session.setAttribute("otp_time", System.currentTimeMillis());
            
            // Store temp user data
            session.setAttribute("temp_email", email);
            session.setAttribute("temp_fullname", fullname);
            session.setAttribute("temp_password", password);

            // Send mail
            boolean sent = MailUtil_24110064.sendOTP(email, otp);
            if (sent) {
                req.setAttribute("message", "Mã OTP đã được gửi đến email của bạn.");
                req.getRequestDispatcher("/WEB-INF/views/verify_otp.jsp").forward(req, resp);
            } else {
                req.setAttribute("error", "Không thể gửi email OTP, vui lòng kiểm tra lại cấu hình.");
                req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            }
        } else if ("verify_otp".equals(action)) {
            String userOtp = req.getParameter("otp");
            String sessionOtp = (String) session.getAttribute("otp");
            Long otpTime = (Long) session.getAttribute("otp_time");

            if (sessionOtp != null && otpTime != null) {
                if (System.currentTimeMillis() - otpTime > 5 * 60 * 1000) {
                    req.setAttribute("error", "Mã OTP đã hết hạn!");
                    req.getRequestDispatcher("/WEB-INF/views/verify_otp.jsp").forward(req, resp);
                    return;
                }
                if (sessionOtp.equals(userOtp)) {
                    // Success, create user
                    String email = (String) session.getAttribute("temp_email");
                    String fullname = (String) session.getAttribute("temp_fullname");
                    String password = (String) session.getAttribute("temp_password");
                    
                    userService.register(email, password, fullname);
                    
                    // Clear temp data
                    session.removeAttribute("otp");
                    session.removeAttribute("otp_time");
                    session.removeAttribute("temp_email");
                    session.removeAttribute("temp_fullname");
                    session.removeAttribute("temp_password");
                    
                    req.setAttribute("message", "Đăng ký thành công, vui lòng đăng nhập!");
                    req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
                } else {
                    req.setAttribute("error", "Mã OTP không đúng!");
                    req.getRequestDispatcher("/WEB-INF/views/verify_otp.jsp").forward(req, resp);
                }
            } else {
                req.setAttribute("error", "Vui lòng đăng ký lại.");
                req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            }
        }
    }
}
