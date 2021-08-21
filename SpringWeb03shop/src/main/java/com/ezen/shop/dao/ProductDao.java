package com.ezen.shop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ezen.shop.dto.ProductVO;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class ProductDao {

	//기존 데이터 베이스 연결 방법
	//@Autowired
	//DBManager dbm;
	//dbm.getConnection();
	//dbm.close(con, pstmt, rs);
	
	//ComboPooledDataSource(스프링 컨테이너에 수동으로 넣어놓은 클래스) 에서 연결한 
	// Connection 을 JdbcTemplate 에 전달하고, JdbcTemplate 를 이용해서 
	// 데이터베이스의 CRUD 를 실행합니다
	
	private JdbcTemplate template;
	
	// 태플릿의 초기화
	@Autowired
	public ProductDao(ComboPooledDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	
	
	
	
	public ProductVO getProduct(String pseq) {
		String sql = "Select * from product where pseq=?";
		List<ProductVO> list = null;
		list = template.query(sql, 	new RowMapper<ProductVO>() {
			@Override
			public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProductVO pvo = new ProductVO();  	pvo.setPseq(rs.getInt("pseq"));
	    	   	pvo.setIndate(rs.getTimestamp("indate"));  	pvo.setName(rs.getString("name"));
	    	   	pvo.setPrice1(rs.getInt("price1"));   	pvo.setPrice2(rs.getInt("price2"));
	    	   	pvo.setKind(rs.getString("kind"));    	pvo.setImage(rs.getString("image"));
	    	   	pvo.setUseyn(rs.getString("useyn"));     pvo.setBestyn(rs.getString("bestyn"));
	    	   	pvo.setContent(rs.getString("content"));
				return pvo;
			}
		}, pseq);
		return list.get(0);
	}
	
	
	
	
	
	
	public List<ProductVO> getKindList(String kind){
		List<ProductVO> list = null;
		String sql = "Select * from product where kind=?";
		list = template.query(sql, new RowMapper<ProductVO>(){
			@Override
			public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProductVO pvo = new ProductVO();
    	    	pvo.setPseq(rs.getInt("pseq"));
    	    	pvo.setIndate(rs.getTimestamp("indate"));
    	    	pvo.setName(rs.getString("name"));
    	    	pvo.setPrice1(rs.getInt("price1"));
    	    	pvo.setPrice2(rs.getInt("price2"));
    	    	pvo.setImage(rs.getString("image"));
    	    	pvo.setUseyn(rs.getString("useyn"));
    	        pvo.setBestyn(rs.getString("bestyn"));
				return pvo;
			}
		} , kind);
		return list;
	}
	
	
	
	
	
	
	
	public List<ProductVO> getNewList(){
		List<ProductVO> list = null;
		String sql = "select * from new_pro_view";
		// list = template(첫번째 요소 : sql , 두번째 요소 : 생성자 매서드 new RowMapper<>(){} );
		list = template.query(sql, new RowMapper<ProductVO>(){
			@Override
			public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				//rs 에 검색된 결과중 순서데로 하나식 레코드를 리턴합니다
				ProductVO pvo = new ProductVO();
				pvo.setPseq(rs.getInt("pseq"));	    	    	
    	    	pvo.setName(rs.getString("name"));
    	    	pvo.setPrice2(rs.getInt("price2"));
    	    	pvo.setImage(rs.getString("image"));
				return pvo;  // list 로 리턴되어 add 됩니다.
			}
		});
		return list;
	}
	
	public List<ProductVO> getBestList(){
		List<ProductVO> list = null;
		String sql = "select * from best_pro_view";
		list = template.query(sql, new RowMapper<ProductVO>() {
			@Override
			public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProductVO pvo = new ProductVO();
				pvo.setPseq(rs.getInt("pseq"));	    	    	
    	    	pvo.setName(rs.getString("name"));
    	    	pvo.setPrice2(rs.getInt("price2"));
    	    	pvo.setImage(rs.getString("image"));
				return pvo;
			}	
		});
		return list;
	}
}
