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
            <th>描述</th>
            <th>输入</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td style='vertical-align: middle;'>牧场名称</td>
            <td>
                <input id="name" name="name" type="text" class="form-control" placeholder="少于20个字">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>行政区划</td>
            <td>
                <input id="city" name="city" type="text" class="form-control" placeholder="行政区划">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>详细地址</td>
            <td>
                <input id="address" name="address" type="text" class="form-control" placeholder="详细地址">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>厂长姓名</td>
            <td>
                <input id="cname" name="cname" type="text" class="form-control" placeholder="厂长姓名">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>厂长电话</td>
            <td>
                <input id="cphone" name="cphone" type="text" class="form-control" placeholder="厂长电话">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>交奶地行政区划</td>
            <td>
                <input id="naicity" name="naicity" type="text" class="form-control" placeholder="交奶地行政区划">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>交奶地详细地址</td>
            <td>
                <input id="naiaddress" name="naiaddress" type="text" class="form-control" placeholder="交奶地详细地址">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>牧场规模</td>
            <td>
                <input id="scale" name="scale" type="text" class="form-control" placeholder="牧场规模">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>用饲料量(吨/月)</td>
            <td>
                <input id="used" name="used" type="text" class="form-control" placeholder="用饲料量">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>库存(吨)</td>
            <td>
                <input id="left" name="left" type="text" class="form-control" placeholder="库存">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>道路情况</td>
            <td>
                <input id="road" name="road" type="text" class="form-control" placeholder="道路情况">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>预计运费</td>
            <td>
                <input id="price" name="price" type="text" class="form-control" placeholder="预计运费">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>备注</td>
            <td>
                <input id="remarks" name="remarks" type="text" class="form-control" placeholder="备注">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>数据员</td>
            <td>
                <input id="staffid" name="staffid" type="text" class="form-control" placeholder="数据员">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <li class="btn" data-toggle="modal" data-target="#change">提交</li>
            </td>
        </tr>
        </tbody>
    </table>
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="change" tabindex="-1" role="dialog" aria-labelledby="title" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="title">
                        是否确认添加
                    </h4>
                </div>
                <div class="modal-body">
                    在这里添加一些文本
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
