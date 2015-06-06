<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html class="no-js">
  <head>
    <title><s:property value="#request.pageTitle eq null ? '租车系统' : #request.pageTitle"/></title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/public/assets/css/amazeui.flat.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/public/assets/css/admin.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/public/assets/css/amazeui.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/public/assets/css/akalin.css" />