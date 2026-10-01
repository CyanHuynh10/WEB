<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - <sitemesh:write property='title' /></title>
    <!-- Tailwind CSS -->
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        body { background-color: #f8fafc; font-family: ui-sans-serif, system-ui, -apple-system, sans-serif; }
        .glass {
            background: rgba(255, 255, 255, 0.8);
            backdrop-filter: blur(12px);
            border: 1px solid rgba(226, 232, 240, 0.8);
        }
        .shadcn-btn {
            display: inline-flex; align-items: center; justify-content: center; border-radius: 0.375rem;
            font-size: 0.875rem; font-weight: 500; height: 2.5rem; padding: 0 1rem;
            transition: background-color 0.2s, color 0.2s;
        }
        .shadcn-btn-primary { background-color: #0f172a; color: white; }
        .shadcn-btn-primary:hover { background-color: #334155; }
        .shadcn-btn-danger { background-color: #ef4444; color: white; }
        .shadcn-btn-danger:hover { background-color: #dc2626; }
        .shadcn-input {
            display: flex; height: 2.5rem; width: 100%; border-radius: 0.375rem;
            border: 1px solid #e2e8f0; padding: 0.5rem 0.75rem; font-size: 0.875rem;
        }
        .shadcn-input:focus { outline: none; border-color: #0f172a; }
        .shadcn-card {
            border-radius: 0.5rem; border: 1px solid #e2e8f0; background-color: white;
            box-shadow: 0 1px 2px 0 rgba(0,0,0,0.05);
        }
        .admin-layout { display: flex; min-height: calc(100vh - 140px); }
        .admin-sidebar { width: 250px; border-right: 1px solid #e2e8f0; padding: 1.5rem; }
        .admin-content { flex: 1; padding: 1.5rem; }
    </style>
    <sitemesh:write property='head' />
</head>
<body class="antialiased text-slate-900">
    <header class="glass sticky top-0 z-50 w-full border-b">
        <div class="container mx-auto px-4 h-16 flex items-center justify-between">
            <div class="flex gap-6 md:gap-10">
                <a href="${pageContext.request.contextPath}/home" class="flex items-center space-x-2">
                    <span class="inline-block font-bold">Bảng điều khiển Quản trị</span>
                </a>
                <nav class="hidden md:flex gap-6">
                    <a href="${pageContext.request.contextPath}/home" class="flex items-center text-sm font-medium text-slate-600 hover:text-slate-900">Trang Chủ</a>
                    <a href="${pageContext.request.contextPath}/admin/books" class="flex items-center text-sm font-medium text-slate-900 font-bold">Trang quản trị</a>
                </nav>
            </div>
            <div class="flex flex-1 items-center justify-end space-x-4">
                <span class="text-sm font-medium mr-4">Xin chào, ${sessionScope.user.fullname}</span>
                <a href="${pageContext.request.contextPath}/logout" class="shadcn-btn shadcn-btn-primary">Đăng xuất</a>
            </div>
        </div>
    </header>

    <div class="container mx-auto admin-layout">
        <aside class="admin-sidebar glass">
            <nav class="flex flex-col gap-2">
                <a href="${pageContext.request.contextPath}/admin/books" class="px-3 py-2 rounded-md hover:bg-slate-100 text-sm font-medium">Quản lý Sách</a>
                <a href="${pageContext.request.contextPath}/admin/authors" class="px-3 py-2 rounded-md hover:bg-slate-100 text-sm font-medium">Quản lý Tác giả</a>
            </nav>
        </aside>
        <main class="admin-content">
            <sitemesh:write property='body' />
        </main>
    </div>

    <footer class="glass border-t py-6">
        <div class="container mx-auto px-4 text-center text-sm text-slate-600">
            <p>Họ và tên: Phạm Nguyễn Huỳnh Trường | MSSV: 24110064 | Mã đề: 01</p>
        </div>
    </footer>
</body>
</html>
