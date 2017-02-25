<%@page import="cn.edu.jxnu.x3104.team3.bean.Cookbook"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>菜谱管理</title>
<meta name="description" content="表格页面">
<meta name="keywords" content="table">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png" href="http://localhost:8080/RiceRoll/WebContent/PAGE/JSP/admin/i/favicon.png">
<link rel="apple-touch-icon-precomposed" href="http://localhost:8080/RiceRoll/WebContent/PAGE/JSP/admin/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="stylesheet" href="http://localhost:8080/RiceRoll/WebContent/PAGE/JSP/admin/css/amazeui.css" />
<link rel="stylesheet" href="http://localhost:8080/RiceRoll/WebContent/PAGE/JSP/admin/css/admin.css">
</head>
<body>
	<%
		//String name = (String) request.getParameter("name"); 
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
				href="javascript:;"> <span class="am-icon-users"></span> 
				<%-- <% 	if (name != null) {%> <%=name%> <%
						} else {
					%>管理员<%
						}
					%> --%> <span class="am-icon-caret-down"></span>
			</a>
				<ul class="am-dropdown-content">
					<li><a href="/RiceRoll/admins/info.action"><span
							class="am-icon-user"></span> 资料</a></li>
					<li><a href="#"><span class="am-icon-cog"></span> 设置</a></li>
					<li><a href="/RiceRoll/admins/logout.action"><span
							class="am-icon-power-off"></span> 退出</a></li>
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
						<li><a href="/RiceRoll/WebContent/PAGE/JSP/admin/admin-cookbook-form.jsp" class="am-cf"><span
								class="am-icon-check"></span> 菜谱管理</a></li>
						<li><a href="/RiceRoll/WebContent/PAGE/JSP/admin/admin-food-material-form.jsp"><span
								class="am-icon-puzzle-piece"></span> 食材管理</a></li>
						<li><a href="/RiceRoll/WebContent/PAGE/JSP/admin/admin-condiment-form.jsp"><span
								class="/RiceRoll/WebContent/PAGE/JSP/admin/am-icon-th"></span> 调料管理</a></li>
						<li><a href="admin-form.jsp"><span
								class="/RiceRoll/WebContent/PAGE/JSP/admin/am-icon-calendar"></span> 每日推荐</a></li>
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
					<strong class="am-text-primary am-text-lg">表格</strong> / <small>菜谱</small>
				</div>
			</div>
			<form action="">
				<div class="am-g">
					<div class="am-u-md-6 am-cf">
						<div class="am-fl am-cf">
							<div class="am-btn-toolbar am-fl">
								<div class="am-btn-group am-btn-group-xs">
									<button type="button" class="am-btn am-btn-default"
										onclick="window.location.reload('admin-form.jsp')">
										<span class="am-icon-plus"></span> 新增
									</button>
								</div>

								<div class="am-form-group am-margin-left am-fl">
									<select>
										<option value="option1">按编号搜索</option>
										<option value="option2">按名称搜索</option>
										<option value="option3">按关键词搜索</option>
										<option value="option4">按食材搜索</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="am-u-md-3 am-cf">
						<div class="am-fr">
							<div class="am-input-group am-input-group-sm">
								<input type="text" class="am-form-field"> <span
									class="am-input-group-btn"> <input
									class="am-btn am-btn-default" type="submit" value="搜索">
								</span>
							</div>
						</div>
					</div>
				</div>
			</form>
			<div class="am-g">
				<div class="am-u-sm-12">
					<form class="am-form">
						<table class="am-table am-table-striped am-table-hover table-main">
							<thead>
								<tr>
									<th class="table-check">
									<th class="table-id">ID</th>
									<th class="table-title">标题</th>
									<th class="table-type">类别</th>
									<th class="table-author">作者</th>
									<th class="table-date">修改日期</th>
									<th class="table-set">操作</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="cookbooks" >
									<tr>
										<td><input type="checkbox"/></td>
										
										<td><s:text name="cookbook_id"></s:text></td>
										<td><s:text name="cookbook_name"></s:text></td>
										<td><s:text name="cookbook_collect_time"></s:text></td>
										<td><s:property value="cookbook_click_time" /></td>
										<td><s:property value="cookbook_download_time" /></td>
										<td>
											<div class="am-btn-toolbar">
												<div class="am-btn-group am-btn-group-xs">
													<button
														class="am-btn am-btn-default am-btn-xs am-text-secondary">
														<span class="am-icon-pencil-square-o"></span> 编辑
													</button>
													<button
														class="am-btn am-btn-default am-btn-xs am-text-danger">
														<span class="am-icon-trash-o"></span> 删除
													</button>
												</div>
											</div>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
						<div class="am-cf">
						<!-- 共 15 条记录 -->	
							<div class="am-fr">
								<ul class="am-pagination">
									<li class="am-disabled"><a href="#">«</a></li>
									<li class="am-active"><a href="#">1</a></li>
									<li><a href="#">2</a></li>
									<li><a href="#">3</a></li>
									<li><a href="#">4</a></li>
									<li><a href="#">5</a></li>
									<li><a href="#">»</a></li>
								</ul>
							</div>
						</div>
						<hr />
						<p>注：.....</p>
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
	<script src="/RiceRoll/WebContent/PAGE/JSP/admin/js/jquery.min.js"></script>
	<script src="/RiceRoll/WebContent/PAGE/JSP/admin/js/amazeui.min.js"></script>
	<!--<![endif]-->
	<script src="/RiceRoll/WebContent/PAGE/JSP/admin/js/app.js"></script>
</body>
</html>
