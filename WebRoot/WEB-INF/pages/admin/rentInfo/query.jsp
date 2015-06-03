<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 头部 -->
 <%@include file="/WEB-INF/pages/admin/header-tpl.jsp" %>
 <!-- 顶部导航 -->
 <%@include file="/WEB-INF/pages/admin/top-nav-tpl.jsp" %>
 <!-- 侧边导航 -->
 <%@include file="/WEB-INF/pages/admin/sidebar.jsp" %>
<!--  出租信息展示 start -->
<div class="am-panel am-panel-default">
    <div class="am-panel-bd">

        <h3>出租信息展示</h3>

    </div>
    <div class="am-panel-bd">
		<form id="rentInfoForm">
			<table class="am-table am-table-striped am-table-hover">
				<thead>
            		<tr>
                		<th>
                    		<input type="checkbox" data-opt="select-all">
                		</th>
                		<th>序号</th>
                		<th>车编号</th>
                		<th>限租时间</th>
                		<th>初始时间</th>
                		<th>用户名</th>
                		<th>操作</th>
            		</tr>
            	</thead>
            	<tbody>
            		<s:iterator value="#request.rlist" id="id" status="loop">
            			<tr>
            				<td>
            					<input type="checkbox" name="rentInfoArray" value="<s:property value="#id.id"/>">
            				</td>
            				<td>${clist[loop.index].carId}</td>
            				<td><s:property value="#id.timeLimit"/></td>
            				<td><s:property value="#id.start"/></td>
            				<td>${ulist[loop.index].userName }</td>
            				<td>
                        		<div class="am-dropdown" data-am-dropdown>
                            		<button class="am-btn am-btn-primary am-btn-xs am-dropdown-toggle" data-am-dropdown-toggle>操作 <span class="am-icon-caret-down"></span>
                            		</button>
                            		<ul class="am-dropdown-content">
                                		<li><a class="deleteCar" href="${pageContext.request.contextPath}/admin/rentInfo/deleterentInfo.action?carId=${clist[loop.index].carId}&carId=${ulist[loop.index].userId}">删除</a></li>
                            		</ul>
                        		</div>
                    		</td>
            			</tr>
            		</s:iterator>
            	</tbody>
			</table>
		</form>
    </div>
    
</div>
<!-- 出租信息展示 end -->
<%--footer 与 java script脚本--%>
<%@include file="/WEB-INF/pages/admin/script-tpl.jsp" %>
<script type="text/javascript">
$(function(){
	$('.deleteRentInfo').click(function(e){
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
});
</script>
<%--尾部--%>
<%@include file="/WEB-INF/pages/admin/footer-tpl.jsp"%>
