<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<form  method="GET" id="eapiapply_form" name="eapiapply_form" action="eapiapply.do">
    <table class="table table-striped">
        <thead>
        <tr class="span6">
            职位编号:<input type="text" name="positionId" value='${positionId}' style="width:200px"/>
            <input class="btn btn-primary" type="submit" value="search" />
        </tr>
        <tr>
            <th>日期</th>
            <th>城市</th>
            <th>职位小类</th>
            <th>应聘量</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${items}" var="item" varStatus="i">
            <tr>
                <td class="center" id="topic${i.index}">${item.positionId}</td>
                <td class="center" id="groupName${i.index}">${item.city}</td>
                <td class="center" id="backupTime${i.index}">${item.subJobType}</td>
                <td class="center" id="message${i.index}">${item.applyCount}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>