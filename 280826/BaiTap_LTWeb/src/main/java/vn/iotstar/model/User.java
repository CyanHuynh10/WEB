package vn.iotstar.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String email;
    private String userName;
    private String fullName;
    private String password;
    private String avatar;
    private int roleid;
    private String phone;
    private Date createdDate;
    
    // Thuộc tính OTP
    private boolean status;
    private String otpCode;
    private Timestamp otpExpiry;

    public User() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }
    public int getRoleid() { return roleid; }
    public void setRoleid(int roleid) { this.roleid = roleid; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }
    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
    public String getOtpCode() { return otpCode; }
    public void setOtpCode(String otpCode) { this.otpCode = otpCode; }
    public Timestamp getOtpExpiry() { return otpExpiry; }
    public void setOtpExpiry(Timestamp otpExpiry) { this.otpExpiry = otpExpiry; }
}