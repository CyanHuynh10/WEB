package vn.iotstar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import vn.iotstar.model.Category;
import vn.iotstar.service.CategoryService;
import vn.iotstar.service.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = {"/admin/category/list"})
public class CategoryListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        
        // Thiết lập phân trang
        int page = 1;
        int pageSize = 3; // Số lượng item trên 1 trang (để số nhỏ để dễ test)
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }

        List<Category> list;
        int totalCategories = 0;

        // Nếu có từ khóa tìm kiếm thì hiển thị tất cả kết quả tìm kiếm (hoặc bạn có thể phân trang cả tìm kiếm)
        if (keyword != null && !keyword.trim().isEmpty()) {
            list = cateService.searchByName(keyword);
            totalCategories = list.size();
        } else {
            // Nếu không tìm kiếm thì hiển thị danh sách có phân trang
            list = cateService.findAll(page, pageSize);
            totalCategories = cateService.count();
            keyword = "";
        }

        // Tính tổng số trang
        int totalPages = (int) Math.ceil((double) totalCategories / pageSize);

        req.setAttribute("cateList", list);
        req.setAttribute("keyword", keyword);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);
        
        req.getRequestDispatcher("/views/admin/list-category.jsp").forward(req, resp);
    }
}