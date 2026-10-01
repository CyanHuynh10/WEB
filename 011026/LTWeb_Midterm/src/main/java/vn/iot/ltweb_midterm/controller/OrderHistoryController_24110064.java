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
import java.util.List;

@WebServlet(urlPatterns = {"/orders"})
public class OrderHistoryController_24110064 extends HttpServlet {

    private final OrderService_24110064 orderService = new OrderService_24110064();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User_24110064 user = (User_24110064) session.getAttribute("user");
        
        String status = request.getParameter("status");
        if (status == null) {
            status = "ALL";
        }
        
        List<Order_24110064> orders = orderService.getOrdersByUser(user.getId(), status);
        
        request.setAttribute("orders", orders);
        request.setAttribute("currentStatus", status);
        
        request.getRequestDispatcher("/WEB-INF/views/order_history.jsp").forward(request, response);
    }
}
