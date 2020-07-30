package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import JavaBean.Picture;
import JavaBean.User;
import dao.PictureDao;
import dao.UserDao;
import impls.PictureDaoJdbcImpl;
import impls.UserDaoJdbcImpl;

@WebServlet("/favorPhotoServlet")
public class FavorPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int pagesize = 9;
	PictureDao pictureDao = new PictureDaoJdbcImpl();
	UserDao userDao = new UserDaoJdbcImpl();
	int remainder = 0;
	int totalpage = 0;
	boolean whetherPublic = true;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Picture> oldPictures = null;
		
		if (request.getParameter("uid") == null) {
			User user = (User) request.getSession().getAttribute("user");
			oldPictures = pictureDao.getFavor(user.getUID());
		}else {
			int uid = Integer.parseInt(request.getParameter("uid"));
			if (!userDao.whetherPublic(uid)) {
				whetherPublic = userDao.whetherPublic(uid);
				request.setAttribute("message", request.getParameter("username") + "的收藏不对外公开哦");
			}else {
				oldPictures = pictureDao.getFavor(uid);
				request.setAttribute("message", request.getParameter("username") + "的收藏");
			}	
		}
		
		List<Picture> pictures = new ArrayList<Picture>();
		
		Cookie [] cookies = request.getCookies();
		List<Picture> cookiePictures = new ArrayList<Picture>();
		
		if(cookies != null && cookies.length > 0) {
			for (Cookie c:cookies) {
				String cookieName = c.getName();
				if (cookieName.startsWith("PICTURE_")) {
					cookiePictures.add(pictureDao.get(Integer.parseInt(c.getValue())));
				}
			}
		}
		
		int page = 0;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		if (oldPictures == null && (!whetherPublic)) {
			request.getRequestDispatcher("pages/favorPhotos.jsp").forward(request, response);
		}else if (oldPictures.size() == 0 && whetherPublic){
			request.setAttribute("message", request.getParameter("username") + "的收藏空空如也");
			request.getRequestDispatcher("pages/favorPhotos.jsp").forward(request, response);
		}else {
			for(int i=0; i < oldPictures.size(); i++){
				int imageID = oldPictures.get(i).getImageID();
				Picture picture =  pictureDao.get(imageID);
				pictures.add(picture);
			}
			
			if (pictures != null && pictures.size() > 0) {
				
				remainder = pictures.size()%pagesize;
				if (remainder==0) {totalpage = pictures.size()/pagesize;}
				else {totalpage = pictures.size()/pagesize + 1;}
				
				if (page == 0) {
					List<Picture> pagePictures;
					if (totalpage > 1 || remainder == 0) {pagePictures = new ArrayList<Picture>(pictures.subList(0, pagesize));}
					else {pagePictures = new ArrayList<Picture>(pictures.subList(0, remainder));}
					request.setAttribute("totalpage", totalpage);
					request.setAttribute("page", page);
					request.setAttribute("pictures", pagePictures);
					request.setAttribute("cookies", cookiePictures);
					request.getRequestDispatcher("pages/favorPhotos.jsp").forward(request, response);
				}
				else {
					List<Picture> pagePictures;
					if (page == totalpage) {pagePictures = pictures.subList((page-1)*pagesize,(page-1)*pagesize+remainder);}
					else {pagePictures = pictures.subList((page-1)*pagesize,page*pagesize);}
					
					ObjectMapper mapper = new ObjectMapper(); 			
					String jsonStr = mapper.writeValueAsString(pagePictures);
					
					response.setContentType("text/javascript;charset=utf-8");
					response.getWriter().print(jsonStr);
				}
			}else {
				request.getRequestDispatcher("pages/favorPhotos.jsp").forward(request, response);
			}
		}
 
		
		
	}

}
