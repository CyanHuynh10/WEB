package vn.iot.ltweb_midterm.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iot.ltweb_midterm.entity.User_24110064;

import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*"})
public class AdminFilter_24110064 implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        HttpSession session = req.getSession(false);
        if (session != null) {
            User_24110064 user = (User_24110064) session.getAttribute("user");
            if (user != null && Boolean.TRUE.equals(user.getIs_admin())) {
                chain.doFilter(request, response);
                return;
            }
        }
        
        res.sendRedirect(req.getContextPath() + "/login");
    }
}
