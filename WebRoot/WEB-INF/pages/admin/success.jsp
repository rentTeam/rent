<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<%--
    黄向阳
    后台成功页面模板
    List<String> 展示
--%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>成功提示</title>
    <link rel="stylesheet" href="/public/assets/css/amazeui/css/amazeui.flat.css" />
    <style>
        body {
            background: #EEEEEE;
        }
    </style>
</head>

<body>
<div class="am-container">
    <div class="am-g am-margin-top-xl">
        <div class="am-u-sm-12 am-u-md-6 am-u-lg-5 am-u-sm-centered">
            <div class="am-panel am-panel-default">
                <div class="am-panel-hd">
                    <h3 class="am-panel-title"><i class="am-icon-info-circle am-padding-right-sm"></i>成功提示</h3>
                </div>
                <div class="am-panel-bd">
                    <div class="am-text-center am-center"><i class="am-icon-check am-text-xxxl am-text-success"></i>
                    </div>
                    <div class="am-center am-text-center">
                        <s:property value="#request.msgList.size() eq 0 ? '<p>成功啦!</p>':''"/>
                        <s:iterator value="#request.msgList" status="status" id="msg">

                            <p><s:property value="#msg"/></p>

                        </s:iterator>
                    </div>
                    <p class="am-text-center"><a href="javascript:window.history.back();">返回</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>