package vn.iot.ltweb_midterm.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "author")
public class Author_24110064 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer author_id;

    @Column(length = 100)
    private String author_name;

    @Temporal(TemporalType.DATE)
    private Date date_of_birth;

    @ManyToMany(mappedBy = "authors")
    private Set<Book_24110064> books;

    public Integer getAuthor_id() { return author_id; }
    public void setAuthor_id(Integer author_id) { this.author_id = author_id; }
    public String getAuthor_name() { return author_name; }
    public void setAuthor_name(String author_name) { this.author_name = author_name; }
    public Date getDate_of_birth() { return date_of_birth; }
    public void setDate_of_birth(Date date_of_birth) { this.date_of_birth = date_of_birth; }
    public Set<Book_24110064> getBooks() { return books; }
    public void setBooks(Set<Book_24110064> books) { this.books = books; }
}
