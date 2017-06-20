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

                                <tr>
                                    <td colspan = "3">
                                        ${urlServices.message == "true" ? "处理结果:成功处理1条" : ""}
                                    </td>
                                </tr>
                                <tr>
                                    <th colspan = "3">
                                        总计${urlServices.numFound}条
                                    </th>
                                </tr>
                            <form method="get" id="editSendServiceUrl" name="editSendServiceUrl"
                                  action="editSendServiceUrl.do"
                                  enctype="multipart/form-data" accept-charset="utf-8">
                                <tr>
                                    <td>
                                        <select id="mark" name="mark" class="form-control" style="width: 100px">
                                                <option value="present">小库简历</option>
                                                <option value="similarPosition">相似职位简历</option>
                                                <option value="rdSearch">大库搜索</option>
                                                <option value="similarResume">相似简历</option>
                                        </select>
                                    </td>
                                    <td>
                                        <textarea style="width: 900px; height: 80px;" rows="1" name="markId" id="markId"
                                                  class="span12"></textarea>
                                    </td>
                                </tr>
                            <c:forEach items="${urlServices.results}"
                                       var="UrlServiceModel" varStatus="i">
                                <tr>
                                    <td>
                                        职位ID
                                    </td>
                                    <td>
                                        ${UrlServiceModel.positionId}
                                        <input type="hidden" id="positionId" name="positionId"  value="${UrlServiceModel.positionId}">
                                    </td>
                                </tr>
                                <c:forEach items="${UrlServiceModel.presentMap}"
                                           var="presentMap" varStatus="i">
                                    <tr>
                                        <td>
                                            小库简历数
                                        </td>
                                        <td>
                                            <textarea style="width: 900px; height: 80px;" rows="1" name="present" id="present"
                                                      class="span12">${presentMap.key}</textarea>
                                        </td>
                                        <td>
                                            <a href="${presentMap.key}" target="_blank">${presentMap.value}
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:forEach items="${UrlServiceModel.similarPositionMap}"
                                           var="similarPositionMap" varStatus="i">
                                    <tr>
                                        <td>
                                            相似职位简历数
                                        </td>
                                        <td>
                                            <textarea style="width: 900px; height: 80px;" rows="1" name="similarPosition" id="similarPosition"
                                                      class="span12">${similarPositionMap.key}</textarea>
                                        </td>
                                        <td>
                                            <a href="javascript:editUrl('${similarPositionMap.key}')" target="_blank">${similarPositionMap.value}
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:forEach items="${UrlServiceModel.rdSearchMap}"
                                       var="rdSearchMap" varStatus="i">
                                <tr>
                                    <td>
                                        大库搜索简历数
                                    </td>
                                    <td>
                                        <textarea style="width: 900px; height: 80px;" rows="1" name="rdSearch" id="rdSearch"
                                                  class="span12">${rdSearchMap.key}</textarea>
                                    </td>
                                    <td>
                                        <a href="${rdSearchMap.key}" target="_blank">${rdSearchMap.value}
                                    </td>
                                </tr>
                                </c:forEach>
                                <c:forEach items="${UrlServiceModel.similarResumeMap}"
                                           var="similarResumeMap" varStatus="i">
                                    <tr>
                                        <td>
                                            相似简历数
                                        </td>
                                        <td>
                                            <textarea style="width: 900px; height: 80px;" rows="1" name="similarResume" id="similarResume"
                                                      class="span12">${similarResumeMap.key}</textarea>
                                        </td>
                                        <td>
                                                ${similarResumeMap.value}
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:forEach>
                                <tr>
                                    <td>
                                        <input  class="btn btn-primary" type="submit" value="修改">
                                    </td>
                                    <td>
                                        <input  class="btn btn-primary" type="button" value="返回" onclick="back()">
                                    </td>
                                </tr>

                            </form>
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