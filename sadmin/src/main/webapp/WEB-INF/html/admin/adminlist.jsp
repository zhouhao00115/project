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
            <th colspan="3">
                总计 ${dto.count} 条，其中管理员 ${dto.adminCountDto.admin} 条，只读用户 ${dto.adminCountDto.read} 条
            </th>
            <th colspan="2">
                <h6>每页显示
                    <small>
                        <a href="admin.do?rows=10">10</a>
                    </small>
                    <small>
                        <a href="admin.do?rows=30">30</a>
                    </small>
                    <small>
                        <a href="admin.do?rows=50">50</a>
                    </small>
                    <small>
                        <a href="admin.do?rows=100">100</a>
                    </small>
                    条
                </h6>
            </th>
            <th colspan="1">
                <c:if test="${'admin' eq sessionScope.username}">
                    <li class="btn"><a href="addadmin.do">新增用户</a></li>
                </c:if>
            </th>
        </tr>
        <tr>
            <th>编号</th>
            <th>用户名</th>
            <th>权限</th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${dto.list}" var="data" varStatus="i">
                <tr>
                    <td class="center" id="id${i.index}">${data.id}</td>
                    <td class="center" id="name${i.index}">
                            ${data.username}
                    </td>
                    <td class="center" id="power${i.index}">
                        <c:if test="${'1' eq data.power}">管理</c:if>
                        <c:if test="${'2' eq data.power}">查看</c:if>
                    </td>
                    <td></td>
                    <td ></td>
                    <c:if test="${data.username eq sessionScope.username}">
                        <td class="center"><h5><small><a href="addadmin.do?id=${data.id}">修改密码</a></small></h5></td>
                    </c:if>
                    <c:if test="${data.username ne sessionScope.username}">
                        <td class="center"><h5><small><a href="addadmin.do?id=${data.id}">修改权限</a></small></h5></td>
                    </c:if>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<div class="pagination pagination-centered">
    <ul>
        <li><a href="admin.do?start=0&rows=${dto.rows}">首页</a></li>
        <c:if test="${dto.start >= dto.rows}">
            <li><a href="admin.do?start=${dto.start - dto.rows}&rows=${dto.rows}">上一页</a></li>
        </c:if>
        <li><a href="#">${dto.page}</a></li>
        <c:if test="${dto.count > dto.start + dto.rows}">
            <li><a href="admin.do?start=${dto.start + dto.rows}&rows=${dto.rows}">下一页</a></li>
        </c:if>
        <li><a href="admin.do?start=${dto.end}&rows=${dto.rows}">尾页</a></li>
    </ul>
    <p class="text-right">
        <ul class="pagination pagination-sm">
            <li></li>
        </ul>
    </p>
</div>
