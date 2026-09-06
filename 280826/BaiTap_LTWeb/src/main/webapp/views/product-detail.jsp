<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <style>
        :root { --bg-color: #fcfbf9; --card-bg: #ffffff; --text-main: #4a4a4a; --accent-blue: #bde0fe; }
        body { font-family: 'Segoe UI', sans-serif; background: var(--bg-color); color: var(--text-main); padding: 4rem 2rem; margin: 0; display: flex; justify-content: center; }
        .detail-card { display: flex; gap: 2rem; background: var(--card-bg); padding: 2rem; border-radius: 16px; box-shadow: 0 10px 30px rgba(0,0,0,0.05); max-width: 800px; width: 100%; }
        .detail-card img { width: 300px; height: 300px; object-fit: cover; border-radius: 12px; }
        .info h2 { margin-top: 0; font-size: 2rem; color: #333; }
        .price { font-size: 1.5rem; color: #e74c3c; font-weight: bold; }
        .category { font-size: 0.9rem; color: #888; text-transform: uppercase; letter-spacing: 1px; }
    </style>
</head>
<body>
    <div class="detail-card">
        <img src="${pageContext.request.contextPath}/${product.imageUrl}" alt="${product.productName}">
        <div class="info">
            <div class="category">Danh mục: ${product.category.categoryname}</div>
            <h2>${product.productName}</h2>
            <p class="price">${product.price} VNĐ</p>
            <p style="line-height: 1.6; color: #666;">${product.description}</p>
            <a href="${pageContext.request.contextPath}/product" style="display:inline-block; margin-top:1rem; color:#888; text-decoration:none;">← Quay lại cửa hàng</a>
        </div>
    </div>
</body>
</html>