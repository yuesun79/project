package servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaBean.Picture;
import JavaBean.User;
import dao.PictureDao;
import dao.UserDao;
import impls.PictureDaoJdbcImpl;
import impls.UserDaoJdbcImpl;

@WebServlet("/pageServlet")
public class PageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PictureDao pictureDao = new PictureDaoJdbcImpl();
		UserDao userDao = new UserDaoJdbcImpl();
		
		User user = (User) request.getSession().getAttribute("user");
		Integer imageID = Integer.parseInt(request.getParameter("imageID"));
		
		Picture picture = pictureDao.get(imageID);		
		picture.setAsciiName(pictureDao.getForCity(picture.getCityCode()));
		picture.setCountryName(pictureDao.getForCountry(picture.getCountryCodeISO()));
		picture.setUserName(pictureDao.getForUser(picture.getUID()));
		
		if (user == null) {
			request.setAttribute("message", "likeButton");
			request.setAttribute("buttonstyle", "喜欢");
		}else {
			long whetherMatch = userDao.getPicMatch(user.getUID(),picture.getImageID());
			if (whetherMatch == 0) {
				request.setAttribute("message", "likeButton");
				request.setAttribute("buttonstyle", "喜欢");
			}else {
				request.setAttribute("message", "unlikeButton");
				request.setAttribute("buttonstyle", "取消喜欢");
			}
		}
		
		request.setAttribute("picture", picture);
		request.getRequestDispatcher("/pages/detail.jsp").forward(request, response);;
	}

}
