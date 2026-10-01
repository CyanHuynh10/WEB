<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>

<div class="max-w-4xl mx-auto">
    <h1 class="text-3xl font-bold mb-6">Thanh toán đơn hàng</h1>
    
    <c:if test="${not empty sessionScope.errorMessage}">
        <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4">
            ${sessionScope.errorMessage}
        </div>
        <c:remove var="errorMessage" scope="session" />
    </c:if>

    <div class="grid md:grid-cols-2 gap-8">
        <div>
            <div class="shadcn-card p-6 mb-6">
                <h2 class="text-xl font-bold mb-4">Thông tin nhận hàng</h2>
                <form action="${pageContext.request.contextPath}/checkout" method="post" id="checkoutForm">
                    <div class="mb-4">
                        <label class="block text-sm font-medium mb-1">Họ và tên</label>
                        <input type="text" name="name" class="shadcn-input" value="${sessionScope.user.fullname}" required>
                    </div>
                    <div class="mb-4">
                        <label class="block text-sm font-medium mb-1">Số điện thoại</label>
                        <input type="text" name="phone" class="shadcn-input" value="${sessionScope.user.phone}" required pattern="[0-9]{9,11}" title="Vui lòng nhập số điện thoại hợp lệ">
                    </div>
                    <div class="mb-4">
                        <label class="block text-sm font-medium mb-1">Địa chỉ giao hàng</label>
                        <textarea name="address" class="shadcn-input h-24 py-2" required></textarea>
                    </div>
                </form>
            </div>
            
            <div class="shadcn-card p-6">
                <h2 class="text-xl font-bold mb-4">Phương thức thanh toán</h2>
                <div class="p-4 border rounded bg-slate-50 flex items-center gap-3">
                    <input type="radio" checked disabled class="w-4 h-4 text-blue-600">
                    <span class="font-medium">COD - Thanh toán khi nhận hàng</span>
                </div>
            </div>
        </div>
        
        <div>
            <div class="shadcn-card p-6 sticky top-20">
                <h2 class="text-xl font-bold mb-4">Tóm tắt đơn hàng</h2>
                
                <div class="mb-4 max-h-64 overflow-y-auto">
                    <c:forEach var="item" items="${sessionScope.cart.items}">
                        <div class="flex justify-between items-center py-2 border-b last:border-0">
                            <div class="flex items-center gap-3 w-2/3">
                                <img src="${item.book.cover_image}" class="w-12 h-16 object-cover rounded shadow-sm">
                                <div>
                                    <div class="font-medium text-sm truncate" title="${item.book.title}">${item.book.title}</div>
                                    <div class="text-slate-500 text-xs">Số lượng: ${item.quantity}</div>
                                </div>
                            </div>
                            <div class="font-medium">
                                <fmt:formatNumber value="${item.subtotal}" type="currency" currencySymbol="₫" maxFractionDigits="0"/>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                
                <div class="border-t pt-4 mt-4">
                    <div class="flex justify-between items-center text-lg font-bold">
                        <span>Tổng tiền:</span>
                        <span class="text-red-600"><fmt:formatNumber value="${sessionScope.cart.total}" type="currency" currencySymbol="₫" maxFractionDigits="0"/></span>
                    </div>
                </div>
                
                <button type="submit" form="checkoutForm" class="shadcn-btn shadcn-btn-primary w-full mt-6 text-lg h-12">
                    Đặt hàng
                </button>
            </div>
        </div>
    </div>
</div>
