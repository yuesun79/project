<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="JavaBean.Picture"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,
                                     initial-scale=1.0,
                                     maximum-scale=1.0,
                                     user-scalable=no">
    <title>detail</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/detail.css">
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
                <a class="navbar-brand" href="index.jsp" id="logo"><img src="static/icon/jupiter_logo.png" alt="logo"></a>
            </div>
            <div class="collapse navbar-collapse" id="example-navbar-collapse">
                <ul class="nav navbar-nav" id="mynav">
                    <li><a href="index.jsp">首页</a></li>
                    <li><a href="pages/search.jsp">搜索</a></li>
                </ul>

            <ul class="nav navbar-nav nav-pills navbar-right">
                <li class="dropdown pull-right" id="user">
	                <c:if test="${sessionScope.user == null}"> 
	                    <a class="dropdown-toggle navbar-right" href="pages/login.jsp?method=login">登陆</a>
	                </c:if>
	                <c:if test="${sessionScope.user != null}">
	                    <a class="dropdown-toggle navbar-right" data-toggle="dropdown" href="#">${user.userName }<span class="caret"></span></a>
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
    
<section>
    <figure class="container">
        <div class="row">
            <div class="col-md-8">
                
                <div class="all-region">
			    <div class="image-wrap">
			       <!--缩略图div-->
			        <div class="little-img">
                		<img src="static/photos/${picture.path }" alt="${picture.title }">
			        </div>
			       <!--图片放大div-->
			        <div class="large-img">
			        </div>
			        <!--缩略图上展示被放大的区域,网格区域-->
			        <div class="relative-region"></div>
			    </div>
</div>
            </div>

            <div class="col-md-4">
                <figcaption>
                    <div id="picture" data-imageid="${picture.imageID }">
                        <h2>${picture.title }</h2>
                        <p class="author">${picture.userName }</p>
                        <p class="like">${picture.likes }</p>
                    </div>
                    <div>
                        <h2>图片详情</h2>
                        <p>主题：${picture.content }</p>
                        <p>国家：${picture.countryName }</p>
                        <p>城市：${picture.asciiName }</p>
                    </div>
                    <div id="like-button">
                        <input type="button" name="like" value="${requestScope.buttonstyle }" id="likeButton" 
                        data-url="${pageContext.request.contextPath}/likeButtonServlet"
                        data-type="${requestScope.message }"
                        <c:if test="${sessionScope.user != null}">data-user="${sessionScope.user.UID }"</c:if>
                        >
                    </div>
                </figcaption>
            </div>
		</div>
    </figure>

    <div class="row">
        <div class="description"><!--             文字描述-->
            <p>${picture.description }</p>
        </div>
    </div>
    
    <%
    	Picture pic = (Picture)request.getAttribute("picture");
    	String imageID = pic.getImageID()+"";
    	Cookie [] cookies = request.getCookies();
    	List<Cookie> cookieList = new ArrayList<Cookie>();
    	Cookie tempCookie = null;
    	
    	if(cookies != null && cookies.length > 0) {
    		for (Cookie c:cookies) {
    			String cookieName = c.getName();
    			if (cookieName.startsWith("PICTURE_")) {
    				cookieList.add(c);
	    			if (c.getValue().equals(imageID)) {
	    				tempCookie = c;
	    			}
    			}
    		}
    	}
    	
    	if (cookieList.size() >= 10 && tempCookie == null) {
    		tempCookie = cookieList.get(0);
    	}
    	
    	if (tempCookie != null) {
    		tempCookie.setMaxAge(0);
    		response.addCookie(tempCookie);
    	}
    	
    	Cookie cookie = new Cookie("PICTURE_"+imageID,imageID);
    	response.addCookie(cookie);
    %>

</section>

<footer class="footer"><p>备案号：18307110426</p></footer>
</div>
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/css/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/likeFromDetail.js"></script>
<script src="${pageContext.request.contextPath}/js/zoomPicture.js"></script>

</body>
</html>
	
