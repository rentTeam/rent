<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- 头部 -->
 <%@include file="/WEB-INF/pages/admin/header-tpl.jsp" %>
	</head>
	<body>
<!--顶部-->
    	<header class="am-topbar">
        	<span class="am-topbar-brand my-center am-text-xxxl">
            	<a href="#"><strong>租车系统首页</strong></a>
            </span>
            <div class="am-collapse am-topbar-collapse" id="topbar-collapse">
    			<ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
      				<li class="am-dropdown" data-am-dropdown>
        				<a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
          				<span class="am-icon-users"></span> 管理员 <span class="am-icon-caret-down"></span>
        				</a>
        				<ul class="am-dropdown-content">
          					<li><a href="home/user/intoLoginUser.action"><span class="am-icon-user"></span>登录</a></li>
          					<li id="01"><a href="${pageContext.request.contextPath}/home/picture/identityUploadPicture.action"><span class="am-icon-user"></span> 上传身份证图片</a></li>
          					<li id="02"><a href="${pageContext.request.contextPath}/home/picture/studentUploadPicture.action"><span class="am-icon-user"></span> 上传身份证图片</a></li>
          					<li id="03"><a href="${pageContext.request.contextPath}/home/picture/otherUploadPicture.action"><span class="am-icon-user"></span> 上传身份证图片</a></li>
          					<li id="04"><a href="${pageContext.request.contextPath}/home/user/userLogoutUser.action"><span class="am-icon-power-off"></span> 退出</a></li>
        				</ul>
      				</li>
    			</ul>
  			</div>
        </header>
        <tbody>
        	<!--content start-->
		<div class="">
			<div class="am-u-sm-4">
				<img src="http://7jpqbr.com1.z0.glb.clouddn.com/bw-2014-06-19.jpg?imageView/1/w/1000/h/1000/q/80"
					width="220" height="230"/>
			</div>
			<div class="am-u-sm-8">
				<p class="am-text-xxl">
					<strong>
					title<br/>
					price:88888888 $<br/>
					desc:aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
					</strong>
				</p>
			</div>
			<div class="am-u-sm-8 am-fr">
				<form class="am-form">
					<div class="am-form-group">
						<label class="am-text-xxl">
							<strong>keep time</strong>
						</label>
						<input  type="text" name="time"/>
					</div>
					<div class="am-form-group">
						<button type="submit" class="am-btn am-btn-primary">submit</button>
					</div>
				</form>
			</div>
		</div>
		<!--content end-->
        </tbody>
<%--footer 与 java script脚本--%>
<%@include file="/WEB-INF/pages/admin/script-tpl.jsp" %>
        <script type="text/javascript">
        	$(function(){
        		var a=$("#01");
        		var b=$("#02");
        		var c=$("#03");
        		var d=$("#04");
        		$("#01").ready(function(e){
        			if(<s:property value="#session.userName" />!=null){
        				a.show();
        				b.show();
        				c.show();
        				d.show();
        			}
        		});
        	});
        </script>
	</body>
</html>