package Test;

import org.junit.jupiter.api.Test;
import dao.Dao;


class DaoTest {

	@Test
	void testGet() {
		
		Dao<JavaBean.Picture> dao = new Dao<JavaBean.Picture>();
		String sql = "SELECT imageID, title, description, cityCode, countryCodeISO, UID, path, content,likes"
				+ " FROM travelimage WHERE imageID = ?";
		dao.get(sql, 2);
		
	}

}
