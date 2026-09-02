<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cập nhật danh mục</title>
    <style>
        :root {
            --bg-color: #fcfbf9; --card-bg: #ffffff; --text-main: #4a4a4a; 
            --accent-blue: #bde0fe; --accent-hover: #a2c8ea; --border: #e6e2d8;
        }
        body { font-family: 'Segoe UI', sans-serif; background: var(--bg-color); color: var(--text-main); display: flex; justify-content: center; padding: 4rem 1rem; }
        .container { background: var(--card-bg); padding: 3rem; border-radius: 16px; box-shadow: 0 10px 30px rgba(0,0,0,0.03); width: 100%; max-width: 450px; }
        h2 { margin-top: 0; margin-bottom: 2rem; font-weight: 600; text-align: center; }
        .form-group { margin-bottom: 1.5rem; }
        label { display: block; margin-bottom: 0.5rem; font-size: 0.9rem; font-weight: 500; }
        input[type="text"], input[type="file"] { width: 100%; padding: 0.8rem; border: 1px solid var(--border); border-radius: 8px; box-sizing: border-box; outline: none; transition: 0.2s; font-family: inherit; }
        input[type="text"]:focus { border-color: var(--accent-hover); }
        .btn { width: 100%; padding: 0.9rem; border-radius: 8px; font-size: 1rem; font-weight: 600; cursor: pointer; border: none; background: var(--accent-blue); color: #333; transition: 0.2s; margin-top: 1rem; }
        .btn:hover { background: var(--accent-hover); }
        .back-link { display: block; text-align: center; margin-top: 1.5rem; text-decoration: none; color: #888; font-size: 0.9rem; }
        .current-img { margin-top: 10px; width: 80px; height: 80px; object-fit: cover; border-radius: 8px; border: 1px solid var(--border); }
    </style>
</head>
<body>
    <div class="container">
        <h2>Cập nhật danh mục</h2>
        <form action="${pageContext.request.contextPath}/admin/category/edit" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${category.id}">
            <div class="form-group">
                <label>Tên danh mục</label>
                <input type="text" name="name" value="${category.name}" required>
            </div>
            <div class="form-group">
                <label>Tải lên Icon mới (để trống nếu giữ nguyên)</label>
                <input type="file" name="icon" accept="image/*">
                <c:if test="${not empty category.icon}">
                    <p style="font-size: 0.85rem; margin-bottom: 5px; color: #888;">Ảnh hiện tại:</p>
                    <img src="${pageContext.request.contextPath}/uploads/${category.icon}" class="current-img" alt="Current icon">
                </c:if>
            </div>
            <button type="submit" class="btn">Lưu thay đổi</button>
        </form>
        <a href="${pageContext.request.contextPath}/admin/category/list" class="back-link">← Quay lại danh sách</a>
    </div>
</body>
</html>