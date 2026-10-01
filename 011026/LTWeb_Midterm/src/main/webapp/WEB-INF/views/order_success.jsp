<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>

<div class="max-w-2xl mx-auto text-center py-12">
    <div class="w-20 h-20 bg-green-100 text-green-500 rounded-full flex items-center justify-center mx-auto mb-6">
        <svg xmlns="http://www.w3.org/2000/svg" class="h-10 w-10" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
        </svg>
    </div>
    
    <h1 class="text-3xl font-bold mb-2">Đặt hàng thành công!</h1>
    <p class="text-slate-500 mb-8">Cảm ơn bạn đã mua sắm tại Nhà Sách.</p>
    
    <c:if test="${not empty sessionScope.successMessage}">
        <div class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded mb-8 inline-block">
            ${sessionScope.successMessage}
        </div>
        <c:remove var="successMessage" scope="session" />
    </c:if>

    <div class="shadcn-card p-6 mb-8 text-left max-w-md mx-auto">
        <div class="flex justify-between border-b pb-3 mb-3">
            <span class="text-slate-500">Mã đơn hàng:</span>
            <span class="font-bold">#${order.orderId}</span>
        </div>
        <div class="flex justify-between border-b pb-3 mb-3">
            <span class="text-slate-500">Trạng thái:</span>
            <span class="font-medium text-blue-600">Đơn hàng mới</span>
        </div>
        <div class="flex justify-between border-b pb-3 mb-3">
            <span class="text-slate-500">Phương thức:</span>
            <span class="font-medium">COD</span>
        </div>
        <div class="flex justify-between">
            <span class="text-slate-500">Tổng tiền:</span>
            <span class="font-bold text-red-600">
                <fmt:formatNumber value="${order.totalAmount}" type="currency" currencySymbol="₫" maxFractionDigits="0"/>
            </span>
        </div>
    </div>
    
    <div class="flex gap-4 justify-center">
        <a href="${pageContext.request.contextPath}/order/detail?id=${order.orderId}" class="shadcn-btn shadcn-btn-outline">Xem đơn hàng</a>
        <a href="${pageContext.request.contextPath}/home" class="shadcn-btn shadcn-btn-primary">Tiếp tục mua sắm</a>
    </div>
</div>
