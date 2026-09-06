package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import vn.iotstar.connection.DBConnection;
import vn.iotstar.dao.UserDao;
import vn.iotstar.model.User;

public class UserDaoImpl implements UserDao {
    @Override
    public User get(String username) {
        String sql = "SELECT * FROM [User] WHERE username = ?";
        return queryUser(sql, username);
    }

    @Override
    public User getByEmail(String email) {
        String sql = "SELECT * FROM [User] WHERE email = ?";
        return queryUser(sql, email);
    }

    private User queryUser(String sql, String param) {
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, param);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setUserName(rs.getString("username"));
                    user.setFullName(rs.getString("fullname"));
                    user.setPassword(rs.getString("password"));
                    user.setRoleid(rs.getInt("roleid"));
                    user.setStatus(rs.getBoolean("status"));
                    user.setOtpCode(rs.getString("otp_code"));
                    user.setOtpExpiry(rs.getTimestamp("otp_expiry"));
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(User user) {
        String sql = "INSERT INTO [User](email, username, fullname, password, roleid, status, otp_code, otp_expiry) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getRoleid());
            ps.setBoolean(6, user.isStatus());
            ps.setString(7, user.getOtpCode());
            ps.setTimestamp(8, user.getOtpExpiry());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE [User] SET password=?, status=?, otp_code=?, otp_expiry=? WHERE email=?";
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getPassword());
            ps.setBoolean(2, user.isStatus());
            ps.setString(3, user.getOtpCode());
            ps.setTimestamp(4, user.getOtpExpiry());
            ps.setString(5, user.getEmail());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkExistEmail(String email) {
        return getByEmail(email) != null;
    }

    @Override
    public boolean checkExistUsername(String username) {
        return get(username) != null;
    }
}