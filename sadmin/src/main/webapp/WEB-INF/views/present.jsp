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
                        <a href="<%=basePath%>present.do">到面职位消息</a>
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
                            <table>
                                <tr>
                                    <td>
                                         职位ID
                                    </td>
                                    <td>
                                        <input type="text" id="markid" name="markid" value="${positionNumber.id}">
                                    </td>
                                    <td>
                                        职位名称
                                    </td>
                                    <td>
                                        <input type="text" id="markname" name="markname" value="${positionNumber.name}">
                                    </td>
                                    <td>
                                        <input type="checkbox" id="testCompany" name="testCompany" ${positionNumber.exclude}>排除测试职位
                                    </td>
                                    <td>
                                        <input  class="btn btn-primary" type="button" value="search" onclick="quirybyname()">
                                    </td>
                                </tr>
                            </table>
                            <table class="table table-condensed" id="positiontable">
                                <tr>
                                    <th colspan="16">
                                        职位总计${positionNumber.count}条
                                    </th>
                                </tr>
                                <tr>
                                    <td>
                                        职位ID
                                    </td>
                                    <td>
                                        职位名称
                                    </td>
                                    <td bgcolor=#E6E6E6>
                                        发布城市
                                    </td>
                                    <td>
                                        发布时间
                                    </td>
                                    <td bgcolor=#E6E6E6>
                                        套餐余量
                                    </td>
                                    <td>
                                        可推送的简历数
                                    </td>
                                    <td>
                                        产品规则可显示的简历数
                                    </td>
                                    <td>
                                        HR确认邀请数
                                    </td>
                                    <td>
                                        HR拒绝数
                                    </td>
                                    <td>
                                        HR邀请的投递数
                                    </td>
                                    <td bgcolor=#E6E6E6>
                                        过滤后的简历数
                                    </td>
                                    <td bgcolor=#E6E6E6>
                                        投递数
                                    </td>
                                    <td>
                                        同行业应聘
                                    </td>
                                    <td>
                                        大库搜索
                                    </td>
                                    <td>
                                        根据确认简历推荐的相似简历数
                                    </td>
                                    <td bgcolor=#E6E6E6>
                                        C端接受数
                                    </td>
                                    <td bgcolor=#E6E6E6>
                                        C端拒绝数
                                    </td>
                                    <td bgcolor=#E6E6E6>
                                        C端待处理数
                                    </td>
                                </tr>
                                 <c:forEach items="${positionNumber.positionContentList}" var="positionContent" varStatus="i">
                                     <tr>
                                         <td>
                                             ${positionContent.id}
                                         </td>
                                         <td>
                                             <a href="http://jobs.zhaopin.com/${positionContent.id}.htm" target="_blank">
                                             ${positionContent.name}
                                             </a>
                                         </td>
                                         <td bgcolor=#E6E6E6>
                                              ${positionContent.city}
                                         </td>
                                         <td>
                                              ${positionContent.createTime}
                                         </td>
                                         <td bgcolor=#E6E6E6>
                                             ${positionContent.packageCount}
                                         </td>
                                         <td>
                                             ${positionContent.optionalNumber}
                                         </td>
                                         <td>
                                             ${positionContent.showNumber}
                                         </td>
                                         <td>
                                             ${positionContent.inviteNumber}
                                         </td>
                                         <td>
                                             ${positionContent.rejectNumber}
                                         </td>
                                         <td>
                                             ${positionContent.hrInviteApplyNumber}
                                         </td>
                                         <td bgcolor=#E6E6E6>
                                             ${positionContent.applyNumber}
                                         </td>
                                         <td bgcolor=#E6E6E6>
                                             ${positionContent.realApplyNumber}
                                         </td>
                                         <td>
                                             ${positionContent.employNumber}
                                         </td>
                                         <td>
                                             ${positionContent.rdsearchNumber}
                                         </td>
                                         <td>
                                             ${positionContent.similarNumber}
                                         </td>
                                         <td bgcolor=#E6E6E6>
                                             ${positionContent.clientAcceptNumber}
                                         </td>
                                         <td bgcolor=#E6E6E6>
                                             ${positionContent.clientRejectNumber}
                                         </td>
                                         <td bgcolor=#E6E6E6>
                                             ${positionContent.clientUndisposedNumber}
                                         </td>
                                     </tr>
                                 </c:forEach>
                            </table>
                            <div class="box-content" id="pageNumber">
                                <table class="table table-condensed" id="pagenumbertable" align="right">
                                    <tr align="right">
                                        <td>
                                            <a href="present.do?id=${positionNumber.id}&name=${positionNumber.name}&start=0&exclude=${positionNumber.exclude}">首页</a>
                                            <a href="present.do?id=${positionNumber.id}&name=${positionNumber.name}&start=${(positionNumber.curPage*20-40)>-1?(positionNumber.curPage*20-40):0}&exclude=${positionNumber.exclude}">上页</a>
                                            <a href="present.do?id=${positionNumber.id}&name=${positionNumber.name}&start=${(positionNumber.curPage*20)<(positionNumber.pageCount*20-20)
                                                ?(positionNumber.curPage*20):(positionNumber.pageCount*20-20)}&exclude=${positionNumber.exclude}">下页</a>
                                            <a href="present.do?id=${positionNumber.id}&name=${positionNumber.name}&start=${positionNumber.pageCount*20-20}&exclude=${positionNumber.exclude}">尾页</a>
                                            第${positionNumber.curPage}/${positionNumber.pageCount}页,去
                                            <input style="width: 20px; height: 15px;" type="text" id="goalnumber" name="goalnumber" value="">
                                            页
                                            <input type="button" value="Go" onclick="goalnumber(${positionNumber.pageCount})">
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<%=basePath %>/js/daomian.js"></script>
</body>
</html>



