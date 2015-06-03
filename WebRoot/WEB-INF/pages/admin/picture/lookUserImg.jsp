<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- 头部 -->
 <%@include file="/WEB-INF/pages/admin/header-tpl.jsp" %>
 <!-- 顶部导航 -->
 <%@include file="/WEB-INF/pages/admin/top-nav-tpl.jsp" %>
 <!-- 侧边导航 -->
 <%@include file="/WEB-INF/pages/admin/sidebar.jsp" %>
 
<div class="am-panel am-panel-default">
    <div class="am-panel-bd">
    	<h3>查看用户图片</h3>
    </div>
    <div class="am-panel-bd">
		<form id="pictureForm">
			<table class="am-table am-table-striped am-table-hover">
				<thead>
            		<tr>
                		<th colspan="2">身份证图</th>
                		<th colspan="2">学生证图</th>
                		<th>其他</th>
                		<th>用户名</th>
            		</tr>
            	</thead>
            	<tbody>
            		<tr>
            			<td><img alt="identity" src="user/${identity[0].fileFileName }"></td>
            			<td><img alt="identity" src="user/${identity[1].fileFileName }"></td>
            			<td><img alt="identity" src="user/${student[0].fileFileName }"></td>
            			<td><img alt="identity" src="user/${student[1].fileFileName }"></td>
            			<td><img alt="identity" src="user/${other[0].fileFileName }"></td>
            			<td>${user.userName }</td>
            		</tr>	
            	</tbody>
			</table>
		</form>
    </div>
</div>

<%--footer 与 java script脚本--%>
<%@include file="/WEB-INF/pages/admin/script-tpl.jsp" %>
<%--尾部--%>
<%@include file="/WEB-INF/pages/admin/footer-tpl.jsp"%>
