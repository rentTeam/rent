<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 头部 -->
 <%@include file="/WEB-INF/pages/admin/header-tpl.jsp" %>
 <!-- 顶部导航 -->
 <%@include file="/WEB-INF/pages/admin/top-nav-tpl.jsp" %>
 <!-- 侧边导航 -->
 <%@include file="/WEB-INF/pages/admin/sidebar.jsp" %>
 
<div class="am-panel am-panel-default">
    <div class="am-panel-bd">
    	<h1>欢迎您，<s:property value="#session.adminUsername" /></h1>
    </div>
    <div class="am-panel-bd">
    	<!--file upload start-->
		<div class="am-g user-col-no-padding">
			<div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
				<form  id="fileUpload" action="${pageContext.request.contextPath}/admin/picture/imgUpload2Picture.action" enctype="multipart/form-data" method="post" class="am-form am-margin-top-lg">
					<fieldset>
                        <div class="am-form-group" style="display: none">
							   <input hidden="hidden" type="text" value="${managerId}" name="managerId"/>
                        </div>
                        <div class="am-form-group" style="display: none">
							   <input hidden="hidden" type="text" value="manager" name="type"/>
                        </div>
						<div class="am-form-group">
							<label class="am-sr-only">图片<span class="require">*</span></label>
							<input type="file"  id="file" name="file">
                            <p class="am-form-help am-text-danger user-hide" id="errormsg"><i class="am-icon-info-circle"></i><span></span></p>
						</div>

						<p>
							<button type="submit" class="am-btn am-btn-block user-btn-danger">上&nbsp;&nbsp;传</button>
						</p>
					</fieldset>
				</form>
			</div>
		</div>
		<!--file upload end-->
    </div>
</div>

<%--footer 与 java script脚本--%>
<%@include file="/WEB-INF/pages/admin/script-tpl.jsp" %>
 <script type="text/javascript">
            $(function() {
                var $errormsg = $('#errormsg');
                $('#fileUpload').submit(function() {
            
                    if ( $('#file').val() == "" ) {
                        showError("请选择要上传的图片");
                        return false;
                    }
                    return true;
                });


                function  showError(msg) {
                    $errormsg.show();
                    $errormsg.find('span').html(msg);
                }
            });


        </script>
<%--尾部--%>
<%@include file="/WEB-INF/pages/admin/footer-tpl.jsp"%>