<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>

<div class="max-w-4xl mx-auto">
    <h1 class="text-3xl font-bold mb-6">Giỏ hàng</h1>
    
    <c:if test="${not empty sessionScope.errorMessage}">
        <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4">
            ${sessionScope.errorMessage}
        </div>
        <c:remove var="errorMessage" scope="session" />
    </c:if>
    <c:if test="${not empty sessionScope.successMessage}">
        <div class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded mb-4">
            ${sessionScope.successMessage}
        </div>
        <c:remove var="successMessage" scope="session" />
    </c:if>

    <c:choose>
        <c:when test="${empty sessionScope.cart or sessionScope.cart.empty}">
            <div class="shadcn-card p-8 text-center">
                <p class="text-lg text-slate-500 mb-4">Giỏ hàng của bạn đang trống.</p>
                <a href="${pageContext.request.contextPath}/home" class="shadcn-btn shadcn-btn-primary">Tiếp tục mua hàng</a>
            </div>
        </c:when>
        <c:otherwise>
            <div class="shadcn-card p-6">
                <div class="overflow-x-auto">
                    <table class="w-full text-left border-collapse">
                        <thead>
                            <tr class="border-b">
                                <th class="py-3 font-medium">Sản phẩm</th>
                                <th class="py-3 font-medium text-right">Đơn giá</th>
                                <th class="py-3 font-medium text-center">Số lượng</th>
                                <th class="py-3 font-medium text-right">Thành tiền</th>
                                <th class="py-3 font-medium text-center">Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${sessionScope.cart.items}">
                                <tr class="border-b">
                                    <td class="py-4">
                                        <div class="flex items-center gap-4">
                                            <img src="${item.book.cover_image}" alt="${item.book.title}" class="w-16 h-20 object-cover rounded shadow-sm">
                                            <div>
                                                <a href="${pageContext.request.contextPath}/book/detail?id=${item.book.bookid}" class="font-medium hover:underline text-blue-600">${item.book.title}</a>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="py-4 text-right">
                                        <fmt:formatNumber value="${item.unitPrice}" type="currency" currencySymbol="₫" maxFractionDigits="0"/>
                                    </td>
                                    <td class="py-4 text-center">
                                        <form action="${pageContext.request.contextPath}/cart/update" method="post" class="flex items-center justify-center gap-2">
                                            <input type="hidden" name="bookId" value="${item.book.bookid}">
                                            <button type="submit" name="quantity" value="${item.quantity - 1}" class="w-8 h-8 flex items-center justify-center bg-slate-100 hover:bg-slate-200 rounded border">-</button>
                                            <input type="number" name="quantity" value="${item.quantity}" min="1" class="w-16 text-center border rounded h-8">
                                            <button type="submit" name="quantity" value="${item.quantity + 1}" class="w-8 h-8 flex items-center justify-center bg-slate-100 hover:bg-slate-200 rounded border">+</button>
                                            <button type="submit" class="text-sm text-blue-600 ml-2">Cập nhật</button>
                                        </form>
                                    </td>
                                    <td class="py-4 text-right font-medium">
                                        <fmt:formatNumber value="${item.subtotal}" type="currency" currencySymbol="₫" maxFractionDigits="0"/>
                                    </td>
                                    <td class="py-4 text-center">
                                        <a href="${pageContext.request.contextPath}/cart/remove?bookId=${item.book.bookid}" class="text-red-500 hover:text-red-700 text-sm font-medium">Xóa</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                
                <div class="mt-6 flex flex-col items-end gap-4">
                    <div class="text-xl font-bold">
                        Tổng tiền: <span class="text-red-600"><fmt:formatNumber value="${sessionScope.cart.total}" type="currency" currencySymbol="₫" maxFractionDigits="0"/></span>
                    </div>
                    <div class="flex gap-4">
                        <a href="${pageContext.request.contextPath}/home" class="shadcn-btn shadcn-btn-outline">Tiếp tục mua hàng</a>
                        <a href="${pageContext.request.contextPath}/checkout" class="shadcn-btn shadcn-btn-primary">Tiến hành thanh toán</a>
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>
