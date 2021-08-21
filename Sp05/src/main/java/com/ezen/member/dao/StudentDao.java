package com.ezen.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezen.member.dto.Student;

public class StudentDao {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "scott";
	String pw = "tiger";
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public Student select(String sNum) {
		Student std = null;
		String sql = "select * from student where sNum=?";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				std = new Student( rs.getString("sNum"), rs.getString("sId"),rs.getString("sPw"),
				rs.getString("sName"),	rs.getInt("sAge"), rs.getString("sGender"), rs.getString("sMajor")	);				
			}
		} catch (ClassNotFoundException e) {	e.printStackTrace();
		} catch (SQLException e) {	e.printStackTrace();
		} finally {
				try { if(pstmt != null) pstmt.close();
					if(con != null) con.close();
					if( rs != null ) rs.close();
				} catch (SQLException e) {	e.printStackTrace();	}
		}
		return std;
	}
	
	
	
	public void update(Student std) {
		String sql = "update student set sId=?, sPw=?, sName=?, ";
		sql += " sAge=?, sGender=?, sMajor=? where sNum=?";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  std.getsId());      	pstmt.setString(2,  std.getsPw());
			pstmt.setString(3,  std.getsName());    pstmt.setInt(4,  std.getsAge());
			pstmt.setString(5,  std.getsGender());   	pstmt.setString(6,  std.getsMajor());
			pstmt.setString(7,  std.getsNum());    int result = pstmt.executeUpdate();	
			if(result==1)System.out.println("수정 성공");
			else System.out.println("수정 실패");
		} catch (ClassNotFoundException e) { e.printStackTrace();
		} catch (SQLException e) {	e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {pstmt.close();}
				if(con != null) {	con.close();}
			} catch (SQLException e) {	e.printStackTrace();	}
		}
		
	}
	
	
	
	public ArrayList<Student> selectAll() {
		ArrayList<Student> list = new ArrayList<Student>();
		String sql = "select * from student";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Student std = new Student( rs.getString("sNum"), rs.getString("sId"), 
				rs.getString("sPw"), rs.getString("sName"), rs.getInt("sAge"),
				rs.getString("sGender"), rs.getString("sMajor") 	);
				list.add(std);
			}
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
				if(rs != null) rs.close();
			} catch (SQLException e) {	e.printStackTrace();	}
		}
		return list;
	}
	
	
	
	
	
	public void insert(Student std) {	
		String sql = "insert into student(snum, sid, spw, sname, sage, sgender, smajor)"
				+ " values(?,?,?,?,?,?,?)";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  std.getsNum());
			pstmt.setString(2,  std.getsId());
			pstmt.setString(3,  std.getsPw());
			pstmt.setString(4,  std.getsName());
			pstmt.setInt(5,  std.getsAge());
			pstmt.setString(6,  std.getsGender());
			pstmt.setString(7,  std.getsMajor());
			int result = pstmt.executeUpdate();			
			System.out.println(result);
			if(result==1)System.out.println("저장 성공");
			else System.out.println("저장 실패");
		} catch (ClassNotFoundException e) { 	e.printStackTrace();
		} catch (SQLException e) { 	e.printStackTrace();
		} finally {
				try {
					if(pstmt != null) {pstmt.close();}
					if(con != null) {	con.close();}
				} catch (SQLException e) {	e.printStackTrace();	}
		}		
	}
}
