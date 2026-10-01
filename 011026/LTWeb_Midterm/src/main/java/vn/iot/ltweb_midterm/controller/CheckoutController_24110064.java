package vn.iot.ltweb_midterm.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iot.ltweb_midterm.entity.Order_24110064;
import vn.iot.ltweb_midterm.entity.User_24110064;
import vn.iot.ltweb_midterm.model.Cart_24110064;
import vn.iot.ltweb_midterm.service.OrderService_24110064;

import java.io.IOException;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController_24110064 extends HttpServlet {

    private final OrderService_24110064 orderService = new OrderService_24110064();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart_24110064 cart = (Cart_24110064) session.getAttribute("cart");
        
        if (cart == null || cart.isEmpty()) {
            session.setAttribute("errorMessage", "Không thể thanh toán khi giỏ hàng trống.");
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        request.getRequestDispatcher("/WEB-INF/views/checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User_24110064 user = (User_24110064) session.getAttribute("user");
        Cart_24110064 cart = (Cart_24110064) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            session.setAttribute("errorMessage", "Không thể thanh toán khi giỏ hàng trống.");
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        try {
            Order_24110064 order = orderService.placeOrder(user, cart, name, phone, address);
            cart.clear(); // Clear cart on success
            session.setAttribute("successMessage", "Đặt hàng thành công!");
            response.sendRedirect(request.getContextPath() + "/order/success?id=" + order.getOrderId());
        } catch (Exception e) {
            session.setAttribute("errorMessage", e.getMessage());
            response.sendRedirect(request.getContextPath() + "/checkout");
        }
    }
}
