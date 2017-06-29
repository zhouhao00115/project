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
                        <a href="track.do?rows=10">10</a>
                    </small>
                    <small>
                        <a href="track.do?rows=30">30</a>
                    </small>
                    <small>
                        <a href="track.do?rows=50">50</a>
                    </small>
                    <small>
                        <a href="track.do?rows=100">100</a>
                    </small>
                    条
                </h6>
            </th>
            <th colspan="1">
                <c:if test="${'1' eq sessionScope.power}">
                    <li class="btn"><a href="addtrack.do">新增货车</a></li>
                </c:if>
            </th>
        </tr>
        <tr>
            <th>编号</th>
            <th>车牌号</th>
            <th>姓名</th>
            <th>电话</th>
            <th>车型</th>
            <th>载货量(吨)</th>
            <th>备注</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${dto.list}" var="track" varStatus="i">
            <tr>
                <td class="center">${track.tid}</td>
                <td class="center">${track.license}</td>
                <td class="center">${track.tname}</td>
                <td class="center">${track.tphone}</td>
                <td class="center">${track.ttype}</td>
                <td class="center">${track.capacity}</td>
                <td class="center">${track.remarks}</td>
                <td class="center"><h5><small><a href="trackinfo.do?tid=${track.tid}">查看</a></small></h5></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="pagination pagination-centered">
    <ul>
        <li><a href="track.do?start=0&rows=${dto.rows}">首页</a></li>
        <c:if test="${dto.start >= dto.rows}">
            <li><a href="track.do?start=${dto.start - dto.rows}&rows=${dto.rows}">上一页</a></li>
        </c:if>
        <li><a href="#">${dto.page}</a></li>
        <c:if test="${dto.count > dto.start + dto.rows}">
            <li><a href="track.do?start=${dto.start + dto.rows}&rows=${dto.rows}">下一页</a></li>
        </c:if>
        <li><a href="track.do?start=${dto.end}&rows=${dto.rows}">尾页</a></li>
    </ul>
    <p class="text-right">
    <ul class="pagination pagination-sm">
        <li></li>
    </ul>
    </p>
</div>
