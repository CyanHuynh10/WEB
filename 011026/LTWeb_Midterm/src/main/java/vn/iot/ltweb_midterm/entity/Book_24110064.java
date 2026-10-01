package vn.iot.ltweb_midterm.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book_24110064 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookid;

    private Integer isbn;

    @Column(length = 200)
    private String title;

    @Column(length = 100)
    private String publisher;

    @Column(columnDefinition = "decimal(6,2)")
    private Double price;

    @Column(columnDefinition = "text")
    private String description;

    @Temporal(TemporalType.DATE)
    private Date publish_date;

    @Column(length = 100)
    private String cover_image;

    private Integer quantity;

    @ManyToMany
    @JoinTable(
        name = "book_author",
        joinColumns = @JoinColumn(name = "bookid"),
        inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author_24110064> authors;

    public Integer getBookid() { return bookid; }
    public void setBookid(Integer bookid) { this.bookid = bookid; }
    public Integer getIsbn() { return isbn; }
    public void setIsbn(Integer isbn) { this.isbn = isbn; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Date getPublish_date() { return publish_date; }
    public void setPublish_date(Date publish_date) { this.publish_date = publish_date; }
    public String getCover_image() { return cover_image; }
    public void setCover_image(String cover_image) { this.cover_image = cover_image; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public Set<Author_24110064> getAuthors() { return authors; }
    public void setAuthors(Set<Author_24110064> authors) { this.authors = authors; }
}
