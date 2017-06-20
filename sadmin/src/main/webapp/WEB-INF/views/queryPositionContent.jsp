<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

%>
<!DOCTYPE html>
<html lang="zh-CN">
<jsp:include page="global/head.jsp"></jsp:include>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <script src="<%=basePath %>js/jquery-1.9.1.js"></script>
    <script src="<%=basePath %>js/jquery-ui.js"></script>
</head>
<body>
<jsp:include page="global/navbar.jsp"></jsp:include>
<br><br><br>
<!-- topbar ends -->
<div class="container-fluid">
    <div class="row-fluid">
        <jsp:include page="global/lift_menu.jsp"></jsp:include>
        <div id="content" class="span10">
            <!-- content starts -->

            <div>
                <ul class="breadcrumb" id="title-001">
                    <li>
                        <a href="<%=basePath%>index.do">Home</a> <span class="divider">/</span>
                    </li>
                    <li>
                        <a href="<%=basePath%>daomian.do">到面职位消息</a>
                    </li>
                </ul>
            </div>
            <div class="row-fluid sortable">
                <div class="box span12">
                    <div class="box-header well" data-original-title>
                        <h2>到面职位管理</h2>
                        <div class="box-icon">
                            <a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
                            <a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
                        </div>
                    </div>
                    <div class="box-content" id="LineMessage">
                        <table class="table table-condensed" id="table-001">
                            <form method="get" id="queryposiotioncontent" name="queryposiotioncontent"
                                  action="submitPositionContent.do"
                                  enctype="multipart/form-data" accept-charset="utf-8">
                                <input type="hidden" id="curPage" name="curPage"  value="${positionNumber.curPage}">
                                <input type="hidden" id="name" name="name"  value="${positionNumber.name}">
                                <input type="hidden" id="exclude" name="exclude"  value="${positionNumber.exclude}">
                                <c:forEach items="${positionNumber.positionContentList}" var="positionContent" varStatus="i">
                                <tr>
                                    <td>
                                        职位Id
                                    </td>
                                    <td>
                                        ${positionContent.id}
                                        <input type="hidden" id="id" name="id" value="${positionContent.id}">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        职位名称
                                    </td>
                                    <td>
                                        ${positionContent.name}
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        职位名称分词
                                    </td>
                                    <td>
                                        ${positionContent.nameWord}
                                        <input type="hidden" id="nameWord" name="nameWord"  value="${positionContent.nameWord}">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        职位详情
                                    </td>
                                    <td>
                                        ${positionContent.content}
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        职位详情分词
                                    </td>
                                    <td>
                                        <textarea style="width: 950px; height: 80px;" rows="4" name="contentWord" id="contentWord"
                                                  class="span12">${positionContent.contentWord}</textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input  class="btn btn-primary" type="submit" value="submit">
                                    </td>
                                     <td><a class="btn btn-primary"
                                            href="<%=basePath%>daomian.do?name=${positionNumber.name}&start=${positionNumber.curPage*20-20}&exclude=${positionNumber.exclude}">back</a>
                                     </td>
                                </tr>
                                <tr>
                                    <td colspan = "2">
                                            ${positionNumber.pageCount == "" ? "处理结果:成功处理1条" : ""}
                                    </td>
                                </tr>
                                </c:forEach>
                            </form>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<%=basePath %>/js/monitor.js"></script>
</body>
</html>