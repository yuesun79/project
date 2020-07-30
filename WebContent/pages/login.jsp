<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <title>Welcome-Jupiter</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/log-in.css">
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
                    <a class="dropdown-toggle navbar-right" href="${pageContext.request.contextPath}/pages/login.jsp?method=login">未登录</a>
                </c:if>
                
                <c:if test="${sessionScope.user != null}">
                    <a class="dropdown-toggle navbar-right" data-toggle="dropdown" href="#">${sessionScope.user.userName }<span class="caret"></span></a>
                    <ul class="dropdown-menu ">
                        <li><a href="pages/upload.jsp"><span class="glyphicon glyphicon-cloud-upload"></span> 上传</a></li>
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
    <div class="main-body">

        <div class="logo">
            <img src="${pageContext.request.contextPath}/static/icon/jupiter_logo.png" alt="jupiter-logo">
            <h3>welcome to the Jupiter</h3>
        </div>

        <div class="row">
            <a class="in" href="${pageContext.request.contextPath}/pages/login.jsp">登陆</a>
            <a class="out" href="${pageContext.request.contextPath}/pages/register.jsp">注册</a>
        </div>

        <section>
            <div class="input">
                <form action="${pageContext.request.contextPath}/loginServlet" method="post" name="form1">
                	<p id="tip">${requestScope.message }</p>
                    <input type="text" name="username" required placeholder="用户名/常用邮箱登录" aria-placeholder="以字母开始的用户名"
                    value="${param.username }" id="user">
                    <input type="password"  name="password" required placeholder="密码" id="password">
                    <input type="text" name="validationCode" id="validationCode" placeholder="请输入验证码" data-url="${pageContext.request.contextPath}"> 
					<img src="${pageContext.request.contextPath}/pages/validate.jsp" id="validationCode_img" title="看不清?换一个" 
					name="validationCode_img" align="middle">
                    <input type="submit" value="登录" name="login">
                </form>
            </div>
        </section>

    </div>

    <footer class="footer">备案号：18307110426</footer>
</div>
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/css/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/register.js"></script>
</body>
</html>

