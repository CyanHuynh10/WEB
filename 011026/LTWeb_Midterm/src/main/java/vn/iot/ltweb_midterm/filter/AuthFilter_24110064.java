package vn.iot.ltweb_midterm.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iot.ltweb_midterm.entity.User_24110064;

import java.io.IOException;

@WebFilter(urlPatterns = {"/book/detail", "/checkout", "/orders", "/order/*"})
public class AuthFilter_24110064 implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        String path = req.getServletPath();
        
        boolean isProtectedGet = path.startsWith("/checkout") || path.startsWith("/order");
        boolean isProtectedPost = req.getMethod().equalsIgnoreCase("POST") && (path.equals("/book/detail") || isProtectedGet);

        if (isProtectedGet || isProtectedPost) {
            HttpSession session = req.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                session = req.getSession(true);
                session.setAttribute("errorMessage", "Vui lòng đăng nhập để tiếp tục.");
                res.sendRedirect(req.getContextPath() + "/login");
                return;
            }
        }
        
        chain.doFilter(request, response);
    }
}
