<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý Sản phẩm</title>
    <style>
        :root {
            --bg-color: #f8f9fa; --card-bg: #ffffff; --text-main: #333333; --text-muted: #999999;
            --accent-blue: #dbeafe; --accent-text: #1e40af; --border-color: #f1f3f5; --danger-text: #ff8787;
        }
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: var(--bg-color); color: var(--text-main); margin: 0; padding: 3rem 1rem; display: flex; justify-content: center; }
        .container { background: var(--card-bg); padding: 3rem; border-radius: 16px; box-shadow: 0 10px 30px rgba(0,0,0,0.02); width: 100%; max-width: 1000px; }
        
        .back-link { display: inline-block; color: var(--text-muted); text-decoration: none; font-size: 0.9rem; margin-bottom: 1.5rem; transition: 0.2s; }
        .back-link:hover { color: var(--text-main); }
        
        /* CSS cho Admin Tabs */
        .admin-tabs { display: flex; gap: 0.5rem; margin-bottom: 2rem; background: var(--bg-color); padding: 0.4rem; border-radius: 10px; width: fit-content; }
        .tab-link { text-decoration: none; color: var(--text-muted); font-weight: 500; font-size: 0.9rem; padding: 0.6rem 1.2rem; border-radius: 8px; transition: 0.2s; }
        .tab-link:hover { color: var(--text-main); }
        .tab-link.active { background: var(--card-bg); color: var(--accent-text); box-shadow: 0 2px 5px rgba(0,0,0,0.02); font-weight: 600; }

        .header-row { display: flex; justify-content: space-between; align-items: center; margin-bottom: 2rem; }
        h2 { margin: 0; font-size: 1.6rem; font-weight: 500; color: #222; }
        .header-actions { display: flex; gap: 0.8rem; }
        
        .search-input { padding: 0.6rem 1rem; border: 1px solid var(--border-color); border-radius: 8px; outline: none; font-size: 0.9rem; width: 220px; color: var(--text-main); }
        .search-input::placeholder { color: #bbb; }
        .btn { padding: 0.6rem 1.2rem; border-radius: 8px; font-weight: 500; font-size: 0.9rem; text-decoration: none; background: var(--accent-blue); color: var(--accent-text); border: none; cursor: pointer; transition: 0.2s; display: inline-block; }
        .btn:hover { filter: brightness(0.95); }
        
        table { width: 100%; border-collapse: collapse; }
        th, td { padding: 1.2rem 0; text-align: left; border-bottom: 1px solid var(--border-color); }
        th { font-size: 0.8rem; font-weight: 600; color: var(--text-muted); text-transform: uppercase; letter-spacing: 1px; }
        td { font-size: 0.95rem; font-weight: 500; color: #444; vertical-align: middle; }
        th:first-child, td:first-child { width: 120px; }
        
        .img-placeholder { width: 60px; height: 60px; background: #f1f3f5; border-radius: 8px; display: flex; align-items: center; justify-content: center; color: #adb5bd; font-size: 0.75rem; text-transform: uppercase; font-weight: 600; }
        .preview-img { width: 60px; height: 60px; object-fit: cover; border-radius: 8px; }
        
        .action-link { text-decoration: none; color: var(--text-muted); font-size: 0.9rem; margin-right: 0.8rem; transition: 0.2s; }
        .action-link:hover { color: var(--text-main); }
        .action-delete { color: var(--danger-text); }
        .action-delete:hover { filter: brightness(0.8); }
        
        .pagination { margin-top: 2.5rem; display: flex; justify-content: center; gap: 0.5rem; }
        .page-link { padding: 0.5rem 1rem; border-radius: 8px; font-size: 0.9rem; color: var(--text-muted); text-decoration: none; border: 1px solid transparent; transition: 0.2s; }
        .page-link:hover { border-color: var(--border-color); color: var(--text-main); }
        .page-link.active { background: var(--accent-blue); color: var(--accent-text); font-weight: 600; }
    </style>
</head>
<body>
    <div class="container">
        <a href="${pageContext.request.contextPath}/home" class="back-link">← Về trang chủ</a>
        
        <!-- Tabs Điều hướng -->
        <div class="admin-tabs">
            <a href="${pageContext.request.contextPath}/admin/category/list" class="tab-link">Danh mục</a>
            <a href="${pageContext.request.contextPath}/admin/product/list" class="tab-link active">Sản phẩm</a>
        </div>
        
        <div class="header-row">
            <h2>Danh sách sản phẩm</h2>
            <div class="header-actions">
                <form action="${pageContext.request.contextPath}/admin/product/list" method="get" style="display:flex; gap:0.5rem;">
                    <input type="text" name="search" placeholder="Tìm kiếm..." class="search-input">
                    <button type="submit" class="btn">Tìm</button>
                </form>
                <a href="${pageContext.request.contextPath}/admin/product/add" class="btn">+ Thêm mới</a>
            </div>
        </div>
        
        <table>
            <thead>
                <tr>
                    <th>Hình ảnh</th>
                    <th>ID</th>
                    <th>Tên Sản Phẩm</th>
                    <th>Giá</th>
                    <th>Danh mục</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${proList}" var="pro">
                    <tr>
                        <td>
                            <c:choose>
                                <c:when test="${not empty pro.imageUrl}">
                                    <img src="${pageContext.request.contextPath}/${pro.imageUrl}" class="preview-img" alt="img">
                                </c:when>
                                <c:otherwise>
                                    <div class="img-placeholder">No IMG</div>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${pro.productId}</td>
                        <td>${pro.productName}</td>
                        <td>${pro.price}</td>
                        <td>${pro.category.name}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/product/edit?id=${pro.productId}" class="action-link">Sửa</a>
                            <a href="${pageContext.request.contextPath}/admin/product/delete?id=${pro.productId}" class="action-link action-delete" onclick="return confirm('Bạn có chắc muốn xóa?');">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <div class="pagination">
            <c:forEach begin="1" end="${totalPages}" var="i">
                <a href="${pageContext.request.contextPath}/admin/product/list?page=${i}" class="page-link ${currentPage == i ? 'active' : ''}">${i}</a>
            </c:forEach>
        </div>
    </div>
</body>
</html>