package com.ezen.word.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

public class DataBaseManager {
	private String driver;
	private String url;
	private String id;
	private String pw;
	
	@Autowired
	private DataBaseUserinfo dbi;
	// applicationContext.xml 의존주입 -> @Autowired 에의 한 의존 자동주입
	// @Autowired 가 constructor-arg 역할을 대신합니다.
	
	/*public DataBaseManager(DataBaseUserinfo dbi) {
		url = dbi.getUrl();
		id = dbi.getId();
		pw = dbi.getPw();
		driver = dbi.getDriver();
	}*/
	
	public Connection getConnection() {
		Connection con=null;
		url = dbi.getUrl();
		id = dbi.getId();
		pw = dbi.getPw();
		driver = dbi.getDriver();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();}
		return con;
	}
	public void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if( con != null) con.close();
			if( pstmt != null) pstmt.close();
			if( rs != null) rs.close();
		} catch (SQLException e) {e.printStackTrace();}		
	}
}
