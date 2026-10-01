<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<html>
<head>
    <title>${book.title}</title>
</head>
<body>
    <div class="max-w-4xl mx-auto bg-white rounded-xl shadow-md overflow-hidden border border-slate-200">
        <div class="md:flex">
            <div class="md:shrink-0 bg-slate-100 w-full md:w-64 h-64 md:h-auto border-r border-slate-200">
                <img src="${pageContext.request.contextPath}/assets/images/${not empty book.cover_image ? book.cover_image : 'placeholder.png'}" 
                     alt="${book.title}" class="h-full w-full object-cover" onerror="this.src='https://via.placeholder.com/400x500?text=No+Cover'">
            </div>
            <div class="p-8 w-full">
                <div class="uppercase tracking-wide text-sm text-blue-600 font-semibold mb-1">Mã isbn: ${book.isbn}</div>
                <h2 class="block mt-1 text-2xl leading-tight font-bold text-black">${book.title}</h2>
                <div class="mt-4 space-y-2 text-slate-600">
                    <p><span class="font-medium text-slate-900">Tác giả:</span> 
                        <c:forEach var="author" items="${book.authors}" varStatus="status">
                            ${author.author_name}${!status.last ? ', ' : ''}
                        </c:forEach>
                    </p>
                    <p><span class="font-medium text-slate-900">Nhà xuất bản:</span> ${book.publisher}</p>
                    <p><span class="font-medium text-slate-900">Ngày xuất bản:</span> ${book.publish_date}</p>
                    <p><span class="font-medium text-slate-900">Số lượng:</span> ${book.quantity}</p>
                    <p><span class="font-medium text-slate-900">Giá:</span> <fmt:formatNumber value="${book.price}" type="currency" currencySymbol="$"/></p>
                </div>
                <div class="mt-6">
                    <h3 class="font-medium text-slate-900 mb-2">Mô tả</h3>
                    <p class="text-slate-600">${book.description}</p>
                </div>
                
                <div class="mt-8 flex gap-4">
                    <form action="${pageContext.request.contextPath}/cart/add" method="post" class="flex gap-2">
                        <input type="hidden" name="bookId" value="${book.bookid}">
                        <input type="number" name="quantity" value="1" min="1" max="${book.quantity}" class="shadcn-input w-20 text-center" ${book.quantity <= 0 ? 'disabled' : ''}>
                        <button type="submit" class="shadcn-btn shadcn-btn-primary" ${book.quantity <= 0 ? 'disabled' : ''}>
                            Thêm vào giỏ hàng
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Reviews Section -->
    <div class="max-w-4xl mx-auto mt-8">
        <h3 class="text-xl font-bold mb-4">Đánh giá (${reviews.size()})</h3>
        
        <div class="space-y-4 mb-8">
            <c:choose>
                <c:when test="${empty reviews}">
                    <p class="text-slate-500 italic">Chưa có đánh giá nào.</p>
                </c:when>
                <c:otherwise>
                    <c:forEach var="review" items="${reviews}">
                        <div class="bg-white p-4 rounded-lg shadow-sm border border-slate-200">
                            <div class="flex justify-between items-start mb-2">
                                <h4 class="font-semibold text-slate-800">${review.user.fullname}</h4>
                                <span class="bg-yellow-100 text-yellow-800 text-xs font-semibold px-2.5 py-0.5 rounded">Điểm: ${review.rating}/5</span>
                            </div>
                            <p class="text-slate-600">${review.review_text}</p>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>

        <c:if test="${not empty sessionScope.user}">
            <div class="bg-slate-50 p-6 rounded-xl border border-slate-200">
                <h4 class="font-bold text-lg mb-4">Form thêm reviews</h4>
                <form action="${pageContext.request.contextPath}/book/detail" method="post" class="space-y-4">
                    <input type="hidden" name="bookId" value="${book.bookid}">
                    <div>
                        <label class="block text-sm font-medium mb-1">Đánh giá (1-5)</label>
                        <select name="rating" required class="shadcn-input">
                            <option value="5">5 - Rất tốt</option>
                            <option value="4">4 - Tốt</option>
                            <option value="3">3 - Bình thường</option>
                            <option value="2">2 - Tệ</option>
                            <option value="1">1 - Rất tệ</option>
                        </select>
                    </div>
                    <div>
                        <label class="block text-sm font-medium mb-1">Nội dung review</label>
                        <textarea name="review_text" rows="3" required class="flex w-full rounded-md border border-slate-200 bg-transparent px-3 py-2 text-sm placeholder:text-slate-400 focus:outline-none focus:ring-2 focus:ring-slate-900"></textarea>
                    </div>
                    <button type="submit" class="shadcn-btn shadcn-btn-primary">Gửi</button>
                </form>
            </div>
        </c:if>
        <c:if test="${empty sessionScope.user}">
            <div class="text-center py-6 bg-slate-50 rounded-lg border border-slate-200">
                <p class="text-slate-600">Vui lòng <a href="${pageContext.request.contextPath}/login" class="text-blue-600 font-medium">Đăng nhập</a> để gửi đánh giá.</p>
            </div>
        </c:if>
    </div>
</body>
</html>
