package vn.iot.ltweb_midterm.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iot.ltweb_midterm.entity.Author_24110064;
import vn.iot.ltweb_midterm.entity.Book_24110064;
import vn.iot.ltweb_midterm.service.AuthorService_24110064;
import vn.iot.ltweb_midterm.service.BookService_24110064;

import java.io.IOException;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@WebServlet(urlPatterns = {"/admin/books", "/admin/books/add", "/admin/books/edit", "/admin/books/delete"})
public class AdminBookController_24110064 extends HttpServlet {
    private BookService_24110064 bookService = new BookService_24110064();
    private AuthorService_24110064 authorService = new AuthorService_24110064();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        
        if (path.equals("/admin/books")) {
            int page = 1;
            int size = 10;
            String pageParam = req.getParameter("page");
            if (pageParam != null) {
                try { page = Integer.parseInt(pageParam); } catch (Exception e) {}
            }
            req.setAttribute("books", bookService.getBooks(page, size));
            req.setAttribute("currentPage", page);
            req.setAttribute("totalPages", bookService.getTotalPages(size));
            req.getRequestDispatcher("/WEB-INF/views/admin/book_list.jsp").forward(req, resp);
        } else if (path.equals("/admin/books/add")) {
            req.setAttribute("authorsList", authorService.getAllAuthors());
            req.getRequestDispatcher("/WEB-INF/views/admin/book_form.jsp").forward(req, resp);
        } else if (path.equals("/admin/books/edit")) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("book", bookService.getBookById(id));
            req.setAttribute("authorsList", authorService.getAllAuthors());
            req.getRequestDispatcher("/WEB-INF/views/admin/book_form.jsp").forward(req, resp);
        } else if (path.equals("/admin/books/delete")) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            bookService.delete(id);
            resp.sendRedirect(req.getContextPath() + "/admin/books");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("bookid");
        Book_24110064 book = new Book_24110064();
        
        if (idStr != null && !idStr.isEmpty()) {
            book = bookService.getBookById(Integer.parseInt(idStr));
        }
        
        book.setIsbn(Integer.parseInt(req.getParameter("isbn")));
        book.setTitle(req.getParameter("title"));
        book.setPublisher(req.getParameter("publisher"));
        book.setPrice(Double.parseDouble(req.getParameter("price")));
        book.setDescription(req.getParameter("description"));
        
        String dateStr = req.getParameter("publish_date");
        if (dateStr != null && !dateStr.isEmpty()) {
            book.setPublish_date(Date.valueOf(dateStr));
        }
        
        book.setCover_image(req.getParameter("cover_image"));
        book.setQuantity(Integer.parseInt(req.getParameter("quantity")));
        
        String[] authorIds = req.getParameterValues("author_ids");
        Set<Author_24110064> authors = new HashSet<>();
        if (authorIds != null) {
            for (String aId : authorIds) {
                Author_24110064 author = authorService.getAuthorById(Integer.parseInt(aId));
                if (author != null) {
                    authors.add(author);
                }
            }
        }
        book.setAuthors(authors);
        
        bookService.save(book);
        
        resp.sendRedirect(req.getContextPath() + "/admin/books");
    }
}
