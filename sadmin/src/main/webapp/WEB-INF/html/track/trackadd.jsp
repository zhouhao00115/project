<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<form action="addtrackaction.do" method="POST">
    <table class="table table-hover">
        <thead>
        <tr>
            <th colspan="2">
                <li class="btn"><a href="track.do">返回</a></li>
            </th>
        </tr>
        <tr>
            <th>描述</th>
            <th>输入</th>
        </tr>
        </thead>
        <tbody>
        <%--private int tid;--%>
        <%--private String license;--%>
        <%--private String tname;--%>
        <%--private String tphone;--%>
        <%--private String ttype;--%>
        <%--private int capacity;--%>
        <%--private String citys;--%>
        <%--private String remarks;--%>
        <tr>
            <td style='vertical-align: middle;'>车牌</td>
            <td>
                <input id="license" name="license" type="text" class="form-control" placeholder="车牌号" value="${dto.license}">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>车主</td>
            <td>
                <input id="tname" name="tname" type="text" class="form-control" placeholder="车主" value="${dto.tname}">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>电话</td>
            <td>
                <input id="tphone" name="tphone" type="text" class="form-control" placeholder="电话" value="${dto.tphone}">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>车型</td>
            <td>
                <input id="ttype" name="ttype" type="text" class="form-control" placeholder="车型" value="${dto.ttype}">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>载货量</td>
            <td>
                <input id="capacity" name="capacity" type="text" class="form-control" placeholder="载货量" value="${dto.capacity}">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>城市</td>
            <td>
                <input id="citys" name="citys" type="text" class="form-control" placeholder="城市" value="${dto.citys}">
            </td>
        </tr>
        <tr>
            <td style='vertical-align: middle;'>备注</td>
            <td>
                <input id="cname" name="remarks" type="text" class="form-control" placeholder="备注" value="${dto.remarks}">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <li class="btn" data-toggle="modal" data-target="#change">提交</li>
            </td>
        </tr>
        </tbody>
    </table>
    <input type="hidden" id="staffid" name="staffid" value="${dto.tid}">
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
