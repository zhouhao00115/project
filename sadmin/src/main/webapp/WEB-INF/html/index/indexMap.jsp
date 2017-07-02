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
    //初始化地图对象，加载地图
    var map = new AMap.Map("container", {
        resizeEnable: true,
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
    var infoWindow = new AMap.InfoWindow({offset:new AMap.Pixel(0,-30)});
    <c:forEach items="${list}" var="customer" varStatus="i">
        var positionlens = "${customer.longitude}" + "," + "${customer.latitude}";
        var marker=new AMap.Marker({
            icon: icon,
            position: positionlens.split(","),
            title: '${customer.name}',
            map:map
        });
        var content = [];
        content.push('<h6>编号：<small>${customer.cid}</small></h6>');
        content.push('<h6>名称：<small>${customer.name}</small></h6>');
        content.push('<h6>规模：<small>${customer.scale}</small></h6>');
        content.push('<h6>库存：<small>${customer.left}</small></h6>');
        content.push('<h6>状态：<small>${customer.staus}</small></h6>');
        content.push('<h5><a href="customerinfo.do?number=${customer.cid}">查看详情</a></h5>');
        marker.content=content.join("<br/>");
        marker.on('click',markerClick);
    </c:forEach>
    function markerClick(e){
        infoWindow.setContent(e.target.content);
        infoWindow.open(map, e.target.getPosition());
    }
    map.setFitView();
</script>
<script type="text/javascript" src="https://webapi.amap.com/demos/js/liteToolbar.js"></script>