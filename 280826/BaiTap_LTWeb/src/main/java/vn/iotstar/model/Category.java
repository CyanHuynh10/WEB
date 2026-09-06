package vn.iotstar.model;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "Category")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cate_id")
    private int id;

    @Column(name = "cate_name", columnDefinition = "nvarchar(255) NOT NULL")
    private String name;

    @Column(name = "icons", columnDefinition = "nvarchar(255)")
    private String icon;

    // Quan hệ 1 - N với Video
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Video> videos;

    public Category() {}

    public Category(int id, String name, String icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
    public List<Video> getVideos() { return videos; }
    public void setVideos(List<Video> videos) { this.videos = videos; }
}