package my.vertica.utility;

import java.sql.Connection;

import org.testng.Assert;
import org.testng.annotations.Test;

public class VerticaConnectionTest {

	/**
	 * NOTE: Make sure your local Vertica VM is started
	 */
	@Test
	public void test_createConnection() {
		VerticaConnection manager = new VerticaConnection();
		
		Connection conn = manager.createConnection();
		Assert.assertNotNull(conn);
	}
}
