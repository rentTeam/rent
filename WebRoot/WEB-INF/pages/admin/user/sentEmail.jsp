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
		<form id="sentEmailForm" class="am-form">
			<div class="am-g">
				<div class="am-form-group">
					<label>收件人<span class="require">*</span></label>
                    <input type="text" name="receiveMail" placeholder="收件人" />
				</div>
				<div class="am-form-group">
					<label>发件人<span class="require">*</span></label>
                    <input type="text" name="sentMail" placeholder="发件人" />
				</div>
				<div class="am-form-group">
					<label>登录密码<span class="require">*</span></label>
                    <input type="password" name="password" placeholder="登录密码" />
				</div>
				<div class="am-form-group">
					<label>主题<span class="require">*</span></label>
                    <input type="text" name="subject" placeholder="主题" />
				</div>
				<div class="am-form-group">
					<label>内容<span class="require">*</span></label>
                    <textarea name="content" placeholder="内容" ></textarea>
				</div>
			</div>
			<div class="am-margin-top-sm am-text-center">
                 <p class="am-form-help" id="msgStatus"></p>
                 <button type="submit" class="am-btn am-btn-primary am-margin-right-sm" id="addCar">发送</button>
            </div>
		</form>
    </div>
</div>

<%--footer 与 java script脚本--%>
<%@include file="/WEB-INF/pages/admin/script-tpl.jsp" %>
<script type="text/javascript">
$(function(){
	var msgStatus=$("#msgStatus");
	 
	 $('#sentEmailForm').validate({
                rules: {
                    receiveMail: {required: true},
                    sentMail: {required: true},
                    password: {required: true,number:true},
                    subject: {required: true,number:true},
                    content: {required: true}
                },
                messages: {
                    receiveMail: {required: '收件人不能为空'},
                    sentMail: {required: '发件人不能为空'},
                    password: {required: '登录密码不能为空'},
                    subject: {required: '主题认真填好'},
                    content: {required: '内容不能为空'}
                },
                submitHandler: function() {
                    
                    $.ajax({
                        url: "${pageContext.request.contextPath}/admin/user/sentEmailUser.action",
                        data: $("#sentEmailForm").serialize(),
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