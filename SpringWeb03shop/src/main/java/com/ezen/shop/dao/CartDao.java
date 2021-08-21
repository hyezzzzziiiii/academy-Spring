package com.ezen.shop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ezen.shop.dto.CartVO;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class CartDao {

	private JdbcTemplate template;

	@Autowired
	public CartDao(ComboPooledDataSource dataSource) {
		template = new JdbcTemplate();
		template.setDataSource(dataSource);
	}
	
	
	
	public void deleteCart(String cseq) {
		String sql = "delete from cart where cseq=?";
		int result = template.update(sql, cseq);
	}
	
	
	
	public List<CartVO> listCart(String id){
		List<CartVO> list = null;
		String sql = "select * from cart_view where id=? and result='1' order by cseq desc";
		list = template.query(sql,	new RowMapper<CartVO>() {
				@Override
				public CartVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					CartVO cvo = new CartVO();				
					cvo.setCseq(rs.getInt(1));     cvo.setId(rs.getString(2));
					cvo.setMname(rs.getString(3));     cvo.setPseq(rs.getInt(4));
					cvo.setPname(rs.getString(5));     	cvo.setQuantity(rs.getInt(6));
					cvo.setPrice2(rs.getInt(7));     cvo.setResult(rs.getString(8));    
					cvo.setIndate(rs.getTimestamp(9));
					return cvo;
				}				
		}, id);
		return list;
	}
	
	
	
	
	
	
	
	
	public void insertCart(CartVO cvo) {
		String sql = "Insert into cart(cseq, id, pseq, quantity)"
				+ " values(cart_seq.nextval, ?, ? , ?)";
		int result = template.update(sql, cvo.getId(), cvo.getPseq(), cvo.getQuantity() );
	}
}

