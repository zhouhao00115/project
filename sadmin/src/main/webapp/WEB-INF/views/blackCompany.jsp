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
    <jsp:include page="global/lift_menus.jsp"></jsp:include>
    <div id="content" class="span10">
      <!-- content starts -->

      <div>
        <ul class="breadcrumb" id="title-001">
          <li>
            <a href="<%=basePath%>index.do">Home</a> <span class="divider">/</span>
          </li>
          <li>
            <a href="<%=basePath%>blackCompany.do">投诉公司处理</a>
          </li>
        </ul>
      </div>
      <div class="row-fluid sortable">
        <div class="box span12">
          <div class="box-header well" data-original-title>
            <h2>投诉公司处理</h2>
          </div>
          <div class="box-content" id="LineMessage">
            <table>
              <tr>
                <td>
                  公司ID
                </td>
                <td>
                  <input type="text" id="conpanyId" name="conpanyId" value="">
                </td>
                <td>
                  <input  class="btn btn-primary" type="button" value="查询" onclick="query()">
                </td>
                <td>
                  <input  class="btn btn-primary" type="button" value="解除" onclick="query()">
                </td>
              </tr>
            </table>
            <table class="table table-condensed" id="positiontable">
              <tr>
                <th colspan="16">
                  总计${blackCompanyResult.number}条
                </th>
              </tr>
              <tr>
                <td>
                  职位ID
                </td>
                <td>
                  C端投诉
                </td>
              </tr>
              <c:forEach items="${blackCompanyResult.results}" var="blackCompanyModel">
              <tr>
                <td>
                    ${blackCompanyModel.positionId}
                </td>
                <td>
                    ${blackCompanyModel.remark}
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
<script src="<%=basePath %>/js/blackCompany.js"></script>
</body>
</html>



