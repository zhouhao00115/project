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
                        <a href="datauser.do?rows=10">10</a>
                    </small>
                    <small>
                        <a href="datauser.do?rows=30">30</a>
                    </small>
                    <small>
                        <a href="datauser.do?rows=50">50</a>
                    </small>
                    <small>
                        <a href="datauser.do?rows=100">100</a>
                    </small>
                    条
                </h6>
            </th>
            <th colspan="1">
                <li class="btn"><a href="datauseradd.do">新增数据员</a></li>
            </th>
        </tr>
        <tr>
            <th>编号</th>
            <th>姓名</th>
            <th>电话</th>
            <th>性别</th>
            <th>备注</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${dto.list}" var="data" varStatus="i">
            <tr>
                <td class="center" id="id${i.index}">${data.staffid}</td>
                <td class="center" id="name${i.index}">
                    ${data.sname}
                </td>
                <td class="center" id="sphone${i.index}">${data.sphone}</td>
                <td class="center" id="gender${i.index}">
                    <c:if test="${'1' eq data.gender}">男</c:if>
                    <c:if test="${'2' eq data.gender}">女</c:if>
                </td>
                <td class="center" id="remarks${i.index}">${data.remarks}</td>
                <td class="center"><h5><small><a href="customerinfo.do?number=${data.staffid}">修改</a></small></h5></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="pagination pagination-centered">
    <ul>
        <li><a href="#">首页</a></li>
        <li><a href="#">上一页</a></li>
        <li><a href="#">下一页</a></li>
        <li><a href="#">尾页</a></li>
    </ul>
    <p class="text-right">
    <ul class="pagination pagination-sm">
        <li></li>
    </ul>
    </p>
</div>
