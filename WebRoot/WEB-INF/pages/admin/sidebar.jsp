<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 
	后台侧边导航模版
 -->
 <div class="am-cf admin-main">
 	<!-- sidebar start -->
 	<div class="admin-sidebar">
 		<ul class="am-list admin-sidebar-list">
 			<li><a href="${pageContext.request.contextPath}/admin/manager/indexManager.action"><i class="am-icon-home admin-nav-xs-icon"></i><span class="admin-nav-xs-hiden">系统首页</span></a></li>
 			<li class="admin-parent">
 				 <a class="am-cf" data-am-collapse="{target: '#collapse2-nav'}"><i class="am-icon-building admin-nav-xs-icon"></i>
                    <span class="admin-nav-xs-hiden">车信息管理 <i class="am-icon-angle-right am-fr am-margin-right"></i></span></a>
                <ul class="am-list am-collapse admin-sidebar-sub" id="collapse2-nav">
                    <li><a href="${pageContext.request.contextPath}/admin/car/intoAddCar.action" class="am-cf">车信息添加</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/car/queryCar.action" class="am-cf">车信息查询</a></li>
                </ul>
 			</li>
 			<li class="admin-parent">
 				 <a class="am-cf" data-am-collapse="{target: '#collapse3-nav'}"><i class="am-icon-building admin-nav-xs-icon"></i>
                    <span class="admin-nav-xs-hiden">管理员信息管理 <i class="am-icon-angle-right am-fr am-margin-right"></i></span></a>
                <ul class="am-list am-collapse admin-sidebar-sub" id="collapse3-nav">
                    <li><a href="${pageContext.request.contextPath}/admin/manager/intoAddManager.action" class="am-cf">管理员信息添加</a></li>
                </ul>
 			</li>
 			<li class="admin-parent">
 				 <a class="am-cf" data-am-collapse="{target: '#collapse4-nav'}"><i class="am-icon-building admin-nav-xs-icon"></i>
                    <span class="admin-nav-xs-hiden">图片信息管理 <i class="am-icon-angle-right am-fr am-margin-right"></i></span></a>
                <ul class="am-list am-collapse admin-sidebar-sub" id="collapse4-nav">
                    <li><a href="${pageContext.request.contextPath}/admin/picture/intoUpload2Picture.action" class="am-cf">管理员图片上传</a></li>
                </ul>
 			</li>
 			<li class="admin-parent">
 				 <a class="am-cf" data-am-collapse="{target: '#collapse5-nav'}"><i class="am-icon-building admin-nav-xs-icon"></i>
                    <span class="admin-nav-xs-hiden">出租信息管理 <i class="am-icon-angle-right am-fr am-margin-right"></i></span></a>
                <ul class="am-list am-collapse admin-sidebar-sub" id="collapse5-nav">
                    <li><a href="${pageContext.request.contextPath}/admin/rentInfo/queryRent.action" class="am-cf">出租信息查询</a></li>
                </ul>
 			</li>
 			<li class="admin-parent">
 				 <a class="am-cf" data-am-collapse="{target: '#collapse6-nav'}"><i class="am-icon-building admin-nav-xs-icon"></i>
                    <span class="admin-nav-xs-hiden">用户信息管理 <i class="am-icon-angle-right am-fr am-margin-right"></i></span></a>
                <ul class="am-list am-collapse admin-sidebar-sub" id="collapse6-nav">
                    <li><a href="${pageContext.request.contextPath}/admin/user/queryUser.action" class="am-cf">用户信息查询</a></li>
                </ul>
 			</li>
 		</ul>
 	</div>
 	<!-- sidebar end -->
 	<div class="admin-content">
 	<!-- content start -->