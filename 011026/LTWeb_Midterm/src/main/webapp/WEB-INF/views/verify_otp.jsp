<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Xác thực OTP</title>
</head>
<body>
    <div class="max-w-md mx-auto mt-10 shadcn-card p-6">
        <h2 class="text-2xl font-bold mb-6 text-center">Xác thực OTP</h2>
        <c:if test="${not empty message}">
            <div class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded mb-4">
                ${message}
            </div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4">
                ${error}
            </div>
        </c:if>
        <form action="${pageContext.request.contextPath}/register" method="post" class="space-y-4">
            <input type="hidden" name="action" value="verify_otp">
            <div>
                <label class="block text-sm font-medium mb-1">Mã OTP</label>
                <input type="text" name="otp" required class="shadcn-input text-center text-lg tracking-widest" maxlength="6">
            </div>
            <button type="submit" class="shadcn-btn shadcn-btn-primary w-full mt-4">Xác thực</button>
        </form>
    </div>
</body>
</html>
