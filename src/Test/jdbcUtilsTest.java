package Test;


import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;


class jdbcUtilsTest {

	@Test
	void testGetConnection() throws SQLException {
		Connection conn = utils.jdbcUtils.getConnection();
		System.out.println(conn);
	}

}
