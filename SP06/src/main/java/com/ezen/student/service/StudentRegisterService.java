package com.ezen.student.service;

import com.ezen.student.dao.StudentDao;
import com.ezen.student.dto.Student;

public class StudentRegisterService {
	/*String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "scott";
	String pw = "tiger";
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;*/
	private StudentDao sdao;
	//private StudentDao sdao = new StudentDao();
	
	public StudentRegisterService(StudentDao sdao) {
		this.sdao = sdao;
	}
	
	public void register(Student student) {
		// 전달된 dto 의 내용을 데이터베이스 테이블에 레코드로 추가
		sdao.insert(student);
		
		/*String sql = "insert into student(snum, sid, spw, sname, sage, sgender, smajor)"
				+ " values(?,?,?,?,?,?,?)";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  student.getsNum());
			pstmt.setString(2,  student.getsId());
			pstmt.setString(3,  student.getsPw());
			pstmt.setString(4,  student.getsName());
			pstmt.setInt(5,  student.getsAge());
			pstmt.setString(6,  student.getsGender());
			pstmt.setString(7,  student.getsMajor());
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
		}*/
	}
}
