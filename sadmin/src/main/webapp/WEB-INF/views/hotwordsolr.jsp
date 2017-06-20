<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<form method="get" id="solrListForm" name="solrListForm" action="solrmessage.do"
      enctype="multipart/form-data" accept-charset="utf-8">
<table>
        <tr>
            <td>solr服务器：</td>
            <td>
                <select id="service" name="service" class="form-control" style="width: 160px">
                    <c:forEach items="${solrService}" var="service" varStatus="i">
                        <option value=${service}
                                <c:if test="${service.equals(serv)}">
                                        selected
                        </c:if>
                                > ${service} </option>
                    </c:forEach>
                </select>
            </td>
            <td>索引：</td>
            <td>
                <select id="index" name="index" class="form-control" style="width: 160px">
                    <c:forEach items="${solrIndex}" var="index" varStatus="i">
                        <option value=${index}
                                <c:if test="${index.equals(index)}">
                                        selected
                        </c:if>
                                > ${index} </option>
                    </c:forEach>
                </select>
            </td>
            <td>
                key
            </td>
            <td>
                <input type="text" id="hotwordsolrkey" name="hotwordsolrkey" value="${key}">
            </td>
            <td>
                word
            </td>
            <td>
                <input type="text" id="word" name="word" value="${word}">
            </td>
            <td>
                <input class="btn btn-primary" type="button" value="search" id="search"
                       name = "search" onclick="searchFun()">
            </td>
            <td>
                <input class="btn btn-danger" type="button" value="delete" id="delete"
                       name = "delete" onclick="deleteFun()">
            </td>

        </tr>
        <tr>
            <td colspan="10">
                <textarea style="width: 1050px; height: 80px;" rows="4" name="solrMessageBody" id="solrMessageBody"
                          class="span12">${redisvalue}</textarea>
            </td>
        </tr>
        <tr>
            <td>
                 <input class="btn btn-primary" type="submit" value="添加">
            </td>
        </tr>
</table>
</form>
<script src="<%=basePath %>/js/solrmonitor.js"></script>