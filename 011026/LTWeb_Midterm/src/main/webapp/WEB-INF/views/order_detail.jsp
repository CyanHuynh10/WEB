<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>

<div class="max-w-4xl mx-auto">
    <div class="mb-6 flex items-center gap-4">
        <a href="${pageContext.request.contextPath}/orders" class="text-blue-600 hover:underline flex items-center">
            &larr; Quay lại
        </a>
        <h1 class="text-3xl font-bold">Chi tiết đơn hàng #${order.orderId}</h1>
        
        <c:choose>
            <c:when test="${order.status == 'NEW'}"><span class="px-3 py-1 bg-blue-100 text-blue-700 text-sm rounded-full font-medium ml-auto">Đơn hàng mới</span></c:when>
            <c:when test="${order.status == 'CONFIRMED'}"><span class="px-3 py-1 bg-indigo-100 text-indigo-700 text-sm rounded-full font-medium ml-auto">Đã xác nhận</span></c:when>
            <c:when test="${order.status == 'PREPARING'}"><span class="px-3 py-1 bg-yellow-100 text-yellow-700 text-sm rounded-full font-medium ml-auto">Chuẩn bị hàng</span></c:when>
            <c:when test="${order.status == 'SHIPPING'}"><span class="px-3 py-1 bg-purple-100 text-purple-700 text-sm rounded-full font-medium ml-auto">Vận chuyển</span></c:when>
            <c:when test="${order.status == 'OUT_FOR_DELIVERY'}"><span class="px-3 py-1 bg-orange-100 text-orange-700 text-sm rounded-full font-medium ml-auto">Giao hàng</span></c:when>
            <c:when test="${order.status == 'DELIVERED'}"><span class="px-3 py-1 bg-green-100 text-green-700 text-sm rounded-full font-medium ml-auto">Đã giao</span></c:when>
            <c:when test="${order.status == 'CANCELLED'}"><span class="px-3 py-1 bg-red-100 text-red-700 text-sm rounded-full font-medium ml-auto">Đơn hàng hủy</span></c:when>
            <c:when test="${order.status == 'RETURNED'}"><span class="px-3 py-1 bg-gray-100 text-gray-700 text-sm rounded-full font-medium ml-auto">Đơn hàng hoàn</span></c:when>
            <c:otherwise><span class="px-3 py-1 bg-slate-100 text-slate-700 text-sm rounded-full font-medium ml-auto">${order.status}</span></c:otherwise>
        </c:choose>
    </div>

    <div class="grid md:grid-cols-3 gap-6 mb-8">
        <div class="shadcn-card p-5 md:col-span-2">
            <h2 class="text-lg font-bold mb-3">Sản phẩm</h2>
            <div class="space-y-4">
                <c:forEach var="item" items="${order.orderItems}">
                    <div class="flex items-center justify-between border-b pb-4 last:border-0 last:pb-0">
                        <div class="flex items-center gap-4">
                            <img src="${item.book.cover_image}" class="w-16 h-20 object-cover rounded shadow-sm">
                            <div>
                                <div class="font-medium hover:underline text-blue-600">
                                    <a href="${pageContext.request.contextPath}/book/detail?id=${item.book.bookid}">${item.book.title}</a>
                                </div>
                                <div class="text-sm text-slate-500 mt-1">Đơn giá: <fmt:formatNumber value="${item.unitPrice}" type="currency" currencySymbol="₫" maxFractionDigits="0"/></div>
                                <div class="text-sm text-slate-500">Số lượng: ${item.quantity}</div>
                            </div>
                        </div>
                        <div class="font-bold">
                            <fmt:formatNumber value="${item.subtotal}" type="currency" currencySymbol="₫" maxFractionDigits="0"/>
                        </div>
                    </div>
                </c:forEach>
            </div>
            
            <div class="border-t mt-4 pt-4 flex justify-between items-center">
                <span class="text-lg font-bold">Tổng tiền</span>
                <span class="text-2xl font-bold text-red-600">
                    <fmt:formatNumber value="${order.totalAmount}" type="currency" currencySymbol="₫" maxFractionDigits="0"/>
                </span>
            </div>
        </div>
        
        <div class="space-y-6">
            <div class="shadcn-card p-5">
                <h2 class="text-lg font-bold mb-3 border-b pb-2">Thông tin người nhận</h2>
                <div class="space-y-2 text-sm">
                    <p><span class="text-slate-500">Họ và tên:</span> <br> <span class="font-medium">${order.customerName}</span></p>
                    <p><span class="text-slate-500">Số điện thoại:</span> <br> <span class="font-medium">${order.phone}</span></p>
                    <p><span class="text-slate-500">Địa chỉ:</span> <br> <span class="font-medium">${order.shippingAddress}</span></p>
                </div>
            </div>
            
            <div class="shadcn-card p-5">
                <h2 class="text-lg font-bold mb-3 border-b pb-2">Thông tin thanh toán</h2>
                <div class="space-y-2 text-sm">
                    <p><span class="text-slate-500">Ngày đặt:</span> <br> <span class="font-medium"><fmt:formatDate value="${order.orderDate}" pattern="dd/MM/yyyy HH:mm:ss" /></span></p>
                    <p><span class="text-slate-500">Phương thức:</span> <br> <span class="font-medium">COD - Thanh toán khi nhận hàng</span></p>
                </div>
            </div>
        </div>
    </div>
</div>
