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
            <th colspan="4">
                <li class="btn"><a href="customer.do">返回</a></li>
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="center" id="id${i.index}">编号</td>
            <td class="center" id="name${i.index}">${dto.id}</td>
            <td></td><td></td>
        </tr>
        <tr>
            <td class="center" id="id${i.index}">牧场简称</td>
            <td class="center" id="name${i.index}">${dto.name}</td>
            <td></td><td></td>
        </tr>
        <tr>
            <td class="center" id="id${i.index}">牧场地址</td>
            <td class="center" id="name${i.index}">${dto.address}</td>
            <td></td><td></td>
        </tr>
        <tr>
            <td class="center" id="id${i.index}">厂长姓名</td>
            <td class="center" id="name${i.index}">${dto.userName}</td>
            <td></td><td></td>
        </tr>
        <tr>
            <td class="center" id="id${i.index}">厂长电话</td>
            <td class="center" id="name${i.index}">${dto.phone}</td>
            <td></td><td></td>
        </tr>
        <tr>
            <td class="center" id="id${i.index}">牧场规模</td>
            <td class="center" id="name${i.index}">${dto.scale}</td>
            <td></td><td></td>
        </tr>
        <tr>
            <td class="center" id="id${i.index}">交奶地</td>
            <td class="center" id="name${i.index}">${dto.sendAddress}</td>
            <td></td><td></td>
        </tr>
        <tr>
            <td class="center" id="id${i.index}">月饲料用量(吨/月)</td>
            <td class="center" id="name${i.index}">${dto.used}</td>
            <td></td><td></td>
        </tr>
        <tr>
            <td class="center" id="id${i.index}">存栏</td>
            <td class="center" id="name${i.index}">${dto.save}</td>
            <td></td><td></td>
        </tr>
        <tr>
            <td class="center" id="id${i.index}">库存剩余</td>
            <td class="center" id="name${i.index}">${dto.surplus}</td>
            <td></td><td></td>
        </tr>
        <tr>
            <td class="center" id="id${i.index}">路程路况</td>
            <td class="center" id="name${i.index}">${dto.road}</td>
            <td></td><td></td>
        </tr>
        <tr>
            <td class="center" id="id${i.index}">运费(吨/元)</td>
            <td class="center" id="name${i.index}">${dto.freight}</td>
            <td></td><td></td>
        </tr>
        <tr>
            <td class="center" id="id${i.index}">价格表运费</td>
            <td class="center" id="name${i.index}">${dto.priceFreight}</td>
            <td></td><td></td>
        </tr>
        <tr>
            <td class="center" id="id${i.index}">数据员</td>
            <td class="center" id="name${i.index}">${dto.dataUser}</td>
            <td></td><td></td>
        </tr>
        <tr>
            <td class="center" id="id${i.index}">数据员电话</td>
            <td class="center" id="name${i.index}">${dto.dataUserPhone}</td>
            <td></td><td></td>
        </tr>
        <tr>
            <td class="center" id="id${i.index}">备注</td>
            <td class="center" id="name${i.index}">${dto.remark}</td>
            <td></td><td></td>
        </tr>
        </tbody>
    </table>
</div>