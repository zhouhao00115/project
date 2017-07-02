<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<div class="table-responsive">
    <table class="table">
        <thead>
        <tr>
            <th colspan="6">
                总计 ${dto.count} 条
            </th>
            <th colspan="3">
                <h6>每页显示
                    <small>
                        <a href="customer.do?rows=10">10</a>
                    </small>
                    <small>
                        <a href="customer.do?rows=30">30</a>
                    </small>
                    <small>
                        <a href="customer.do?rows=50">50</a>
                    </small>
                    <small>
                        <a href="customer.do?rows=100">100</a>
                    </small>
                    条
                </h6>
            </th>
            <th colspan="2">
                <c:if test="${'1' eq sessionScope.power}">
                    <li class="btn"><a href="customeradd.do">添加牧场信息</a></li>
                </c:if>
            </th>
        </tr>
        <tr>
            <th>编号</th>
            <th>牧场</th>
            <th>牧场所在城市</th>
            <th>联系方式</th>
            <th>牧场规模</th>
            <th>月使用量</th>
            <th>剩余库存</th>
            <th>库存状态</th>
            <th>数据员</th>
            <th>数据员电话</th>
            <th>备注</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${dto.list}" var="customer" varStatus="i">
            <tr
                <c:if test="${'0'eq customer.staus}">
                    style="color:#0d0c22"
                </c:if>
                <c:if test="${'1'eq customer.staus}">
                    style="color:green"
                </c:if>
                <c:if test="${'2'eq customer.staus}">
                    style="color:black"
                </c:if>
                <c:if test="${'3'eq customer.staus}">
                    style="color:red"
                </c:if>>
                <td class="center" id="id${i.index}">${customer.cid}</td>
                <td class="center" id="name${i.index}">
                    <a href="customerinfo.do?number=${customer.cid}" class="btn btn-small"
                       data-dismiss="modal">${customer.name}</a>
                </td>
                <td class="center" id="city${i.index}">${customer.city}</td>
                <td class="center" id="phone${i.index}">${customer.cphone}</td>
                <td class="center" id="scale${i.index}">${customer.scale}</td>
                <td class="center" id="used${i.index}">${customer.used}</td>
                <td class="center" id="left${i.index}">${customer.left}</td>
                <td class="center" id="staus${i.index}">${customer.stausdes}</td>
                <td class="center" id="dataUser${i.index}">${customer.sname}</td>
                <td class="center" id="dataUserPhone${i.index}">${customer.sphone}</td>
                <td class="center" id="remark${i.index}">${customer.remarks}</td>
                <td class="center">
                    <h5>
                        <small><a href="customerinfo.do?number=${customer.cid}">查看</a></small>
                    </h5>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="pagination pagination-centered">
    <ul>
        <li><a href="customer.do?start=0&rows=${dto.rows}">首页</a></li>
        <c:if test="${dto.start >= dto.rows}">
            <li><a href="customer.do?start=${dto.start - dto.rows}&rows=${dto.rows}">上一页</a></li>
        </c:if>
        <li><a href="#">${dto.page}</a></li>
        <c:if test="${dto.count > dto.start + dto.rows}">
            <li><a href="customer.do?start=${dto.start + dto.rows}&rows=${dto.rows}">下一页</a></li>
        </c:if>
        <li><a href="customer.do?start=${dto.end}&rows=${dto.rows}">尾页</a></li>
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
            resizeEnable: true,
            center: [115.562084, 38.016749],
            zoom: 9
        });
        map.plugin(["AMap.ToolBar"], function () {
            map.addControl(new AMap.ToolBar());
        });
        var infoWindow = new AMap.InfoWindow({offset: new AMap.Pixel(0, -30)});
        <c:forEach items="${dto.list}" var="customer" varStatus="i">
            var position = "${customer.longitude}" + "," + "${customer.latitude}";
            var iconpath = 'img/niu.jpg';
            var sizegit = new AMap.Size(30, 30);
            var staus = "${customer.staus}";
            if(1 == staus){
                iconpath='img/zhan.gif';
                sizegit = new AMap.Size(32, 48);
            }
            if(2 == staus){
                iconpath='img/eat.jpg';
                sizegit = new AMap.Size(36, 27);
            }
            if(3 == staus){
                iconpath='img/pa.gif';
                sizegit = new AMap.Size(50, 25);
            }
            var icon = new AMap.Icon({
                image: iconpath,//24px*24px
                //icon可缺省，缺省时为默认的蓝色水滴图标，
                size: sizegit
            });
            marker = new AMap.Marker({
                icon: icon,
                position: position.split(","),
                offset: new AMap.Pixel(-12, -12),
                title: '${customer.cid}--${customer.name}',
                map: map
            });
            var content = [];
            content.push('<h6>编号：<small>${customer.cid}</small></h6>');
            content.push('<h6>名称：<small>${customer.name}</small></h6>');
            content.push('<h6>规模：<small>${customer.scale}</small></h6>');
            content.push('<h6>库存：<small>${customer.left}</small></h6>');
            content.push('<h6>状态：<small>${customer.stausdes}</small></h6>');
            content.push('<h5><a href="customerinfo.do?number=${customer.cid}">查看详情</a></h5>');
            marker.content = content.join("<br/>");
            marker.on('click', markerClick);
        </c:forEach>
        function markerClick(e) {
            infoWindow.setContent(e.target.content);
            infoWindow.open(map, e.target.getPosition());
        }
        map.setFitView();
    }
</script>