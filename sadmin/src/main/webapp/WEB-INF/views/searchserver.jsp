<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<jsp:include page="global/head.jsp"></jsp:include>

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
				<ul class="breadcrumb">
					<li>
						<a href="<%=basePath%>index.do">Home</a> <span class="divider">/</span>
					</li>
					<li>
						<a href="<%=basePath%>index.do">服务器管理</a>
					</li>
				</ul>
			</div>
            <!-- content ends -->
            <div class="row-fluid sortable">
                <div class="box span12">
                    <div class="box-header well" data-original-title>
                        <h2><i class="icon-list"></i> 引擎列表</h2>
                        <div class="box-icon">
                            <a href="#addModal" data-toggle="modal" class="btn btn-setting btn-round"><i class="icon-plus"></i></a>
                            <a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
                            <a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
                        </div>
                    </div>
                    <div class="box-content">
                        <br>
                        <select name="server" onchange="location.href='searchserver.do?server='+this.options[this.options.selectedIndex].value">
                            <option value="query" <c:if test="${'query' eq server}">selected</c:if>>query</option>
                            <option value="backup" <c:if test="${'backup' eq server}">selected</c:if>>backup</option>
                        </select>
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th colspan="4">
                                    numFound总计 ${numFoundTotal} 条,  companyCount总计 ${companyCountTotal} 条
                                </th>
                            </tr>
                            <tr>
                                <th>SolrAddress</th>
                                <th>CoreName</th>
                                <th>NumFound</th>
                                <th>CompanyCount</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${serverCores}" var="serverCore" varStatus="i">
                                <tr>
                                    <td class="center" id="solrAddress${i.index}"><a href="searchenterprise.do?solrAddress=${serverCore.solrAddress}">${serverCore.solrAddress}</a></td>
                                    <td class="center" id="coreName${i.index}"><a href="searchenterprise.do?solrAddress=${serverCore.solrAddress}&coreName=${serverCore.coreName}">${serverCore.coreName}</a></td>
                                    <td class="center" id="numFound${i.index}">${serverCore.numFound}</td>
                                    <td class="center" id="numFound${i.index}">${serverCore.companyCount}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                        <div class="pagination pagination-centered">
                            <ul>
                                <c:forEach items="${num}" var="n">
                                    <c:choose>
                                        <c:when test="${n==current }">
                                            <li class="active">
                                                <a href="searchserver.do?server=${server}&adr=${n}" >${n }</a>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <li><a href="searchserver.do?server=${server}&adr=${n}" >${n }</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </ul>
                        </div>

                    </div>
                </div>
            </div>

            </div><!--/#content.span10-->

		</div><!--/fluid-row-->
		<jsp:include page="global/foot.jsp"></jsp:include>

	</div><!--/.fluid-container-->

	<!-- external javascript
	================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->

	<!-- jQuery -->
	<script src="<%=basePath %>js/jquery-1.7.2.min.js"></script>
	<!-- jQuery UI -->
	<script src="<%=basePath %>js/jquery-ui-1.8.21.custom.min.js"></script>
	<!-- transition / effect library -->
	<script src="<%=basePath %>js/bootstrap-transition.js"></script>
	<!-- alert enhancer library -->
	<script src="<%=basePath %>js/bootstrap-alert.js"></script>
	<!-- modal / dialog library -->
	<script src="<%=basePath %>js/bootstrap-modal.js"></script>
	<!-- custom dropdown library -->
	<script src="<%=basePath %>js/bootstrap-dropdown.js"></script>
	<!-- scrolspy library -->
	<script src="<%=basePath %>js/bootstrap-scrollspy.js"></script>
	<!-- library for creating tabs -->
	<script src="<%=basePath %>js/bootstrap-tab.js"></script>
	<!-- library for advanced tooltip -->
	<script src="<%=basePath %>js/bootstrap-tooltip.js"></script>
	<!-- popover effect library -->
	<script src="<%=basePath %>js/bootstrap-popover.js"></script>
	<!-- button enhancer library -->
	<script src="<%=basePath %>js/bootstrap-button.js"></script>
	<!-- accordion library (optional, not used in demo) -->
	<script src="<%=basePath %>js/bootstrap-collapse.js"></script>
	<!-- carousel slideshow library (optional, not used in demo) -->
	<script src="<%=basePath %>js/bootstrap-carousel.js"></script>
	<!-- autocomplete library -->
	<script src="<%=basePath %>js/bootstrap-typeahead.js"></script>
	<!-- tour library -->
	<script src="<%=basePath %>js/bootstrap-tour.js"></script>
	<!-- library for cookie management -->
	<script src="<%=basePath %>js/jquery.cookie.js"></script>
	<!-- calander plugin -->
	<script src='<%=basePath %>js/fullcalendar.min.js'></script>
	<!-- data table plugin -->
	<script src='<%=basePath %>js/jquery.dataTables.min.js'></script>

	<!-- chart libraries start -->
	<script src="<%=basePath %>js/excanvas.js"></script>
	<script src="<%=basePath %>js/jquery.flot.min.js"></script>
	<script src="<%=basePath %>js/jquery.flot.pie.min.js"></script>
	<script src="<%=basePath %>js/jquery.flot.stack.js"></script>
	<script src="<%=basePath %>js/jquery.flot.resize.min.js"></script>
	<!-- chart libraries end -->

	<!-- select or dropdown enhancer -->
	<script src="<%=basePath %>js/jquery.chosen.min.js"></script>
	<!-- checkbox, radio, and file input styler -->
	<script src="<%=basePath %>js/jquery.uniform.min.js"></script>
	<!-- plugin for gallery image view -->
	<script src="<%=basePath %>js/jquery.colorbox.min.js"></script>
	<!-- rich text editor library -->
	<script src="<%=basePath %>js/jquery.cleditor.min.js"></script>
	<!-- notification plugin -->
	<script src="<%=basePath %>js/jquery.noty.js"></script>
	<!-- file manager library -->
	<script src="<%=basePath %>js/jquery.elfinder.min.js"></script>
	<!-- star rating plugin -->
	<script src="<%=basePath %>js/jquery.raty.min.js"></script>
	<!-- for iOS style toggle switch -->
	<script src="<%=basePath %>js/jquery.iphone.toggle.js"></script>
	<!-- autogrowing textarea plugin -->
	<script src="<%=basePath %>js/jquery.autogrow-textarea.js"></script>
	<!-- multiple file upload plugin -->
	<script src="<%=basePath %>js/jquery.uploadify-3.1.min.js"></script>
	<!-- history.js for cross-browser state change on ajax -->
	<script src="<%=basePath %>js/jquery.history.js"></script>
	<!-- application script for Charisma demo -->
	<script src="<%=basePath %>js/charisma.js"></script>

	<script src="<%=basePath %>js/monitor.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/c.js"></script>
</body>
</html>