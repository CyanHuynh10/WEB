<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>${empty author ? 'Thêm Tác giả' : 'Sửa Tác giả'}</title>
</head>
<body>
    <div class="mb-6 flex items-center space-x-4">
        <a href="${pageContext.request.contextPath}/admin/authors" class="text-slate-500 hover:text-slate-900">
            &larr; Quay lại
        </a>
        <h1 class="text-2xl font-bold">${empty author ? 'Thêm Tác giả' : 'Sửa Tác giả'}</h1>
    </div>

    <div class="shadcn-card p-6 max-w-xl">
        <form action="${pageContext.request.contextPath}/admin/authors" method="post" class="space-y-4">
            <input type="hidden" name="author_id" value="${author.author_id}">
            
            <div>
                <label class="block text-sm font-medium mb-1">Tên tác giả</label>
                <input type="text" name="author_name" value="${author.author_name}" required class="shadcn-input">
            </div>
            <div>
                <label class="block text-sm font-medium mb-1">Ngày sinh</label>
                <input type="date" name="date_of_birth" value="${author.date_of_birth}" class="shadcn-input">
            </div>
            
            <div class="pt-4 flex justify-end">
                <button type="submit" class="shadcn-btn shadcn-btn-primary">Lưu</button>
            </div>
        </form>
    </div>
</body>
</html>
