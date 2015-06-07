<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 头部 -->
 <%@include file="/WEB-INF/pages/admin/header-tpl.jsp" %>
 <!-- 顶部导航 -->
 <%@include file="/WEB-INF/pages/admin/top-nav-tpl.jsp" %>
 <!-- 侧边导航 -->
 <%@include file="/WEB-INF/pages/admin/sidebar.jsp" %>
<!--  车辆信息展示 start -->
<div class="am-panel am-panel-default">
	<style>
    	.imgStyle{
        	width:120px;
        	height:90px;
    	}
	</style>
</head>
<body>
    <div class="am-panel-bd">

        <h3>车辆信息展示</h3>

    </div>
    <div class="am-panel-bd">
		<form id="carForm">
			<table class="am-table am-table-striped am-table-hover">
				<thead>
            		<tr>
                		<th>
                    		<input type="checkbox" data-opt="select-all">
                		</th>
                		<th>车编号</th>
                		<th>展示时的标题</th>
                		<th>车的图片</th>
                		<th>每小时的出租价格</th>
                		<th>限租时间</th>
                		<th>车的种类</th>
                		<th>操作</th>
            		</tr>
            	</thead>
            	<tbody>
            		<s:iterator value="#request.clist" id="id" status="loop">
            			<tr>
            				<td>
            					<input type="checkbox" name="carArray" value="<s:property value="#id.id"/>">
            				</td>
            				<td><s:property value="#id.carId"/></td>
            				<td><s:property value="#id.title"/></td>
            				<td><img class="imgStyle" src="${pageContext.request.contextPath }/${url[loop.index] }"></td>
            				<td><s:property value="#id.price"/></td>
            				<td><s:property value="#id.timeLimit"/></td>
            				<td><s:property value="#id.type"/></td>
            				<td>
                        		<div class="am-dropdown" data-am-dropdown>
                            		<button class="am-btn am-btn-primary am-btn-xs am-dropdown-toggle" data-am-dropdown-toggle>操作 <span class="am-icon-caret-down"></span>
                            		</button>
                            		<ul class="am-dropdown-content">
                                		<li><a href="${pageContext.request.contextPath}/admin/car/intoUpdateCar.action?carId=<s:property value="#id.carId"/>">修改车辆信息</a></li>
                                		<li><a href="${pageContext.request.contextPath}/admin/picture/intoUploadPicture.action?carId=<s:property value="#id.carId"/>">上传图片</a></li>
                                		<li><a href="${pageContext.request.contextPath}/admin/picture/intoModifyPicture.action?carId=<s:property value="#id.carId"/>">更改图片</a></li>
                                		<li><a class="deleteCar" href="${pageContext.request.contextPath}/admin/car/deleteCar.action?carId=<s:property value="#id.carId"/>">删除</a></li>
                            		</ul>
                        		</div>
                    		</td>
            			</tr>
            		</s:iterator>
            	</tbody>
			</table>
		</form>
		 <div class="am-cf">
            <a href="${pageContext.request.contextPath}/admin/car/intoAddCar.action" class="am-btn am-btn-success am-btn-sm am-fr"><i class="am-icon-plus"></i>添加</a>
        </div>
    </div>
    
</div>
<!-- 车辆信息展示 end -->
<%--footer 与 java script脚本--%>
<%@include file="/WEB-INF/pages/admin/script-tpl.jsp" %>
<script type="text/javascript">
$(function(){
	$('.deleteCar').click(function(e){
		 e.preventDefault();
            var delelteUrl = $(this).attr('href');
            $.ajax({
                url: delelteUrl,
                dataType: "json",
                type: "POST"
            }).success(function(data) {
                if ( data ) {
                    if ( data.status == "ok" ) {
                        alert(data.message);
                        window.location.reload(true);
                    }else{
                        alert(data.message);
                    }
                } else {
                    alert("网络异常");
                }
            }).fail(function() {
                alert(" error final！");
                alert("网络异常");
            });
	});
})
</script>
<%--尾部--%>
<%@include file="/WEB-INF/pages/admin/footer-tpl.jsp"%>