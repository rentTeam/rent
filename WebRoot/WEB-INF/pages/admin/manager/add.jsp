<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- 头部 -->
 <%@include file="/WEB-INF/pages/admin/header-tpl.jsp" %>
 <!-- 顶部导航 -->
 <%@include file="/WEB-INF/pages/admin/top-nav-tpl.jsp" %>
 <!-- 侧边导航 -->
 <%@include file="/WEB-INF/pages/admin/sidebar.jsp" %>
 
<div class="am-panel am-panel-default">
    <div class="am-panel-bd">
    	<h3>管理员信息添加</h3>
    </div>
    
    <div class="am-panel-bd">
    	<form class="am-form" id="addManagerForm"  method="post">
    		<div class="am-g">
    			 <div class="am-form-group">
                        <label>管理员名<span class="require">*</span></label>
                        <input type="text" name="userName" placeholder="管理员名" />
                 </div>
                 <div class="am-form-group">
                        <label>登录密码<span class="require">*</span></label>
                        <input type="password" name="password" placeholder="登录密码" />
                 </div>
                 <div class="am-form-group">
                        <label>身份证号<span class="require">*</span></label>
                        <input type="text" name="identity" placeholder="身份证号" />
                 </div>
           
                 <div class="am-margin-top-sm am-text-center">
                        <p class="am-form-help" id="msgStatus"></p>
                        <button type="submit" class="am-btn am-btn-primary am-margin-right-sm" id="addManager">添加</button>
                    </div>
    		</div>
    	</form>
    </div>
</div>

<%--footer 与 java script脚本--%>
<%@include file="/WEB-INF/pages/admin/script-tpl.jsp" %>
<script type="text/javascript">
$(function(){
	 var msgStatus=$("#msgStatus");
	 
	 $('#addManagerForm').validate({
                rules: {
                    userName: {required: true},
                    password: {required: true},
                    identity: {required: true}
                },
                messages: {
                    model: {required: '用户名不能为空'},
                    title: {required: '登录密码不能为空'},
                    price: {required: '请认真填好身份证号码'}
                },
                submitHandler: function() {
                    
                    $.ajax({
                        url: "${pageContext.request.contextPath}/admin/manager/addManager.action",
                        data: $("#addManagerForm").serialize(),
                        dataType: "json",
                        type: "POST"
                    }).success(function(data) {
                        if ( data ) {
                            if ( data.status == "ok" ) {
                                msgStas.html('<span class="am-text-success">' + data.message + '</span>');
                     
                                window.location.href="${pageContext.request.contextPath}/admin/manager/queryManager.action";
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