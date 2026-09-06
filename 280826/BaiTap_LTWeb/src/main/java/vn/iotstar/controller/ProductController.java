package vn.iotstar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import vn.iotstar.model.Product;
import vn.iotstar.service.ProductService;
import vn.iotstar.service.impl.ProductServiceImpl;

@WebServlet(urlPatterns = {"/product"})
public class ProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        
        if ("detail".equals(action)) {
            // Xem chi tiết 1 sản phẩm
            int id = Integer.parseInt(req.getParameter("id"));
            Product product = productService.findById(id);
            req.setAttribute("product", product);
            req.getRequestDispatcher("/views/product-detail.jsp").forward(req, resp);
        } else {
            // Hiển thị danh sách phân trang (6 sp / trang)
            int page = 1;
            int pageSize = 6;
            if (req.getParameter("page") != null) {
                page = Integer.parseInt(req.getParameter("page"));
            }
            
            int totalProducts = productService.count();
            int totalPages = (int) Math.ceil((double) totalProducts / pageSize);
            List<Product> proList = productService.findAll(page, pageSize);

            req.setAttribute("proList", proList);
            req.setAttribute("currentPage", page);
            req.setAttribute("totalPages", totalPages);
            req.getRequestDispatcher("/views/product.jsp").forward(req, resp);
        }
    }
}