package servlet;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import JavaBean.Picture;
import JavaBean.User;
import dao.PictureDao;
import impls.PictureDaoJdbcImpl;

@WebServlet("/uploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String content;
	private String countryCodeISO;
	private int cityCode;
	private String title;
	private String description;
	private String fileName;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		PictureDao pictureDao = new PictureDaoJdbcImpl();

		factory.setSizeThreshold(1024 * 1024 * 5);
		File yourTempDirectory = new File("");
		factory.setRepository(yourTempDirectory);

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("utf-8");
		upload.setSizeMax(1024 * 1024 * 5);

		try {
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem item: items) {
				if (!item.isFormField()) {
					String fieldName = item.getFieldName();
				    fileName = item.getName();
//				    System.out.println(request.getParameter("imageID")!=null);
//				    System.out.println(fileName.contentEquals(""));
				    if (request.getParameter("imageID") != null && fileName.contentEquals("")) {
				    	fileName = (String) request.getParameter("path");
				    	System.out.println("--"+fileName);
				    }
				    String contentType = item.getContentType();
				    long sizeInBytes = item.getSize();
//				    System.out.println(fieldName);
//				    System.out.println(fileName);
//				    System.out.println(contentType);
//				    System.out.println(sizeInBytes);
				    
				    InputStream in = item.getInputStream();
				    byte [] buffer = new byte[1024];
				    int len = 0;
				    if (request.getParameter("imageID")==null) {
					    String path = request.getServletContext().getRealPath("/")+"static/photos/" + fileName;
					    OutputStream out = new FileOutputStream(path);
					    
					    while ((len = in.read(buffer)) != -1) {
					    	out.write(buffer,0,len);
					    }
					    
					    out.close();
					    in.close();
				    }
				}
				else {
					String fieldName = item.getFieldName();
					String value = item.getString("UTF-8");
//					System.out.println(fieldName +":"+value);
					switch(fieldName) {
					case "content":content = item.getString("UTF-8");break;
					case "country":countryCodeISO = pictureDao.getCountryISO(item.getString("UTF-8"));break;
					case "city":cityCode = pictureDao.getCityCode(item.getString("UTF-8"));break;
					case "title":title = item.getString("UTF-8");break;
					case "description":description = item.getString("UTF-8");break;
					
					}
				}
			}
			//数据库操作：修改还是新增
			Timestamp dateTime = new Timestamp(new Date().getTime());
			if (request.getParameter("imageID")!=null) {
				System.out.println("modified");
				int imageID = Integer.parseInt(request.getParameter("imageID"));
				Picture picture = new Picture(imageID, title, description, cityCode, countryCodeISO, fileName, content, dateTime);
				pictureDao.modify(picture);
			}
			else {
				User user = (User) request.getSession().getAttribute("user");
				Picture picture = new Picture(title, description, cityCode, countryCodeISO, user.getUID(), fileName, content, "0", dateTime);
				pictureDao.save(picture);
			}
			
			response.sendRedirect(request.getContextPath()+"/myPhotoServlet");
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
