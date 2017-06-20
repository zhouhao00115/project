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
                        <a href="<%=basePath%>service.do">到面职位微调URL消息</a>
                    </li>
                </ul>
            </div>
            <div class="row-fluid sortable">
                <div class="box span12">
                    <div class="box-header well" data-original-title>
                        <h2>到面职位微调URL管理</h2>
                        <div class="box-icon">
                            <a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
                            <a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
                        </div>
                    </div>
                    <div class="box-content" id="LineMessage">
                        <table>
                            <tr>
                                <td>
                                    职位ID
                                </td>
                                <td>
                                    <input type="text" id="Id" name="positionId" value="${positionNumber.id}">
                                </td>
                                <td>
                                    <input  class="btn btn-primary" type="button" value="search" onclick="quirybyId()">
                                </td>
                            </tr>
                        </table>
                        <table class="table table-condensed" id="table-001">
                            <form method="get" id="sendServiceUrl" name="sendServiceUrl"
                                  action="sendServiceUrl.do"
                                  enctype="multipart/form-data" accept-charset="utf-8">
                                <tr>
                                    <td>
                                        <textarea style="width: 200px; height: 20px;" rows="1" name="positionId" id="positionId"
                                                  class="span12"></textarea>
                                    </td>
                                    <td>
                                        <textarea style="width: 700px; height: 20px;" rows="1" name="url" id="url"
                                                  class="span12"></textarea>
                                    </td>
                                    <td colspan = "2">
                                        <input  class="btn btn-primary" type="submit" value="添加">
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan = "4">
                                        ${presentServices.message == "true" ? "处理结果:成功处理1条" : ""}
                                    </td>
                                </tr>
                                <tr>
                                    <th colspan = "4">
                                        总计${presentServices.numFound}条
                                    </th>
                                </tr>
                            </form>
                                <tr>
                                    <td>
                                        职位ID
                                    </td>
                                    <td>
                                        职位URL
                                    </td>
                                    <td colspan = "2">
                                        操作
                                    </td>
                                </tr>
                                <c:forEach begin="0" end="100" items="${presentServices.results}"
                                           var="presentServiceModel" varStatus="i">
                                <tr>
                                    <td>
                                        ${presentServiceModel.id}
                                    </td>
                                    <td>
                                        <textarea style="width: 700px; height: 80px;" rows="1"
                                                  name="${presentServiceModel.id}" id="${presentServiceModel.id}"
                                                  class="span12">${presentServiceModel.url}</textarea>
                                    </td>
                                    <td>
                                        <input type="button" value="修改" onclick="setPresentService('${presentServiceModel.id}')">
                                    </td>
                                    <td>
                                        <input type="button" value="删除" onclick="deletePresentService('${presentServiceModel.id}')">
                                    </td>
                                </tr>
                                </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<%=basePath %>/js/presentService.js"></script>
</body>
</html>