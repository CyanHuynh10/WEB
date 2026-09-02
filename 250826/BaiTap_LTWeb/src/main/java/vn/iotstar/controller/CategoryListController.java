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
        List<Category> list;
        if (keyword != null && !keyword.trim().isEmpty()) {
            list = cateService.search(keyword);
        } else {
            list = cateService.getAll();
            keyword = "";
        }
        req.setAttribute("cateList", list);
        req.setAttribute("keyword", keyword);
        req.getRequestDispatcher("/views/admin/list-category.jsp").forward(req, resp);
    }
}