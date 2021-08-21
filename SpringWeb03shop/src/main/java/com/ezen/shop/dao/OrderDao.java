package com.ezen.shop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ezen.shop.dto.CartVO;
import com.ezen.shop.dto.OrderVO;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class OrderDao {

	private JdbcTemplate template;
	
	@Autowired
	public OrderDao(ComboPooledDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	
	
	public List<Integer> oseqListAll(String id){
		List<Integer> list = null;
		String sql = "select distinct oseq from order_view "
				+ "where id=? order by oseq desc";
		list = template.query(sql, new RowMapper<Integer>(){
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				int oseq = 0;
				oseq = rs.getInt("oseq");
				return oseq;
			}
		}, id);
		return list;
	}
	public List<Integer> selectSeqOrderIng(String id){
		List<Integer> list = null;
		String sql = "select distinct oseq from order_view "
				+ "where id=? and result='1' order by oseq desc";
		list = template.query(sql, new RowMapper<Integer>(){
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				int oseq = 0;
				oseq = rs.getInt("oseq");
				return oseq;
			}
		}, id);
		return list;
	}
	
	
	
	
	
	
	public List<OrderVO> listOrderById(String id, String result, int oseq){
		List<OrderVO> list = null;
		String sql = "select * from order_view where id=? "
				+ "and result like '%'||?||'%' and oseq=?";
		list = template.query(sql, new RowMapper<OrderVO>(){
			@Override
			public OrderVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				OrderVO ovo = new OrderVO();
				ovo.setOdseq(rs.getInt("odseq")); 	ovo.setOseq(rs.getInt("oseq"));
				ovo.setId(rs.getString("id")); ovo.setIndate(rs.getTimestamp("indate"));
				ovo.setMname(rs.getString("mname")); ovo.setZipnum(rs.getString("zip_num"));
				ovo.setAddress(rs.getString("address")); ovo.setPhone(rs.getString("phone"));
				ovo.setPseq(rs.getInt("pseq")); 	ovo.setQuantity(rs.getInt("quantity"));
				ovo.setPname(rs.getString("pname")); 	ovo.setPrice2(rs.getInt("price2"));
				ovo.setResult(rs.getString("result"));
				return ovo;
			}
		}, id, result, oseq );
		return list;
	}
	
	
	
	
	
	
	
	public void updateCart(int cseq) {
		String sql = "update cart set result=2 where cseq=?";
		template.update(sql, cseq);
	}
	public void insertOderDetail(CartVO cvo, int oseq) {
		String sql = "insert into order_detail(odseq, oseq, "
				+ "pseq, quantity) values(order_detail_seq.nextval, ?, ?, ?)";
		template.update(sql, oseq, cvo.getPseq(), cvo.getQuantity() );
	}
	public int LookupMaxOseq() {
		String sql = "select max(oseq) as moseq from orders";
		List<Integer> list = null;
		list = template.query(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				int maxoseq = rs.getInt("moseq");
				return maxoseq;
			}
		});
		return list.get(0);
	}
	public void insertOders(String id) {
		String sql = "insert into orders(oseq, id) values(orders_seq.nextVal, ?)";
		int result = template.update(sql, id);
	}
	
}
