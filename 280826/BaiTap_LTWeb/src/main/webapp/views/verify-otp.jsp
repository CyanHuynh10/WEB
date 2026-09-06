<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Xác thực mã OTP</title>
    <style>
        :root {
            --bg-color: #fcfbf9; --card-bg: #ffffff; --text-main: #4a4a4a; --text-muted: #888888;
            --accent-blue: #bde0fe; --accent-hover: #a2c8ea; --border: #f0ebe1;
        }
        body { font-family: 'Segoe UI', sans-serif; background: var(--bg-color); color: var(--text-main); display: flex; justify-content: center; align-items: center; min-height: 100vh; margin: 0; }
        .container { background: var(--card-bg); padding: 2.8rem 2.5rem; border-radius: 16px; box-shadow: 0 10px 30px rgba(0,0,0,0.03); width: 100%; max-width: 380px; text-align: center; }
        h2 { margin: 0 0 0.5rem 0; font-weight: 600; color: #333; }
        p { font-size: 0.85rem; color: var(--text-muted); margin-bottom: 1.5rem; line-height: 1.4; }
        .otp-input { width: 100%; padding: 0.9rem; font-size: 1.5rem; letter-spacing: 6px; text-align: center; border: 1px solid var(--border); border-radius: 8px; outline: none; margin-bottom: 1.2rem; box-sizing: border-box; font-weight: bold; }
        .otp-input:focus { border-color: var(--accent-hover); }
        .btn { width: 100%; padding: 0.85rem; border-radius: 8px; font-size: 0.95rem; font-weight: 600; cursor: pointer; border: none; background: var(--accent-blue); color: #333; transition: 0.2s; }
        .btn:hover { background: var(--accent-hover); }
        .alert { color: #d63031; background: #ffeaa7; padding: 10px; border-radius: 8px; font-size: 0.85rem; margin-bottom: 1.2rem; }
        .footer-link { margin-top: 1.5rem; font-size: 0.85rem; color: var(--text-muted); }
        .footer-link a { color: #333; font-weight: 600; text-decoration: none; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Xác thực OTP</h2>
        <p>Mã kích hoạt đã được gửi đến hộp thư:<br><strong>${email}</strong></p>
        <c:if test="${alert != null}"><div class="alert">${alert}</div></c:if>

        <form action="${pageContext.request.contextPath}/verify-otp" method="post">
            <input type="hidden" name="email" value="${email}">
            <input type="text" name="otp" class="otp-input" maxlength="6" placeholder="------" required autofocus>
            <button type="submit" class="btn">Xác nhận kích hoạt</button>
        </form>
        <div class="footer-link">
            <a href="${pageContext.request.contextPath}/login">← Quay lại Đăng nhập</a>
        </div>
    </div>
</body>
</html>