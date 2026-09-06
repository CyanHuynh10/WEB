<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm danh mục</title>
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
    </style>
</head>
<body>
    <div class="container">
        <h2>Thêm danh mục mới</h2>
        <form action="${pageContext.request.contextPath}/admin/category/add" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label>Tên danh mục</label>
                <input type="text" name="name" placeholder="Ví dụ: Laptop, Điện thoại..." required>
            </div>
            <div class="form-group">
                <label>Tải lên Icon/Hình ảnh</label>
                <input type="file" name="icon" accept="image/*">
            </div>
            <button type="submit" class="btn">Lưu danh mục</button>
        </form>
        <a href="${pageContext.request.contextPath}/admin/category/list" class="back-link">← Quay lại danh sách</a>
    </div>
</body>
</html>