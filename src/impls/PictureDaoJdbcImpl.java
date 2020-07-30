package impls;

import java.util.List;

import JavaBean.Picture;
import dao.Dao;
import dao.PictureDao;

public class PictureDaoJdbcImpl extends Dao<Picture> implements PictureDao{

	

	@Override
	public List<Picture> getPartByLikes(Integer a, Integer b) {
		String sql = "SELECT * FROM travelimage ORDER BY Likes DESC LIMIT ?,?";
		return getForList(sql,a,b);
	}
	
	@Override
	public List<Picture> getPartByTime(Integer a, Integer b) {
		String sql = "SELECT * FROM travelimage ORDER BY dateTime DESC LIMIT ?,?";
		return getForList(sql,a,b);
	}


	@Override
	public void save(Picture picture) {
		String sql = "INSERT INTO travelimage (title, description, cityCode, countryCodeISO, UID, path, content, likes, dateTime)"
				+ " VALUES (?,?,?,?,?,?,?,?,?)";
		update(sql,picture.getTitle(),picture.getDescription(),picture.getCityCode(),picture.getCountryCodeISO(),picture.getUID(),
				picture.getPath(),picture.getContent(),picture.getLikes(),picture.getDateTime());

	}

	@Override
	public void modify(Picture picture) {
		String sql = "UPDATE travelimage SET title = ?, description = ?, cityCode = ?, countryCodeISO = ?, "
				+ "path = ?, content = ?, dateTime = ? WHERE imageId = ?";
		update(sql,picture.getTitle(),picture.getDescription(),picture.getCityCode(),picture.getCountryCodeISO(),
				picture.getPath(),picture.getContent(),picture.getDateTime(),picture.getImageID());
	}

	@Override
	public void delete(Integer imageID) {
		String sql = "DELETE FROM travelimage WHERE imageID = ?";
		String sql2 = "DELETE FROM travelimagefavor WHERE imageID = ?";
		update(sql,imageID);
		update(sql2,imageID);
		
	}

	@Override
	public Picture get(Integer imageId) {
		String sql = "SELECT * FROM travelimage WHERE imageId = ?";
		return get(sql,imageId);
	}

	@Override
	public String getForCountry(String value) {
		String sql = "SELECT CountryName FROM geocountries WHERE CountryCodeISO = ?";
		return getForValue(sql,value);
	}

	@Override
	public String getForCity(Integer value) {
		String sql = "SELECT AsciiName FROM geocities WHERE GeoNameID = ?";
		return getForValue(sql,value);
	}

	@Override
	public String getForUser(Integer value) {
		String sql = "SELECT UserName FROM traveluser WHERE UID = ?";
		return getForValue(sql,value);
	}

	@Override
	public List<Picture> getPartByTitleLikes(String title, Integer a, Integer b) {
		String sql = "SELECT * FROM travelimage WHERE Title LIKE ? ORDER BY Likes DESC LIMIT ?,?";
		return getForList(sql,title,a,b);
	}

	@Override
	public List<Picture> getPartByTitleTime(String title, Integer a, Integer b) {
		String sql = "SELECT * FROM travelimage WHERE Title LIKE ? ORDER BY dateTime DESC LIMIT ?,?";
		return getForList(sql,title,a,b);
	}

	@Override
	public List<Picture> getPartByContentLikes(String content, Integer a, Integer b) {
		String sql = "SELECT * FROM travelimage WHERE content LIKE ? ORDER BY Likes DESC LIMIT ?,?";
		return getForList(sql,content,a,b);
	}

	@Override
	public List<Picture> getPartByContentTime(String content, Integer a, Integer b) {
		String sql = "SELECT * FROM travelimage WHERE content LIKE ? ORDER BY dateTime DESC LIMIT ?,?";
		return getForList(sql,content,a,b);	}

	@Override
	public List<Picture> getAllByTitleLikes(String content) {
		String sql = "SELECT * FROM travelimage WHERE Title LIKE ? ORDER BY Likes DESC";
		return getForList(sql,content);
	}

	@Override
	public List<Picture> getAllByTitleTime(String content) {
		String sql = "SELECT * FROM travelimage WHERE Title LIKE ? ORDER BY dateTime DESC";
		return getForList(sql,content);
	}

	@Override
	public List<Picture> getAllByContentLikes(String content) {
		String sql = "SELECT * FROM travelimage WHERE content LIKE ? ORDER BY Likes DESC";
		return getForList(sql,content);
	}

	@Override
	public List<Picture> getAllByContentTime(String content) {
		String sql = "SELECT * FROM travelimage WHERE content LIKE ? ORDER BY dateTime DESC";
		return getForList(sql,content);
	}


	@Override
	public int getLike(String imageID) {
		String sql = "SELECT Likes FROM travelimage WHERE imageID = ?";
		return getForValue(sql,imageID);
	}

	@Override
	public void setLike(String imageID, int content) {
		String sql = "UPDATE travelimage SET Likes = ? WHERE imageID = ?";
		update(sql,content,imageID);
	}

	@Override
	public List<Picture> getMy(int uid) {
		String sql = "SELECT * FROM travelimage WHERE UID = ?";
		return getForList(sql,uid);
	}
	
	@Override
	public List<Picture> getFavor(int uid) {
		String sql = "SELECT * FROM travelimagefavor WHERE UID = ?";
		return getForList(sql,uid);
	}

	@Override
	public List<Picture> getAllCountry() {
		String sql = "SELECT DISTINCT CountryName,countryCodeISO FROM geocountries";
		return getForList(sql);
	}

	@Override
	public List<Picture> getMatchCity(String iso) {
		String sql = "SELECT AsciiName FROM geocities WHERE CountryCodeISO = ? AND Population > 800000";
		return getForList(sql,iso);
	}

	@Override
	public String getCountryISO(String countryName) {
		String sql = "SELECT countryCodeISO FROM geocountries WHERE CountryName = ?"; 
		return getForValue(sql,countryName);
	}

	@Override
	public Integer getCityCode(String asciiName) {
		String sql = "SELECT GeoNameID FROM geocities WHERE AsciiName = ?";
		return getForValue(sql,asciiName);
	}


}
