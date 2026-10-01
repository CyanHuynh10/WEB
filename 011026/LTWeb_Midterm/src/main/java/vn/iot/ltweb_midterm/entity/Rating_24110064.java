package vn.iot.ltweb_midterm.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "rating")
public class Rating_24110064 implements Serializable {

    @EmbeddedId
    private RatingId_24110064 id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userid")
    @JoinColumn(name = "userid")
    private User_24110064 user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bookid")
    @JoinColumn(name = "bookid")
    private Book_24110064 book;

    @Column(columnDefinition = "tinyint")
    private Integer rating;

    @Column(columnDefinition = "text")
    private String review_text;

    public RatingId_24110064 getId() { return id; }
    public void setId(RatingId_24110064 id) { this.id = id; }
    public User_24110064 getUser() { return user; }
    public void setUser(User_24110064 user) { this.user = user; }
    public Book_24110064 getBook() { return book; }
    public void setBook(Book_24110064 book) { this.book = book; }
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    public String getReview_text() { return review_text; }
    public void setReview_text(String review_text) { this.review_text = review_text; }
}
