<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 
	后台默认首页
 -->
 <!-- 头部 -->
 <%@include file="/WEB-INF/pages/admin/header-tpl.jsp" %>
 <!-- 顶部导航 -->
 <%@include file="/WEB-INF/pages/admin/top-nav-tpl.jsp" %>
 <!-- 侧边导航 -->
 <%@include file="/WEB-INF/pages/admin/sidebar.jsp" %>

  <div class="am-panel am-panel-default">

    <div class="am-panel-bd">

        <h2>欢迎您，<%-- <s:property value="#session.adminUsername" /> --%></h2>
    </div>
 </div>
<%--footer 与 java script脚本--%>
<%@include file="/WEB-INF/pages/admin/script-tpl.jsp" %>
<%--尾部--%>
<%@include file="/WEB-INF/pages/admin/footer-tpl.jsp"%>