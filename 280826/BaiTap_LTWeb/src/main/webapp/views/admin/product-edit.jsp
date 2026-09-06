<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sửa Sản phẩm</title>
    <style>
        :root {
            --bg-color: #f8f9fa; --card-bg: #ffffff; --text-main: #333333; --text-muted: #999999;
            --accent-blue: #dbeafe; --accent-text: #1e40af; --border-color: #e5e7eb;
        }
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: var(--bg-color); color: var(--text-main); margin: 0; padding: 3rem 1rem; display: flex; justify-content: center; }
        .container { background: var(--card-bg); padding: 3rem; border-radius: 16px; box-shadow: 0 10px 30px rgba(0,0,0,0.02); width: 100%; max-width: 550px; }
        
        .header-row { margin-bottom: 2rem; text-align: center; }
        h2 { margin: 0; font-size: 1.6rem; font-weight: 500; color: #222; }
        
        .form-group { margin-bottom: 1.5rem; }
        label { display: block; margin-bottom: 0.5rem; font-weight: 500; font-size: 0.9rem; color: #444; }
        input[type="text"], input[type="number"], select, textarea, input[type="file"] { 
            width: 100%; padding: 0.8rem; border: 1px solid var(--border-color); border-radius: 8px; 
            box-sizing: border-box; outline: none; font-family: inherit; font-size: 0.95rem; color: var(--text-main); transition: 0.2s;
        }
        input:focus, select:focus, textarea:focus { border-color: #93c5fd; }
        
        .current-img { display: block; margin-top: 10px; border-radius: 8px; width: 80px; height: 80px; object-fit: cover; border: 1px solid var(--border-color); }
        .btn { width: 100%; padding: 0.9rem; border-radius: 8px; font-weight: 600; font-size: 1rem; cursor: pointer; border: none; background: var(--accent-blue); color: var(--accent-text); margin-top: 1rem; transition: 0.2s; }
        .btn:hover { filter: brightness(0.95); }
        .back-link { display: block; text-align: center; margin-top: 1.5rem; color: var(--text-muted); text-decoration: none; font-size: 0.9rem; transition: 0.2s; }
        .back-link:hover { color: var(--text-main); }
    </style>
</head>
<body>
    <div class="container">
        <div class="header-row">
            <h2>Cập Nhật Sản Phẩm</h2>
        </div>
        <form action="${pageContext.request.contextPath}/admin/product/edit" method="post" enctype="multipart/form-data">
            <input type="hidden" name="productId" value="${product.productId}">
            
            <div class="form-group">
                <label>Tên sản phẩm</label>
                <input type="text" name="productName" value="${product.productName}" required>
            </div>
            <div class="form-group">
                <label>Danh mục</label>
                <select name="categoryId" required>
                    <c:forEach items="${cateList}" var="cate">
                        <option value="${cate.id}" <c:if test="${cate.id == product.category.id}">selected</c:if>>
                            ${cate.id} - ${cate.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label>Giá (VNĐ)</label>
                <input type="number" name="price" value="${product.price}" min="0" required>
            </div>
            <div class="form-group">
                <label>Hình ảnh mới (Bỏ trống nếu giữ nguyên)</label>
                <input type="file" name="image" accept="image/*">
                <c:if test="${not empty product.imageUrl}">
                    <img src="${pageContext.request.contextPath}/${product.imageUrl}" class="current-img" alt="Ảnh hiện tại">
                </c:if>
            </div>
            <div class="form-group">
                <label>Mô tả chi tiết</label>
                <textarea name="description" rows="4">${product.description}</textarea>
            </div>
            <button type="submit" class="btn">Lưu Thay Đổi</button>
        </form>
        <a href="${pageContext.request.contextPath}/admin/product/list" class="back-link">← Quay lại danh sách</a>
    </div>
</body>
</html>