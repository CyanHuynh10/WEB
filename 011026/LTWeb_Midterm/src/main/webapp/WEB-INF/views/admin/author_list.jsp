<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Quản lý Tác giả</title>
</head>
<body>
    <div class="flex justify-between items-center mb-6">
        <h1 class="text-2xl font-bold">Danh sách Tác giả</h1>
        <a href="${pageContext.request.contextPath}/admin/authors/add" class="shadcn-btn shadcn-btn-primary">+ Thêm tác giả</a>
    </div>

    <div class="shadcn-card overflow-x-auto">
        <table class="w-full text-sm text-left">
            <thead class="text-xs text-slate-700 uppercase bg-slate-50 border-b">
                <tr>
                    <th class="px-6 py-3">ID</th>
                    <th class="px-6 py-3">Tên tác giả</th>
                    <th class="px-6 py-3">Ngày sinh</th>
                    <th class="px-6 py-3 text-right">Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="author" items="${authors}">
                    <tr class="bg-white border-b hover:bg-slate-50">
                        <td class="px-6 py-4">${author.author_id}</td>
                        <td class="px-6 py-4 font-medium">${author.author_name}</td>
                        <td class="px-6 py-4">${author.date_of_birth}</td>
                        <td class="px-6 py-4 text-right space-x-2">
                            <a href="${pageContext.request.contextPath}/admin/authors/edit?id=${author.author_id}" class="text-blue-600 hover:underline">Sửa</a>
                            <a href="${pageContext.request.contextPath}/admin/authors/delete?id=${author.author_id}" class="text-red-600 hover:underline" onclick="return confirm('Bạn có chắc chắn muốn xóa?')">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <c:if test="${totalPages > 1}">
        <div class="mt-6 flex justify-end space-x-2">
            <c:forEach begin="1" end="${totalPages}" var="i">
                <a href="${pageContext.request.contextPath}/admin/authors?page=${i}" 
                   class="w-8 h-8 flex items-center justify-center rounded border ${currentPage == i ? 'bg-slate-900 text-white' : 'bg-white text-slate-700 hover:bg-slate-50'}">
                    ${i}
                </a>
            </c:forEach>
        </div>
    </c:if>
</body>
</html>
