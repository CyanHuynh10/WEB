<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký tài khoản</title>
    <style>
        :root {
            --bg-color: #fcfbf9; --card-bg: #ffffff; --text-main: #4a4a4a; --text-muted: #888888;
            --accent-blue: #bde0fe; --accent-hover: #a2c8ea; --border: #f0ebe1;
        }
        body { font-family: 'Segoe UI', sans-serif; background: var(--bg-color); color: var(--text-main); display: flex; justify-content: center; align-items: center; min-height: 100vh; margin: 0; }
        .container { background: var(--card-bg); padding: 2.8rem 2.5rem; border-radius: 16px; box-shadow: 0 10px 30px rgba(0,0,0,0.03); width: 100%; max-width: 380px; text-align: center; }
        h2 { margin: 0 0 1.5rem 0; font-weight: 600; color: #333; }
        .form-group { margin-bottom: 1rem; text-align: left; }
        label { display: block; margin-bottom: 0.35rem; font-size: 0.85rem; font-weight: 500; }
        input[type="text"], input[type="email"], input[type="password"] { width: 100%; padding: 0.8rem; border: 1px solid var(--border); border-radius: 8px; box-sizing: border-box; outline: none; transition: 0.2s; font-size: 0.95rem; }
        input:focus { border-color: var(--accent-hover); }
        .btn { width: 100%; padding: 0.85rem; border-radius: 8px; font-size: 0.95rem; font-weight: 600; cursor: pointer; border: none; background: var(--accent-blue); color: #333; transition: 0.2s; margin-top: 0.5rem; }
        .btn:hover { background: var(--accent-hover); }
        .alert { color: #d63031; background: #ffeaa7; padding: 10px; border-radius: 8px; font-size: 0.85rem; margin-bottom: 1.2rem; }
        .footer-link { margin-top: 1.5rem; font-size: 0.85rem; color: var(--text-muted); }
        .footer-link a { color: #333; font-weight: 600; text-decoration: none; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Đăng ký</h2>
        <c:if test="${alert != null}"><div class="alert">${alert}</div></c:if>

        <form action="${pageContext.request.contextPath}/register" method="post">
            <div class="form-group">
                <label>Tên đăng nhập</label>
                <input type="text" name="username" placeholder="Nhập username" required>
            </div>
            <div class="form-group">
                <label>Email xác thực</label>
                <input type="email" name="email" placeholder="example@gmail.com" required>
            </div>
            <div class="form-group">
                <label>Họ và tên</label>
                <input type="text" name="fullname" placeholder="Nguyễn Văn A" required>
            </div>
            <div class="form-group">
                <label>Mật khẩu</label>
                <input type="password" name="password" placeholder="Tối thiểu 6 ký tự" required>
            </div>
            <button type="submit" class="btn">Đăng ký & Nhận OTP</button>
        </form>
        <div class="footer-link">
            Đã có tài khoản? <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
        </div>
    </div>
</body>
</html>