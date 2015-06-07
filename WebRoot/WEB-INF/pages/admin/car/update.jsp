<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/pages/admin/header-tpl.jsp" %>
 <!-- 顶部导航 -->
 <%@include file="/WEB-INF/pages/admin/top-nav-tpl.jsp" %>
 <!-- 侧边导航 -->
 <%@include file="/WEB-INF/pages/admin/sidebar.jsp" %>
 
<div class="am-panel am-panel-default">
    <div class="am-panel-bd">
        <h3>车信息更新</h3>
    </div>
    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">首页</strong> / <small>车辆信息更新</small></div>

    </div>
    <div class="am-panel-bd">
    	<form class="am-form" id="updateCarForm"  method="post">
    		<div class="am-g">
    			<input type="hidden" value="<%=request.getParameter("carId") %>" name="carId"/>
    			 <div class="am-form-group am-u-lg-centered am-u-lg-5">
                        <label>车型号<span class="require">*</span></label>
                        <input type="text" name="model" value="${car.model}" placeholder="车型号" />
                 </div>
                 <div class="am-form-group am-u-lg-centered am-u-lg-5">
                        <label>展示时的标题<span class="require">*</span></label>
                        <input type="text" name="title" value="${car.title }" placeholder="展示时的标题" />
                 </div>
                 <div class="am-form-group am-u-lg-centered am-u-lg-5">
                        <label>每小时的出租价格<span class="require">*</span></label>
                        <input type="text" name="price" value="${car.price }" placeholder="每小时的出租价格" />
                 </div>
                 <div class="am-form-group am-u-lg-centered am-u-lg-5">
                        <label>出租时间限制<span class="require">*</span></label>
                        <input type="text" name="timeLimit" value="${car.timeLimit }" placeholder="出租时间限制" />
                 </div>
                 <div class="am-form-group am-u-lg-centered am-u-lg-5">
                        <label>车的种类<span class="require">*</span></label>
                        <input type="text" name="type" value="${car.type }" placeholder="车的种类" />
                 </div>
                 <div class="am-margin-top-sm am-text-center am-u-lg-centered am-u-lg-5">
                        <p class="am-form-help" id="msgStatus"></p>
                        <button type="submit" class="am-btn am-btn-primary am-margin-right-sm" id="addCar">更新</button>
                    </div>
    		</div>
    	</form>
    </div>
</div>

<%--footer 与 java script脚本--%>
<%@include file="/WEB-INF/pages/admin/script-tpl.jsp" %>
<script type="text/javascript">
$(function(){
	 var msgStas=$("#msgStatus");
	 
	 $('#updateCarForm').validate({
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
                        url: "${pageContext.request.contextPath}/admin/car/updateCar.action",
                        data: $("#updateCarForm").serialize(),
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
