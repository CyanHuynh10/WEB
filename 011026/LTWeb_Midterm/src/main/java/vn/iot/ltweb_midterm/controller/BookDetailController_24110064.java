package vn.iot.ltweb_midterm.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iot.ltweb_midterm.entity.Book_24110064;
import vn.iot.ltweb_midterm.entity.Rating_24110064;
import vn.iot.ltweb_midterm.entity.User_24110064;
import vn.iot.ltweb_midterm.service.BookService_24110064;
import vn.iot.ltweb_midterm.service.RatingService_24110064;

import java.io.IOException;
import java.util.List;

@WebServlet("/book/detail")
public class BookDetailController_24110064 extends HttpServlet {
    private BookService_24110064 bookService = new BookService_24110064();
    private RatingService_24110064 ratingService = new RatingService_24110064();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }

        Integer bookId = Integer.parseInt(idParam);
        Book_24110064 book = bookService.getBookById(bookId);
        
        if (book == null) {
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }

        List<Rating_24110064> reviews = ratingService.getRatingsByBook(bookId);

        req.setAttribute("book", book);
        req.setAttribute("reviews", reviews);

        req.getRequestDispatcher("/WEB-INF/views/book_detail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User_24110064 user = (User_24110064) session.getAttribute("user");
        
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String bookIdParam = req.getParameter("bookId");
        String ratingParam = req.getParameter("rating");
        String reviewText = req.getParameter("review_text");

        if (bookIdParam != null && ratingParam != null) {
            Integer bookId = Integer.parseInt(bookIdParam);
            Integer rating = Integer.parseInt(ratingParam);
            
            Book_24110064 book = bookService.getBookById(bookId);
            if (book != null) {
                ratingService.addRating(user, book, rating, reviewText);
            }
            resp.sendRedirect(req.getContextPath() + "/book/detail?id=" + bookId);
        } else {
            resp.sendRedirect(req.getContextPath() + "/home");
        }
    }
}
