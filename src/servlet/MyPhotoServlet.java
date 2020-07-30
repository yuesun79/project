package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import JavaBean.Picture;
import JavaBean.User;
import dao.PictureDao;
import impls.PictureDaoJdbcImpl;

@WebServlet("/myPhotoServlet")
public class MyPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int pagesize = 9;
	int remainder = 0;
	int totalpage = 0;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PictureDao pictureDao = new PictureDaoJdbcImpl();
		User user = (User) request.getSession().getAttribute("user");
		List<Picture> pictures = pictureDao.getMy(user.getUID());
		int page = 0;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			
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
					
					request.getRequestDispatcher("pages/myPhotos.jsp").forward(request, response);
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
			request.getRequestDispatcher("pages/myPhotos.jsp").forward(request, response);
			
		}
		
	}

}
