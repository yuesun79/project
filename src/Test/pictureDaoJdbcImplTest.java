package Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import JavaBean.Picture;
import dao.PictureDao;


class pictureDaoJdbcImplTest {
	
	private PictureDao pictureDao = new impls.PictureDaoJdbcImpl();
//	@Test
//	Picture testGet() {
//		Picture picture = pictureDao.get(2);
//		System.out.println(picture);
//		return picture;
//	}
//	@Test
//	void testGetPartBy() {
//		System.out.println(pictureDao.getPartByTitleLikes("%win%",0,2));
//		
//	}
//	
//	@Test
//	void testGetForValue() {
//		Picture picture = testGet();
//		
//		System.out.println(pictureDao.getForCity(picture.getCityCode()));
//		System.out.println(pictureDao.getForUser(picture.getUID()));
//		System.out.println(pictureDao.getForCountry(picture.getCountryCodeISO()));
//	}
//	@Test
//	void testGetAll() {
//		System.out.println(pictureDao.getAllByTitleLikes("%win%"));
//		
//	}
//	
//	@Test
//	void testGetValue() {
//		System.out.println("hey");
//		System.out.println("hey"+pictureDao.getLike("13"));
//		pictureDao.setLike("13", 0);
//		System.out.println("hey"+pictureDao.getLike("13"));
//		
//	}
//	@Test
//	void testGetMyOrFavor() {
//		System.out.println(pictureDao.getMy(1));
//		System.out.println(pictureDao.getFavor(1));
//		
//	}
//	
//	@Test
//	void testGetCountry() {
////		System.out.println(pictureDao.getFavor(1));
//		List<Picture> pictures = pictureDao.getAllCountry();
////		String [][] list = new String [pictures.size()][];
//		Map<String, List<String>> map = new HashMap<String, List<String>>();
//		
//		
//		for (int i = 0; i < pictures.size(); i++) {
//			String iso = pictures.get(i).getCountryCodeISO();
//			String countryName = pictureDao.getForCountry(iso);
//			List<Picture> cities = pictureDao.getMatchCity(iso);
//			if (cities.size()>0) {
//				List<String> cityList = new ArrayList<String>();
//				
//				for (int j = 0; j < cities.size(); j++) {
//					cityList.add(cities.get(j).getAsciiName());
//				}
//				map.put(countryName, cityList);
//			}
//		}
//		System.out.println(map);
//		
//	}
//	
//	@Test
//	void testGetValue() {
//
//		System.out.println(pictureDao.getCountryISO("Mali"));
//		System.out.println(pictureDao.getCityCode("`Ayn Halaqim"));
//	}		
//	
//	@Test
//	void testGetValue() {
//		Timestamp dateTime = new Timestamp(new Date().getTime());
//		int id = 112;
//		Picture picture = new Picture(id, "Peaceful Scene", "hiiiii", 1529102, "CN", "WechatIMG110.jpeg", "people", dateTime);
//		pictureDao.modify(picture);
//	}	

	@Test
	void testGetValue() {
		
	}
	

}
