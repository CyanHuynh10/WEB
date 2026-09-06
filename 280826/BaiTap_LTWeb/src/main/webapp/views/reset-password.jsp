<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đặt lại mật khẩu</title>
    <style>
        :root {
            --bg-color: #fcfbf9; --card-bg: #ffffff; --text-main: #4a4a4a; --text-muted: #888888;
            --accent-blue: #bde0fe; --accent-hover: #a2c8ea; --border: #f0ebe1;
        }
        body { font-family: 'Segoe UI', sans-serif; background: var(--bg-color); color: var(--text-main); display: flex; justify-content: center; align-items: center; min-height: 100vh; margin: 0; }
        .container { background: var(--card-bg); padding: 2.8rem 2.5rem; border-radius: 16px; box-shadow: 0 10px 30px rgba(0,0,0,0.03); width: 100%; max-width: 380px; text-align: center; }
        h2 { margin: 0 0 0.5rem 0; font-weight: 600; color: #333; }
        p { font-size: 0.85rem; color: var(--text-muted); margin-bottom: 1.2rem; }
        .form-group { margin-bottom: 1rem; text-align: left; }
        label { display: block; margin-bottom: 0.35rem; font-size: 0.85rem; font-weight: 500; }
        input[type="text"], input[type="password"] { width: 100%; padding: 0.8rem; border: 1px solid var(--border); border-radius: 8px; box-sizing: border-box; outline: none; font-size: 0.95rem; }
        .otp-box { text-align: center; font-size: 1.3rem; letter-spacing: 4px; font-weight: bold; }
        .btn { width: 100%; padding: 0.85rem; border-radius: 8px; font-size: 0.95rem; font-weight: 600; cursor: pointer; border: none; background: var(--accent-blue); color: #333; transition: 0.2s; margin-top: 0.5rem; }
        .btn:hover { background: var(--accent-hover); }
        .alert { color: #d63031; background: #ffeaa7; padding: 10px; border-radius: 8px; font-size: 0.85rem; margin-bottom: 1.2rem; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Đặt lại mật khẩu</h2>
        <p>Mã đã gửi đến: <strong>${email}</strong></p>
        <c:if test="${alert != null}"><div class="alert">${alert}</div></c:if>

        <form action="${pageContext.request.contextPath}/reset-password" method="post">
            <input type="hidden" name="email" value="${email}">
            <div class="form-group">
                <label>Mã OTP (6 số)</label>
                <input type="text" name="otp" class="otp-box" maxlength="6" placeholder="------" required>
            </div>
            <div class="form-group">
                <label>Mật khẩu mới</label>
                <input type="password" name="newPassword" placeholder="Nhập mật khẩu mới" required>
            </div>
            <button type="submit" class="btn">Lưu mật khẩu mới</button>
        </form>
    </div>
</body>
</html>