<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<div class="table-responsive">
    <table class="table">
        <thead>
        <tr>
            <th colspan="2">
                总计 ${dto.count} 条
            </th>
            <th colspan="3">
                <h6>每页显示
                    <small>
                        <a href="order.do?rows=10">10</a>
                    </small>
                    <small>
                        <a href="order.do?rows=30">30</a>
                    </small>
                    <small>
                        <a href="order.do?rows=50">50</a>
                    </small>
                    <small>
                        <a href="order.do?rows=100">100</a>
                    </small>
                    条
                </h6>
            </th>
            <th colspan="1">
                <c:if test="${'1' eq sessionScope.power}">
                    <li class="btn"><a href="addorder.do">新增货运订单</a></li>
                </c:if>
            </th>
        </tr>
        <tr>
            <th>编号</th>
            <th>牧场</th>
            <th>车牌号</th>
            <th>发货员</th>
            <th>发货量</th>
            <th>单价(元/吨)</th>
            <th>总价(元)</th>
            <th>创建时间</th>
            <th>备注</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${dto.list}" var="data" varStatus="i">
            <tr>
                <td class="center">${data.oid}</td>
                <td class="center">${data.customerModel.name}</td>
                <td class="center">${data.trackModel.license}</td>
                <td class="center">${data.dataUserModel.sname}</td>
                <td class="center">${data.volume}</td>
                <td class="center">${data.price}</td>
                <td class="center">${data.total}</td>
                <td class="center">${data.createtime}</td>
                <td class="center">${data.remarks}</td>
                <td class="center"><h5><small><a href="orderinfo.do?oid=${data.oid}">查看</a></small></h5></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="pagination pagination-centered">
    <ul>
        <li><a href="order.do?start=0&rows=${dto.rows}">首页</a></li>
        <c:if test="${dto.start >= dto.rows}">
            <li><a href="order.do?start=${dto.start - dto.rows}&rows=${dto.rows}">上一页</a></li>
        </c:if>
        <li><a href="#">${dto.page}</a></li>
        <c:if test="${dto.count > dto.start + dto.rows}">
            <li><a href="order.do?start=${dto.start + dto.rows}&rows=${dto.rows}">下一页</a></li>
        </c:if>
        <li><a href="order.do?start=${dto.end}&rows=${dto.rows}">尾页</a></li>
    </ul>
    <p class="text-right">
    <ul class="pagination pagination-sm">
        <li></li>
    </ul>
    </p>
</div>
