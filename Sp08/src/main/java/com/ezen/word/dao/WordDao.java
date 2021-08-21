package com.ezen.word.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ezen.word.dto.WordSet;
import com.ezen.word.util.DataBaseManager;

public class WordDao {
	Connection con=null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DataBaseManager dbm = null;
	public WordDao(DataBaseManager dbm){
		this.dbm = dbm;
	}
	public void insert(WordSet ws) {
		String sql = "insert into wordset values(?, ?)";
		con = dbm.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ws.getWordKey());
			pstmt.setString(2, ws.getWordValue());
			pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		}  finally {	dbm.close(con, pstmt, rs); }
	}
	public WordSet search(String wk) {
		WordSet ws = null;
		String sql = "select wordvalue from wordset where wordkey=?";
		try {
			con = dbm.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, wk);
			rs = pstmt.executeQuery();
			if(rs.next()) ws = new WordSet(wk, rs.getString("wordvalue"));
		} catch (SQLException e) {	e.printStackTrace();
		} finally {	dbm.close(con, pstmt, rs); }
		return ws;
	}
}
