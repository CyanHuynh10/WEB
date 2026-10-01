<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>

<div class="max-w-6xl mx-auto">
    <div class="flex justify-between items-center mb-6">
        <h1 class="text-3xl font-bold">Lịch sử đặt hàng</h1>
    </div>
    
    <c:if test="${not empty sessionScope.errorMessage}">
        <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4">
            ${sessionScope.errorMessage}
        </div>
        <c:remove var="errorMessage" scope="session" />
    </c:if>

    <div class="shadcn-card p-4 mb-6">
        <form action="${pageContext.request.contextPath}/orders" method="get" class="flex gap-4 items-center overflow-x-auto pb-2" id="filterForm">
            <button type="submit" name="status" value="ALL" class="whitespace-nowrap px-4 py-2 rounded-full text-sm font-medium ${currentStatus == 'ALL' ? 'bg-slate-800 text-white' : 'bg-slate-100 text-slate-600 hover:bg-slate-200'}">Tất cả</button>
            <button type="submit" name="status" value="NEW" class="whitespace-nowrap px-4 py-2 rounded-full text-sm font-medium ${currentStatus == 'NEW' ? 'bg-slate-800 text-white' : 'bg-slate-100 text-slate-600 hover:bg-slate-200'}">Đơn hàng mới</button>
            <button type="submit" name="status" value="CONFIRMED" class="whitespace-nowrap px-4 py-2 rounded-full text-sm font-medium ${currentStatus == 'CONFIRMED' ? 'bg-slate-800 text-white' : 'bg-slate-100 text-slate-600 hover:bg-slate-200'}">Đã xác nhận</button>
            <button type="submit" name="status" value="PREPARING" class="whitespace-nowrap px-4 py-2 rounded-full text-sm font-medium ${currentStatus == 'PREPARING' ? 'bg-slate-800 text-white' : 'bg-slate-100 text-slate-600 hover:bg-slate-200'}">Chuẩn bị hàng</button>
            <button type="submit" name="status" value="SHIPPING" class="whitespace-nowrap px-4 py-2 rounded-full text-sm font-medium ${currentStatus == 'SHIPPING' ? 'bg-slate-800 text-white' : 'bg-slate-100 text-slate-600 hover:bg-slate-200'}">Vận chuyển</button>
            <button type="submit" name="status" value="OUT_FOR_DELIVERY" class="whitespace-nowrap px-4 py-2 rounded-full text-sm font-medium ${currentStatus == 'OUT_FOR_DELIVERY' ? 'bg-slate-800 text-white' : 'bg-slate-100 text-slate-600 hover:bg-slate-200'}">Giao hàng</button>
            <button type="submit" name="status" value="DELIVERED" class="whitespace-nowrap px-4 py-2 rounded-full text-sm font-medium ${currentStatus == 'DELIVERED' ? 'bg-slate-800 text-white' : 'bg-slate-100 text-slate-600 hover:bg-slate-200'}">Đã giao</button>
            <button type="submit" name="status" value="CANCELLED" class="whitespace-nowrap px-4 py-2 rounded-full text-sm font-medium ${currentStatus == 'CANCELLED' ? 'bg-red-600 text-white' : 'bg-slate-100 text-slate-600 hover:bg-slate-200'}">Đơn hàng hủy</button>
            <button type="submit" name="status" value="RETURNED" class="whitespace-nowrap px-4 py-2 rounded-full text-sm font-medium ${currentStatus == 'RETURNED' ? 'bg-orange-500 text-white' : 'bg-slate-100 text-slate-600 hover:bg-slate-200'}">Đơn hàng hoàn</button>
        </form>
    </div>

    <c:choose>
        <c:when test="${empty orders}">
            <div class="shadcn-card p-12 text-center text-slate-500">
                Bạn chưa có đơn hàng nào.
            </div>
        </c:when>
        <c:otherwise>
            <div class="space-y-4">
                <c:forEach var="order" items="${orders}">
                    <div class="shadcn-card p-6 flex flex-col md:flex-row justify-between items-start md:items-center gap-4">
                        <div>
                            <div class="flex items-center gap-3 mb-2">
                                <span class="font-bold text-lg">Đơn hàng #${order.orderId}</span>
                                <c:choose>
                                    <c:when test="${order.status == 'NEW'}"><span class="px-2 py-1 bg-blue-100 text-blue-700 text-xs rounded-full font-medium">Đơn hàng mới</span></c:when>
                                    <c:when test="${order.status == 'CONFIRMED'}"><span class="px-2 py-1 bg-indigo-100 text-indigo-700 text-xs rounded-full font-medium">Đã xác nhận</span></c:when>
                                    <c:when test="${order.status == 'PREPARING'}"><span class="px-2 py-1 bg-yellow-100 text-yellow-700 text-xs rounded-full font-medium">Chuẩn bị hàng</span></c:when>
                                    <c:when test="${order.status == 'SHIPPING'}"><span class="px-2 py-1 bg-purple-100 text-purple-700 text-xs rounded-full font-medium">Vận chuyển</span></c:when>
                                    <c:when test="${order.status == 'OUT_FOR_DELIVERY'}"><span class="px-2 py-1 bg-orange-100 text-orange-700 text-xs rounded-full font-medium">Giao hàng</span></c:when>
                                    <c:when test="${order.status == 'DELIVERED'}"><span class="px-2 py-1 bg-green-100 text-green-700 text-xs rounded-full font-medium">Đã giao</span></c:when>
                                    <c:when test="${order.status == 'CANCELLED'}"><span class="px-2 py-1 bg-red-100 text-red-700 text-xs rounded-full font-medium">Đơn hàng hủy</span></c:when>
                                    <c:when test="${order.status == 'RETURNED'}"><span class="px-2 py-1 bg-gray-100 text-gray-700 text-xs rounded-full font-medium">Đơn hàng hoàn</span></c:when>
                                    <c:otherwise><span class="px-2 py-1 bg-slate-100 text-slate-700 text-xs rounded-full font-medium">${order.status}</span></c:otherwise>
                                </c:choose>
                            </div>
                            <div class="text-sm text-slate-500 mb-1">Ngày đặt: <fmt:formatDate value="${order.orderDate}" pattern="dd/MM/yyyy HH:mm" /></div>
                            <div class="text-sm text-slate-500">Phương thức: ${order.paymentMethod}</div>
                        </div>
                        
                        <div class="flex flex-col items-end gap-2 w-full md:w-auto">
                            <div class="font-bold text-red-600 text-xl">
                                <fmt:formatNumber value="${order.totalAmount}" type="currency" currencySymbol="₫" maxFractionDigits="0"/>
                            </div>
                            <a href="${pageContext.request.contextPath}/order/detail?id=${order.orderId}" class="shadcn-btn shadcn-btn-outline w-full md:w-auto">Xem chi tiết</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</div>
