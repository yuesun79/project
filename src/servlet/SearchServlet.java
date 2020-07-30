package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import JavaBean.Picture;
import dao.PictureDao;
import impls.PictureDaoJdbcImpl;

@WebServlet("/searchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int pagesize = 9;
	PictureDao picturedao = new PictureDaoJdbcImpl();
	int start = 0;
	int totalpage = 0;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Picture> pictures = null;
		int page = Integer.parseInt(request.getParameter("page"));
		
		String oldContent = request.getParameter("content");
		String content = "%"+oldContent+"%";
		String contentRadio = request.getParameter("contentRadio");
		String orderRadio = request.getParameter("orderRadio");
		
		if (page == 0) {
			pictures = getAllPictures(content,contentRadio,orderRadio);
			
			if (pictures != null && pictures.size() > 0) {
				totalpage = pictures.size()/pagesize + 1;
			}
			pictures = getPictures(content,contentRadio,orderRadio,1);
			
			request.setAttribute("page", page);
			request.setAttribute("content", oldContent);
			request.setAttribute("contentRadio", contentRadio);
			request.setAttribute("orderRadio", orderRadio);
			request.setAttribute("totalpage", totalpage);
			request.setAttribute("pictures", pictures);
			request.getRequestDispatcher("/pages/search.jsp").forward(request, response);
		}
		else {
			pictures = getPictures(content,contentRadio,orderRadio,page);
			
			ObjectMapper mapper = new ObjectMapper(); 			
			String jsonStr = mapper.writeValueAsString(pictures);

			response.setContentType("text/javascript");
			response.getWriter().print(jsonStr);
			
		}
	}
	
	
	public List<Picture> getAllPictures(String content, String contentRadio, String orderRadio) {
		List<Picture> pictures = null;

		if ("title".equals(contentRadio)&&"likes".equals(orderRadio)) {
			pictures = picturedao.getAllByTitleLikes(content);
		}else if ("title".equals(contentRadio)&&"timestamp".equals(orderRadio)) {
			pictures = picturedao.getAllByTitleTime(content);
		}else if ("description".equals(contentRadio)&&"likes".equals(orderRadio)) {
			pictures = picturedao.getAllByContentLikes(content);
		}else if ("description".equals(contentRadio)&&"timestamp".equals(orderRadio)) {
			pictures = picturedao.getAllByContentTime(content);
		}else {
			pictures = null;
		}
		return pictures;
	}

	public List<Picture> getPictures(String content, String contentRadio, String orderRadio, int page) {
		List<Picture> pictures = null;
		start = (page-1) * pagesize;
		
		if ("title".equals(contentRadio)&&"likes".equals(orderRadio)) {
			pictures = picturedao.getPartByTitleLikes(content,start,9);
		}else if ("title".equals(contentRadio)&&"timestamp".equals(orderRadio)) {
			pictures = picturedao.getPartByTitleTime(content,start,9);
		}else if ("description".equals(contentRadio)&&"likes".equals(orderRadio)) {
			pictures = picturedao.getPartByContentLikes(content,start,9);
		}else if ("description".equals(contentRadio)&&"timestamp".equals(orderRadio)) {
			pictures = picturedao.getPartByContentTime(content,start,9);
		}else {
			pictures = null;
		}

		return pictures;
	}
}
