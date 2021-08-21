package com.ezen.spboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.spboard.dto.SpMember;
import com.ezen.spboard.util.DBManager;

@Repository
public class MemberDao {

	@Autowired
	DBManager dbm;
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	public int memberUpdate(SpMember sm) {
		int result = 0;
		String sql = "Update spmember set pw=?, name=?,"
				+ " phone1=?, phone2=?, phone3=?, email=? where id = ?";
		try {
			con = dbm.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sm.getPw());
			pstmt.setString(2, sm.getName());
			pstmt.setString(3, sm.getPhone1());
			pstmt.setString(4, sm.getPhone2());
			pstmt.setString(5, sm.getPhone3());
			pstmt.setString(6, sm.getEmail());
			pstmt.setString(7, sm.getId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();
		} finally {	dbm.close(con, pstmt, rs); }
		return result;
	}
	
	
	
	
	public void memberInsert(SpMember sm) {
		String sql = "Insert into spmember(id, pw, name, phone1,"
				+ "phone2, phone3, email) values(?,?,?,?,?,?,?)";
		try {
			con = dbm.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sm.getId());
			pstmt.setString(2, sm.getPw());
			pstmt.setString(3, sm.getName());
			pstmt.setString(4, sm.getPhone1());
			pstmt.setString(5, sm.getPhone2());
			pstmt.setString(6, sm.getPhone3());
			pstmt.setString(7, sm.getEmail());
			pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();
		} finally {	dbm.close(con, pstmt, rs); }
	}
	
	
	
	
	
	
	public int confirmId(String id) {
		String sql = "select * from spmember where id=?";
		int result=0;
		try {
			con = dbm.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) result = 1;
			else result = -1;
		} catch (SQLException e) {e.printStackTrace();
		} finally {	dbm.close(con, pstmt, rs);}
		return result;
	}
	
	
	
	
	public SpMember getMember(String id) {
		SpMember sdto = null;
		String sql = "Select * from spmember where id = ?";
		try {
			con = dbm.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				sdto = new SpMember();
				sdto.setId(id);
				sdto.setPw(rs.getString("pw"));
				sdto.setName(rs.getString("name"));
				sdto.setEmail(rs.getString("email"));
				sdto.setPhone1(rs.getString("phone1"));
				sdto.setPhone2(rs.getString("phone2"));
				sdto.setPhone3(rs.getString("phone3"));
			}
		} catch (SQLException e) {e.printStackTrace();
		} finally {	dbm.close(con, pstmt, rs); }
		return sdto;
	}
}
