package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaBean.User;
import dao.UserDao;
import impls.UserDaoJdbcImpl;

@WebServlet("/changeAuthorityServlet")
public class ChangeAuthorityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDaoJdbcImpl();
		User user = (User) request.getSession().getAttribute("user");
		
		boolean whetherPublic = userDao.whetherPublic(user.getUID());
		userDao.setPublic(user, !whetherPublic);
	}

}
