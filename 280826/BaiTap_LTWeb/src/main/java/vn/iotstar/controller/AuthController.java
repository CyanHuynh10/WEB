package vn.iotstar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Random;
import vn.iotstar.model.User;
import vn.iotstar.service.UserService;
import vn.iotstar.service.impl.UserServiceImpl;
import vn.iotstar.utils.EmailUtil;

@WebServlet(urlPatterns = {"/auth"})
public class AuthController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "login";

        switch (action) {
            case "register":
                req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
                break;
            case "verify-otp":
                req.setAttribute("email", req.getParameter("email"));
                req.getRequestDispatcher("/views/verify-otp.jsp").forward(req, resp);
                break;
            case "forgot-password":
                req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
                break;
            case "reset-password":
                req.setAttribute("email", req.getParameter("email"));
                req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
                break;
            case "logout":
                HttpSession session = req.getSession(false);
                if (session != null) session.invalidate();
                // Xóa cookie remember me
                Cookie[] cookies = req.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if ("username".equals(cookie.getName())) {
                            cookie.setMaxAge(0);
                            resp.addCookie(cookie);
                        }
                    }
                }
                resp.sendRedirect(req.getContextPath() + "/auth?action=login");
                break;
            case "login":
            default:
                req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) action = "login";

        switch (action) {
            case "register":
                handleRegister(req, resp);
                break;
            case "verify-otp":
                handleVerifyOtp(req, resp);
                break;
            case "forgot-password":
                handleForgotPassword(req, resp);
                break;
            case "reset-password":
                handleResetPassword(req, resp);
                break;
            case "login":
            default:
                handleLogin(req, resp);
                break;
        }
    }

    private void handleLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean isRememberMe = req.getParameter("remember") != null;

        User user = service.login(username, password);
        if (user != null) {
            // Đăng nhập thành công -> Lưu Session
            HttpSession session = req.getSession(true);
            session.setAttribute("account", user);
            
            // Xử lý Remember Me
            if (isRememberMe) {
                Cookie cookie = new Cookie("username", username);
                cookie.setMaxAge(30 * 24 * 60 * 60);
                resp.addCookie(cookie);
            }
            
            // ĐIỀU HƯỚNG THEO ROLE (Quyền)
            if (user.getRoleid() == 1) {
                // Nếu là Admin -> Chuyển thẳng vào khu vực Admin (Quản lý Category)
                resp.sendRedirect(req.getContextPath() + "/admin/category/list");
            } else {
                // Nếu là User thường -> Chuyển ra trang chủ User
                resp.sendRedirect(req.getContextPath() + "/home");
            }
        } else {
            // Xử lý lỗi đăng nhập
            User checkUser = service.get(username);
            String alertMsg = (checkUser != null && !checkUser.isStatus() && password.equals(checkUser.getPassword())) 
                              ? "Tài khoản chưa được kích hoạt. Vui lòng kiểm tra Email!" 
                              : "Tài khoản hoặc mật khẩu không đúng";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }

    private void handleRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String password = req.getParameter("password");

        String otp = String.format("%06d", new Random().nextInt(999999));
        Timestamp expiry = new Timestamp(System.currentTimeMillis() + (5 * 60 * 1000));

        User user = new User();
        user.setUserName(username);
        user.setEmail(email);
        user.setFullName(fullname);
        user.setPassword(password);
        user.setRoleid(2);
        user.setStatus(false);
        user.setOtpCode(otp);
        user.setOtpExpiry(expiry);

        if (service.register(user)) {
            EmailUtil.sendEmail(email, "Kích hoạt tài khoản LTWeb", "Mã OTP kích hoạt của bạn là: " + otp);
            resp.sendRedirect(req.getContextPath() + "/auth?action=verify-otp&email=" + email);
        } else {
            req.setAttribute("alert", "Tài khoản hoặc Email đã tồn tại!");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
        }
    }

    private void handleVerifyOtp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String otp = req.getParameter("otp");

        if (service.verifyOtp(email, otp)) {
            req.setAttribute("alert", "Kích hoạt thành công. Vui lòng đăng nhập!");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("alert", "Mã OTP không chính xác hoặc đã hết hạn!");
            req.setAttribute("email", email);
            req.getRequestDispatcher("/views/verify-otp.jsp").forward(req, resp);
        }
    }

    private void handleForgotPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        User user = service.getByEmail(email);

        if (user != null) {
            String otp = String.format("%06d", new Random().nextInt(999999));
            Timestamp expiry = new Timestamp(System.currentTimeMillis() + (5 * 60 * 1000));
            user.setOtpCode(otp);
            user.setOtpExpiry(expiry);
            service.update(user);

            EmailUtil.sendEmail(email, "Khôi phục mật khẩu LTWeb", "Mã OTP khôi phục mật khẩu của bạn là: " + otp);
            resp.sendRedirect(req.getContextPath() + "/auth?action=reset-password&email=" + email);
        } else {
            req.setAttribute("alert", "Email không tồn tại trong hệ thống!");
            req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
        }
    }

    private void handleResetPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String otp = req.getParameter("otp");
        String newPassword = req.getParameter("newPassword");

        if (service.resetPassword(email, otp, newPassword)) {
            req.setAttribute("alert", "Đổi mật khẩu thành công. Vui lòng đăng nhập!");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("alert", "Mã OTP không chính xác hoặc đã hết hạn!");
            req.setAttribute("email", email);
            req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
        }
    }
}