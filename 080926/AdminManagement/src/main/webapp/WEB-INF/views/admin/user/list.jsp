<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý Tài khoản</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-4">
        <!-- Menu điều hướng tạm thời -->
        <nav class="mb-4">
            <a href="${pageContext.request.contextPath}/admin/categories" class="btn btn-secondary">Quản lý Category</a>
            <a href="${pageContext.request.contextPath}/admin/users" class="btn btn-primary">Quản lý User</a>
        </nav>

        <div class="card shadow-sm">
            <div class="card-header bg-white d-flex justify-content-between align-items-center">
                <h4 class="mb-0">Danh sách Tài khoản</h4>
                <a href="${pageContext.request.contextPath}/admin/users/add" class="btn btn-success">Thêm mới</a>
            </div>
            <div class="card-body">
                <c:if test="${not empty successMessage}">
                    <div class="alert alert-success">${successMessage}</div>
                </c:if>
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger">${errorMessage}</div>
                </c:if>

                <!-- Tìm kiếm -->
                <form action="${pageContext.request.contextPath}/admin/users" method="get" class="d-flex mb-3">
                    <input type="text" name="keyword" value="${keyword}" class="form-control me-2" placeholder="Tìm theo tên đăng nhập hoặc họ tên...">
                    <button type="submit" class="btn btn-outline-primary">Tìm kiếm</button>
                    <a href="${pageContext.request.contextPath}/admin/users" class="btn btn-outline-secondary ms-2">Xóa lọc</a>
                </form>

                <!-- Bảng dữ liệu -->
                <table class="table table-bordered table-hover">
                    <thead class="table-light">
                        <tr>
                            <th>ID</th>
                            <th>Username</th>
                            <th>Họ và tên</th>
                            <th>Số điện thoại</th>
                            <th>Vai trò</th>
                            <th class="text-center">Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${users}" var="u">
                            <tr>
                                <td>${u.id}</td>
                                <td><span class="badge bg-secondary">${u.username}</span></td>
                                <td>${u.fullname}</td>
                                <td>${u.phone}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${u.role == 'ADMIN'}"><span class="badge bg-danger">ADMIN</span></c:when>
                                        <c:otherwise><span class="badge bg-info">USER</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td class="text-center">
                                    <a href="${pageContext.request.contextPath}/admin/users/edit/${u.id}" class="btn btn-sm btn-warning">Sửa</a>
                                    <a href="${pageContext.request.contextPath}/admin/users/delete/${u.id}" class="btn btn-sm btn-danger" onclick="return confirm('Bạn có chắc muốn xóa tài khoản này?');">Xóa</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>