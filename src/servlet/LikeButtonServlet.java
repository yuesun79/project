package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.PictureDao;
import dao.UserDao;
import impls.PictureDaoJdbcImpl;
import impls.UserDaoJdbcImpl;

@WebServlet("/likeButtonServlet")
public class LikeButtonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao userDao = new UserDaoJdbcImpl();
	PictureDao pictureDao = new PictureDaoJdbcImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int imageID = Integer.parseInt(request.getParameter("imageID"));
		String methodName = request.getParameter("method");
	
//		try {
//			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
//			method.invoke(this,request,response);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
		switch(methodName) {
		case "likeButton":likeButton(request,response);break;
		case "unlikeButton":unlikeButton(request,response);break;
		}
		StringBuilder jsonStr = new StringBuilder();
		jsonStr.append("{\"likes\":\"");
		jsonStr.append(pictureDao.getLike(""+imageID) + "\"}");
		response.getWriter().print(jsonStr);
	}
	
	private void likeButton(HttpServletRequest request, HttpServletResponse response) {
		int imageID = Integer.parseInt(request.getParameter("imageID"));
		int uid = Integer.parseInt(request.getParameter("uid"));
		int likes = pictureDao.getLike(""+imageID) + 1;
		userDao.likePic(uid, imageID);
		pictureDao.setLike(""+imageID,likes);
	}
	
	private void unlikeButton(HttpServletRequest request, HttpServletResponse response) {
		int imageID = Integer.parseInt(request.getParameter("imageID"));
		int uid = Integer.parseInt(request.getParameter("uid"));
		int likes = pictureDao.getLike(""+imageID) - 1;
		userDao.unlikePic(uid, imageID);
		pictureDao.setLike(""+imageID, likes);
	}

}
