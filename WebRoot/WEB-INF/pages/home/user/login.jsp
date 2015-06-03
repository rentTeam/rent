<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- 头部 -->
 <%@include file="/WEB-INF/pages/admin/header-tpl.jsp" %>
 		<style>
    		.header {
      			text-align: center;
   			 }
    		.header h1 {
      			font-size: 200%;
     			color: #333;
     			margin-top: 30px;
    		}
    		.header p {
      			font-size: 14px;
    		}
  		</style>
  </head>
  <body>
	<div class="header">
  			<div class="am-g">
    			<h1>用户   登录</h1>
    			
  			</div>
  			<hr/>
		</div>
		
		<div class="am-g user-col-no-padding">
			<div class="am-u-sm-centered am-u-sm-5">
				<form class="am-form" method="post" id="loginForm">
					<div class="am-form-group">
						<label class="">用户名:</label>
						<input type="text" name="username"/>
					</div>
					<div class="am-form-group">
						<label class="">password:</label>
						<input type="password" name="password"/>
					</div>
					<div class="am-form-group">
						<p class="am-form-help" id="msgStatus"></p>
						<button type="submit" class="am-btn am-btn-secondary">登录</button>
					</div>
				</form>
				<hr>
				<p class="am-center"></p>
			</div>
		</div>

<%--footer 与 java script脚本--%>
<%@include file="/WEB-INF/pages/admin/script-tpl.jsp" %>
<script type="text/javascript">
$(function(){
	 var msgStatus=$("#msgStatus");
	 
	 $('#loginForm').validate({
                rules: {
                    userName: {required: true},
                    password: {required: true}
                },
                messages: {
                    userName: {required: '用户名不能为空'},
                    password: {required: '密码不能为空'}
                },
                submitHandler: function() {
                    
                    $.ajax({
                        url: "${pageContext.request.contextPath}/home/user/checkUser.action",
                        data: $("#loginForm").serialize(),
                        dataType: "json",
                        type: "POST"
                    }).success(function(data) {
                        if ( data ) {
                            if ( data.status == "ok" ) {
                                msgStas.html('<span class="am-text-success">' + data.message + '</span>');
                     
                                window.location.href="${pageContext.request.contextPath}/home/car/queryCar.action";
                            }else{
                                msgDesc.html('<span class="am-text-danger">' + data.message + '</span>');
                            }
                        } else {
                            msgStas.html('<span class="am-text-danger">网络异常</span>');
                        }
                    }).fail(function() {
                        alert(" error final！");
                        msgStas.html('<span class="am-text-danger">网络异常</span>');
                    });

                    return false;
                }
            });
});
</script>
<%--尾部--%>
<%@include file="/WEB-INF/pages/admin/footer-tpl.jsp"%>
