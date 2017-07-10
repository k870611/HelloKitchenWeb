package Utility;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MySqlJDNIConnect {

	private DataSource ds;

	public MySqlJDNIConnect() 
	{
		InitialContext context;
		try {
			context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/connectMySQL/ECOrderService");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Connection getConnection() throws SQLException 
	{
		return ds.getConnection();
	}

	public boolean closeConnection(Connection conn) throws SQLException 
	{
		conn.close();
		return conn.isClosed();
	}
	/* --context
	 * Context <Resource name = "connectMySQL/ECOrderService"
	 * type="javax.sql.DataSource" auth="Container" username="root"
	 * password="password" driverClassName="com.mysql.jdbc.Driver"
	 * url="jdbc:mysql://localhost:3306/foods"/>
	 */
	
	
/* --dd
  <resource-ref>
    <description>MySQLConnection</description>
    <res-ref-name>connectMySQL/ECOrderService</res-ref-name>
    <res-type>javax.sql.DataSource</res-type>
    <res-auth>Container</res-auth>
  </resource-ref>
 
  */
	
	
}
