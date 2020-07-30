package impls;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import JavaBean.User;
import dao.Dao;
import dao.UserDao;

public class UserDaoJdbcImpl extends Dao<User> implements UserDao {

	
	@Override
	public void addUser(User user) {
		Timestamp dateTime = new Timestamp(new Date().getTime());
		String sql = "INSERT INTO traveluser (email, userName, pass, DateJoined) VALUES (?,?,?,?)";
		update(sql,user.getEmail(),user.getUserName(),user.getPass(),dateTime);
	}

	@Override
	public long getCountWithName(String name) {
		String sql = "SELECT count(userName) FROM traveluser WHERE userName = ?";
		return getForValue(sql,name);
	}

	@Override
	public User getUser(User user) {
		String sql = "SELECT * FROM traveluser WHERE userName = ?";
		return get(sql,user.getUserName());
	}

	@Override
	public long getPicMatch(int uid, int imageID) {
		String sql = "SELECT count(FavorID) FROM travelimagefavor WHERE UID = ? AND imageID = ?";
		return getForValue(sql,uid,imageID);
	}

	@Override
	public void likePic(int uid, int imageID) {
		String sql = "INSERT INTO travelimagefavor (UID,imageID) VALUES (?,?)";
		update(sql,uid,imageID);
	}

	@Override
	public void unlikePic(int uid, int imageID) {
		String sql = "DELETE FROM travelimagefavor WHERE UID = ? AND imageID = ?";
		update(sql,uid,imageID);
	}

	@Override
	public List<User> getAllUser() {
		String sql = "SELECT * FROM traveluser";
		return getForList(sql);
	}

	@Override
	public boolean whetherPublic(int uid) {
		String sql = "SELECT whetherPublic FROM traveluser WHERE UID = ?";
		return getForValue(sql, uid);
	}

	@Override
	public void setPublic(User user, boolean whetherPublic) {
		String sql = "UPDATE traveluser SET whetherPublic = ? WHERE UID = ?";
		update(sql,whetherPublic,user.getUID());
	}
	
	

}
