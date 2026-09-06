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

@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String password = req.getParameter("password");

        String otp = String.format("%06d", new Random().nextInt(999999));
        Timestamp expiry = new Timestamp(System.currentTimeMillis() + (5 * 60 * 1000)); // 5 phút

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
            EmailUtil.sendEmail(email, "Kích hoạt tài khoản", "Mã OTP kích hoạt tài khoản của bạn là: <b>" + otp + "</b> (Hết hạn sau 5 phút).");
            resp.sendRedirect(req.getContextPath() + "/verify-otp?email=" + email);
        } else {
            req.setAttribute("alert", "Tên đăng nhập hoặc Email đã được sử dụng!");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
        }
    }
}