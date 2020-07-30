package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaBean.User;
import dao.UserDao;
import impls.UserDaoJdbcImpl;

@WebServlet("/friendServlet")
public class FriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDaoJdbcImpl();
		List<User> users = userDao.getAllUser();
		
		User user = (User) request.getSession().getAttribute("user");
		boolean whetherPublic = userDao.whetherPublic(user.getUID());
		if (whetherPublic) {
			request.setAttribute("message", "不让其他人看我的收藏");
		}else {
			request.setAttribute("message", "允许其他人看我的收藏");
			
		}
		request.setAttribute("users", users);
		request.getRequestDispatcher("/pages/friends.jsp").forward(request, response);
	}

}
