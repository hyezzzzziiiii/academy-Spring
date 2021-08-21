package com.ezen.spboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.spboard.dto.Paging;
import com.ezen.spboard.dto.ReplyVO;
import com.ezen.spboard.dto.SpBoard;
import com.ezen.spboard.util.DBManager;

@Repository
public class BoardDao {

	@Autowired
	DBManager dbm;
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	public void addReply(ReplyVO rvo) {
		String sql = "insert into reply(num, boardnum, userid, content) "
				+ "values( reply_seq.nextVal, ?, ?, ?)";
		try {
			con = dbm.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  rvo.getBoardnum());
			pstmt.setString(2, rvo.getUserid());
			pstmt.setString(3, rvo.getContent());
			pstmt.executeUpdate();
		} catch (SQLException e) { 	e.printStackTrace();		
		} finally {	dbm.close(con, pstmt, rs); }		
	}
	
	
	
	
	
	public void deleteReply(String num) {
		String sql = "delete from reply where num = ?";
		try {
			con = dbm.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  Integer.parseInt( num ));
			pstmt.executeUpdate();
		} catch (SQLException e) { 	e.printStackTrace();		
		} finally {	dbm.close(con, pstmt, rs); }		
	}
	
	
	
	public void boardDelete(String num) {
		String sql = "delete from board where num=?";
		try {
			con = dbm.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(num));
			pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();
		} finally {	dbm.close(con, pstmt, rs); }
	}
	
	
	
	
	
	public void boardUpdate(SpBoard svo) {
		String sql = "Update board set  pass=?, userid=?, email=?,title=?, "
				+ " content=? where num=?";
		try {
			con = dbm.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, svo.getPass() );
			pstmt.setString(2, svo.getUserid() );
			pstmt.setString(3, svo.getEmail() );
			pstmt.setString(4, svo.getTitle() );
			pstmt.setString(5, svo.getContent() );
			pstmt.setInt(6, svo.getNum() );
			pstmt.executeUpdate();
		}catch (SQLException e) {e.printStackTrace();
		} finally {	dbm.close(con, pstmt, rs); }
	}
	
	
	
	
	
	public void boardInsert(SpBoard sb) {
		String sql = "insert into board(num, pass, userid, email, title, content) "
				+ "values( board_seq.nextVal, ?,?,?,?,? )";
		try {
			con = dbm.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString( 1, sb.getPass() );
			pstmt.setString( 2, sb.getUserid() );
			pstmt.setString( 3, sb.getEmail() );
			pstmt.setString( 4, sb.getTitle() );
			pstmt.setString( 5, sb.getContent() );
			pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		} finally {	dbm.close(con, pstmt, rs); }
	}
	
	
	
	public ArrayList<ReplyVO> selectReply(String num){
		ArrayList<ReplyVO> list = new ArrayList<ReplyVO>();
		String sql = "select * from reply where boardnum=? order by num desc";
		try {
			con = dbm.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(num));
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				ReplyVO rvo = new ReplyVO();
				rvo.setNum(rs.getInt("num"));
				rvo.setBoardnum( rs.getInt("boardnum") );
				rvo.setUserid(rs.getString("userid"));
				rvo.setWritedate(rs.getTimestamp("writedate") );
				rvo.setContent(rs.getNString("content"));
				list.add(rvo);
			}
		} catch (SQLException e) {e.printStackTrace();
		} finally {	dbm.close(con, pstmt, rs); }	
		return list;
	}
	
	
	
	
	
	
	public void increaseReadcount(String num) {
		String sql="Update board set readcount = readcount +1 where num=?";
		try {
			con = dbm.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(num));
			pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();
		} finally {	dbm.close(con, pstmt, rs); }	
	}
	
	public SpBoard getBoard(String num) {
		SpBoard sb = new SpBoard();
		String sql = "select * from board where num = ?";
		try {
			con = dbm.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(num));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sb.setNum(rs.getInt("num"));
				sb.setUserid(rs.getString("userid"));
				sb.setPass(rs.getString("pass"));
				sb.setEmail(rs.getString("email"));
				sb.setTitle(rs.getString("title"));
				sb.setContent(rs.getString("content"));
				sb.setWritedate(rs.getTimestamp("writedate"));
				sb.setReadcount(rs.getInt("readcount"));
			}
		} catch (SQLException e) {e.printStackTrace();
		} finally {	dbm.close(con, pstmt, rs); }	
		return sb;
	}
	
	
	public int getAllCount() {
		String sql = "select count(*) as count from board";
		int count = 0;
		try {
			con = dbm.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) count = rs.getInt("count");
		} catch (SQLException e) {e.printStackTrace();
		} finally {	dbm.close(con, pstmt, rs); }
		return count;
	}
	
	
	
	public ArrayList<SpBoard> selectBoardAll(Paging paging){
		ArrayList<SpBoard> list = new ArrayList<SpBoard>();
		int startNum = paging.getStartNum();
	    int endNum = paging.getEndNum();
		String sql = "select * from ("
				+ "select * from ("
				+ " select rownum as rn, b.* from ( (select * from board order by num desc) b )"
				+ " ) where rn >= ?"
				+ " ) where rn <= ?";
		try {
			con = dbm.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  startNum);
			pstmt.setInt(2,  endNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				SpBoard sb = new SpBoard();
				sb.setNum(rs.getInt("num"));
				sb.setPass(rs.getString("pass"));
				sb.setUserid(rs.getString("userid"));
				sb.setTitle(rs.getString("title"));
				sb.setEmail(rs.getString("email"));
				sb.setContent(rs.getString("content"));
				sb.setWritedate(rs.getTimestamp("writedate"));
				sb.setReadcount(rs.getInt("readcount"));
				
				String sql2 = "select count(*) as cnt from reply where boardnum = ?";
				int num = rs.getInt("num");
				PreparedStatement pstmt2 =  con.prepareStatement(sql2);
				pstmt2.setInt(1, num);
				ResultSet rs2 = pstmt2.executeQuery();
				if(rs2.next()) sb.setReplycnt( rs2.getInt("cnt") );
				else sb.setReplycnt( 0 );
				
				list.add(sb);
			}
		} catch (SQLException e) {e.printStackTrace();
		} finally {	dbm.close(con, pstmt, rs); }
		return list;
	}
}
