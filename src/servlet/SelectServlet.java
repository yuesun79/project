package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import JavaBean.Picture;
import dao.PictureDao;
import impls.PictureDaoJdbcImpl;

@WebServlet("/selectServlet")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PictureDao pictureDao = new PictureDaoJdbcImpl();
		List<Picture> pictures = pictureDao.getAllCountry();
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		
		
		for (int i = 0; i < pictures.size(); i++) {
			String iso = pictures.get(i).getCountryCodeISO();
			String countryName = pictureDao.getForCountry(iso);
			List<Picture> cities = pictureDao.getMatchCity(iso);
			if (cities.size()>0) {
				List<String> cityList = new ArrayList<String>();
				
				for (int j = 0; j < cities.size(); j++) {
					cityList.add(cities.get(j).getAsciiName());
				}
				map.put(countryName, cityList);
			}
		}
		
		ObjectMapper mapper = new ObjectMapper(); 			
		String jsonStr = mapper.writeValueAsString(map);
		
		response.setContentType("text/javascript");
		response.getWriter().print(jsonStr);
		
	}

}
