package vn.iot.ltweb_midterm.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users")
public class User_24110064 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(columnDefinition = "nvarchar(50)")
    private String fullname;

    private Integer phone;

    @Column(nullable = false, length = 32)
    private String passwd;

    @Temporal(TemporalType.TIMESTAMP)
    private Date signup_date;

    @Temporal(TemporalType.TIMESTAMP)
    private Date last_login;

    private Boolean is_admin;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }
    public Integer getPhone() { return phone; }
    public void setPhone(Integer phone) { this.phone = phone; }
    public String getPasswd() { return passwd; }
    public void setPasswd(String passwd) { this.passwd = passwd; }
    public Date getSignup_date() { return signup_date; }
    public void setSignup_date(Date signup_date) { this.signup_date = signup_date; }
    public Date getLast_login() { return last_login; }
    public void setLast_login(Date last_login) { this.last_login = last_login; }
    public Boolean getIs_admin() { return is_admin; }
    public void setIs_admin(Boolean is_admin) { this.is_admin = is_admin; }
}
