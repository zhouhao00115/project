<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<div class="table-responsive">
    <table class="table table-striped table-condensed">
        <thead>
        <tr>
            <th colspan="2">
                <li class="btn"><a href="order.do">返回</a></li>
            </th>
            <th>
                <c:if test="${'1' eq sessionScope.power}">
                    <a href="addorder.do?oid=${dto.oid}">修改订单信息</a>
                </c:if>
            </th>
            <th>
                <%--<c:if test="${'1' eq sessionScope.power}">--%>
                <%--<li class="btn" data-toggle="modal" data-target="#delete">删除该条</li>--%>
                <%--</c:if>--%>
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="center" width="170">订单编号</td>
            <td class="center">${dto.oid}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center" width="170">发货量（吨）</td>
            <td class="center">${dto.volume}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center" width="170">单价(元/吨)</td>
            <td class="center">${dto.price}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center" width="170">总价</td>
            <td class="center">${dto.total}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">牧场编号</td>
            <td class="center">${dto.customerModel.cid}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">牧场名称</td>
            <td class="center">${dto.customerModel.name}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">牧场主</td>
            <td class="center">${dto.customerModel.cname}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">牧场电话</td>
            <td class="center">${dto.customerModel.cphone}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">牧场规模</td>
            <td class="center">${dto.customerModel.scale}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">月使用量（吨/月）</td>
            <td class="center">${dto.customerModel.used}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">库存剩余量</td>
            <td class="center">${dto.customerModel.left}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">货车编号</td>
            <td class="center">${dto.trackModel.tid}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">车牌号</td>
            <td class="center">${dto.trackModel.license}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">车主</td>
            <td class="center">${dto.trackModel.tname}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">车主电话</td>
            <td class="center">${dto.trackModel.tphone}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">车型</td>
            <td class="center">${dto.trackModel.ttype}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">容量(吨)</td>
            <td class="center">${dto.trackModel.capacity}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">数据员编号</td>
            <td class="center">${dto.dataUserModel.staffid}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">数据员姓名</td>
            <td class="center">${dto.dataUserModel.sname}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">数据员电话</td>
            <td class="center">${dto.dataUserModel.sphone}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center" width="170">备注</td>
            <td class="center">${dto.remarks}</td>
            <td></td>
            <td></td>
        </tr>
        </tbody>
    </table>
</div>