<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý Danh mục</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-4">
        <!-- Menu điều hướng tạm thời -->
        <nav class="mb-4">
            <a href="${pageContext.request.contextPath}/admin/categories" class="btn btn-primary">Quản lý Category</a>
            <a href="${pageContext.request.contextPath}/admin/users" class="btn btn-secondary">Quản lý User</a>
        </nav>

        <div class="card shadow-sm">
            <div class="card-header bg-white d-flex justify-content-between align-items-center">
                <h4 class="mb-0">Danh sách Category</h4>
                <a href="${pageContext.request.contextPath}/admin/categories/add" class="btn btn-success">Thêm mới</a>
            </div>
            <div class="card-body">
                <!-- Thông báo -->
                <c:if test="${not empty successMessage}">
                    <div class="alert alert-success">${successMessage}</div>
                </c:if>
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger">${errorMessage}</div>
                </c:if>

                <!-- Tìm kiếm -->
                <form action="${pageContext.request.contextPath}/admin/categories" method="get" class="d-flex mb-3">
                    <input type="text" name="keyword" value="${keyword}" class="form-control me-2" placeholder="Nhập tên danh mục để tìm...">
                    <button type="submit" class="btn btn-outline-primary">Tìm kiếm</button>
                    <a href="${pageContext.request.contextPath}/admin/categories" class="btn btn-outline-secondary ms-2">Xóa lọc</a>
                </form>

                <!-- Bảng dữ liệu -->
                <table class="table table-bordered table-hover">
                    <thead class="table-light">
                        <tr>
                            <th>ID</th>
                            <th>Tên danh mục</th>
                            <th class="text-center" style="width: 200px;">Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${categories}" var="cat">
                            <tr>
                                <td>${cat.id}</td>
                                <td>${cat.name}</td>
                                <td class="text-center">
                                    <a href="${pageContext.request.contextPath}/admin/categories/edit/${cat.id}" class="btn btn-sm btn-warning">Sửa</a>
                                    <a href="${pageContext.request.contextPath}/admin/categories/delete/${cat.id}" class="btn btn-sm btn-danger" onclick="return confirm('Bạn có chắc muốn xóa danh mục này?');">Xóa</a>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty categories}">
                            <tr>
                                <td colspan="3" class="text-center text-muted">Không tìm thấy dữ liệu.</td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>