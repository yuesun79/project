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
import javax.servlet.http.HttpSession;

@WebServlet("/logoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		
		Cookie [] cookies = request.getCookies();

    	if(cookies != null && cookies.length > 0) {
    		for (Cookie c:cookies) {
    			String cookieName = c.getName();
    			if (cookieName.startsWith("PICTURE_")) {
    				c.setMaxAge(0);
    	    		response.addCookie(c);
    			}
			}
		}
 
		
		response.sendRedirect(request.getContextPath()+"/pages/login.jsp");
	}

}