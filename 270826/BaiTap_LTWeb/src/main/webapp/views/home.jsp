<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
    <style>
        :root {
            --bg-color: #fcfbf9; --card-bg: #ffffff; --text-main: #4a4a4a; --text-muted: #888888;
            --accent-blue: #bde0fe; --accent-hover: #a2c8ea; --border: #f0ebe1;
        }
        body { font-family: 'Segoe UI', sans-serif; background: var(--bg-color); color: var(--text-main); display: flex; justify-content: center; padding: 4rem 1rem; margin: 0; }
        .container { background: var(--card-bg); padding: 3rem; border-radius: 16px; box-shadow: 0 10px 30px rgba(0,0,0,0.03); width: 100%; max-width: 600px; text-align: center; }
        h2 { margin-top: 0; margin-bottom: 1rem; font-weight: 600; color: #333; }
        p { color: var(--text-muted); margin-bottom: 2rem; }
        .btn { text-decoration: none; padding: 0.9rem 1.5rem; border-radius: 8px; font-size: 0.95rem; font-weight: 600; transition: 0.2s; display: inline-block; border: none; margin: 0.5rem; }
        .btn-primary { background: var(--accent-blue); color: #333; }
        .btn-primary:hover { background: var(--accent-hover); }
        .btn-outline { background: transparent; border: 1px solid var(--border); color: var(--text-main); }
        .btn-outline:hover { background: #f9f9f9; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Xin chào, ${sessionScope.account.fullName}!</h2>
        
        <c:choose>
            <c:when test="${sessionScope.account.roleid == 1}">
                <p>Quyền truy cập: Quản trị viên (Admin)</p>
                <a href="${pageContext.request.contextPath}/admin/category/list" class="btn btn-primary">Quản lý Category</a>
            </c:when>
            <c:otherwise>
                <p>Quyền truy cập: Người dùng tiêu chuẩn</p>
                <a href="#" class="btn btn-primary">Khám phá sản phẩm</a>
            </c:otherwise>
        </c:choose>
        
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-outline">Đăng xuất</a>
    </div>
</body>
</html>