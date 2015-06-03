<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- 头部 -->
 <%@include file="/WEB-INF/pages/admin/header-tpl.jsp" %>
 <!-- 顶部导航 -->
 <%@include file="/WEB-INF/pages/admin/top-nav-tpl.jsp" %>
 <!-- 侧边导航 -->
 <%@include file="/WEB-INF/pages/admin/sidebar.jsp" %>
 
<div class="am-panel am-panel-default">
    <div class="am-panel-bd">
    	<h3>用户角色更新</h3>
    </div>
    
    <div class="am-panel-bd">
    	<form class="am-form" id="upodateRoleForm"  method="post">
    		<div class="am-g">
    			 <div class="am-form-group">
                        <label>角色名<span class="require">*</span></label>
                        <select name="roleId">
                        	<option value="${role.id }" selected="selected">${role.name }</option>
                        	<option value="2">普通管理员</option>
                        	<option value="3">vip用户</option>
                        	<option value="4">可租借用户</option>
                        	<option value="5">普通用户</option>
                        </select>
                 </div>
                 <div class="am-form-group">
                        <label>用户名<span class="require">*</span></label>
                        <input type="text" name="userName" value="${user.userName }" placeholder="用户名" />
                 </div>
           
                 <div class="am-margin-top-sm am-text-center">
                        <p class="am-form-help" id="msgStatus"></p>
                        <button type="submit" class="am-btn am-btn-primary am-margin-right-sm" id="addManager">更新</button>
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
	 
	 $('#updateUserForm').validate({
                rules: {
                    userName: {required: true}
                },
                messages: {
                    model: {required: '用户名不能为空'}
                },
                submitHandler: function() {
                    
                    $.ajax({
                        url: "${pageContext.request.contextPath}/admin/role/updateRole.action",
                        data: $("#updateRoleForm").serialize(),
                        dataType: "json",
                        type: "POST"
                    }).success(function(data) {
                        if ( data ) {
                            if ( data.status == "ok" ) {
                                msgStas.html('<span class="am-text-success">' + data.message + '</span>');
                     
                                window.location.href="${pageContext.request.contextPath}/admin/user/queryUser.action";
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

