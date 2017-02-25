<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>饭团后台首页</title>
<meta name="description" content="这是一个 index 页面">
<meta name="keywords" content="index">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png" href="i/favicon.png">
<link rel="apple-touch-icon-precomposed" href="i/app-icon72x72@2x.png">
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
		String userNum = (String) request.getParameter("userNum");
		String todayUserNum = (String) request.getParameter("todayUserNum");
		String cookbookNum = (String) request.getParameter("cookbookNum");
		String downloadNum = (String) request.getParameter("downloadNum");
		String rank = (String) request.getParameter("rank");
		String authority = (String) request.getParameter("authority");
		String[] cookbook = rank.split(";");
		String[] no1 = cookbook[0].split(",");
		String[] no2 = cookbook[1].split(",");
		String[] no3 = cookbook[2].split(",");
		String[] no4 = cookbook[3].split(",");
		String[] no5 = cookbook[4].split(",");
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
				href="javascript:;"> <span class="am-icon-users"></span> <%
 	if (name != null) {%>					
 					<%=name%>
					<%
						} else {
					%>管理员<%
						}
					%>  <span class="am-icon-caret-down"></span>
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
						<li><a href="/RiceRoll/admins/getcookbooklist.action" class="am-cf"><span
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
					<strong class="am-text-primary am-text-lg">首页</strong> / <small>数据统计</small>
				</div>
			</div>

			<ul
				class="am-avg-sm-1 am-avg-md-4 am-margin am-padding am-text-center admin-content-list ">
				<li><a href="#" class="am-text-success"><span
						class="am-icon-btn am-icon-file-text"></span><br />菜谱总量<br /><%=cookbookNum%></a></li>
				<li><a href="#" class="am-text-warning"><span
						class="am-icon-btn am-icon-briefcase"></span><br />下载总量<br /><%=downloadNum%></a></li>
				<li><a href="#" class="am-text-danger"><span
						class="am-icon-btn am-icon-recycle"></span><br />今日注册<br /><%=todayUserNum%></a></li>
				<li><a href="#" class="am-text-secondary"><span
						class="am-icon-btn am-icon-user-md"></span><br />用户总量<br /><%=userNum%></a></li>
			</ul>

			<!-- <div class="am-g">
				<div class="am-u-sm-12">
					<table
						class="am-table am-table-bd am-table-striped admin-content-table">
						<thead>
							<tr>
								<th>ID</th>
								<th>用户名</th>
								<th>最后成交任务</th>
								<th>成交订单</th>
								<th>管理</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td>John Clark</td>
								<td><a href="#">Business management</a></td>
								<td><span class="am-badge am-badge-success">+20</span></td>
								<td>
									<div class="am-dropdown" data-am-dropdown>
										<button
											class="am-btn am-btn-default am-btn-xs am-dropdown-toggle"
											data-am-dropdown-toggle>
											<span class="am-icon-cog"></span> <span
												class="am-icon-caret-down"></span>
										</button>
										<ul class="am-dropdown-content">
											<li><a href="#">1. 编辑</a></li>
											<li><a href="#">2. 下载</a></li>
											<li><a href="#">3. 删除</a></li>
										</ul>
									</div>
								</td>
							</tr>
							<tr>
								<td>2</td>
								<td>风清扬</td>
								<td><a href="#">公司LOGO设计</a></td>
								<td><span class="am-badge am-badge-danger">+2</span></td>
								<td>
									<div class="am-dropdown" data-am-dropdown>
										<button
											class="am-btn am-btn-default am-btn-xs am-dropdown-toggle"
											data-am-dropdown-toggle>
											<span class="am-icon-cog"></span> <span
												class="am-icon-caret-down"></span>
										</button>
										<ul class="am-dropdown-content">
											<li><a href="#">1. 编辑</a></li>
											<li><a href="#">2. 下载</a></li>
											<li><a href="#">3. 删除</a></li>
										</ul>
									</div>
								</td>
							</tr>
							<tr>
								<td>3</td>
								<td>詹姆斯</td>
								<td><a href="#">开发一款业务数据软件</a></td>
								<td><span class="am-badge am-badge-warning">+10</span></td>
								<td>
									<div class="am-dropdown" data-am-dropdown>
										<button
											class="am-btn am-btn-default am-btn-xs am-dropdown-toggle"
											data-am-dropdown-toggle>
											<span class="am-icon-cog"></span> <span
												class="am-icon-caret-down"></span>
										</button>
										<ul class="am-dropdown-content">
											<li><a href="#">1. 编辑</a></li>
											<li><a href="#">2. 下载</a></li>
											<li><a href="#">3. 删除</a></li>
										</ul>
									</div>
								</td>
							</tr>
							<tr>
								<td>4</td>
								<td>云适配</td>
								<td><a href="#">适配所有网站</a></td>
								<td><span class="am-badge am-badge-secondary">+50</span></td>
								<td>
									<div class="am-dropdown" data-am-dropdown>
										<button
											class="am-btn am-btn-default am-btn-xs am-dropdown-toggle"
											data-am-dropdown-toggle>
											<span class="am-icon-cog"></span> <span
												class="am-icon-caret-down"></span>
										</button>
										<ul class="am-dropdown-content">
											<li><a href="#">1. 编辑</a></li>
											<li><a href="#">2. 下载</a></li>
											<li><a href="#">3. 删除</a></li>
										</ul>
									</div>
								</td>
							</tr>

							<tr>
								<td>5</td>
								<td>呵呵呵</td>
								<td><a href="#">基兰会获得BUFF</a></td>
								<td><span class="am-badge">+22</span></td>
								<td>
									<div class="am-dropdown" data-am-dropdown>
										<button
											class="am-btn am-btn-default am-btn-xs am-dropdown-toggle"
											data-am-dropdown-toggle>
											<span class="am-icon-cog"></span> <span
												class="am-icon-caret-down"></span>
										</button>
										<ul class="am-dropdown-content">
											<li><a href="#">1. 编辑</a></li>
											<li><a href="#">2. 下载</a></li>
											<li><a href="#">3. 删除</a></li>
										</ul>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
 -->
			<div class="am-g">
				 <div class="am-u-md-6">				 
					<!--<div class="am-panel am-panel-default">
						<div class="am-panel-hd am-cf"
							data-am-collapse="{target: '#collapse-panel-1'}">
							文件上传<span class="am-icon-chevron-down am-fr"></span>
						</div>
						<div class="am-panel-bd am-collapse am-in" id="collapse-panel-1">
							<ul class="am-list admin-content-file">
								<li><strong><span class="am-icon-upload"></span>
										Kong-cetian.Mp3</strong>
									<p>3.3 of 5MB - 5 mins - 1MB/Sec</p>
									<div
										class="am-progress am-progress-striped am-progress-sm am-active">
										<div class="am-progress-bar am-progress-bar-success"
											style="width: 82%">82%</div>
									</div></li>
								<li><strong><span class="am-icon-check"></span>
										好人-cetian.Mp3</strong>
									<p>3.3 of 5MB - 5 mins - 3MB/Sec</p></li>
								<li><strong><span class="am-icon-check"></span>
										其实都没有.Mp3</strong>
									<p>3.3 of 5MB - 5 mins - 3MB/Sec</p></li>
							</ul>
						</div>-->
					</div> 
					<div class="am-panel am-panel-default">
						<div class="am-panel-hd am-cf"
							data-am-collapse="{target: '#collapse-panel-2'}">
							菜谱热度排行<span class="am-icon-chevron-down am-fr"></span>
						</div>
						<div id="collapse-panel-2" class="am-in">
							<table
								class="am-table am-table-bd am-table-bdrs am-table-striped am-table-hover">
								<tbody>
									<tr>
										<th class="am-text-center">编号</th>
										<th>菜谱名</th>
										<th>点击量</th>
										<th>下载量</th>
										<th>收藏量</th>
									</tr>
									 <tr>
										<td class="am-text-center">1</td>
										<td><%=no1[0] %></td>
										<td><%=no1[1] %></td>
										<td><%=no1[2] %></td>
										<td><%=no1[3] %></td>
									</tr>
									<tr>
										<td class="am-text-center">2</td>
										<td><%=no2[0] %></td>
										<td><%=no2[1] %></td>
										<td><%=no2[2] %></td>
										<td><%=no2[3] %></td>
									</tr>
									<tr>
										<td class="am-text-center">3</td>
										<td><%=no3[0] %></td>
										<td><%=no3[1] %></td>
										<td><%=no3[2] %></td>
										<td><%=no3[3] %></td>
									</tr>
									<tr>
										<td class="am-text-center">4</td>
										<td><%=no4[0] %></td>
										<td><%=no4[1] %></td>
										<td><%=no4[2] %></td>
										<td><%=no4[3] %></td>
									</tr>
									<tr>
										<td class="am-text-center">5</td>
										<td><%=no5[0] %></td>
										<td><%=no5[1] %></td>
										<td><%=no5[2] %></td>
										<td><%=no5[3] %></td>
									</tr> 
								</tbody>
							</table>
						</div>
					</div>
					<div class="am-panel am-panel-default">
						<div class="am-panel-hd am-cf"
							data-am-collapse="{target: '#collapse-panel-4'}">
							留言<span class="am-icon-chevron-down am-fr"></span>
						</div>
						<div id="collapse-panel-4" class="am-panel-bd am-collapse am-in">
							<ul class="am-list admin-content-task">
								<li>
									<div class="admin-task-meta">Posted on 25/1/2120 by John
										Clark</div>
									<div class="admin-task-bd">The starting place for
										exploring business management; helping new managers get
										started and experienced managers get better.</div>
									<div class="am-cf">
										<div class="am-btn-toolbar am-fl">
											<div class="am-btn-group am-btn-group-xs">
												<button type="button" class="am-btn am-btn-default">
													<span class="am-icon-check"></span>
												</button>
												<button type="button" class="am-btn am-btn-default">
													<span class="am-icon-pencil"></span>
												</button>
												<button type="button" class="am-btn am-btn-default">
													<span class="am-icon-times"></span>
												</button>
											</div>
										</div>
										<div class="am-fr">
										<%if(authority.equals("1")||authority=="1"){%> 
											<button type="button" class="am-btn am-btn-default am-btn-xs">删除</button>
										<%}%>
										</div>
									</div>
								</li>
								<li>
									<div class="admin-task-meta">Posted on 25/1/2120 by 呵呵呵</div>
									<div class="admin-task-bd">
										基兰和狗熊出现在不同阵营时。基兰会获得BUFF，“装甲熊憎恨者”。狗熊会获得BUFF，“时光老人憎恨者”。</div>
									<div class="am-cf">
										<div class="am-btn-toolbar am-fl">
											<div class="am-btn-group am-btn-group-xs">
												<button type="button" class="am-btn am-btn-default">
													<span class="am-icon-check"></span>
												</button>
												<button type="button" class="am-btn am-btn-default">
													<span class="am-icon-pencil"></span>
												</button>
												<button type="button" class="am-btn am-btn-default">
													<span class="am-icon-times"></span>
												</button>
											</div>
										</div>
										<div class="am-fr">
											<%if(authority.equals("1")||authority=="1"){%> 
											<button type="button" class="am-btn am-btn-default am-btn-xs">删除</button>
											<%}%>
										</div>
									</div>
								</li>
							</ul>
							<br>
							<ul class="am-pagination am-fr admin-content-pagination">
								<li class="am-disabled"><a href="#">&laquo;</a></li>
								<li class="am-active"><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
								<li><a href="#">&raquo;</a></li>
							</ul>
						</div>
					</div>
				

				<div class="am-u-md-6">
					<!--  

					<div class="am-panel am-panel-default">
						<div class="am-panel-hd am-cf"
							data-am-collapse="{target: '#collapse-panel-3'}">
							最近留言<span class="am-icon-chevron-down am-fr"></span>
						</div>
						<div class="am-panel-bd am-collapse am-in am-cf"
							id="collapse-panel-3">
							<ul class="am-comments-list admin-content-comment">
								<li class="am-comment"><a href="#"><img
										src="http://amui.qiniudn.com/bw-2014-06-19.jpg?imageView/1/w/96/h/96"
										alt="" class="am-comment-avatar" width="48" height="48"></a>
									<div class="am-comment-main">
										<header class="am-comment-hd">
										<div class="am-comment-meta">
											<a href="#" class="am-comment-author">某人</a> 评论于
											<time>2014-7-12 15:30</time>
										</div>
										</header>
										<div class="am-comment-bd">
											<p>遵循 “移动优先（Mobile First）”、“渐进增强（Progressive
												enhancement）”的理念，可先从移动设备开始开发网站，逐步在扩展的更大屏幕的设备上，专注于最重要的内容和交互，很好。</p>
										</div>
									</div></li>

								<li class="am-comment"><a href="#"><img
										src="http://amui.qiniudn.com/bw-2014-06-19.jpg?imageView/1/w/96/h/96"
										alt="" class="am-comment-avatar" width="48" height="48"></a>
									<div class="am-comment-main">
										<header class="am-comment-hd">
										<div class="am-comment-meta">
											<a href="#" class="am-comment-author">某人</a> 评论于
											<time>2014-7-12 15:30</time>
										</div>
										</header>
										<div class="am-comment-bd">
											<p>有效减少为兼容旧浏览器的臃肿代码；基于 CSS3
												的交互效果，平滑、高效。AMUI专注于现代浏览器（支持HTML5），不再为过时的浏览器耗费资源，为更有价值的用户提高更好的体验。</p>
										</div>
									</div></li>

							</ul>
							
						</div>
					</div>
				</div>
			</div>-->
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
