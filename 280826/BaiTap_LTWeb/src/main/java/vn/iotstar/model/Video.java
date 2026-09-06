package vn.iotstar.model;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "Video")
public class Video implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "video_id", length = 50)
    private String videoId;

    @Column(name = "title", columnDefinition = "nvarchar(255) NOT NULL")
    private String title;

    @Column(name = "poster", columnDefinition = "varchar(255)")
    private String poster;

    @Column(name = "description", columnDefinition = "nvarchar(MAX)")
    private String description;

    @Column(name = "views")
    private int views;

    @Column(name = "active")
    private boolean active;

    // Quan hệ N - 1 với Category
    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category category;

    public Video() {}

    // Getters and Setters
    public String getVideoId() { return videoId; }
    public void setVideoId(String videoId) { this.videoId = videoId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getPoster() { return poster; }
    public void setPoster(String poster) { this.poster = poster; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getViews() { return views; }
    public void setViews(int views) { this.views = views; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}