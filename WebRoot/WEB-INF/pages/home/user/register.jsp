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
	<div class="header">
  			<div class="am-g">
    			<h1>用户   注册</h1>
    			
  			</div>
  			<hr/>
		</div>
		
		<div class="am-g am-center">
            <div class="am-center am-u-sm-6 am-u-sm-centered">
            	<form class="am-form">
                	<div class="am-form-group">
                    	<label class="am-form-label">用户名:</label>
                        <input type="text" name="userName" placeholder="username"/>
                    </div>
                    <div class="am-form-group">
                    	<label class="am-form-label">密码:</label>
                        <input type="password" name="password" placeholder="ples set your password!"/>
                    </div>
                    <div class="am-form-group">
                    	<label class="am-form-label">邮箱号:</label>
                       	<input type="text" name="email" placeholder="ples set your email!"/>
                    </div>
                    <div class="am-form-group">
                    	<label class="am-form-label">手机号:</label>
                        <input type="text" name="phone" placeholder="ples set your phone!"/>
                    </div>
                    <div class="am-form-group">
                    	<label class="am-form-label">学校:</label>
                        <input type="text" name="school" placeholder="ples write your school!"/>
                    </div>
                    <div class="am-form-group">
                    	<label class="am-form-label">学院:</label>
                        <input type="text" name="college" placeholder="ples write your college!"/>
                    </div>
                    <div class="am-cf">
                    	<input type="submit" value="提交" class="am-btn am-btn-secondary"/>
                    </div>
                </form>
                <hr>
                <p>............</p>
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
                    password: {required: true},
                    email: {required: true,email:true},
                    phone: {required: true},
                    school: {required: true},
                    college: {required: true}
                },
                messages: {
                    userName: {required: '用户名不能为空'},
                    password: {required: '密码不能为空'},
                    email: {required: '邮箱不能为空'},
                    phone: {required: '手机号不能为空'},
                    school: {required: '学校不能为空'},
                    college: {required: '学院不能为空'}
                },
                submitHandler: function() {
                    
                    $.ajax({
                        url: "${pageContext.request.contextPath}/home/user/addUser.action",
                        data: $("#loginForm").serialize(),
                        dataType: "json",
                        type: "POST"
                    }).success(function(data) {
                        if ( data ) {
                            if ( data.status == "ok" ) {
                                msgStas.html('<span class="am-text-success">' + data.message + '</span>');
                     
                                window.location.href="${pageContext.request.contextPath}/home/picture/identityUploadPicture.action";
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
