package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PictureDao;
import impls.PictureDaoJdbcImpl;


@WebServlet("/deleteMyPhotoServlet")
public class DeleteMyPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int imageID = Integer.parseInt(request.getParameter("imageID"));
		PictureDao pictureDao = new PictureDaoJdbcImpl();
		pictureDao.delete(imageID);
	}

}
