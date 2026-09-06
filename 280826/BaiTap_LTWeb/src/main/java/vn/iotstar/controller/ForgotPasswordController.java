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

@WebServlet(urlPatterns = {"/forgot-password"})
public class ForgotPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        User user = service.getByEmail(email);

        if (user != null) {
            String otp = String.format("%06d", new Random().nextInt(999999));
            Timestamp expiry = new Timestamp(System.currentTimeMillis() + (5 * 60 * 1000));
            user.setOtpCode(otp);
            user.setOtpExpiry(expiry);
            service.update(user);

            EmailUtil.sendEmail(email, "Khôi phục mật khẩu", "Mã OTP khôi phục mật khẩu của bạn là: <b>" + otp + "</b>");
            resp.sendRedirect(req.getContextPath() + "/reset-password?email=" + email);
        } else {
            req.setAttribute("alert", "Email không tồn tại trong hệ thống!");
            req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
        }
    }
}