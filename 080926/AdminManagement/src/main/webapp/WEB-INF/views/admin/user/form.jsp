<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${pageTitle}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card shadow-sm">
                    <div class="card-header bg-white">
                        <h4 class="mb-0">${pageTitle}</h4>
                    </div>
                    <div class="card-body">
                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-danger">${errorMessage}</div>
                        </c:if>

                        <form action="${pageContext.request.contextPath}/admin/users/save" method="post">
                            <input type="hidden" name="id" value="${user.id}">
                            
                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label class="form-label">Tên đăng nhập <span class="text-danger">*</span></label>
                                    <input type="text" name="username" value="${user.username}" class="form-control" required ${not empty user.id ? 'readonly' : ''}>
                                    <small class="text-muted">Không thể đổi tên đăng nhập sau khi tạo.</small>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label">Mật khẩu <span class="text-danger">*</span></label>
                                    <input type="password" name="password" value="${user.password}" class="form-control" required>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label class="form-label">Họ và tên</label>
                                    <input type="text" name="fullname" value="${user.fullname}" class="form-control">
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label">Số điện thoại</label>
                                    <input type="text" name="phone" value="${user.phone}" class="form-control">
                                </div>
                            </div>

                            <div class="mb-4">
                                <label class="form-label">Vai trò <span class="text-danger">*</span></label>
                                <select name="role" class="form-select" required>
                                    <option value="USER" ${user.role == 'USER' ? 'selected' : ''}>Người dùng (USER)</option>
                                    <option value="ADMIN" ${user.role == 'ADMIN' ? 'selected' : ''}>Quản trị viên (ADMIN)</option>
                                </select>
                            </div>
                            
                            <button type="submit" class="btn btn-primary">Lưu tài khoản</button>
                            <a href="${pageContext.request.contextPath}/admin/users" class="btn btn-secondary">Hủy bỏ</a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>