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
    			<h3>身份证图片上传</h3>
    			<p>Integrated Development Environment<br/>代码编辑，代码生成，界面设计，调试，编译</p>
  			</div>
  			<hr/>
		</div>
		
		<div class="am-g">
			<div class="am-u-sm-centered am-u-sm-5">
				<form class="am-form" id="imgUpload" action="${pageContext.request.contextPath}/home/picture/imgUploadPicture.action" enctype="multipart/form-data" method="post" class="am-form am-margin-top-lg">
					<div class="am-form-group am-form-file" for="doc-ipt-file-1">
						<label class="am-fl am-u-sm-4 am-text-left">身份证正面:</label>
						<div>
        					<button type="button" class="am-btn am-btn-default am-btn-sm">
          					<i class="am-icon-cloud-upload"></i> 选择要上传的文件</button>
      					</div>
						<input type="file" name="img" id="doc-ipt-file-1"/>
						<div hidden="hidden">
							<input type="text" name="type" value="identity">
						</div>
					</div>
					<div class="am-form-group am-form-file">
						<label class="am-fl am-u-sm-4 am-text-left " for="doc-ipt-file-2">身份证反面:</label>
						<div>
        					<button type="button" class="am-btn am-btn-default am-btn-sm">
          					<i class="am-icon-cloud-upload"></i> 选择要上传的文件</button>
      					</div>
						<input type="file" name="img" id="doc-ipt-file-2"/>
					</div>
					<div class="am-form-group">
						<p class="am-form-help am-text-danger user-hide" id="errormsg"><i class="am-icon-info-circle"></i><span></span></p>
						<input type="submit" class="am-btn am-btn-secondary am-margin-left-sm"/>
					</div>
				</form>
				<hr>
				<p class="am-center"></p>
			</div>
		</div>
<%--footer 与 java script脚本--%>
<%@include file="/WEB-INF/pages/admin/script-tpl.jsp" %>	
		<script type="text/javascript">
            $(function() {
                var $errormsg = $('#errormsg');
                $('#fileUpload').submit(function() {
            
                    if ( $('#doc-ipt-file-1').val() == "" ) {
                        showError("请选择要上传的图片");
                        return false;
                    }
                    if ( $('#doc-ipt-file-2').val() == "" ) {
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
  </body>
</html>
