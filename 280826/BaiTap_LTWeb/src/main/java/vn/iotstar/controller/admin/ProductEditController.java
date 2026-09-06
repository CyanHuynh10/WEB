package vn.iotstar.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import vn.iotstar.model.Category;
import vn.iotstar.model.Product;
import vn.iotstar.service.CategoryService;
import vn.iotstar.service.ProductService;
import vn.iotstar.service.impl.CategoryServiceImpl;
import vn.iotstar.service.impl.ProductServiceImpl;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = {"/admin/product/edit"})
public class ProductEditController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService = new ProductServiceImpl();
    private CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findById(id);
        List<Category> cateList = categoryService.findAll();
        
        req.setAttribute("product", product);
        req.setAttribute("cateList", cateList);
        req.getRequestDispatcher("/views/admin/product-edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        int productId = Integer.parseInt(req.getParameter("productId"));
        String productName = req.getParameter("productName");
        String description = req.getParameter("description");
        double price = Double.parseDouble(req.getParameter("price"));
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        
        Product product = productService.findById(productId);
        product.setProductName(productName);
        product.setDescription(description);
        product.setPrice(price);

        Category category = new Category();
        category.setId(categoryId);
        product.setCategory(category);

        // Xử lý Upload Ảnh mới (nếu có)
        Part part = req.getPart("image");
        if (part != null && part.getSize() > 0) {
            String uploadPath = getServletContext().getRealPath("/uploads");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            
            String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            part.write(uploadPath + File.separator + fileName);
            product.setImageUrl("uploads/" + fileName);
        }

        productService.update(product);
        resp.sendRedirect(req.getContextPath() + "/admin/product/list");
    }
}