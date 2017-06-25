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
                <li class="btn"><a href="datauser.do">返回</a></li>
            </th>
        </tr>
        <tr>
            <th>描述</th>
            <th>输入</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td style='vertical-align: middle;'>姓名</td>
            <td>
                <input id="sname" name="sname" type="text" class="form-control" placeholder="少于20个字" value="${dto.sname}">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>电话</td>
            <td>
                <input id="sphone" name="sphone" type="text" class="form-control" placeholder="电话" value="${dto.sphone}">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>性别</td>
            <td>
                <select class="form-control" id="gender" name="gender">
                    <option value="1" <c:if test="${'1' eq dto.gender}">selected</c:if>>男</option>
                    <option value="2" <c:if test="${'2' eq dto.gender}">selected</c:if>>女</option>
                </select>
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>备注</td>
            <td>
                <input id="cname" name="remarks" type="text" class="form-control" placeholder="备注" value="${dto.remarks}">
            </td>
        </tr>
        </tbody>
    </table>
    <input type="hidden" id="staffid" name="staffid" value="${dto.staffid}">
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
