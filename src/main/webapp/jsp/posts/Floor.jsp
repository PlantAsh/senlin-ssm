<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*"%>
<%@ page import="ws.senlin.entity.Posts"%>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>森林</title>
<meta name="description" content="这是一个 table 页面">
<meta name="keywords" content="table">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png"
	href="${pageContext.request.contextPath}/assets/i/favicon.png">
<link rel="apple-touch-icon-precomposed"
	href="${pageContext.request.contextPath}/assets/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/amazeui.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/PostsPage.js" ></script>
</head>

<%
if(request.getParameter("postsFloor") != null) {
	session.setAttribute("postsFloor", request.getParameter("postsFloor"));
}
%>
<body onload="floor('<%=session.getAttribute("postsFloor") %>')"/>
	
	<%@ include file="../frame/Frame_top.jsp"%>

	<div class="am-cf admin-main">
		<%@ include file="../frame/Frame_left.jsp"%>

		<div class="admin-content">
			<div class="admin-content-body">
				<hr />

				<form action="" name="posts">
					<div class="am-g">
						<div class="am-u-sm-12 am-u-md-3">
							<div class="am-input-group am-input-group-sm">
								<span class="am-input-group-btn">
									<button class="am-btn am-btn-default" type="button"
										onclick="post()">发帖</button>
								</span>
							</div>
						</div>
					</div>
				</form>

				<div class="am-g">
					<div class="am-u-sm-12">
						<form class="am-form">
							<table
								class="am-table am-table-striped am-table-hover table-main">
								<thead>
									<tr>
										<th class="table-title">标题</th>
										<th class="table-author am-hide-sm-only">作者</th>
										<th class="table-date am-hide-sm-only">发布时间</th>
									</tr>
								</thead>
								
								<tbody id="posts">
								
								</tbody>
							</table>
							
							<div class="am-cf">
								<div class="am-fr">
									<ul id="page" class="am-pagination">
										
									</ul>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>

			<footer class="admin-content-footer">
			<hr>
			<p class="am-padding-left"><%@ include
					file="../frame/Frame_bottom.jsp"%></p>
			</footer>

		</div>
		<!-- content end -->
	</div>


	<script
		src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>

	<script
		src="${pageContext.request.contextPath}/assets/js/amazeui.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
</body>
</html>