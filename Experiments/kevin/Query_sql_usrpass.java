import java.sql.*;
import java.sql.DriverManager;

public class Query_sql_usrpass {
	public static void main (String[] args) throws ClassNotFoundException, SQLException{
	Class.forName("com.mysql.jdbc.Driver");
	String connectionUrl = "jdbc:mysql://coms-309-sr-7.misc.iastate.edu:3306/test?useUnicod=true&characterEncoding=UTF-8&user=joshua&password=1q2w3e4r5t";
	Connection conn = DriverManager.getConnection(connectionUrl);
	conn.prepareStatement("use nation_simulator;").executeQuery();
	//ResultSet rs = conn.prepareStatement("show tables;").executeQuery();
	ResultSet rs = conn.prepareStatement("select * from accounts;").executeQuery();
	while (rs.next()) {
		String s = rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3);
		System.out.println(s);
		}
		
	}
}
