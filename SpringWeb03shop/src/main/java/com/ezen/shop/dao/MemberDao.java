package com.ezen.shop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ezen.shop.dto.AddressVO;
import com.ezen.shop.dto.MemberVO;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class MemberDao {
	
	private JdbcTemplate template;

	@Autowired
	public MemberDao(ComboPooledDataSource dataSource) {
		//this.template = new JdbcTemplate(dataSource);
		template = new JdbcTemplate();
		template.setDataSource(dataSource);
	}
	
	
	
	
	public void resetPw(MemberVO mvo) {
		String sql = "Update member set pwd=? where id = ?";
		template.update(sql, mvo.getPwd(), mvo.getId()	);
	}
	
	
	
	public MemberVO confirmIdNamePhone(String id, String name, String phone) {
		String sql = "select * from member where id=? and name=? and phone=?";
		List<MemberVO> list = null;
		list = template.query( sql, new RowMapper<MemberVO>() {
			@Override
			public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberVO mvo = new MemberVO();
				mvo.setId(rs.getString("id"));
				mvo.setName(rs.getString("name"));
				mvo.setPhone(rs.getString("phone"));
				return mvo;
			}
		}, id, name, phone);
		
		if(list.size()==0) return null;
		else return list.get(0);
	}
	
	public MemberVO confirmNamePhone(String name, String phone) {
		String sql = "select * from member where name=? and phone=?";
		List<MemberVO> list = null;
		list = template.query( sql, new RowMapper<MemberVO>() {
			@Override
			public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberVO mvo = new MemberVO();
				mvo.setId(rs.getString("id"));
				mvo.setName(rs.getString("name"));
				mvo.setPhone(rs.getString("phone"));
				return mvo;
			}
		}, name, phone);
		
		if(list.size()==0) return null;
		else return list.get(0);
	}
	
	
	
	public void updateMember(MemberVO mvo) {
		String sql = "Update member set pwd=?, name=?, zip_num=?, address=?, "
				+ "email=?, phone=? where id = ?";
		template.update(sql, mvo.getPwd(), mvo.getName(), mvo.getZip_num(), 
				mvo.getAddress(), mvo.getEmail(), mvo.getPhone(), mvo.getId() 
		);
	}
	
	
	public void insertMember(MemberVO mvo) {
		String sql = "insert into member(id, pwd, name, zip_num,"
                + " address, email, phone) values(?, ?, ?, ?, ?, ?, ?)";
		template.update(sql, mvo.getId(), mvo.getPwd(), mvo.getName(), 
				mvo.getZip_num(), mvo.getAddress(), mvo.getEmail(), mvo.getPhone()
		);
	}
	
	
	
	public List<AddressVO> slelectAddressByDong(String dong){
		String sql = "select * from address where dong like '%'||?||'%'";
		List<AddressVO> list = null;
		list = template.query(sql, new RowMapper<AddressVO>(){
			@Override
			public AddressVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				AddressVO avo = new AddressVO();
				avo.setSido(rs.getString("sido"));
				avo.setGugun(rs.getString("gugun"));
				avo.setDong(rs.getString("dong"));
				avo.setBunji(rs.getString("bunji"));
				avo.setZip_code(rs.getString("zip_code"));
				avo.setZip_num(rs.getString("zip_num"));
				return avo;
			}
		} ,  dong );
		return list;
	}
	
	
	
	public int confirmID(String id) {
		int result ;
		String sql = "select * from member where id=?";
		List<MemberVO> list = null;
		//list = template.query(sql, new RowMapper<MemberVO>(){});
		list = template.query(sql, new RowMapper<MemberVO>(){
			@Override
			public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberVO mvo = new MemberVO();
				mvo.setId(rs.getString("id"));
				mvo.setPwd(rs.getString("pwd"));
				return mvo;
			}
		}, id );
		if(list.size() == 0 ) result = 1;  // ?????? ??????
		else result = -1;   // ?????? ?????????
		return result;
	}
	
	
	
	public MemberVO getMember(String id) {
		String sql = "Select * from member where id=?";
		List<MemberVO> list = null;
		list = template.query(sql, new RowMapper<MemberVO>(){
			@Override
			public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberVO mvo = new MemberVO();
				mvo.setId(rs.getString("id"));
				mvo.setPwd(rs.getString("pwd"));
		        mvo.setName(rs.getString("name"));
		        mvo.setEmail(rs.getString("email"));
		        mvo.setZip_num(rs.getString("zip_num"));
		        mvo.setAddress(rs.getString("address"));
		        mvo.setPhone(rs.getString("phone"));
		        mvo.setUseyn(rs.getString("useyn"));
		        mvo.setIndate(rs.getTimestamp("indate"));
				return mvo;
			}
		}, id );
		
		if( list.size()==0) return null;
		else return list.get(0);
	}
}





