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
    <title>Collections</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/collection.css">
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
	<c:if test="${requestScope.message != null }">
		<p id="title">${requestScope.message }</p>
	</c:if>
	<c:forEach items="${requestScope.pictures }" var="pic">
		<figure>
	        <a target="_Blank" href="${pageContext.request.contextPath}/pageServlet?imageID=${pic.imageID }&page=1">
	        	<img src="${pageContext.request.contextPath}/static/photos/${pic.path }" alt="${pic.title }"></a>
	        <figcaption>
	            <h2>${pic.title }</h2>
	            <p>${pic.description }</p>
	            <c:if test="${requestScope.message == null }">
		            <p class="differButtons">
	                        <input class="unlikeButton" type="button" name="like" value="取消喜欢"
	                        data-url="${pageContext.request.contextPath}/likeButtonServlet?method=unlikeButton"
	                        data-imageid="${pic.imageID }"
	                        <c:if test="${sessionScope.user != null}">data-user="${sessionScope.user.UID }"</c:if>
	                        >
	                </p>
	            </c:if>
                
	        </figcaption>
        </figure>
	</c:forEach>
</div>

<c:if test="${requestScope.cookies != null}">
<p id="title">我的足迹：</p>
<div id="foots">
		
		<c:forEach items="${requestScope.cookies }" var="lookedPic">
			<figure class="cookie">
	        	<a target="_Blank" href="${pageContext.request.contextPath}/pageServlet?imageID=${lookedPic.imageID }" >
	        	<img src="${pageContext.request.contextPath}/static/photos/${lookedPic.path }" alt="${lookedPic.title }" ></a>
        	</figure>
		</c:forEach>
	
</div>
</c:if>
<c:if test="${requestScope.totalpage != null }">
<div class="page">
    <ul class="pagination" id="page" data-pagetype="favor">
    
        <li class="arrows"><a href="#" class="singlePage" id="prev" data-page="1"
        data-url="${pageContext.request.contextPath}/favorPhotoServlet"
        data-baseurl="${pageContext.request.contextPath}"> << </a></li>
        <c:forEach var="i" begin="1" end="${requestScope.totalpage }">
        	<li><a class="singlePage" href="#" 
        	data-page="${i }" data-url="${pageContext.request.contextPath}/favorPhotoServlet" data-baseurl="${pageContext.request.contextPath}">${i }
        	</a></li>
        </c:forEach>
        <li class="arrows"><a  href="#" class="singlePage" id="next" 
        <c:if test="${requestScope.page == 0 }">
        	<c:choose>
	        	<c:when test="${requestScope.totalPage == 1 }">
	        		<c:out value="data-page=1"></c:out>
	        	</c:when>
	        	<c:otherwise>
	        		<c:out value="data-page=2"></c:out>
	        	</c:otherwise>
        	</c:choose>
        </c:if>
        data-url="${pageContext.request.contextPath}/favorPhotoServlet" data-baseurl="${pageContext.request.contextPath}"> >> </a></li>
        
    </ul>
</div>
</c:if>
<footer class="footer"><p>备案号：18307110426</p></footer>
</div>
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/css/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/unlikeFromFavor.js"></script>
<script src="${pageContext.request.contextPath}/js/pageForPhotos.js"></script>
</body>
</html>