<%@page import="impls.PictureDaoJdbcImpl"%>
<%@page import="dao.PictureDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,
                                     initial-scale=1.0,
                                     maximum-scale=1.0,
                                     user-scalable=no">
    <title>Jupiter</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">

</head>
<body>

<div class="container">
    <!--navigation-->
    <nav class="navbar navbar-inverse" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#example-navbar-collapse">
                    <span class="sr-only">切换导航</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.jsp" id="logo"><img src="static/icon/jupiter_logo.png" alt="logo"></a>
            </div>
            <div class="collapse navbar-collapse" id="example-navbar-collapse">
                <ul class="nav navbar-nav" id="mynav">
                    <li class="active"><a href="${pageContext.request.contextPath}/index.jsp">首页</a></li>
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
    <!-- 只能用相对路径 -->
	<jsp:include page="pages/indexData.jsp"></jsp:include>
	
    <!--carousel-->
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- 轮播（Carousel）指标 -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
            <li data-target="#myCarousel" data-slide-to="3"></li>
            <li data-target="#myCarousel" data-slide-to="4"></li>
        </ol>
        <!-- 轮播（Carousel）项目 -->
        <div class="carousel-inner">
        	<c:forEach items="${hottest }" var="hot" begin="0" end="0">
	        	<div class="item active">
                <a target="_Blank" href="pageServlet?imageID=${hot.imageID }">
                	<img src="static/photos/${hot.path }" alt="slide">
                </a>
                <div class="carousel-caption">${hot.title }</div>
            </div>
            </c:forEach>
        	<c:forEach items="${hottest }" var="hot" begin="1">
				<div class="item">
	                <a target="_Blank" href="pageServlet?imageID=${hot.imageID }">
	                	<img src="static/photos/${hot.path }" alt="slide">
	                </a>
	                <div class="carousel-caption">${hot.title }</div>
	            </div>
			</c:forEach>
            
        </div>

        <!-- 轮播（Carousel）导航 -->
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>

    <div class="row" id="display">
    	<c:forEach items="${latest }" var="pic">
 
		<div class="col-md-3">
		    <figure>
		        <a target="_Blank" href="pageServlet?imageID=${pic.imageID }">
		        	<img src="static/photos/${pic.path }" alt="${pic.title }">
		        </a>
		    </figure>
		    <div class="figcaption">
		        <p>${pic.userName }     ${pic.content }</p>
		        <p>${pic.dateTime }</p>
		    </div>
		</div>
		<div class="clearfix visible-xs"></div>
		
		</c:forEach>
    </div>

    <div class="icon"><!--             小图标-->
        <a href="#top"><img src="static/icon/top.png" alt="返回顶部"></a>
    </div>

    <footer class="home-footer">
        <div class="words">
            <p>关于</p>
            <p>联系我们</p>
            <p>侵权申诉</p>
            <p>隐私政策</p>
            <p>帮助中心</p>
            <div class="words-div">
                <div class="footer-img-one">

                    <img class="weixin" src="static/icon/WeiXin.png" alt="微信">
                    <div class="code">
                        <img src="static/icon/weixin-code.png" alt="微信二维码">
                    </div>
                </div>
                <img class="weibo" src="static/icon/Weibo.png" alt="微博">

            </div>

        </div>

        <p>备案号：18307110426</p>
    </footer>
</div>
<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<!-- 包括所有已编译的插件 -->
<script src="${pageContext.request.contextPath}/css/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>