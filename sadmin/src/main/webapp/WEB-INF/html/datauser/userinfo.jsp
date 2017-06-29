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
                <li class="btn"><a href="datauser.do">返回</a></li>
            </th>
            <th>
                <c:if test="${'1' eq sessionScope.power}">
                    <a href="adddatauser.do?staffid=${dto.staffid}">修改数据员信息</a>
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
            <td class="center" id="staffid">${dto.staffid}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">数据员</td>
            <td class="center" id="name">${dto.sname}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">电话</td>
            <td class="center" id="sphone">${dto.sphone}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">性别</td>
            <td class="center" id="gender">
                <c:if test="${'1' eq dto.gender}">男</c:if>
                <c:if test="${'2' eq dto.gender}">女</c:if>
            </td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">备注</td>
            <td class="center" id="remarks">${dto.remarks}</td>
            <td></td>
            <td></td>
        </tr>
        </tbody>
    </table>
</div>