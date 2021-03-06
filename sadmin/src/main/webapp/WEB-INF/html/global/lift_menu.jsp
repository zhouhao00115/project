<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- left menu starts -->
<div class="span2 main-menu-span">
    <div class="well nav-collapse sidebar-nav">
        <ul class="nav nav-tabs nav-stacked main-menu">
            <li class="nav-header hidden-tablet ">Main</li>
            <li><a class="ajax-link" href="customer.do"><i class="icon-home"></i><span
                    class="hidden-tablet"> 牧场管理</span></a></li>
            <li><a class="ajax-link" href="track.do"><i class="icon-plane"></i><span
                    class="hidden-tablet"> 货车管理</span></a></li>
            <li><a class="ajax-link" href="datauser.do"><i class="icon-user"></i><span
                    class="hidden-tablet"> 数据员管理</span></a></li>
            <li><a class="ajax-link" href="order.do"><i class="icon-briefcase"></i><span
                    class="hidden-tablet"> 货运订单</span></a></li>
            <li><a class="ajax-link" href="charts.do"><i class="icon-signal"></i><span
                    class="hidden-tablet"> 报表系统</span></a></li>
            <c:if test="${'admin' eq sessionScope.username}">
                <li><a class="ajax-link" href="admin.do"><i class="icon-wrench"></i><span
                        class="hidden-tablet"> 权限管理</span></a></li>
            </c:if>
            <c:if test="${'admin' ne sessionScope.username}">
                <li><a class="ajax-link" href="addadmin.do?id=${sessionScope.id}"><i class="icon-wrench"></i><span
                        class="hidden-tablet"> 用户管理</span></a></li>
            </c:if>
        </ul>
    </div>
    <!--/.well -->
</div>
<!--/span-->
<!-- left menu ends -->
<noscript>
    <div class="alert alert-block span10">
        <h4 class="alert-heading">Warning!</h4>

        <p>You need to have <a href="" target="_blank">JavaScript</a> enabled to use this site.</p>
    </div>
</noscript>
