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
                <li class="btn"><a href="customer.do">返回</a></li>
            </th>
            <th colspan="1">
                <li class="btn"><a href="customer.do">修改</a></li>
            </th>
            <th colspan="1">
                <li class="btn"><a href="customer.do">新增</a></li>
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
            <td class="center">月饲料用量(吨/月)</td>
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
            <td class="center">库存剩余</td>
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
            <td class="center" id="yunfei"></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">数据员</td>
            <td class="center" id="data"></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="center">数据源联系方式</td>
            <td class="center" id="dataphone"></td>
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