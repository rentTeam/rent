<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 
	车辆信息添加
 -->
 <!-- 头部 -->
 <%@include file="/WEB-INF/pages/admin/header-tpl.jsp" %>
 <!-- 顶部导航 -->
 <%@include file="/WEB-INF/pages/admin/top-nav-tpl.jsp" %>
 <!-- 侧边导航 -->
 <%@include file="/WEB-INF/pages/admin/sidebar.jsp" %>
 <!-- 车辆信息添加 START -->
<div class="am-panel am-panel-default">

    <div class="am-panel-bd">

        <h3 class="am-panel-title"></h3>

    </div>
    <div class="am-panel-bd">
    	<form class="am-form" id="addCarForm" action="${pageContext.request.contextPath}/admin/car/addCar.action" method="post">
    		<div class="am-g">
    			 <div class="am-form-group">
                        <label>车型号<span class="require">*</span></label>
                        <input type="text" name="model" placeholder="车型号" />
                 </div>
                 <div class="am-form-group">
                        <label>展示时的标题<span class="require">*</span></label>
                        <input type="text" name="title" placeholder="展示时的标题" />
                 </div>
                 <div class="am-form-group">
                        <label>每小时的出租价格<span class="require">*</span></label>
                        <input type="text" name="price" placeholder="每小时的出租价格" />
                 </div>
                 <div class="am-form-group">
                        <label>出租时间限制<span class="require">*</span></label>
                        <input type="text" name="timeLimit" placeholder="出租时间限制" />
                 </div>
                 <div class="am-form-group">
                        <label>车的种类<span class="require">*</span></label>
                        <input type="text" name="type" placeholder="车的种类" />
                 </div>
                 <div class="am-margin-top-sm am-text-center">
                        <p class="am-form-help" id="msgStatus"></p>
                        <button type="submit" class="am-btn am-btn-primary am-margin-right-sm" id="addCar">添加</button>
                 </div>
    		</div>
    	</form>
    </div>
</div>
<!-- 车辆信息添加 END-->
<%--footer 与 java script脚本--%>
<%@include file="/WEB-INF/pages/admin/script-tpl.jsp" %>
<script type="text/javascript">
$(function(){
	 var msgStatus=$("#msgStatus");
	 
	 $('#addCarForm').validate({
                rules: {
                    model: {required: true},
                    title: {required: true},
                    price: {required: true,number:true},
                    timeLimit: {required: true,number:true},
                    type: {required: true}
                },
                messages: {
                    model: {required: '车辆类型不能为空'},
                    title: {required: '展示时的标题不能为空'},
                    price: {required: '请认真填好价格'},
                    timeLimit: {required: '限租时间认真填好'},
                    type: {required: '种类不能为空'}
                },
                submitHandler: function() {
                    
                    $.ajax({
                        url: "${pageContext.request.contextPath}/admin/car/addCar.action",
                        data: $("#addCarForm").serialize(),
                        dataType: "json",
                        type: "POST"
                    }).success(function(data) {
                        if ( data ) {
                            if ( data.status == "ok" ) {
                                msgStas.html('<span class="am-text-success">' + data.message + '</span>');
                     
                                window.location.href="${pageContext.request.contextPath}/admin/car/queryCar.action";
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
