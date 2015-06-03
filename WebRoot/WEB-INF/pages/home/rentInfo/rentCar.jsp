<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>租车页面</title>

	<head>
		<title>picture</title>
		<meta charset="utf-8">
		<link rel="stylesheet" href="public/assets/css/amazeui.min.css"/>
        <link rel="stylesheet" href="public/assets/css/amazeui.css"/>
        <link rel="stylesheet" href="public/assets/css/app.css"/>
		<link rel="stylesheet" href="public/admin.css"/>
	</head>
	<body>
		<!--headnav start-->
		<div class="am-topbar am-topbar-inverse am-padding-sm">
			<div class="am-u-sm-3 am-margin-top-sm">
				<a href="#"><span class="am-icon-reply am-icon-lg am-btn-primary"></span></a>
			</div>
			<div class="am-u-sm-6">
				<span class="am-topbar-brand am-text-xxxl">
					<a href="#"><strong>租车信息</strong></a>
				</span>
			</div>
			<div class="am-u-sm-3"></div>
		</div>
		<!--headnav end-->
		<!--content start-->
		<div class="">
			<div class="am-u-sm-4">
				<img src="carPicture/${picture.fileFileName }"
					width="220" height="230"/>
			</div>
			<div class="am-u-sm-8">
				<p class="am-text-xxl">
					<strong>
					${car.title }<br/>
					price:${car.price }/小时<br/>
					</strong>
				</p>
			</div>
			<div class="am-u-sm-8 am-fr">
				<form class="am-form">
					<div class="am-form-group">
						<label class="am-text-xxl">
							<strong>租借时间</strong>
						</label>
						<input  type="text" name="timeLimit"/>
					</div>
					<div class="am-form-group">
						<button type="submit" class="am-btn am-btn-primary">提交</button>
					</div>
				</form>
			</div>
		</div>
		<!--content end-->
		<script type="text/javascript" src="public/assets/js/amazeui.min.js"></script>
        <script type="text/javascript" src="public/assets/js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="public/assets/js/app.js"></script>
	</body>
</html>