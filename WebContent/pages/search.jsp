<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
    <!--    <meta name="viewport" content="width=device-width">-->
    <meta name="viewport" content="width=device-width,
                                     initial-scale=1.0,
                                     maximum-scale=1.0,
                                     user-scalable=no">
    <title>Jupiter-Search</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css">
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
                <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp" id="logo"><img src="${pageContext.request.contextPath}/static/icon/jupiter_logo.png" alt="logo"></a>
            </div>
            <div class="collapse navbar-collapse" id="example-navbar-collapse">
                <ul class="nav navbar-nav" id="mynav">
                    <li><a href="${pageContext.request.contextPath}/index.jsp">首页</a></li>
                    <li class="active"><a href="${pageContext.request.contextPath}/pages/search.jsp">搜索</a></li>
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
	                        <li><a href="${pageContext.request.contextPath}/myPhotoServlet"><span class="glyphicon glyphicon-picture"></span> 我的</a></li>
	                        <li><a href="${pageContext.request.contextPath}/favorPhotoServlet"><span class="glyphicon glyphicon-heart"></span> 收藏</a></li>
	                        <li><a href="${pageContext.request.contextPath}/friendServlet"><span class="glyphicon glyphicon-user"></span> 好友</a></li>
	                        <li><a href="${pageContext.request.contextPath}/logoutServlet"><span class="glyphicon glyphicon-share"></span> 登出</a></li>
	                 	</ul>
	                 </c:if> 
                </li>
            </ul>
            </div>
        </div>
    </nav>
    <div class="search"><!--            搜索部分-->

        <form action="${pageContext.request.contextPath}/searchServlet?page=0" method="post" name="form" id="form">                
        	<div><input type="text" name="content" id="input" value="${requestScope.content }"></div>
        
	        		<div class="radio">
		            	<p>选择输入框内容的搜索类型：标题/主题；</p>
		                <input class="radio-type" id="radio-1" type="radio" name="contentRadio" value="title" checked
		                <c:choose>
							<c:when test="${requestScope.contentRadio == \"title\"}">checked</c:when>
							<c:when test="${requestScope.contentRadio == null }">checked</c:when>
							<c:otherwise></c:otherwise>
		                </c:choose>>
		                <label for="radio-1" class="radio-label">  标题搜索</label>
	            	</div>
         
	        		<div class="radio">
	        			<input class="radio-type" id="radio-2" type="radio" name="contentRadio" value="description"
	        			<c:choose>
							<c:when test="${'description' == requestScope.contentRadio}">checked</c:when>
							<c:otherwise></c:otherwise>
		                </c:choose>>
	        			<label for="radio-2" class="radio-label">  主题搜索</label>
		            </div>
            		<div class="radio">
		            	<p>选择搜索图片结果的排序规则：热度/上传时间；</p>
		                <input class="radio-type" id="radio-3" type="radio" name="orderRadio" value="likes" checked
		                <c:choose>
							<c:when test="${requestScope.contentRadio == \"likes\"}">checked</c:when>
							<c:when test="${requestScope.orderRadio == null }">checked</c:when>
							<c:otherwise></c:otherwise>
		                </c:choose>>
		                <label for="radio-3" class="radio-label">  依照热度</label>
		            </div>
            	
            		<div class="radio">
		                <input class="radio-type" id="radio-4" type="radio" name="orderRadio" value="timestamp"
		                <c:choose>
							<c:when test="${'timestamp' == requestScope.orderRadio}">checked</c:when>
							<c:otherwise></c:otherwise>
		                </c:choose>>
		                <label for="radio-4" class="radio-label">  依照时间</label>
		            </div>
           
            <br>
            <input type="submit" name="go" value="搜索">
        </form>

    </div>

    <div class="result" id="txtHint">
    <c:forEach items="${requestScope.pictures }" var="pic">
    	<figure>
	        <a target="_Blank" href="${pageContext.request.contextPath}/pageServlet?imageID=${pic.imageID }&page=1">
	        	<img src="${pageContext.request.contextPath}/static/photos/${pic.path }" alt="${pic.title }"></a>
	        <figcaption>
	            <h2>${pic.title }</h2>
	            <p>${pic.description }</p>
	        </figcaption>
        </figure>
    </c:forEach>
    </div>
<c:if test="${requestScope.totalpage != null }">
<div class="page">
    <ul class="pagination" id="page">
    
        <li class="arrows"><a href="#" class="singlePage" id="prev" data-page="1"
        data-url="${pageContext.request.contextPath}"
        data-content="${requestScope.content }" data-radio="${requestScope.contentRadio }" data-order="${orderRadio }"> << </a></li>
        <c:forEach var="i" begin="1" end="${requestScope.totalpage }">
        	<li><a class="singlePage" href="#" 
        	data-page="${i }" data-url="${pageContext.request.contextPath}"
        	data-content="${requestScope.content }" data-radio="${requestScope.contentRadio }" data-order="${orderRadio }">${i }
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
        data-url="${pageContext.request.contextPath}"
        data-content="${requestScope.content }" data-radio="${requestScope.contentRadio }" data-order="${orderRadio }"> >> </a></li>
        
    </ul>
</div>
</c:if>


<footer class="footer"><p>备案号：18307110426</p></footer>
</div>
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/css/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/page.js"></script>
</body>
</html>