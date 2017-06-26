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
        <th colspan="1">
            状态：
        </th>
        <th colspan="4">
            <select name="state" style="width:70px" class="btn btn-default btn-sm">
                <option value="0" <c:if test="${'0' eq state}">selected</c:if> >全部</option>
                <option value="1" <c:if test="${'1' eq state}">selected</c:if> >充足</option>
                <option value="2" <c:if test="${'2' eq state}">selected</c:if> >正常</option>
                <option value="3" <c:if test="${'3' eq state}">selected</c:if> >缺货</option>
                <option value="4" <c:if test="${'4' eq state}">selected</c:if> >急缺</option>
            </select>
        </th>
    </tr>
    <tr>
        <th>编号</th>
        <th>牧场</th>
        <th>交奶地</th>
        <th>月饲料用量(吨)</th>
        <th>库存剩余(吨)</th>
        <th>状态</th>
        <th>价格表运费(元/吨)</th>
        <th>备注</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${dto.list}" var="muchang" varStatus="i">
        <tr
        <c:if test="${muchang.state == 3}">style="color:#ff4b46"</c:if>
        <c:if test="${muchang.state == 4}">style="color:#ff0428"</c:if>>
            <td class="center" id="id${i.index}">${muchang.id}</td>
            <td class="center" id="name${i.index}">${muchang.name}</td>
            <td class="center" id="city${i.index}">${muchang.city}</td>
            <td class="center" id="used${i.index}">${muchang.used}</td>
            <td class="center" id="left${i.index}">${muchang.left}</td>
            <td class="center" id="state${i.index}">
                <c:if test="${muchang.state == 1}">充足</c:if>
                <c:if test="${muchang.state == 2}">正常</c:if>
                <c:if test="${muchang.state == 3}">缺货</c:if>
                <c:if test="${muchang.state == 4}">急缺</c:if>
            </td>
            <td class="center" id="freight${i.index}">${muchang.freight}</td>
            <td class="center" id="remark${i.index}">${muchang.remark}</td>
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
</div>