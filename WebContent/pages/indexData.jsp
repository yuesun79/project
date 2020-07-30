<%@page import="JavaBean.Picture"%>
<%@page import="java.util.List"%>
<%@page import="dao.PictureDao"%>
<%@page import="impls.PictureDaoJdbcImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% 
	PictureDao pictureDao = new PictureDaoJdbcImpl();
	List<Picture> hottest = pictureDao.getPartByLikes(0, 5);
	List<Picture> latest = pictureDao.getPartByTime(0, 8);
	
	for(int i=0; i<latest.size(); i++){
	    Picture picture = latest.get(i);
	    picture.setUserName(pictureDao.getForUser(picture.getUID()));
	}
	
 	request.setAttribute("hottest",hottest); 
 	request.setAttribute("latest",latest); 
%>

