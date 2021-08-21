package com.ezen.student.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezen.student.dto.DataBaseConnectionInfo;
import com.ezen.student.dto.Student;

public class StudentDao {
	String driver = null;
	String url = null;
	String id = null;
	String pw = null;
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	StudentDao( DataBaseConnectionInfo dbconinfo ){
		driver = dbconinfo.getDriver();
		url = dbconinfo.getJdbcUrl();
		id = dbconinfo.getUserId();
		pw = dbconinfo.getUserPw();
	}

	private Connection getConnection() {
		Connection conn = null;
		try {
			System.out.println(driver + url + pw + id);
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) { e.printStackTrace();
		} catch (SQLException e) { e.printStackTrace();  }
		return conn;
	}
	
	private void close() {
		try {
			if(con!=null) con.close();
			if(pstmt!=null) pstmt.close();
			if(rs!=null) rs.close();
		} catch (SQLException e) {  e.printStackTrace();  	}
	}
	
	// 보통 Dao 에는 CRUD 의 기능이 기본으로 탑재 됩니다.
	// C : Create(Insert) - 레코드 추가
	// R : Read(Select) - 테이블 조회
	// U : Update(Update) - 레코드 업데이터
	// D : Delete(Delete) - 레코드 삭제
	// 테이블 : student  (Dto :  Student)
	
	public void insert(Student std) {  
		String sql = "insert into student(snum, sid, spw, sname, sage, sgender, smajor)"
				+ " values(?,?,?,?,?,?,?)";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  std.getsNum());
			pstmt.setString(2,  std.getsId());
			pstmt.setString(3,  std.getsPw());
			pstmt.setString(4,  std.getsName());
			pstmt.setInt(5,  std.getsAge());
			pstmt.setString(6,  std.getsGender());
			pstmt.setString(7,  std.getsMajor());
			pstmt.executeUpdate();						
		} catch (SQLException e) { 	e.printStackTrace();
		} finally { 		close();	}	
	}
	
	
	
	public void update(Student std) { 
		String sql = "update student set sId=?, sPw=?, sName=?, ";
		sql += " sAge=?, sGender=?, sMajor=? where sNum=?";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  std.getsId());      	pstmt.setString(2,  std.getsPw());
			pstmt.setString(3,  std.getsName());    pstmt.setInt(4,  std.getsAge());
			pstmt.setString(5,  std.getsGender());   	pstmt.setString(6,  std.getsMajor());
			pstmt.setString(7,  std.getsNum());    int result = pstmt.executeUpdate();	
			if(result==1)System.out.println("수정 성공");
			else System.out.println("수정 실패");
		}catch (SQLException e) {	e.printStackTrace();
		} finally { close(); }
	}
	
	
	public ArrayList<Student> selectAll( ) { 
		ArrayList<Student> list = new ArrayList<Student>();
		String sql = "select * from student";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Student std = new Student( rs.getString("sNum"), rs.getString("sId"), 
				rs.getString("sPw"), rs.getString("sName"), rs.getInt("sAge"),
				rs.getString("sGender"), rs.getString("sMajor") 	);
				list.add(std);
			}
		} catch (SQLException e) {e.printStackTrace();
		} finally { 	close();	}
		return list;
	}
	
	
	public Student select( String id ) { 
		Student std = null;
		String sql = "select * from student where sNum=?";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				std = new Student( rs.getString("sNum"), rs.getString("sId"),
						rs.getString("sPw"),	rs.getString("sName"),	rs.getInt("sAge"), 
						rs.getString("sGender"), rs.getString("sMajor")	);				
			}
		} catch (SQLException e) {	e.printStackTrace();
		} finally { close(); }
		return std;
	}
	
	
	public void delete(String id) {     }
}
