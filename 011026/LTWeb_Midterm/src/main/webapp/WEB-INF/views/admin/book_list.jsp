<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Quản lý Sách</title>
</head>
<body>
    <div class="flex justify-between items-center mb-6">
        <h1 class="text-2xl font-bold">Danh sách Sách</h1>
        <a href="${pageContext.request.contextPath}/admin/books/add" class="shadcn-btn shadcn-btn-primary">+ Thêm sách mới</a>
    </div>

    <div class="shadcn-card overflow-x-auto">
        <table class="w-full text-sm text-left">
            <thead class="text-xs text-slate-700 uppercase bg-slate-50 border-b">
                <tr>
                    <th class="px-6 py-3">ID</th>
                    <th class="px-6 py-3">Tiêu đề</th>
                    <th class="px-6 py-3">ISBN</th>
                    <th class="px-6 py-3">Publisher</th>
                    <th class="px-6 py-3">Giá</th>
                    <th class="px-6 py-3 text-right">Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="book" items="${books}">
                    <tr class="bg-white border-b hover:bg-slate-50">
                        <td class="px-6 py-4">${book.bookid}</td>
                        <td class="px-6 py-4 font-medium">${book.title}</td>
                        <td class="px-6 py-4">${book.isbn}</td>
                        <td class="px-6 py-4">${book.publisher}</td>
                        <td class="px-6 py-4">${book.price}</td>
                        <td class="px-6 py-4 text-right space-x-2">
                            <a href="${pageContext.request.contextPath}/admin/books/edit?id=${book.bookid}" class="text-blue-600 hover:underline">Sửa</a>
                            <a href="${pageContext.request.contextPath}/admin/books/delete?id=${book.bookid}" class="text-red-600 hover:underline" onclick="return confirm('Bạn có chắc chắn muốn xóa?')">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <c:if test="${totalPages > 1}">
        <div class="mt-6 flex justify-end space-x-2">
            <c:forEach begin="1" end="${totalPages}" var="i">
                <a href="${pageContext.request.contextPath}/admin/books?page=${i}" 
                   class="w-8 h-8 flex items-center justify-center rounded border ${currentPage == i ? 'bg-slate-900 text-white' : 'bg-white text-slate-700 hover:bg-slate-50'}">
                    ${i}
                </a>
            </c:forEach>
        </div>
    </c:if>
</body>
</html>
