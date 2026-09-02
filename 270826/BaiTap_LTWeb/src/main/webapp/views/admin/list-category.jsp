<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý danh mục</title>
    <style>
        :root {
            --bg-color: #fcfbf9; --card-bg: #ffffff; --text-main: #4a4a4a; --text-muted: #888888;
            --accent-blue: #bde0fe; --accent-hover: #a2c8ea; --danger: #ffadad; --border: #f0ebe1;
        }
        body { font-family: 'Segoe UI', sans-serif; background: var(--bg-color); color: var(--text-main); display: flex; justify-content: center; padding: 3rem 1rem; margin: 0; }
        .container { background: var(--card-bg); padding: 2.5rem; border-radius: 16px; box-shadow: 0 10px 30px rgba(0,0,0,0.03); width: 100%; max-width: 900px; }
        .header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 2rem; flex-wrap: wrap; gap: 15px; }
        h2 { margin: 0; font-weight: 600; color: #333; }
        .tools { display: flex; gap: 10px; align-items: center; }
        .search-box { padding: 0.6rem 1rem; border: 1px solid var(--border); border-radius: 8px; outline: none; transition: 0.2s; }
        .search-box:focus { border-color: var(--accent-hover); }
        table { width: 100%; border-collapse: collapse; }
        th, td { padding: 1.2rem 1rem; text-align: left; border-bottom: 1px solid var(--border); vertical-align: middle; }
        th { color: var(--text-muted); font-size: 0.85rem; text-transform: uppercase; letter-spacing: 1px; font-weight: 600; border-bottom: 2px solid var(--border); }
        .btn { text-decoration: none; padding: 0.6rem 1.2rem; border-radius: 8px; font-size: 0.9rem; font-weight: 500; transition: 0.2s; color: #333; border: none; display: inline-block; cursor: pointer; }
        .btn-primary { background: var(--accent-blue); }
        .btn-primary:hover { background: var(--accent-hover); }
        .action-links a { color: var(--text-muted); margin-right: 10px; text-decoration: none; font-weight: 500; transition: 0.2s; }
        .action-links a:hover { color: var(--text-main); }
        .icon-img { width: 50px; height: 50px; object-fit: cover; border-radius: 8px; background: #f0f0f0; }
        .back-link { display: inline-block; margin-bottom: 1rem; text-decoration: none; color: #888; font-size: 0.9rem; }
        
        /* CSS Phân trang */
        .pagination { display: flex; justify-content: center; gap: 8px; margin-top: 2rem; }
        .page-link { text-decoration: none; padding: 0.5rem 1rem; border-radius: 8px; background: #fff; border: 1px solid var(--border); color: var(--text-main); transition: 0.2s; font-weight: 500; }
        .page-link:hover { background: #f9f9f9; border-color: #dcdcdc; }
        .page-link.active { background: var(--accent-blue); border-color: var(--accent-blue); color: #333; font-weight: bold; pointer-events: none; }
    </style>
</head>
<body>
    <div class="container">
        <a href="${pageContext.request.contextPath}/home" class="back-link">← Về trang chủ</a>
        <div class="header">
            <h2>Danh mục sản phẩm</h2>
            <div class="tools">
                <form action="${pageContext.request.contextPath}/admin/category/list" method="get" style="margin: 0; display: flex; gap: 5px;">
                    <input type="text" name="keyword" class="search-box" placeholder="Tìm kiếm..." value="${keyword}">
                    <button type="submit" class="btn btn-primary" style="padding: 0.6rem 1rem;">Tìm</button>
                </form>
                <a href="${pageContext.request.contextPath}/admin/category/add" class="btn btn-primary">+ Thêm mới</a>
            </div>
        </div>
        <table>
            <thead>
                <tr>
                    <th>Hình ảnh</th>
                    <th>ID</th>
                    <th>Tên danh mục</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${cateList}" var="cate">
                    <tr>
                        <td>
                            <c:if test="${not empty cate.icon}">
                                <img src="${pageContext.request.contextPath}/uploads/${cate.icon}" class="icon-img" alt="icon">
                            </c:if>
                            <c:if test="${empty cate.icon}">
                                <div class="icon-img" style="display:flex; align-items:center; justify-content:center; color:#ccc; font-size:12px;">No IMG</div>
                            </c:if>
                        </td>
                        <td>${cate.id}</td>
                        <td><strong>${cate.name}</strong></td>
                        <td class="action-links">
                            <a href="${pageContext.request.contextPath}/admin/category/edit?id=${cate.id}">Sửa</a>
                            <a href="${pageContext.request.contextPath}/admin/category/delete?id=${cate.id}" style="color: var(--danger);">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Khối Phân Trang -->
        <c:if test="${totalPages > 1}">
            <div class="pagination">
                <c:forEach begin="1" end="${totalPages}" var="i">
                    <a href="${pageContext.request.contextPath}/admin/category/list?page=${i}&keyword=${keyword}" 
                       class="page-link ${currentPage == i ? 'active' : ''}">${i}</a>
                </c:forEach>
            </div>
        </c:if>
    </div>
</body>
</html>