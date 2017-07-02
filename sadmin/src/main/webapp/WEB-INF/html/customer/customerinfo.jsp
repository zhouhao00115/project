<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<div class="table-responsive">
    <table class="table table-striped table-condensed">
        <thead>
        <tr>
            <th colspan="2">
                <li class="btn"><a href="customer.do">列表页</a></li>
            </th>
            <th>
                <c:if test="${'1' eq sessionScope.power}">
                    <a href="customeradd.do?cid=${dto.cid}">修改牧场信息</a>
                </c:if>
            </th>
            <th>
                <%--<c:if test="${'1' eq sessionScope.power}">--%>
                    <%--<li class="btn" data-toggle="modal" data-target="#delete">删除该条</li>--%>
                <%--</c:if>--%>
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="center" width="170">编号</td>
            <td class="center" id="id">${dto.cid}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">牧场简称</td>
            <td class="center" id="name">${dto.name}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">牧场地址</td>
            <td class="center" id="city">${dto.city}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">具体位置</td>
            <td class="center" id="address">${dto.address}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">厂长姓名</td>
            <td class="center" id="cname">${dto.cname}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">厂长电话</td>
            <td class="center" id="cphone">${dto.cphone}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">交奶城市</td>
            <td class="center" id="naicity">${dto.naicity}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">交奶地</td>
            <td class="center" id="naiaddress">${dto.naiaddress}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">月饲料用量(元/吨)</td>
            <td class="center" id="used">${dto.used}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">规模</td>
            <td class="center" id="scale">${dto.scale}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">库存剩余(吨)</td>
            <td class="center" id="left">${dto.left}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">路程路况</td>
            <td class="center" id="road">${dto.road}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">运费(吨/元)</td>
            <td class="center" id="price">${dto.price}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">状态</td>
            <td class="center" id="staus">
                <c:if test="${'0'eq customer.staus}">
                    未知
                </c:if>
                <c:if test="${'1'eq customer.staus}">
                    充足
                </c:if>
                <c:if test="${'2'eq customer.staus}">
                    正常
                </c:if>
                <c:if test="${'3'eq customer.staus}">
                    缺少
                </c:if></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">数据员</td>
            <td class="center" id="data">${dto.sname}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">数据源联系方式</td>
            <td class="center" id="dataphone">${dto.sphone}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">备注</td>
            <td class="center" id="remarks">${dto.remarks}</td>
            <td></td>
            <td></td>
        </tr>
        </tbody>
    </table>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="change" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    模态框（Modal）标题
                </h4>
            </div>
            <div class="modal-body">
                在这里添加一些文本
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary">
                    提交更改
                </button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>
<div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="deletecustomer" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="deletecustomer">
                    删除确认
                </h4>
            </div>
            <div class="modal-body">
                <h5>编号为
                    <small>${dto.cid}</small>
                </h5>
                </br>
                <h5>牧场简称
                    <small>${dto.name}</small>
                </h5>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button id="deletebutton" name="deletebutton" type="button" class="btn btn-primary">
                    确认删除
                </button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>
<script>
    $("deletebutton").click(function () {
        alert("删除");
        $.ajax({
            type: 'post', //post方式
            async: false, //是否异步，默认为true
            url: "deletecustomer.do", //发送的接收地址。
            data: { "cid":${dto.cid} }, //参数
            error: function (xhr) {
                alert("删除失败");
            },
            success: function (str) { //如果成功，返回一个结果，在这里处理
                alert(str);
            },
            dataType: "text" //返回结果的类型。
        });
    });
</script>