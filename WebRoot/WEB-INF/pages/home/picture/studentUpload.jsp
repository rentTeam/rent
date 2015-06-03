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
    			<h1>Web ide</h1>
    			<p>Integrated Development Environment<br/>代码编辑，代码生成，界面设计，调试，编译</p>
  			</div>
  			<hr/>
		</div>
		
		<div class="am-g">
			<div class="am-u-sm-centered am-u-sm-5">
				<form class="am-form" id="imgUpload" action="${pageContext.request.contextPath}/home/picture/imgUploadPicture.action" enctype="multipart/form-data" method="post" class="am-form am-margin-top-lg">
					<div class="am-form-group">
						<label class="am-fl am-u-sm-3 am-text-left">学生证正面:</label>
						<input type="file" name="img" id="file1"/>
						<input type="text" hidden="hidden" name="type" value="student"/>
					</div>
					<div class="am-form-group">
						<label class="am-fl am-u-sm-3 am-text-left">学生证反面:</label>
						<input type="file" name="img" id="file2"/>
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
            
                    if ( $('#file1').val() == "" ) {
                        showError("请选择要上传的图片");
                        return false;
                    }
                    if ( $('#file12').val() == "" ) {
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