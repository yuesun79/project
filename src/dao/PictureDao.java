package dao;

import java.util.List;
import JavaBean.Picture;

public interface PictureDao {
	//for searchPage
	public List<Picture> getAllByTitleLikes(String content);
	public List<Picture> getAllByTitleTime(String content);
	public List<Picture> getAllByContentLikes(String content);
	public List<Picture> getAllByContentTime(String content);
	
	public List<Picture> getPartByTitleLikes(String title,Integer a, Integer b);
	public List<Picture> getPartByTitleTime(String title,Integer a, Integer b);
	public List<Picture> getPartByContentLikes(String content,Integer a, Integer b);
	public List<Picture> getPartByContentTime(String content,Integer a, Integer b);
	//just match countryName cityName userName
	public String getForCountry(String value);
	public String getForCity(Integer value);
	public String getForUser(Integer value);
	
	public String getCountryISO(String countryName);
	public Integer getCityCode(String asciiName);
	
	//limited numbers of photos with different order
	public List<Picture> getPartByLikes(Integer a, Integer b);
	public List<Picture> getPartByTime(Integer a, Integer b);
	//for like button
	public int getLike(String imageID);
	public void setLike(String imageID,int content);
	//
	public List<Picture> getMy(int uid);
	public List<Picture> getFavor(int uid);
	//
	public List<Picture> getAllCountry();
	public List<Picture> getMatchCity(String iso);
	//
	public Picture get(Integer imageId);
	
	public void save(Picture picture);
	
	public void modify(Picture picture);
	
	public void delete(Integer imageID);
}
