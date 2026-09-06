<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tất cả sản phẩm</title>
    <style>
        :root { --bg-color: #fcfbf9; --card-bg: #ffffff; --text-main: #4a4a4a; --accent-blue: #bde0fe; --nav-bg: #ffffff; }
        body { font-family: 'Segoe UI', sans-serif; background: var(--bg-color); color: var(--text-main); margin: 0; }
        /* Dùng chung CSS Navbar như trang chủ */
        .navbar { background: var(--nav-bg); padding: 1rem 2rem; display: flex; justify-content: space-between; align-items: center; box-shadow: 0 2px 10px rgba(0,0,0,0.02); }
        .nav-brand { font-size: 1.5rem; font-weight: bold; color: var(--text-main); text-decoration: none; }
        .nav-links a { margin-left: 1.5rem; text-decoration: none; color: var(--text-main); font-weight: 500; }
        .nav-links .btn-logout { background: #ffadad; padding: 0.5rem 1rem; border-radius: 8px; color: #882222; }
        .nav-links .btn-admin { background: var(--accent-blue); padding: 0.5rem 1rem; border-radius: 8px; color: #333; }
        
        .container { padding: 2rem; max-width: 1200px; margin: 0 auto; }
        .grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(220px, 1fr)); gap: 1.5rem; }
        .card { background: var(--card-bg); padding: 1rem; border-radius: 12px; box-shadow: 0 4px 15px rgba(0,0,0,0.03); text-align: center; }
        .card img { width: 100%; height: 180px; object-fit: cover; border-radius: 8px; }
        .card h3 { font-size: 1.1rem; margin: 0.8rem 0 0.4rem; color: #333; }
        .card p { color: #888; font-weight: bold; margin-bottom: 1rem; }
        .btn-view { padding: 0.6rem 1.2rem; border-radius: 8px; background: var(--accent-blue); color: #333; text-decoration: none; font-size: 0.9rem; font-weight: 600; display: inline-block; }
        
        .pagination { margin-top: 3rem; display: flex; justify-content: center; gap: 0.5rem; }
        .page-link { padding: 0.6rem 1.2rem; border-radius: 8px; background: var(--card-bg); color: var(--text-main); text-decoration: none; border: 1px solid #f0ebe1; transition: 0.2s; }
        .page-link.active { background: var(--accent-blue); font-weight: bold; border-color: var(--accent-blue); }
    </style>
</head>
<body>
    <nav class="navbar">
        <a href="${pageContext.request.contextPath}/home" class="nav-brand">Shop Pastel</a>
        <div class="nav-links">
            <a href="${pageContext.request.contextPath}/product">Cửa hàng</a>
            <c:choose>
                <c:when test="${not empty sessionScope.account}">
                    <c:if test="${sessionScope.account.roleid == 1}">
                        <a href="${pageContext.request.contextPath}/admin/category/list" class="btn-admin">Trang Quản Trị</a>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/logout" class="btn-logout">Đăng xuất</a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/login" class="btn-admin">Đăng nhập</a>
                </c:otherwise>
            </c:choose>
        </div>
    </nav>

    <div class="container">
        <h2 style="margin-bottom: 2rem;">Tất cả sản phẩm</h2>
        
        <c:if test="${empty proList}">
            <p style="text-align: center; color: #888; padding: 2rem;">Cửa hàng hiện tại chưa có sản phẩm nào.</p>
        </c:if>

        <div class="grid">
            <c:forEach items="${proList}" var="p">
                <div class="card">
                    <img src="${pageContext.request.contextPath}/${p.imageUrl}" alt="img">
                    <h3>${p.productName}</h3>
                    <p>${p.price} VNĐ</p>
                    <a href="${pageContext.request.contextPath}/product?action=detail&id=${p.productId}" class="btn-view">Xem chi tiết</a>
                </div>
            </c:forEach>
        </div>

        <div class="pagination">
            <c:forEach begin="1" end="${totalPages}" var="i">
                <a href="${pageContext.request.contextPath}/product?page=${i}" class="page-link ${currentPage == i ? 'active' : ''}">${i}</a>
            </c:forEach>
        </div>
    </div>
</body>
</html>