<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Đăng nhập</title>
</head>
<body>
    <div class="max-w-md mx-auto mt-10 shadcn-card p-6">
        <h2 class="text-2xl font-bold mb-6 text-center">Đăng nhập</h2>
        <c:if test="${not empty error}">
            <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4">
                ${error}
            </div>
        </c:if>
        <c:if test="${not empty message}">
            <div class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded mb-4">
                ${message}
            </div>
        </c:if>
        <form action="${pageContext.request.contextPath}/login" method="post" class="space-y-4">
            <div>
                <label class="block text-sm font-medium mb-1">Email</label>
                <input type="email" name="email" required class="shadcn-input">
            </div>
            <div>
                <label class="block text-sm font-medium mb-1">Mật khẩu</label>
                <input type="password" name="password" required class="shadcn-input">
            </div>
            <button type="submit" class="shadcn-btn shadcn-btn-primary w-full mt-4">Đăng nhập</button>
        </form>
        <div class="mt-4 text-center text-sm">
            Chưa có tài khoản? <a href="${pageContext.request.contextPath}/register" class="text-blue-600 hover:underline">Đăng ký ngay</a>
        </div>
    </div>
</body>
</html>
