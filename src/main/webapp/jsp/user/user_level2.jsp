<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="ws.senlin.entity.UserInformation"%>
<html class="no-js fixed-layout">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>森林</title>
  <meta name="description" content="这是一个 index 页面">
  <meta name="keywords" content="index">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css">
  <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/amazeui.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
</head>
<body>
<%@ include file="../frame/admin/admin_top.jsp"%>

<div class="am-cf admin-main">
  <%@ include file="../frame/Frame_left.jsp"%>

  <div class="admin-content">
    <div class="admin-content-body">
    
      <div data-am-widget="intro" class="am-intro am-cf am-intro-default">

        <div class="am-g am-intro-bd">
          <div class="am-intro-left am-u-sm-5">
          <img src="http://s.amazeui.org/media/i/demos/bw-2014-06-19.jpg?imageView/1/w/200/h/200/q/80" class="am-img-thumbnail am-circle" height="300" width="300"/>
          </div>
        </div>
        
      </div>

    </div>
    
    <footer class="admin-content-footer">
      <hr>
      <p class="am-padding-left"><%@ include file="../frame/Frame_bottom.jsp"%></p>
    </footer>
  </div>
</div>

</body>
</html>
