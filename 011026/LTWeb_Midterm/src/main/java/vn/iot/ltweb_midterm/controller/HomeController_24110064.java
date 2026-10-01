package vn.iot.ltweb_midterm.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iot.ltweb_midterm.entity.Book_24110064;
import vn.iot.ltweb_midterm.service.BookService_24110064;
import vn.iot.ltweb_midterm.service.RatingService_24110064;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@WebServlet({"/home", ""})
public class HomeController_24110064 extends HttpServlet {
    private BookService_24110064 bookService = new BookService_24110064();
    private RatingService_24110064 ratingService = new RatingService_24110064();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int size = 6;
        
        String pageParam = req.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                // ignore
            }
        }

        List<Book_24110064> books = bookService.getBooks(page, size);
        int totalPages = bookService.getTotalPages(size);

        Map<Integer, Integer> reviewCounts = new HashMap<>();
        for (Book_24110064 b : books) {
            int count = ratingService.getRatingsByBook(b.getBookid()).size();
            reviewCounts.put(b.getBookid(), count);
        }

        req.setAttribute("books", books);
        req.setAttribute("reviewCounts", reviewCounts);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);

        req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
    }
}
