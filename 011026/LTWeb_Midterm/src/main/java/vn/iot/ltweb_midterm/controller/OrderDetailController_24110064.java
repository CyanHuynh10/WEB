package vn.iot.ltweb_midterm.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iot.ltweb_midterm.entity.Order_24110064;
import vn.iot.ltweb_midterm.entity.User_24110064;
import vn.iot.ltweb_midterm.service.OrderService_24110064;

import java.io.IOException;

@WebServlet(urlPatterns = {"/order/detail", "/order/success"})
public class OrderDetailController_24110064 extends HttpServlet {

    private final OrderService_24110064 orderService = new OrderService_24110064();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User_24110064 user = (User_24110064) session.getAttribute("user");
        String path = request.getServletPath();
        
        try {
            Integer orderId = Integer.parseInt(request.getParameter("id"));
            Order_24110064 order = orderService.getOrderDetails(orderId);
            
            if (order == null) {
                session.setAttribute("errorMessage", "Không tìm thấy đơn hàng.");
                response.sendRedirect(request.getContextPath() + "/orders");
                return;
            }
            
            // Check ownership
            if (!order.getUser().getId().equals(user.getId())) {
                session.setAttribute("errorMessage", "Không được phép xem đơn hàng này.");
                response.sendRedirect(request.getContextPath() + "/orders");
                return;
            }
            
            request.setAttribute("order", order);
            
            if ("/order/success".equals(path)) {
                request.getRequestDispatcher("/WEB-INF/views/order_success.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/WEB-INF/views/order_detail.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            session.setAttribute("errorMessage", "Mã đơn hàng không hợp lệ.");
            response.sendRedirect(request.getContextPath() + "/orders");
        }
    }
}
