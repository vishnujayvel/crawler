/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

/**
 *
 * @author Vadivelu
 */
import java.sql.*;
 
 
public class DB {
 
	public Connection conn = null;
 
	public DB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/Crawler";
			conn = DriverManager.getConnection(url, "root", "");
			System.out.println("conn built");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
 
	public ResultSet runSql(String sql) throws SQLException {
		Statement sta = conn.createStatement();
		return sta.executeQuery(sql);
	}
 
	public boolean runSql2(String sql) throws SQLException {
		Statement sta = conn.createStatement();
		return sta.execute(sql);
	}
 
	@Override
	protected void finalize() throws Throwable {
		if (conn != null || !conn.isClosed()) {
			conn.close();
		}
	}
}