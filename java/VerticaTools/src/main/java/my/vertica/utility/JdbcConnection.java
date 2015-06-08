package my.vertica.utility;

import java.sql.Connection;
import java.util.Properties;

/**
 * Specify the basic behavior of the JDBC Connection classes.
 * 
 * The utility classes that implements this interface will also
 * contains other connection details, which may be sometimes unique
 * for individual databases.
 * 
 * Some standard connection details include current username, password,
 * host, port, database.
 * 
 * @author tdongsi
 *
 */
public interface JdbcConnection {
	
	/**
	 * Create a JDBC connection based on current connection details.
	 * 
	 * @return
	 */
	public Connection createConnection();
	
	/**
	 * Return connection details as a Properties instance.
	 * Standard key strings are: username, password, host, port, database
	 * 
	 * @return
	 */
	public Properties getConnectionProperties();

}
