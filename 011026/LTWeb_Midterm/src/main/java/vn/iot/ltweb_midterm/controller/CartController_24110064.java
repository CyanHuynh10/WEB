package vn.iot.ltweb_midterm.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iot.ltweb_midterm.model.Cart_24110064;
import vn.iot.ltweb_midterm.service.CartService_24110064;

import java.io.IOException;

@WebServlet(urlPatterns = {"/cart", "/cart/add", "/cart/update", "/cart/remove"})
public class CartController_24110064 extends HttpServlet {

    private final CartService_24110064 cartService = new CartService_24110064();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        
        if ("/cart/remove".equals(path)) {
            removeCartItem(request, response);
            return;
        }

        request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        
        if ("/cart/add".equals(path)) {
            addToCart(request, response);
        } else if ("/cart/update".equals(path)) {
            updateCart(request, response);
        }
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart_24110064 cart = (Cart_24110064) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart_24110064();
            session.setAttribute("cart", cart);
        }

        try {
            Integer bookId = Integer.parseInt(request.getParameter("bookId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            
            cartService.addToCart(cart, bookId, quantity);
            session.setAttribute("successMessage", "Đã thêm sản phẩm vào giỏ hàng.");
        } catch (Exception e) {
            session.setAttribute("errorMessage", e.getMessage());
        }
        
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer != null ? referer : request.getContextPath() + "/cart");
    }

    private void updateCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart_24110064 cart = (Cart_24110064) session.getAttribute("cart");
        if (cart != null) {
            try {
                Integer bookId = Integer.parseInt(request.getParameter("bookId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                
                cartService.updateCart(cart, bookId, quantity);
                session.setAttribute("successMessage", "Đã cập nhật số lượng.");
            } catch (Exception e) {
                session.setAttribute("errorMessage", e.getMessage());
            }
        }
        response.sendRedirect(request.getContextPath() + "/cart");
    }

    private void removeCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart_24110064 cart = (Cart_24110064) session.getAttribute("cart");
        if (cart != null) {
            try {
                Integer bookId = Integer.parseInt(request.getParameter("bookId"));
                cartService.removeFromCart(cart, bookId);
                session.setAttribute("successMessage", "Đã xóa sản phẩm khỏi giỏ hàng.");
            } catch (Exception e) {
                session.setAttribute("errorMessage", "Lỗi khi xóa sản phẩm.");
            }
        }
        response.sendRedirect(request.getContextPath() + "/cart");
    }
}
