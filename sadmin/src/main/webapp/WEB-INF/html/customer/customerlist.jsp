<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<div class="table-responsive">
    <table class="table table-condensed">
        <thead>
        <tr>
            <th colspan="8">
                总计 ${dto.count} 条
            </th>
        </tr>
        <tr>
            <th>编号</th>
            <th>牧场</th>
            <th>牧场所在城市</th>
            <th>联系方式</th>
            <th>牧场规模</th>
            <th>数据员</th>
            <th>数据员电话</th>
            <th>备注</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${dto.list}" var="customer" varStatus="i">
            <tr>
                <td class="center" id="id${i.index}">${customer.cid}</td>
                <td class="center" id="name${i.index}">
                    <a href="customerinfo.do?number=${customer.cid}" class="btn btn-small"
                       data-dismiss="modal">${customer.name}</a>
                </td>
                <td class="center" id="city${i.index}">${customer.city}</td>
                <td class="center" id="phone${i.index}">${customer.cphone}</td>
                <td class="center" id="scale${i.index}">${customer.scale}</td>
                <td class="center" id="dataUser${i.index}"></td>
                <td class="center" id="dataUserPhone${i.index}"></td>
                <td class="center" id="remark${i.index}">${customer.remarks}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="pagination pagination-centered">
    <ul>
        <li><a href="#">首页</a></li>
        <li><a href="#">上一页</a></li>
        <li><a href="#">下一页</a></li>
        <li><a href="#">尾页</a></li>
    </ul>
    <p class="text-right">
        <ul class="pagination pagination-sm">
            <li></li>
        </ul>
    </p>
</div>
<div id="container" style="width:99%; height:500px"></div>
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
        var valus = [];
        <c:forEach items="${dto.list}" var="customer" varStatus="i">
        var position = "${customer.longitude}" + "," + "${customer.latitude}";
        marker = new AMap.Marker({
            icon: icon,
            position: position.split(","),
            offset: new AMap.Pixel(-12, -12),
            title: 'test'
        });
        marker.setMap(map);
        </c:forEach>
    }
</script>