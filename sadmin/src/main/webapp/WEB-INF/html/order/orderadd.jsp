<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<form action="addorder.do" method="POST">
    <table class="table table-hover">
        <thead>
        <tr>
            <th colspan="2">
                <li class="btn"><a href="order.do">返回</a></li>
            </th>
        </tr>
        <tr>
            <th>描述</th>
            <th>输入</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${'' ne info}">
            <tr>
                <td style='vertical-align: middle;'>提示</td>
                <td>${info}</td>
            </tr>
        </c:if>
        <tr>
            <td style='vertical-align: middle;'>发货牧场</td>
            <td>
                <select class="form-control" id="cid" name="cid">
                    <c:forEach items="${dto.customers}" var="customer" varStatus="i">
                        <option value="${customer.cid}"
                                <c:if test="${customer.cid eq dto.orderModel.customerModel.cid}">
                                    selected
                                </c:if>
                                >${customer.cid}-${customer.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>货车</td>
            <td>
                <select class="form-control" id="tid" name="tid">
                    <c:forEach items="${dto.tracks}" var="track" varStatus="i">
                        <option value="${track.tid}"
                                <c:if test="${track.tid eq dto.orderModel.trackModel.tid}">
                                    selected
                                </c:if>
                                >${track.tname}-${track.license}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>发货量(吨)</td>
            <td>
                <input id="volume" name="volume" type="text" class="form-control" placeholder="发货量" value="${dto.orderModel.volume}">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>单价(元/吨)</td>
            <td>
                <input id="price" name="price" type="text" class="form-control" placeholder="单价" value="${dto.orderModel.price}">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>总价(元)</td>
            <td>
                <input id="total" name="total" type="text" class="form-control" placeholder="总价" value="${dto.orderModel.total}">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>发货员</td>
            <td>
                <select class="form-control" id="staffid" name="staffid">
                    <c:forEach items="${dto.dataUser}" var="data" varStatus="i">
                        <option value="${data.staffid}"
                                <c:if test="${staffid.staffid eq dto.orderModel.dataUserModel.staffid}">
                                    selected
                                </c:if>
                                >${data.staffid}-${data.sname}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>备注</td>
            <td>
                <input id="remarks" name="remarks" type="text" class="form-control" placeholder="备注" value="${dto.orderModel.remarks}">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <li class="btn" data-toggle="modal" data-target="#change">提交</li>
            </td>
        </tr>
        </tbody>
    </table>
    <input type="hidden" id="oid" name="oid" value="${dto.orderModel.oid}">
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
                    确定添加？
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
</form>
