<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
    <style>
        :root {
            --bg-color: #fcfbf9; --card-bg: #ffffff; --text-main: #4a4a4a; --text-muted: #888888;
            --accent-blue: #bde0fe; --accent-hover: #a2c8ea; --danger: #ffadad; --border: #f0ebe1;
        }
        body { font-family: 'Segoe UI', sans-serif; background: var(--bg-color); color: var(--text-main); display: flex; justify-content: center; align-items: center; min-height: 100vh; margin: 0; }
        .container { background: var(--card-bg); padding: 2.8rem 2.5rem; border-radius: 16px; box-shadow: 0 10px 30px rgba(0,0,0,0.03); width: 100%; max-width: 380px; text-align: center; }
        h2 { margin: 0 0 1.8rem 0; font-weight: 600; color: #333; }
        .form-group { margin-bottom: 1.2rem; text-align: left; }
        label { display: block; margin-bottom: 0.4rem; font-size: 0.85rem; font-weight: 500; }
        input[type="text"], input[type="password"] { width: 100%; padding: 0.8rem; border: 1px solid var(--border); border-radius: 8px; box-sizing: border-box; outline: none; transition: 0.2s; font-size: 0.95rem; }
        input[type="text"]:focus, input[type="password"]:focus { border-color: var(--accent-hover); }
        .row-options { display: flex; justify-content: space-between; align-items: center; margin-bottom: 1.5rem; font-size: 0.85rem; }
        .row-options label { margin: 0; cursor: pointer; color: var(--text-muted); }
        .row-options a { color: var(--text-muted); text-decoration: none; transition: 0.2s; }
        .row-options a:hover { color: var(--text-main); }
        .btn { width: 100%; padding: 0.85rem; border-radius: 8px; font-size: 0.95rem; font-weight: 600; cursor: pointer; border: none; background: var(--accent-blue); color: #333; transition: 0.2s; }
        .btn:hover { background: var(--accent-hover); }
        .alert { color: #d63031; background: #ffeaa7; padding: 10px; border-radius: 8px; font-size: 0.85rem; margin-bottom: 1.2rem; }
        .alert-success { color: #27ae60; background: #e8f8f5; }
        .footer-link { margin-top: 1.5rem; font-size: 0.85rem; color: var(--text-muted); }
        .footer-link a { color: #333; font-weight: 600; text-decoration: none; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Đăng nhập</h2>
        <c:if test="${alert != null}"><div class="alert">${alert}</div></c:if>
        <c:if test="${alertSuccess != null}"><div class="alert alert-success">${alertSuccess}</div></c:if>
        
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-group">
                <label>Tài khoản</label>
                <input type="text" name="username" placeholder="Nhập tên tài khoản" required>
            </div>
            <div class="form-group">
                <label>Mật khẩu</label>
                <input type="password" name="password" placeholder="Nhập mật khẩu" required>
            </div>
            <div class="row-options">
                <label><input type="checkbox" name="remember"> Nhớ mật khẩu</label>
                <a href="${pageContext.request.contextPath}/forgot-password">Quên mật khẩu?</a>
            </div>
            <button type="submit" class="btn">Đăng nhập</button>
        </form>
        <div class="footer-link">
            Chưa có tài khoản? <a href="${pageContext.request.contextPath}/register">Đăng ký ngay</a>
        </div>
    </div>
</body>
</html>