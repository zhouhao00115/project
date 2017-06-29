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
                <li class="btn"><a href="track.do">返回</a></li>
            </th>
            <th>
                <c:if test="${'1' eq sessionScope.power}">
                    <a href="addtrack.do?tid=${dto.tid}">修改货车信息</a>
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
            <td class="center" width="170">编号</td>
            <td class="center">${dto.tid}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">车牌号</td>
            <td class="center">${dto.license}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">车主姓名</td>
            <td class="center">${dto.tname}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">电话</td>
            <td class="center">${dto.tphone}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">货车类型</td>
            <td class="center">${dto.ttype}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">载货量(吨)</td>
            <td class="center">${dto.capacity}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">车主期望城市</td>
            <td class="center">${dto.citys}</td>
            <td></td>
            <td></td>
        </tr><tr>
            <td class="center">备注</td>
            <td class="center">${dto.remarks}</td>
            <td></td>
            <td></td>
        </tr>
        </tbody>
    </table>
</div>