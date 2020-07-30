<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <title>Welcome Jupiter</title>
    <link rel="stylesheet" href="http://jqueryvalidation.org/files/demo/site-demos.css">
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
	        <a class="out" href="${pageContext.request.contextPath}/pages/login.jsp">登录</a>
	        <a class="in" href="r${pageContext.request.contextPath}/pages/register.jsp">注册</a>
	    </div>
	
	    <section>
	        <div class="input">
	            <form action="${pageContext.request.contextPath}/registerServlet" method="post" name="registerForm" id="form">
                <p id="tip">
                	${requestScope.message }
                </p>
                <input type="text" name="username" id="user" required placeholder="用户名 4-15位" value="${requestScope.username }">
                <input type="email" name="email" id="email" required placeholder="E-mail" ${requestScope.email }>
                <input type="password"  name="password" id="password" required placeholder="密码 6-12位">
                <input type="password" class="left" name="repassword" id="repassword" required placeholder="重新输入密码">
                <div class="password-meter">
			        <div class="password-meter-message"> </div>
			        <div class="password-meter-bg">
			            <div class="password-meter-bar"></div>
			        </div>
			    </div>
			    <input type="text" name="validationCode" id="validationCode" placeholder="请输入验证码" data-url="${pageContext.request.contextPath}"> 
				<img src="${pageContext.request.contextPath}/pages/validate.jsp" id="validationCode_img" title="看不清?换一个" 
				name="validationCode_img" align="middle">
                <input type="submit" value="注册" name="register" id="submit">
            </form>
        </div>
    </section>

</div>

	<footer class="footer">备案号：18307110426</footer>
</div>
	<script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.js"></script>
	
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/css/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/register.js"></script>
</body>
</html>