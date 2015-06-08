package my.vertica.utility;

import java.sql.Connection;
import java.util.Properties;

/**
 * Template implementation of JdbcConnection interface.
 * 
 * It contains private fields for standard connection details,
 * namely username, password, host, port, database.
 * Depending on database type, one can add private fields for
 * additional connection detail when subclassing this class.
 * 
 * @author tdongsi
 *
 */
public abstract class AbstractJdbcConnection implements JdbcConnection {
	
	private String username;
	private String password;
	private String host;
	// NOTE: port number is a String instead of int for simplicity
	private String port;
	private String database;
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * @return the database
	 */
	public String getDatabase() {
		return database;
	}

	/**
	 * @param database the database to set
	 */
	public void setDatabase(String database) {
		this.database = database;
	}
	
	/**
	 * Use Builder pattern for fluent style.
	 * Username and password are usually changed together,
	 * making this more user-friendly than standard setter methods.
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public JdbcConnection withLogin(String username, String password) {
		this.username = username;
		this.password = password;
		return this;
	}
	
	/**
	 * Use Builder pattern for fluent style.
	 * 
	 * @param host
	 * @param port
	 * @return
	 */
	public JdbcConnection withHostPort(String host, String port) {
		this.host = host;
		this.port = port;
		return this;
	}
	
	/**
	 * Use Builder pattern for fluent style.
	 * 
	 * @param database
	 * @return
	 */
	public JdbcConnection withDatabase(String database) {
		this.database = database;
		return this;
	}

	@Override
	public Properties getConnectionProperties() {
		Properties myProp = new Properties();
		myProp.put("username", this.username);
		myProp.put("password", this.password);
		myProp.put("host", this.host);
		myProp.put("port", this.port);
		myProp.put("database", this.database);
		
		return myProp;
	}
	
	@Override
	public abstract Connection createConnection();
}
