package blog.commons;
import java.sql.*;
public class DBUtil {
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mariadb://localhost/alswjdd", "alswjdd", "java1004");
		
		return conn;
	}
	
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(stmt != null) {
			try {
				stmt.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(conn != null) {
			try {
				conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
