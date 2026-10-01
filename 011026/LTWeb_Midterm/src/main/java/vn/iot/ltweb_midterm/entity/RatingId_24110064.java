package vn.iot.ltweb_midterm.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RatingId_24110064 implements Serializable {

    private Integer userid;
    private Integer bookid;

    public RatingId_24110064() {}

    public RatingId_24110064(Integer userid, Integer bookid) {
        this.userid = userid;
        this.bookid = bookid;
    }

    public Integer getUserid() { return userid; }
    public void setUserid(Integer userid) { this.userid = userid; }
    public Integer getBookid() { return bookid; }
    public void setBookid(Integer bookid) { this.bookid = bookid; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatingId_24110064 that = (RatingId_24110064) o;
        return Objects.equals(userid, that.userid) &&
               Objects.equals(bookid, that.bookid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, bookid);
    }
}
