package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaBean.Picture;
import dao.PictureDao;
import impls.PictureDaoJdbcImpl;


@WebServlet("/modifyServlet")
public class ModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PictureDao pictureDao = new PictureDaoJdbcImpl();
		int imageID = Integer.parseInt(request.getParameter("imageID"));

		Picture picture = pictureDao.get(imageID);
		picture.setAsciiName(pictureDao.getForCity(imageID));
		picture.setCountryName(pictureDao.getForCountry(picture.getCountryCodeISO()));
		
		request.setAttribute("picture", picture);
		
		request.getRequestDispatcher("/pages/upload.jsp").forward(request, response);
	}

}
