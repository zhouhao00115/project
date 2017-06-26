<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<form action="addcustomer.do" method="POST">
    <table class="table table-hover">
        <thead>
        <tr>
            <th colspan="2">
                <li class="btn"><a href="customer.do">返回</a></li>
            </th>
        </tr>
        <tr>
            <th>描述</th>
            <th>输入</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td style='vertical-align: middle;'>牧场名称</td>
            <td>
                <input id="name" name="name" type="text" class="form-control" placeholder="少于20个字" value="${dto.name}">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>地图</td>
            <td>
                <li class="btn" data-toggle="modal" data-target="#position">获取坐标</li>
                <input type="hidden" id="longitude" name="longitude" value="${dto.longitude}">
                <input type="hidden" id="latitude" name="latitude" value="${dto.latitude}">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>行政区划</td>
            <td>
                <input id="city" name="city" type="text" class="form-control" placeholder="行政区划" value="${dto.city}">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>详细地址</td>
            <td>
                <input id="address" name="address" type="text" class="form-control" placeholder="详细地址" value="${dto.address}">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>厂长姓名</td>
            <td>
                <input id="cname" name="cname" type="text" class="form-control" placeholder="厂长姓名" value="${dto.cname}">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>厂长电话</td>
            <td>
                <input id="cphone" name="cphone" type="text" class="form-control" placeholder="厂长电话" value="${dto.cphone}">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>交奶地行政区划</td>
            <td>
                <input id="naicity" name="naicity" type="text" class="form-control" placeholder="交奶地行政区划" value="${dto.naicity}">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>交奶地详细地址</td>
            <td>
                <input id="naiaddress" name="naiaddress" type="text" class="form-control" placeholder="交奶地详细地址" value="${dto.naiaddress}">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>牧场规模</td>
            <td>
                <input id="scale" name="scale" type="text" class="form-control" placeholder="牧场规模" value="${dto.scale}">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>用饲料量(吨/月)</td>
            <td>
                <input id="used" name="used" type="text" class="form-control" placeholder="用饲料量" value="${dto.used}">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>库存(吨)</td>
            <td>
                <input id="left" name="left" type="text" class="form-control" placeholder="库存" value="${dto.left}">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>道路情况</td>
            <td>
                <input id="road" name="road" type="text" class="form-control" placeholder="道路情况" value="${dto.road}">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>预计运费</td>
            <td>
                <input id="price" name="price" type="text" class="form-control" placeholder="预计运费" value="${dto.price}">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>备注</td>
            <td>
                <input id="remarks" name="remarks" type="text" class="form-control" placeholder="备注" value="${dto.remarks}">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>数据员</td>
            <td>
                <%--<input id="staffid" name="staffid" type="text" class="form-control" placeholder="数据员" value="${dto.staffid}">--%>
                <select class="form-control" id="staffid" name="staffid">
                    <c:forEach items="${datauser}" var="data" varStatus="i">
                        <option value="${data.staffid}">${data.staffid}-${data.sname}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <li class="btn" data-toggle="modal" data-target="#change">提交</li>
            </td>
        </tr>
        </tbody>
    </table>
    <input type="hidden" id="cid" name="cid" value="${dto.cid}">
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="change" tabindex="-1" role="dialog" aria-labelledby="title" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="title">
                        是否确认
                    </h4>
                </div>
                <div class="modal-body">
                    确认提交吗？
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="submit" class="btn btn-primary">
                        提交更改
                    </button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal -->
    </div>
    <div class="modal fade" id="position" tabindex="-1" role="dialog" aria-labelledby="positiontitle" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="positiontitle">
                        地图标注
                    </h4>
                </div>
                <div class="modal-body">
                    <div id="container" style="width:99%; height:200px"></div>
                    <div id="tip">点击地图试试</div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal -->
    </div>
</form>
<script src="http://webapi.amap.com/maps?v=1.3&key=20e6306ee01f0079e61babcb9e660bed&callback=init "></script>
<script>
    function init() {
        var map = new AMap.Map('container', {
            center: [115.562084, 38.016749],
            zoom: 9
        });
        map.on('click', function(e) {
            $("#longitude").val(e.lnglat.getLng());
            $("#latitude").val(e.lnglat.getLat());
            alert('您在[ '+e.lnglat.getLng()+','+e.lnglat.getLat()+' ]的位置点击了地图！');
        });
    }
</script>
