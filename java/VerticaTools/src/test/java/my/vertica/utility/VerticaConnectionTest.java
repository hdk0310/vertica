package my.vertica.utility;

import java.sql.Connection;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

public class VerticaConnectionTest {

	/**
	 * NOTE: Make sure your local Vertica VM is started
	 */
	@Test
	public void test_createConnection_default() {
		VerticaConnection manager = new VerticaConnection();
		
		Connection conn = manager.createConnection();
		Assert.assertNotNull(conn);
	}

	@Test
	public void test_createConnection_local() {
		VerticaConnection manager = new VerticaConnection(
				VerticaConnection.VM_USERNAME,
				VerticaConnection.VM_PASSWORD,
				VerticaConnection.VM_HOST,
				VerticaConnection.VM_PORT,
				VerticaConnection.VM_DB);
		
		Connection conn = manager.createConnection();
		Assert.assertNotNull(conn);
	}
	
	/**
	 * Check the returned Properties object has the standard keys
	 */
	@Test
	public void test_getProperties() {
		VerticaConnection manager = new VerticaConnection();
		
		Properties prop = manager.getConnectionProperties();
		Assert.assertEquals(prop.getProperty("username"), VerticaConnection.VM_USERNAME);
		Assert.assertEquals(prop.getProperty("password"), VerticaConnection.VM_PASSWORD);
		Assert.assertEquals(prop.getProperty("host"), VerticaConnection.VM_HOST);
		Assert.assertEquals(prop.getProperty("port"), VerticaConnection.VM_PORT);
		Assert.assertEquals(prop.getProperty("database"), VerticaConnection.VM_DB);
	}
}
