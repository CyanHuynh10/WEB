package vn.iot.ltweb_midterm.service;

import vn.iot.ltweb_midterm.entity.Author_24110064;
import vn.iot.ltweb_midterm.repository.AuthorRepository_24110064;

import java.util.List;

public class AuthorService_24110064 {
    private AuthorRepository_24110064 authorRepo = new AuthorRepository_24110064();

    public List<Author_24110064> getAuthors(int page, int size) {
        int offset = (page - 1) * size;
        return authorRepo.findAll(offset, size);
    }
    
    public List<Author_24110064> getAllAuthors() {
        return authorRepo.findAll();
    }

    public int getTotalPages(int size) {
        long total = authorRepo.countAll();
        return (int) Math.ceil((double) total / size);
    }

    public Author_24110064 getAuthorById(Integer id) {
        return authorRepo.findById(id);
    }

    public void save(Author_24110064 author) {
        authorRepo.save(author);
    }

    public void delete(Integer id) {
        authorRepo.delete(id);
    }
}
