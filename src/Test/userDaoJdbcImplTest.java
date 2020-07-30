package Test;

import org.junit.jupiter.api.Test;

import JavaBean.User;
import dao.UserDao;
import impls.UserDaoJdbcImpl;

class userDaoJdbcImplTest {
	UserDao userDao = new UserDaoJdbcImpl();
	@Test
	void testGetUser() {
		User user = new User("luisg","abcd1234");
		System.out.println(userDao.getUser(user));
	}
	
	@Test
	void testGetPicMatch() {
		System.out.println(userDao.getPicMatch(1,2));
		System.out.println(userDao.getPicMatch(3,2));
		
	}

}
