import java.sql.*;

import com.mysql.cj.jdbc.MysqlDataSource;

public class Conexion {
	
	private static String servername = "localhost";
	private static String username = "root";
	private static String dbname = "trabajo_final";
	private static Integer portnumber = 3307;
	private static String password = "Efete132gps";
	
    public static Connection getConnection() {
        Connection con = null;
        MysqlDataSource datasource = new MysqlDataSource();
        
        datasource.setServerName(servername);
        datasource.setUser(username);
        datasource.setPassword(password);
        datasource.setDatabaseName(dbname);
        datasource.setPortNumber(portnumber);
        
        try {
			con = datasource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return con;
    }
}
