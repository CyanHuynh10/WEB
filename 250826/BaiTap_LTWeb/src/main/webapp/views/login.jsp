<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập hệ thống</title>
    <style>
        :root {
            --bg-color: #fcfbf9; --card-bg: #ffffff; --text-main: #4a4a4a; --text-muted: #888888;
            --accent-blue: #bde0fe; --accent-hover: #a2c8ea; --danger: #ffadad; --border: #f0ebe1;
        }
        body { font-family: 'Segoe UI', sans-serif; background: var(--bg-color); color: var(--text-main); display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
        .container { background: var(--card-bg); padding: 3rem; border-radius: 16px; box-shadow: 0 10px 30px rgba(0,0,0,0.03); width: 100%; max-width: 400px; text-align: center; }
        h2 { margin-top: 0; margin-bottom: 2rem; font-weight: 600; color: #333; }
        .form-group { margin-bottom: 1.5rem; text-align: left; }
        label { display: block; margin-bottom: 0.5rem; font-size: 0.9rem; font-weight: 500; }
        input[type="text"], input[type="password"] { width: 100%; padding: 0.9rem; border: 1px solid var(--border); border-radius: 8px; box-sizing: border-box; outline: none; transition: 0.2s; }
        input[type="text"]:focus, input[type="password"]:focus { border-color: var(--accent-hover); }
        .checkbox-group { text-align: left; margin-bottom: 1.5rem; font-size: 0.9rem; color: var(--text-muted); display: flex; align-items: center; gap: 8px; }
        .btn { width: 100%; padding: 0.9rem; border-radius: 8px; font-size: 1rem; font-weight: 600; cursor: pointer; border: none; background: var(--accent-blue); color: #333; transition: 0.2s; }
        .btn:hover { background: var(--accent-hover); }
        .alert { color: #d63031; background: #ffeaa7; padding: 10px; border-radius: 8px; font-size: 0.9rem; margin-bottom: 1.5rem; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Đăng nhập</h2>
        <c:if test="${alert != null}">
            <div class="alert">${alert}</div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-group">
                <label>Tài khoản</label>
                <input type="text" name="username" placeholder="Nhập tên đăng nhập">
            </div>
            <div class="form-group">
                <label>Mật khẩu</label>
                <input type="password" name="password" placeholder="Nhập mật khẩu">
            </div>
            <div class="checkbox-group">
                <input type="checkbox" name="remember" id="remember">
                <label for="remember" style="margin: 0;">Ghi nhớ đăng nhập</label>
            </div>
            <button type="submit" class="btn">Đăng nhập</button>
        </form>
    </div>
</body>
</html>