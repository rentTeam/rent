<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- 头部 -->
 <%@include file="/WEB-INF/pages/admin/header-tpl.jsp" %>
 <!-- 顶部导航 -->
 <%@include file="/WEB-INF/pages/admin/top-nav-tpl.jsp" %>
 <!-- 侧边导航 -->
 <%@include file="/WEB-INF/pages/admin/sidebar.jsp" %>
 
<div class="am-panel am-panel-default">
    <div class="am-panel-bd">

        <h3>车辆信息展示</h3>

    </div>
    <div class="am-panel-bd">
		<form id="userForm">
			<table class="am-table am-table-striped am-table-hover">
				<thead>
            		<tr>
                		<th>
                    		<input type="checkbox" data-opt="select-all">
                		</th>
                		<th>序号</th>
                		<th>用户名</th>
                		<th>身份证号码</th>
                		<th>学校</th>
                		<th>学院</th>
                		<th>邮箱</th>
                		<th>手机号</th>
                		<th>角色</th>
                		<th>操作</th>
            		</tr>
            	</thead>
            	<tbody>
            		<s:iterator value="#request.ulist" id="id" status="loop">
            			<tr>
            				<td>
            					<input type="checkbox" name="userArray" value="<s:property value="#id.id"/>">
            				</td>
            				<td><s:property value="#id.userName"/></td>
            				<td><s:property value="#id.identity"/></td>
            				<td><s:property value="#id.school"/></td>
            				<td><s:property value="#id.college"/></td>
            				<td><s:property value="#id.email"/></td>
            				<td><s:property value="#id.phone"/></td>
            				<td>${rlist[loop.index].name }</td>
            				<td>
                        		<div class="am-dropdown" data-am-dropdown>
                            		<button class="am-btn am-btn-primary am-btn-xs am-dropdown-toggle" data-am-dropdown-toggle>操作 <span class="am-icon-caret-down"></span>
                            		</button>
                            		<ul class="am-dropdown-content">
                                		<li><a href="${pageContext.request.contextPath}/admin/user/intoSentEmailUser.action?id=<s:property value="#id.email"/>">发邮件</a></li>
                                		<li><a href="${pageContext.request.contextPath}/admin/role/intoUpdateRole.action?id=<s:property value="#id.id"/>">更改用户角色</a></li>
                                		<li><a class="deleteUser" href="${pageContext.request.contextPath}/admin/user/deleteUser.action?id=<s:property value="#id.id"/>">删除</a></li>
                            		</ul>
                        		</div>
                    		</td>
            			</tr>
            		</s:iterator>
            	</tbody>
			</table>
		</form>
    </div>
</div>

<%--footer 与 java script脚本--%>
<%@include file="/WEB-INF/pages/admin/script-tpl.jsp" %>
<script type="text/javascript">
$(function(){
	$('.deleteUser').click(function(e){
		 e.preventDefault();
            var delelteUrl = $(this).attr('href');
            $.ajax({
                url: delelteUrl,
                dataType: "json",
                type: "POST"
            }).success(function(data) {
                if ( data ) {
                    if ( data.status == "ok" ) {
                        alert(data.message);
                        window.location.reload(true);
                    }else{
                        alert(data.message);
                    }
                } else {
                    alert("网络异常");
                }
            }).fail(function() {
                alert(" error final！");
                alert("网络异常");
            });
	});
});
</script>
<%--尾部--%>
<%@include file="/WEB-INF/pages/admin/footer-tpl.jsp"%>