package vn.iot.ltweb_midterm.service;

import vn.iot.ltweb_midterm.entity.Book_24110064;
import vn.iot.ltweb_midterm.entity.RatingId_24110064;
import vn.iot.ltweb_midterm.entity.Rating_24110064;
import vn.iot.ltweb_midterm.entity.User_24110064;
import vn.iot.ltweb_midterm.repository.RatingRepository_24110064;

import java.util.List;

public class RatingService_24110064 {
    private RatingRepository_24110064 ratingRepo = new RatingRepository_24110064();

    public List<Rating_24110064> getRatingsByBook(Integer bookId) {
        return ratingRepo.findByBookId(bookId);
    }

    public void addRating(User_24110064 user, Book_24110064 book, Integer score, String text) {
        Rating_24110064 rating = new Rating_24110064();
        rating.setId(new RatingId_24110064(user.getId(), book.getBookid()));
        rating.setUser(user);
        rating.setBook(book);
        rating.setRating(score);
        rating.setReview_text(text);
        
        ratingRepo.save(rating);
    }
}
