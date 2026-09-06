package vn.iotstar.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import vn.iotstar.service.ProductService;
import vn.iotstar.service.impl.ProductServiceImpl;

@WebServlet(urlPatterns = {"/admin/product/delete"})
public class ProductDeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            productService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/admin/product/list");
    }
}