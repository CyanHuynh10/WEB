<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<html>
<head>
    <title>${empty book ? 'Thêm Sách' : 'Sửa Sách'}</title>
</head>
<body>
    <div class="mb-6 flex items-center space-x-4">
        <a href="${pageContext.request.contextPath}/admin/books" class="text-slate-500 hover:text-slate-900">
            &larr; Quay lại
        </a>
        <h1 class="text-2xl font-bold">${empty book ? 'Thêm Sách' : 'Sửa Sách'}</h1>
    </div>

    <div class="shadcn-card p-6 max-w-2xl">
        <form action="${pageContext.request.contextPath}/admin/books" method="post" class="space-y-4">
            <input type="hidden" name="bookid" value="${book.bookid}">
            
            <div class="grid grid-cols-2 gap-4">
                <div>
                    <label class="block text-sm font-medium mb-1">Tiêu đề</label>
                    <input type="text" name="title" value="${book.title}" required class="shadcn-input">
                </div>
                <div>
                    <label class="block text-sm font-medium mb-1">ISBN</label>
                    <input type="number" name="isbn" value="${book.isbn}" class="shadcn-input">
                </div>
                <div>
                    <label class="block text-sm font-medium mb-1">Nhà xuất bản (Publisher)</label>
                    <input type="text" name="publisher" value="${book.publisher}" class="shadcn-input">
                </div>
                <div>
                    <label class="block text-sm font-medium mb-1">Ngày xuất bản</label>
                    <input type="date" name="publish_date" value="${book.publish_date}" class="shadcn-input">
                </div>
                <div>
                    <label class="block text-sm font-medium mb-1">Giá</label>
                    <input type="number" step="0.01" name="price" value="${book.price}" class="shadcn-input">
                </div>
                <div>
                    <label class="block text-sm font-medium mb-1">Số lượng (Quantity)</label>
                    <input type="number" name="quantity" value="${book.quantity}" class="shadcn-input">
                </div>
                <div class="col-span-2">
                    <label class="block text-sm font-medium mb-1">Tác giả (chọn nhiều)</label>
                    <select name="author_ids" multiple class="flex w-full rounded-md border border-slate-200 bg-transparent px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-slate-900 h-32">
                        <c:forEach var="author" items="${authorsList}">
                            <c:set var="isSelected" value="false" />
                            <c:if test="${not empty book and not empty book.authors}">
                                <c:forEach var="bookAuthor" items="${book.authors}">
                                    <c:if test="${bookAuthor.author_id == author.author_id}">
                                        <c:set var="isSelected" value="true" />
                                    </c:if>
                                </c:forEach>
                            </c:if>
                            <option value="${author.author_id}" ${isSelected ? 'selected' : ''}>${author.author_name}</option>
                        </c:forEach>
                    </select>
                    <p class="text-xs text-slate-500 mt-1">Nhấn giữ Ctrl (Windows) hoặc Command (Mac) để chọn nhiều tác giả.</p>
                </div>
                <div class="col-span-2">
                    <label class="block text-sm font-medium mb-1">Cover Image (URL or filename)</label>
                    <input type="text" name="cover_image" value="${book.cover_image}" class="shadcn-input">
                </div>
                <div class="col-span-2">
                    <label class="block text-sm font-medium mb-1">Mô tả</label>
                    <textarea name="description" rows="4" class="flex w-full rounded-md border border-slate-200 bg-transparent px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-slate-900">${book.description}</textarea>
                </div>
            </div>
            
            <div class="pt-4 flex justify-end">
                <button type="submit" class="shadcn-btn shadcn-btn-primary">Lưu</button>
            </div>
        </form>
    </div>
</body>
</html>
