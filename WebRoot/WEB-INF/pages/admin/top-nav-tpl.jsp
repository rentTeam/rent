<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

  </head>
  <body>
<!--top fixed nav start-->
<div class="am-topbar am-header-fixed admin-header">
    <div class="am-topbar-brand">
        <strong>租车系统</strong> <small>后台管理平台</small>
    </div>

    <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">响应式</span> <span class="am-icon-bars"></span>
    </button>

    <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

        <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
            <%--<li><a href="javascript:;"><span class="am-icon-envelope-o"></span>消息 <span class="am-badge am-badge-warning">5</span></a></li>--%>
            <li class="am-dropdown" data-am-dropdown>
                <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
                    <span class="am-icon-user"></span> <s:property value="#session.username"/> <span class="am-icon-caret-down"></span>
                </a>
                <ul class="am-dropdown-content">
                    <li><a href="${pageContext.request.contextPath}/admin/manager/logoutManager.action"><span class="am-icon-power-off"></span> 退出</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<!-- top fixed nav end -->
  