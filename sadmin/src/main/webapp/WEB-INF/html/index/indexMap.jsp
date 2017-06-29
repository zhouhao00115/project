<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<div id="container" style="width:99%; height:800px"></div>
<script src="http://webapi.amap.com/maps?v=1.3&key=20e6306ee01f0079e61babcb9e660bed&callback=init "></script>
<script>
    function init() {
        var map = new AMap.Map('container', {
            center: [115.562084, 38.016749],
            zoom: 9
        });
        map.plugin(["AMap.ToolBar"], function () {
            map.addControl(new AMap.ToolBar());
        });
        var icon = new AMap.Icon({
            image: 'img/niu.jpg',//24px*24px
            //icon可缺省，缺省时为默认的蓝色水滴图标，
            size: new AMap.Size(30, 30)
        });
        <c:forEach items="${list}" var="customer" varStatus="i">
        var position = "${customer.longitude}" + "," + "${customer.latitude}";
        marker = new AMap.Marker({
            icon: icon,
            position: position.split(","),
            offset: new AMap.Pixel(-12, -12),
            title: '${customer.name}'
        });
        var info = [];
        info.push("<div><div><img style=\"float:left;\" src=\" http://webapi.amap.com/images/autonavi.png \"/></div> ");
        info.push("<div style=\"padding:0px 0px 0px 4px;\"><b>高德软件</b>");
        info.push("电话 : 010-84107000   邮编 : 100102");
        info.push("地址 : 北京市望京阜通东大街方恒国际中心A座16层</div></div>");
        marker.setLabel({
            offset: new AMap.Pixel(20, 20),
            content: info.join("<br/>")
        });
        marker.setMap(map);
        </c:forEach>
    }
</script>