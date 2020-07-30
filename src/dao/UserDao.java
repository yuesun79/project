package dao;
import java.util.List;

import JavaBean.User;

public interface UserDao {

	public void addUser(User user);

	public long getCountWithName(String name);
	
	public User getUser(User user);
	
	public long getPicMatch(int uid,int imageID);
	
	public void likePic(int uid,int imageID);
	
	public void unlikePic(int uid,int imageID);
	
	public List<User> getAllUser();
	
	public boolean whetherPublic(int uid);

	public void setPublic(User user, boolean whetherPublic);
	
}
