package vn.iot.ltweb_midterm.service;

import vn.iot.ltweb_midterm.entity.Book_24110064;
import vn.iot.ltweb_midterm.repository.BookRepository_24110064;

import java.util.List;

public class BookService_24110064 {
    private BookRepository_24110064 bookRepo = new BookRepository_24110064();

    public List<Book_24110064> getBooks(int page, int size) {
        int offset = (page - 1) * size;
        return bookRepo.findAll(offset, size);
    }

    public int getTotalPages(int size) {
        long total = bookRepo.countAll();
        return (int) Math.ceil((double) total / size);
    }

    public Book_24110064 getBookById(Integer id) {
        return bookRepo.findById(id);
    }

    public void save(Book_24110064 book) {
        bookRepo.save(book);
    }

    public void delete(Integer id) {
        bookRepo.delete(id);
    }
}
