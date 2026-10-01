<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><sitemesh:write property='title' /> - Nhà Sách</title>
    <!-- Tailwind CSS for utility classes -->
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        body {
            background-color: #f1f5f9;
            font-family: ui-sans-serif, system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
        }
        .glass {
            background: rgba(255, 255, 255, 0.7);
            backdrop-filter: blur(10px);
            -webkit-backdrop-filter: blur(10px);
            border: 1px solid rgba(255, 255, 255, 0.3);
            box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06);
        }
        .shadcn-btn {
            display: inline-flex; align-items: center; justify-content: center; border-radius: 0.375rem;
            font-size: 0.875rem; font-weight: 500; height: 2.5rem; padding: 0 1rem;
            transition: background-color 0.2s, color 0.2s;
        }
        .shadcn-btn-primary {
            background-color: #0f172a; color: white;
        }
        .shadcn-btn-primary:hover {
            background-color: #334155;
        }
        .shadcn-btn-outline {
            border: 1px solid #e2e8f0; background-color: transparent; color: #0f172a;
        }
        .shadcn-btn-outline:hover {
            background-color: #f1f5f9;
        }
        .shadcn-input {
            display: flex; height: 2.5rem; width: 100%; border-radius: 0.375rem;
            border: 1px solid #e2e8f0; background-color: transparent; padding: 0.5rem 0.75rem;
            font-size: 0.875rem; transition: border-color 0.2s;
        }
        .shadcn-input:focus {
            outline: none; border-color: #0f172a; ring: 2px solid #0f172a;
        }
        .shadcn-card {
            border-radius: 0.5rem; border: 1px solid #e2e8f0; background-color: white; color: #0f172a;
            box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05);
        }
        .min-h-screen-content {
            min-height: calc(100vh - 140px);
        }
    </style>
    <sitemesh:write property='head' />
</head>
<body class="antialiased text-slate-900">
    <header class="glass sticky top-0 z-50 w-full border-b">
        <div class="container mx-auto px-4 h-16 flex items-center justify-between">
            <div class="flex gap-6 md:gap-10">
                <a href="${pageContext.request.contextPath}/home" class="flex items-center space-x-2">
                    <span class="inline-block font-bold">Nhà Sách</span>
                </a>
                <nav class="hidden md:flex gap-6">
                    <a href="${pageContext.request.contextPath}/home" class="flex items-center text-sm font-medium text-slate-600 hover:text-slate-900">Trang Chủ</a>
                    <a href="${pageContext.request.contextPath}/home" class="flex items-center text-sm font-medium text-slate-600 hover:text-slate-900">Sản phẩm</a>
                    <a href="${pageContext.request.contextPath}/cart" class="flex items-center text-sm font-medium text-slate-600 hover:text-slate-900">Giỏ hàng
                        <c:if test="${not empty sessionScope.cart and not sessionScope.cart.empty}">
                            <span class="ml-1 bg-red-500 text-white text-xs font-bold px-2 py-0.5 rounded-full">${sessionScope.cart.totalQuantity}</span>
                        </c:if>
                    </a>
                    <c:if test="${not empty sessionScope.user}">
                        <a href="${pageContext.request.contextPath}/orders" class="flex items-center text-sm font-medium text-slate-600 hover:text-slate-900">Lịch sử đặt hàng</a>
                    </c:if>
                    <c:if test="${not empty sessionScope.user and sessionScope.user.is_admin}">
                        <a href="${pageContext.request.contextPath}/admin/books" class="flex items-center text-sm font-medium text-slate-600 hover:text-slate-900">Trang quản trị</a>
                    </c:if>
                </nav>
            </div>
            <div class="flex flex-1 items-center justify-end space-x-4">
                <nav class="flex items-center space-x-2">
                    <c:choose>
                        <c:when test="${not empty sessionScope.user}">
                            <span class="text-sm font-medium mr-4">Xin chào, ${sessionScope.user.fullname}</span>
                            <a href="${pageContext.request.contextPath}/logout" class="shadcn-btn shadcn-btn-outline">Đăng xuất</a>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/login" class="shadcn-btn shadcn-btn-outline">Đăng nhập</a>
                            <a href="${pageContext.request.contextPath}/register" class="shadcn-btn shadcn-btn-primary">Đăng ký</a>
                        </c:otherwise>
                    </c:choose>
                </nav>
            </div>
        </div>
    </header>

    <main class="container mx-auto px-4 py-8 min-h-screen-content">
        <sitemesh:write property='body' />
    </main>

    <footer class="glass border-t py-6">
        <div class="container mx-auto px-4 text-center text-sm text-slate-600">
            <p>Họ và tên: Phạm Nguyễn Huỳnh Trường | MSSV: 24110064 | Mã đề: 01</p>
        </div>
    </footer>
</body>
</html>
