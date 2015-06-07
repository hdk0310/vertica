package my.vertica.utility;

import java.sql.Connection;
import java.util.Properties;

public interface JdbcConnection {
	
	public Connection createConnection();
	
	public Properties getConnectionProperties();

}
