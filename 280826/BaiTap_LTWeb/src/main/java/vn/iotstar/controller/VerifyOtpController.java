package vn.iotstar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import vn.iotstar.service.UserService;
import vn.iotstar.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/verify-otp"})
public class VerifyOtpController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("email", req.getParameter("email"));
        req.getRequestDispatcher("/views/verify-otp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String otp = req.getParameter("otp");

        if (service.verifyOtp(email, otp)) {
            req.setAttribute("alertSuccess", "Tài khoản đã kích hoạt thành công! Hãy đăng nhập.");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("alert", "Mã OTP không chính xác hoặc đã hết hạn!");
            req.setAttribute("email", email);
            req.getRequestDispatcher("/views/verify-otp.jsp").forward(req, resp);
        }
    }
}