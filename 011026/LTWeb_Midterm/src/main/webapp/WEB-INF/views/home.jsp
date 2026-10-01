<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<html>
<head>
    <title>Trang Chủ</title>
</head>
<body>
    <h1 class="text-3xl font-bold mb-8 text-center text-slate-800">Danh sách Sách</h1>
    
    <c:choose>
        <c:when test="${empty books}">
            <div class="text-center text-slate-500 py-10">
                <p class="text-lg">Hiện chưa có sách nào trong hệ thống.</p>
            </div>
        </c:when>
        <c:otherwise>
            <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                <c:forEach var="book" items="${books}">
                    <div class="shadcn-card overflow-hidden hover:shadow-lg transition-shadow duration-300">
                        <div class="h-48 bg-slate-200 overflow-hidden">
                            <!-- Placeholder image, using cover_image if exists -->
                            <img src="${pageContext.request.contextPath}/assets/images/${not empty book.cover_image ? book.cover_image : 'placeholder.png'}" 
                                 alt="${book.title}" class="w-full h-full object-cover" onerror="this.src='https://via.placeholder.com/400x300?text=No+Cover'">
                        </div>
                        <div class="p-5">
                            <h3 class="text-xl font-semibold mb-2 line-clamp-1">
                                <a href="${pageContext.request.contextPath}/book/detail?id=${book.bookid}" class="hover:text-blue-600">
                                    ${book.title}
                                </a>
                            </h3>
                            <div class="text-sm text-slate-600 mb-4 space-y-1">
                                <p><span class="font-medium">Mã isbn:</span> ${book.isbn}</p>
                                <p><span class="font-medium">Tác giả:</span> 
                                    <c:forEach var="author" items="${book.authors}" varStatus="status">
                                        ${author.author_name}${!status.last ? ', ' : ''}
                                    </c:forEach>
                                </p>
                                <p><span class="font-medium">Nhà xuất bản:</span> ${book.publisher}</p>
                                <p><span class="font-medium">Ngày xuất bản:</span> ${book.publish_date}</p>
                                <p><span class="font-medium">Số lượng:</span> ${book.quantity}</p>
                            </div>
                            <div class="mt-4 pt-4 border-t flex justify-between items-center">
                                <span class="font-bold text-lg text-slate-900"><fmt:formatNumber value="${book.price}" type="currency" currencySymbol="$"/></span>
                                <span class="text-sm bg-blue-100 text-blue-800 py-1 px-2 rounded-full mb-2 block w-fit">Đánh giá (${reviewCounts[book.bookid]})</span>
                            </div>
                            <div class="mt-2 flex justify-between items-center">
                                <form action="${pageContext.request.contextPath}/cart/add" method="post" class="w-full">
                                    <input type="hidden" name="bookId" value="${book.bookid}">
                                    <input type="hidden" name="quantity" value="1">
                                    <button type="submit" class="shadcn-btn shadcn-btn-primary w-full text-sm py-1 h-8" ${book.quantity <= 0 ? 'disabled' : ''}>
                                        Thêm vào giỏ
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            
            <!-- Pagination -->
            <c:if test="${totalPages > 1}">
                <div class="mt-10 flex justify-center space-x-2">
                    <c:forEach begin="1" end="${totalPages}" var="i">
                        <a href="${pageContext.request.contextPath}/home?page=${i}" 
                           class="w-10 h-10 flex items-center justify-center rounded-md border ${currentPage == i ? 'bg-slate-900 text-white' : 'bg-white text-slate-700 hover:bg-slate-50'}">
                            ${i}
                        </a>
                    </c:forEach>
                </div>
            </c:if>
        </c:otherwise>
    </c:choose>
</body>
</html>
