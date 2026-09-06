package vn.iotstar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import vn.iotstar.model.User;
import vn.iotstar.service.UserService;
import vn.iotstar.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean isRememberMe = req.getParameter("remember") != null;

        User user = service.login(username, password);
        if (user != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("account", user);
            
            if (isRememberMe) {
                Cookie cookie = new Cookie("username", username);
                cookie.setMaxAge(30 * 24 * 60 * 60);
                resp.addCookie(cookie);
            }
            
            // Điều hướng chuẩn theo Role
            if (user.getRoleid() == 1) {
                resp.sendRedirect(req.getContextPath() + "/admin/category/list");
            } else {
                resp.sendRedirect(req.getContextPath() + "/home");
            }
        } else {
            User check = service.get(username);
            String alert;
            if (check != null && !check.isStatus() && password.equals(check.getPassword())) {
                alert = "Tài khoản chưa được kích hoạt. Vui lòng kiểm tra email xác thực!";
            } else {
                alert = "Tài khoản hoặc mật khẩu không chính xác!";
            }
            req.setAttribute("alert", alert);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }
}