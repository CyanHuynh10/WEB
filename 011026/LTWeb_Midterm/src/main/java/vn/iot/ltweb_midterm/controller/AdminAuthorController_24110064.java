package vn.iot.ltweb_midterm.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iot.ltweb_midterm.entity.Author_24110064;
import vn.iot.ltweb_midterm.service.AuthorService_24110064;

import java.io.IOException;
import java.sql.Date;

@WebServlet(urlPatterns = {"/admin/authors", "/admin/authors/add", "/admin/authors/edit", "/admin/authors/delete"})
public class AdminAuthorController_24110064 extends HttpServlet {
    private AuthorService_24110064 authorService = new AuthorService_24110064();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        
        if (path.equals("/admin/authors")) {
            int page = 1;
            int size = 10;
            String pageParam = req.getParameter("page");
            if (pageParam != null) {
                try { page = Integer.parseInt(pageParam); } catch (Exception e) {}
            }
            req.setAttribute("authors", authorService.getAuthors(page, size));
            req.setAttribute("currentPage", page);
            req.setAttribute("totalPages", authorService.getTotalPages(size));
            req.getRequestDispatcher("/WEB-INF/views/admin/author_list.jsp").forward(req, resp);
        } else if (path.equals("/admin/authors/add")) {
            req.getRequestDispatcher("/WEB-INF/views/admin/author_form.jsp").forward(req, resp);
        } else if (path.equals("/admin/authors/edit")) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("author", authorService.getAuthorById(id));
            req.getRequestDispatcher("/WEB-INF/views/admin/author_form.jsp").forward(req, resp);
        } else if (path.equals("/admin/authors/delete")) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            authorService.delete(id);
            resp.sendRedirect(req.getContextPath() + "/admin/authors");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("author_id");
        Author_24110064 author = new Author_24110064();
        
        if (idStr != null && !idStr.isEmpty()) {
            author = authorService.getAuthorById(Integer.parseInt(idStr));
        }
        
        author.setAuthor_name(req.getParameter("author_name"));
        
        String dateStr = req.getParameter("date_of_birth");
        if (dateStr != null && !dateStr.isEmpty()) {
            author.setDate_of_birth(Date.valueOf(dateStr));
        }
        
        authorService.save(author);
        
        resp.sendRedirect(req.getContextPath() + "/admin/authors");
    }
}
