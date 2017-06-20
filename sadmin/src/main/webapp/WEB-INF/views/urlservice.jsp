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
                                    <input type="text" id="urlPositionId" name="urlPositionId" value="">
                                </td>
                                <td>
                                    <input  class="btn btn-primary" type="button" value="search" onclick="quiryByUrlId()">
                                </td>
                            </tr>
                        </table>
                        <table class="table table-condensed" id="table-001">
                            <form method="get" id="sendUrlService" name="sendUrlService"
                                  action="sendUrlService.do"
                                  enctype="multipart/form-data" accept-charset="utf-8">
                                <tr>
                                    <td>
                                        <textarea style="width: 200px; height: 20px;" rows="1" name="urlId" id="urlId"
                                                  class="span12"></textarea>
                                    </td>
                                    <td colspan = "4">
                                        <textarea style="width: 700px; height: 20px;" rows="1" name="url" id="url"
                                                  class="span12"></textarea>
                                    </td>
                                    <td>
                                        <input  class="btn btn-primary" type="submit" value="添加">
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan = "6">
                                        ${urlServices.message == "true" ? "处理结果:成功处理1条" : ""}
                                    </td>
                                </tr>
                                <tr>
                                    <th colspan = "6">
                                        总计${urlServices.numFound}条
                                    </th>
                                </tr>
                            </form>
                            <tr>
                                <td>
                                    职位ID
                                </td>
                                <td>
                                    小库简历数
                                </td>
                                <td>
                                    相似职位简历数
                                </td>
                                <td>
                                    大库搜索简历数
                                </td>
                                <td>
                                    相似简历数
                                </td>
                                <td colspan="2">
                                    操作
                                </td>
                            </tr>
                            <c:forEach begin="0" end="100" items="${urlServices.results}"
                                       var="UrlServiceModel" varStatus="i">
                                <tr>
                                    <td>
                                        ${UrlServiceModel.positionId}
                                    </td>
                                    <td>
                                        ${UrlServiceModel.presentNumber}
                                    </td>
                                    <td>
                                        ${UrlServiceModel.similarPositionNumber}
                                    </td>
                                    <td>
                                        ${UrlServiceModel.rdSearchNumber}
                                    </td>
                                    <td>
                                        ${UrlServiceModel.similarResumeNumber}
                                    </td>
                                    <td>
                                        <input  class="btn btn-primary" type="button" value="编辑" onclick="editByUrlId('${UrlServiceModel.positionId}')">
                                    </td>
                                    <td>
                                        <input  class="btn btn-primary" type="button" value="删除" onclick="deleteByUrlId('${UrlServiceModel.positionId}')">
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