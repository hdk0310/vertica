package my.vertica.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tdongsi
 * 
 */
public class VerticaConnection extends AbstractJdbcConnection implements
		JdbcConnection {

	private static final Logger logger = LoggerFactory.getLogger(VerticaConnection.class);
	
	// Default values for local Vertica VM provided by HP
	public static final String VM_USERNAME = "dbadmin";
	public static final String VM_PASSWORD = "password";
	public static final String VM_HOST = "192.168.5.133";
	public static final String VM_PORT = "5433";
	public static final String VM_DB = "VMart";
	
	/**
	 * When no connection detail is provided, initialize
	 * with default values for Vertica VM provided by HP
	 */
	public VerticaConnection() {
		this.withLogin(VM_USERNAME, VM_PASSWORD)
			.withHostPort(VM_HOST, VM_PORT)
			.withDatabase(VM_DB);
	}
	
	/**
	 * Initialize Vertica connection with details provided.
	 * 
	 * @param username
	 * @param password
	 * @param host
	 * @param port
	 * @param database
	 */
	public VerticaConnection(String username, String password,
			String host, String port, String database) {
		this.withLogin(username, password)
			.withHostPort(host, port)
			.withDatabase(database);
	}

	@Override
	public Connection createConnection() {
		// Example url: "jdbc:vertica://192.168.5.133:5433/VMart";
		String url = "jdbc:vertica://" + this.getHost() + ":" + this.getPort() + "/"
				+ this.getDatabase();
		logger.debug( "Connection URL: {}", url);
		Connection conn;

		// Load JDBC driver
		try {
			Class.forName("com.vertica.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// Could not find the driver class. Likely an issue
			// with finding the .jar file.
			logger.error("Could not find the JDBC driver class.");
			return null;
		}

		// Create property object to hold username & password
		Properties myProp = new Properties();
		myProp.put("user", this.getUsername());
		myProp.put("password", this.getPassword());
		try {
			conn = DriverManager.getConnection(url, myProp);
		} catch (SQLException e) {
			// Could not connect to database.
			logger.error("Could not connect to database. Check url, username, and password. "
					+ e.getMessage());
			return null;
		}

		// Connection is established
		return conn;
	}
}
