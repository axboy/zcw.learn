<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>个人信息</title>
<meta name="description" content="这是一个 user 页面">
<meta name="keywords" content="user">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png" href="i/favicon.png">
<link rel="apple-touch-icon-precomposed"
	href="i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="stylesheet" href="css/amazeui.min.css" />
<link rel="stylesheet" href="css/admin.css">
</head>
<body>
	<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->
	<%
		String name = (String) request.getParameter("name");
	    String mail = (String) request.getParameter("mail");
	    String phone = (String) request.getParameter("phone");
	    String authority = (String) request.getParameter("authority");
	    String cookbook = (String) request.getParameter("cookbook");
	    String foodmaterial = (String) request.getParameter("foodmaterial");
	    String announce = (String) request.getParameter("announce");
	    String recommend = (String) request.getParameter("recommend");
	    String condiment = (String) request.getParameter("condiment");
	%>
	<header class="am-topbar admin-header">
	<div class="am-topbar-brand">
		<strong>RiceRoll</strong> <small>后台管理系统</small>
	</div>

	<button
		class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
		data-am-collapse="{target: '#topbar-collapse'}">
		<span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span>
	</button>

	<div class="am-collapse am-topbar-collapse" id="topbar-collapse">

		<ul
			class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
			<li><a href="javascript:;"><span class="am-icon-envelope-o"></span>
					收件箱 <span class="am-badge am-badge-warning">5</span></a></li>
			<li class="am-dropdown" data-am-dropdown><a
				class="am-dropdown-toggle" data-am-dropdown-toggle
				href="javascript:;"> <span class="am-icon-users"></span>  <%
 	if (name != null) {%>					
 					<%=name%>
					<%
						} else {
					%>管理员<%
						}
					%>  <span
					class="am-icon-caret-down"></span>
			</a>
				<ul class="am-dropdown-content">
					<li><a href="/RiceRoll/admins/info.action"><span class="am-icon-user"></span> 资料</a></li>
					<li><a href="#"><span class="am-icon-cog"></span> 设置</a></li>
					<li><a href="/RiceRoll/admins/logout.action"><span class="am-icon-power-off"></span> 退出</a></li>
				</ul></li>
			<li><a href="javascript:;" id="admin-fullscreen"><span
					class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
		</ul>
	</div>
	</header>

	<div class="am-cf admin-main">
		<!-- sidebar start -->
		<div class="admin-sidebar">
			<ul class="am-list admin-sidebar-list">
				<li><a href="/RiceRoll/admins/index.action"><span
						class="am-icon-home"></span> 首页</a></li>
				<li class="admin-parent"><a class="am-cf"
					data-am-collapse="{target: '#collapse-nav'}"><span
						class="am-icon-file"></span> 资源管理模块 <span
						class="am-icon-angle-right am-fr am-margin-right"></span></a>
					<ul class="am-list am-collapse admin-sidebar-sub am-in"
						id="collapse-nav">
						<li><a href="admin-cookbook-form.jsp" class="am-cf"><span
								class="am-icon-check"></span> 菜谱管理</a></li>
						<li><a href="admin-food-material-form.jsp"><span
								class="am-icon-puzzle-piece"></span> 食材管理</a></li>
						<li><a href="admin-condiment-form.jsp"><span
								class="am-icon-th"></span> 调料管理</a></li>
						<li><a href="admin-form.jsp"><span
								class="am-icon-calendar"></span> 每日推荐</a></li>
					</ul></li>
				<li><a href="/RiceRoll/admins/logout.action"><span
						class="am-icon-sign-out"></span> 注销</a></li>
			</ul>

			<div class="am-panel am-panel-default admin-sidebar-panel">
				<div class="am-panel-bd">
					<p>
						<span class="am-icon-bookmark"></span> 公告
					</p>
					<p>均衡营养，平衡膳食。—— unknow</p>
				</div>
			</div>
		</div>
		<!-- sidebar end -->

		<!-- content start -->
		<div class="admin-content">
			<div class="am-cf am-padding">
				<div class="am-fl am-cf">
					<strong class="am-text-primary am-text-lg">个人资料</strong> / <small>Personal
						information</small>
				</div>
			</div>

			<hr />

			<div class="am-g">

				 <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
					<div class="am-panel am-panel-default">
						<!-- <div class="am-panel-bd">
							 <div class="am-g">
								<div class="am-u-md-4">
									<img class="am-img-circle am-img-thumbnail"
										src=""
										alt="" />
								</div>
								<div class="am-u-md-8">
									<p>
										你可以使用<a href="#">gravatar.com</a>提供的头像或者使用本地上传头像。
									</p>
									<form class="am-form">
										<div class="am-form-group">
											<input type="file" id="user-pic">
											<p class="am-form-help">请选择要上传的文件...</p>
											<button type="button" class="am-btn am-btn-primary am-btn-xs">保存</button>
										</div>
									</form>
								</div>
							</div> 
						</div> -->
					</div>

					<div class="am-panel am-panel-default">
						<div class="am-panel-bd">
							<div class="user-info">
								<p>权限信息</p>
								<div >
								<%if(authority!=null&&(authority=="1"||authority.equals("1"))){ %>
								<span
								class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span><%}else{%>
								<span
								class="am-icon-star am-fr am-margin-right admin-icon-red"></span><%} %>
									<!-- class="am-progress am-progress-sm"<div class="am-progress-bar" style="width: 60%"></div> -->
									&nbsp授权相关权限
								</div>				
								<div >
								<%if(cookbook!=null&&(cookbook=="1"||cookbook.equals("1"))){ %>
								<span
								class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span><%}else{%>
								<span
								class="am-icon-star am-fr am-margin-right admin-icon-red"></span><%} %>
									<!-- class="am-progress am-progress-sm"<div class="am-progress-bar" style="width: 60%"></div> -->
									&nbsp菜谱相关权限
								</div>			
								<div >
								<%if(foodmaterial!=null&&(foodmaterial=="1"||foodmaterial.equals("1"))){ %>
								<span
								class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span><%}else{%>
								<span
								class="am-icon-star am-fr am-margin-right admin-icon-red"></span><%} %>
									<!-- class="am-progress am-progress-sm"<div class="am-progress-bar" style="width: 60%"></div> -->
									&nbsp食材相关权限
								</div>			
								<div >
								<%if(condiment!=null&&(condiment=="1"||condiment.equals("1"))){ %>
								<span
								class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span><%}else{%>
								<span
								class="am-icon-star am-fr am-margin-right admin-icon-red"></span><%} %>
									<!-- class="am-progress am-progress-sm"<div class="am-progress-bar" style="width: 60%"></div> -->
									&nbsp调料相关权限
								</div>			
								<div >
								<%if(recommend!=null&&(recommend=="1"||recommend.equals("1"))){ %>
								<span
								class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span><%}else{%>
								<span
								class="am-icon-star am-fr am-margin-right admin-icon-red"></span><%} %>
									<!-- class="am-progress am-progress-sm"<div class="am-progress-bar" style="width: 60%"></div> -->
									&nbsp推荐相关权限
								</div>			
								<div >
								<%if(announce!=null&&(announce=="1"||announce.equals("1"))){ %>
								<span
								class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span><%}else{%>
								<span
								class="am-icon-star am-fr am-margin-right admin-icon-red"></span><%} %>
									<!-- class="am-progress am-progress-sm"<div class="am-progress-bar" style="width: 60%"></div> -->
									&nbsp发布公告权限
								</div>							
							</div>
							<div class="user-info">
								<p class="user-info-order">
									<strong>黄色为已有权限</strong>
								</p>
							</div>
						</div>
					</div> 

				</div> 

				<div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
					<form class="am-form am-form-horizontal" 
					action="/RiceRoll/admins/updateinfo.action">
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">姓名
								/ Name</label>
							<div class="am-u-sm-9">
								<input type="text" id="user-name" name="admin_name" placeholder="<%=name%>">
								<small>输入你的名字，让我们记住你。</small>
							</div>
						</div>

						<br>
						
						<div class="am-form-group">
							<label for="user-email" class="am-u-sm-3 am-form-label">电子邮件
								/ Email</label>
							<div class="am-u-sm-9">
								<input type="email" id="user-email" name="admin_mail_address"
									placeholder="<%=mail%>"> <small>邮箱你懂得...</small>
							</div>
						</div>

						<br>
						
						<div class="am-form-group">
							<label for="user-phone" class="am-u-sm-3 am-form-label">电话
								/ Telephone</label>
							<div class="am-u-sm-9">
								<input type="tel" id="user-phone" name="admin_phone_number"
									placeholder="<%=phone%>">
									<small>从这里可以修改电话号码</small>
							</div>
						</div>

						<br>
						
						<!-- <div class="am-form-group">
							<label for="user-QQ" class="am-u-sm-3 am-form-label">QQ</label>
							<div class="am-u-sm-9">
								<input type="email" id="user-QQ" placeholder="输入你的QQ号码">
							</div>
						</div>

						<div class="am-form-group">
							<label for="user-weibo" class="am-u-sm-3 am-form-label">微博
								/ Twitter</label>
							<div class="am-u-sm-9">
								<input type="email" id="user-weibo"
									placeholder="输入你的微博 / Twitter">
							</div>
						</div>

						<div class="am-form-group">
							<label for="user-intro" class="am-u-sm-3 am-form-label">简介
								/ Intro</label>
							<div class="am-u-sm-9">
								<textarea class="" rows="5" id="user-intro" placeholder="输入个人简介"></textarea>
								<small>250字以内写出你的一生...</small>
							</div>
						</div> -->

						<div class="am-form-group">
							<div class="am-u-sm-9 am-u-sm-push-3">
								<input type="submit" class="am-btn am-btn-primary" value="保存修改">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!-- content end -->

	</div>

	<footer>
	<hr>
	<p class="am-padding-left">
		© 2015 RiceRoll. <a
			href="http://localhost:8080/RiceRoll/PAGE/HTML/welcome.html">饭团首页</a>
	</p>
	</footer>

	<!--[if lt IE 9]>
<script src="js/jquery1.11.1.min.js"></script>
<script src="js/modernizr.js"></script>
<script src="js/polyfill/rem.min.js"></script>
<script src="js/polyfill/respond.min.js"></script>
<script src="js/amazeui.legacy.js"></script>
<![endif]-->

	<!--[if (gte IE 9)|!(IE)]><!-->
	<script src="js/jquery.min.js"></script>
	<script src="js/amazeui.min.js"></script>
	<!--<![endif]-->
	<script src="js/app.js"></script>
</body>
</html>
