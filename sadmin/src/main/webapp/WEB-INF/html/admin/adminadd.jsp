<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<c:if test="${'1' eq add}">
    <jsp:include page="add.jsp"></jsp:include>
</c:if>
<c:if test="${'2' eq add}">
    <jsp:include page="changepower.jsp"></jsp:include>
</c:if>
<c:if test="${'3' eq add}">
    <jsp:include page="changepass.jsp"></jsp:include>
</c:if>