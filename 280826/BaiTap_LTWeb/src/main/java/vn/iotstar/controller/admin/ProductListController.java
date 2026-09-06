package vn.iotstar.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import vn.iotstar.model.Product;
import vn.iotstar.service.ProductService;
import vn.iotstar.service.impl.ProductServiceImpl;

@WebServlet(urlPatterns = {"/admin/product/list"})
public class ProductListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Cấu hình phân trang
        int page = 1;
        int pageSize = 6;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }

        // Tính toán tổng số trang
        int totalProducts = productService.count();
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        // Lấy dữ liệu
        List<Product> proList = productService.findAll(page, pageSize);

        req.setAttribute("proList", proList);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);
        
        req.getRequestDispatcher("/views/admin/product-list.jsp").forward(req, resp);
    }
}