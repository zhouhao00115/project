<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<form action="adddadminaction.do" method="POST">
    <table class="table table-hover">
        <thead>
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
            <td style='vertical-align: middle;'>用户名</td>
            <td>
                <input id="username" name="username" type="text" class="form-control" placeholder="少于20个字" value="${dto.username}">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>新密码</td>
            <td>
                <input id="newpassword" name="newpassword" type="password" class="form-control" placeholder="新密码" value="">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>重复新密码</td>
            <td>
                <input id="newrepeatpassword" name="newrepeatpassword" type="password" class="form-control" placeholder="重复新密码" value="">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>权限</td>
            <td>
                <select class="form-control" id="power" name="power">
                    <option value="1" <c:if test="${'1' eq dto.power}">selected</c:if>>管理员</option>
                    <option value="2" <c:if test="${'2' eq dto.power}">selected</c:if>>只读</option>
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
    <input type="hidden" id="add" name="add" value="1">
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
                    确认提交？
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
