<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,
                                     initial-scale=1.0,
                                     maximum-scale=1.0,
                                     user-scalable=no">
    <title>Friends</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/friends.css">
</head>
</head>
<body>
<div class="container">
	<nav class="navbar navbar-inverse" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#example-navbar-collapse">
                    <span class="sr-only">切换导航</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.jsp" id="logo"><img src="${pageContext.request.contextPath}/static/icon/jupiter_logo.png" alt="logo"></a>
            </div>
            <div class="collapse navbar-collapse" id="example-navbar-collapse">
                <ul class="nav navbar-nav" id="mynav">
                    <li><a href="${pageContext.request.contextPath}/index.jsp">首页</a></li>
                    <li><a href="${pageContext.request.contextPath}/pages/search.jsp">搜索</a></li>
                </ul>

            <ul class="nav navbar-nav nav-pills navbar-right">
                <li class="dropdown pull-right" id="user">
	                <c:if test="${sessionScope.user == null}"> 
	                    <a class="dropdown-toggle navbar-right" href="${pageContext.request.contextPath}/pages/login.jsp?method=login">登陆</a>
	                </c:if>
	                <c:if test="${sessionScope.user != null}">
	                    <a class="dropdown-toggle navbar-right" data-toggle="dropdown" href="#">${sessionScope.user.userName }<span class="caret"></span></a>
	                    <ul class="dropdown-menu ">
	                        <li><a href="${pageContext.request.contextPath}/pages/upload.jsp"><span class="glyphicon glyphicon-cloud-upload"></span> 上传</a></li>
	                        <li><a href="${pageContext.request.contextPath}/myPhotoServlet?page=0"><span class="glyphicon glyphicon-picture"></span> 我的</a></li>
	                        <li><a href="${pageContext.request.contextPath}/favorPhotoServlet?page=0"><span class="glyphicon glyphicon-heart"></span> 收藏</a></li>
	                        <li><a href="${pageContext.request.contextPath}/friendServlet"><span class="glyphicon glyphicon-user"></span> 好友</a></li>
	                        <li><a href="${pageContext.request.contextPath}/logoutServlet"><span class="glyphicon glyphicon-share"></span> 登出</a></li>
	                 	</ul>
	                 </c:if> 
                </li>
            </ul>
            </div>
        </div>
    </nav>
<div class="result" id="txtHint">
	<button type="button" class="btn btn-default" data-url="${pageContext.request.contextPath }/changeAuthorityServlet" id="button">${requestScope.message }</button>
	<table>
		<tr>
			<th><span class="glyphicon glyphicon-user"></span>  用户</th>
			<th><span class="glyphicon glyphicon-envelope"></span>  邮箱</th>
			<th><span class="glyphicon glyphicon-time"></span>  注册时间</th>
		</tr>
		<c:forEach items="${requestScope.users }" var="thisuser">
		<tr>
			<td><a href="${pageContext.request.contextPath}/favorPhotoServlet?uid=${thisuser.UID }&username=${thisuser.userName }">
			${thisuser.userName }</a></td>
			<td><a href="${pageContext.request.contextPath}/favorPhotoServlet?uid=${thisuser.UID }&username=${thisuser.userName }">
			${thisuser.email }</a></td>    
			<td><span>${thisuser.dateJoined }</span></td>
		</tr>
		</c:forEach>
	</table>
</div>
<footer class="footer"><p>备案号：18307110426</p></footer>
</div>
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/css/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/friendsButton.js"></script>
</body>
</html>