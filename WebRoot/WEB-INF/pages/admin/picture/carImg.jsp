<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- 头部 -->
 <%@include file="/WEB-INF/pages/admin/header-tpl.jsp" %>
 <!-- 顶部导航 -->
 <%@include file="/WEB-INF/pages/admin/top-nav-tpl.jsp" %>
 <!-- 侧边导航 -->
 <%@include file="/WEB-INF/pages/admin/sidebar.jsp" %>
 
<div class="am-panel am-panel-default">
    <div class="am-panel-bd">
    	<h1>车图片上传</h1>
    </div>
    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">首页</strong> / <small>上传车辆图片</small></div>
    </div>
    <div class="am-panel-bd">
    	<!--file upload start-->
		<div class="am-g user-col-no-padding">
			<div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
				<form  id="fileUpload" action="${pageContext.request.contextPath}/admin/picture/imgUploadPicture.action" enctype="multipart/form-data" method="post" class="am-form am-margin-top-lg">
					<fieldset>
                        <input type="text" value="<%=request.getParameter("carId") %>" name="carId"/>
                        <div class="am-form-group">
							   <input type="hidden"  value="car" name="type"/>
                        </div>
						<div class="am-form-group">
							<label class="am-sr-only">图片<span class="require">*</span></label>
							<input type="file"  id="file" name="img">
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
                    alert(""+$("input[name='carId']").val());
                    $errormsg.find('span').html(msg);
                }
            });


        </script>
<%--尾部--%>
<%@include file="/WEB-INF/pages/admin/footer-tpl.jsp"%>