<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,
                                     initial-scale=1.0,
                                     maximum-scale=1.0,
                                     user-scalable=no">
    <title>UpLoad</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/upload.css">
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
	                        <li><a href="${pageContext.request.contextPath}/favorServlet?page=0"><span class="glyphicon glyphicon-heart"></span> 收藏</a></li>
	                        <li><a href="${pageContext.request.contextPath}/friendServlet"><span class="glyphicon glyphicon-user"></span> 好友</a></li>
	                        <li><a href="${pageContext.request.contextPath}/logoutServlet"><span class="glyphicon glyphicon-share"></span> 登出</a></li>
	                 	</ul>
	                 </c:if> 
                </li>
            </ul>
            </div>
        </div>
    </nav>
    
    
<form id="form2" method="post" action="${pageContext.request.contextPath }/uploadServlet
<c:if test="${picture != null }">?imageID=${picture.imageID }&path=${picture.path }</c:if>" class="upload-content" enctype="multipart/form-data"
accept-charset="UTF-8">
    <div id="box">
        <c:choose>
    		<c:when test="${picture == null }">
        		<img alt="add" src="${pageContext.request.contextPath }/static/icon/add.png" id="add">
    		</c:when>
    		<c:otherwise>
        		<img alt="picture to modify" src="${pageContext.request.contextPath }/static/photos/${picture.path }" id="modify">
    		</c:otherwise>
    	</c:choose>
    </div>
    <a class="file">
    	<c:choose>
    		<c:when test="${picture == null }">
    			上传图片
    		</c:when>
    		<c:otherwise>
    			修改图片
    		</c:otherwise>
    	</c:choose>
        <input name="file" id="file" type="file">
    </a>
    
    <div class="filter">
    <select name="content" id="content" required data-content="${picture.content }">
        <option value="scenery">Scenery</option>
        <option value="city">City</option>
        <option value="people">People</option>
        <option value="animal">Animal</option>
        <option value="building">Building</option>
        <option value="wonder">Wonder</option>
        <option value="others">others</option>
    </select>
    <select name="country" id="country" onchange="select(this)" required data-url="${pageContext.request.contextPath }" required
    data-country="${picture.countryName }"></select>
    <select name="city" id="city" required data-city="${picture.asciiName }"></select>
    </div>
    
    <input name="title" id="title" class="normal" type="text" placeholder="图片标题" required value="${picture.title }">

    <textarea name="description" id="description" cols="100%" rows="1" wrap="soft" placeholder="图片描述" required>${picture.description }</textarea>

    <input name="uploads" class="button" type="submit" 
    <c:choose>
    		<c:when test="${picture == null }">
    			value="上传"
    		</c:when>
    		<c:otherwise>
    			value="修改"
    		</c:otherwise>
    	</c:choose> >

</form>

<footer class="footer"><p>备案号：18307110426</p></footer>
</div>
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/css/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/select.js"></script> 
<script src="${pageContext.request.contextPath}/js/upload.js"></script>

</body>
</html>
