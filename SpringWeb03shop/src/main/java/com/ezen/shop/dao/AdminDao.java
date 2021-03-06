package com.ezen.shop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ezen.shop.dto.MemberVO;
import com.ezen.shop.dto.OrderVO;
import com.ezen.shop.dto.Paging;
import com.ezen.shop.dto.ProductVO;
import com.ezen.shop.dto.QnaVO;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class AdminDao {

	private JdbcTemplate template;

	@Autowired
	public AdminDao(ComboPooledDataSource dataSource) {
		template = new JdbcTemplate();
		template.setDataSource(dataSource);
	}
	
		
	public void updateQna(QnaVO qvo) {
		String sql = "update qna set reply=?, rep='2' where qseq=?";
		int result = template.update(sql, qvo.getReply(), qvo.getQseq());
	}
	
	
	
	
	public void  updateOrderResult(String odseq) {
		String sql = "Update order_detail set result='2' where odseq=?";
		int result = template.update(sql, odseq);
	}
	
	
	
	public void updateProduct(ProductVO pvo) {
		String sql = "update product set kind=?, useyn=?, name=?" +
				", price1=?, price2=?, price3=?, content=?, image=?, " + 
				"bestyn=? where pseq=?";
		
		int result = template.update(sql, pvo.getKind(), pvo.getUseyn(),
		pvo.getName(), pvo.getPrice1(), pvo.getPrice2(), pvo.getPrice3(),
		pvo.getContent(), pvo.getImage(), pvo.getBestyn(), pvo.getPseq()
		);
	}
	
	
	
	
	public void insertProduct(ProductVO pvo) {
		String sql = "insert into product (" +
		        "pseq, kind, name, price1, price2, price3, content, image) " +
		        "values(product_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
		template.update(sql, pvo.getKind(), pvo.getName(), pvo.getPrice1(),
				pvo.getPrice2(), pvo.getPrice3(), pvo.getContent(), pvo.getImage());
	}
	
	
	public List<QnaVO> listQna(Paging paging, String key){
		String sql = "select * from (" 
				+ "select * from ( "  
				+ "select rownum as rn, m.* from "
				+ "((select * from qna where subject like '%'||?||'%' order by indate desc) m)"
				+ ") where rn >= ? " 
				+ ") where rn <= ?"; 
		int startNum = paging.getStartNum();
	    int endNum = paging.getEndNum();
	    List<QnaVO> list = template.query(sql, new RowMapper<QnaVO>() {
			@Override
			public QnaVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				QnaVO qvo = new QnaVO();
		    	qvo.setQseq(rs.getInt("qseq"));   	qvo.setSubject(rs.getString("subject"));
		    	qvo.setContent(rs.getString("content"));   	qvo.setId(rs.getString("id"));
		    	qvo.setIndate(rs.getTimestamp("indate")); 	qvo.setReply(rs.getString("reply"));
		    	qvo.setRep(rs.getString("rep"));	
				return qvo;
			}
	    }, key, startNum, endNum);
		return list;
	}
	
	
	
	public List<MemberVO> listMember(Paging paging, String key){
		String sql = "select * from (" 
				+ "select * from ( "  
				+ "select rownum as rn, m.* from "
				+ "((select * from member where name like '%'||?||'%' order by indate desc) m)"
				+ ") where rn >= ? " 
				+ ") where rn <= ?"; 
		int startNum = paging.getStartNum();
	    int endNum = paging.getEndNum();
	    List<MemberVO> list = template.query(sql, new RowMapper<MemberVO>() {
			@Override
			public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberVO mvo = new MemberVO();
				mvo.setId(rs.getString("id"));     		mvo.setPwd(rs.getString("pwd"));
				mvo.setName(rs.getString("name"));     	mvo.setEmail(rs.getString("email"));
				mvo.setZip_num(rs.getString("zip_num"));  mvo.setAddress(rs.getString("address"));
				mvo.setPhone(rs.getString("phone"));   mvo.setUseyn(rs.getString("useyn"));
				mvo.setIndate(rs.getTimestamp("indate"));
				return mvo;
			}
	    }, key, startNum, endNum);
		return list;
	}
	
	
	
	
	public List<OrderVO> listOrder(Paging paging, String key){
		String sql = "select * from (" 
				+ "select * from ( "  
				+ "select rownum as rn, o.* from "
				+ "((select * from order_view where mname like '%'||?||'%'  "
				+ " order by result, odseq desc) o)"
				+ ") where rn >= ? " 
				+ ") where rn <= ?"; 
		int startNum = paging.getStartNum();
	    int endNum = paging.getEndNum();
	    List<OrderVO> list = template.query(sql, new RowMapper<OrderVO>() {
			@Override
			public OrderVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				OrderVO ovo = new OrderVO();
				ovo.setOdseq(rs.getInt("odseq")); 				ovo.setOseq(rs.getInt("oseq"));
				ovo.setId(rs.getString("id"));				ovo.setPseq(rs.getInt("pseq"));
				ovo.setMname(rs.getString("mname"));	ovo.setPname(rs.getString("pname"));
				ovo.setQuantity(rs.getInt("quantity")); ovo.setZipnum(rs.getString("zip_num"));
				ovo.setAddress(rs.getString("address"));		ovo.setPhone(rs.getString("phone"));
				ovo.setIndate(rs.getTimestamp("indate")); ovo.setPrice2(rs.getInt("Price2"));
				ovo.setResult(rs.getString("result"));
				return ovo;				
			}
	    }, key, startNum, endNum);
		return list;
	}
	
	
	
	
	
	public List<ProductVO> listProduct(Paging paging, String key){
		String sql = "select * from (" 
				+ "select * from ( "  
				+ "select rownum as rn, p.* from "
				+ "((select * from product where name like '%'||?||'%' order by pseq desc) p)"
				+ ") where rn >= ? " 
				+ ") where rn <= ?"; 
		int startNum = paging.getStartNum();
	    int endNum = paging.getEndNum();
	    List<ProductVO> list = template.query(sql, new RowMapper<ProductVO>() {
			@Override
			public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProductVO pvo = new ProductVO();
    	    	pvo.setPseq(rs.getInt("pseq"));      	pvo.setIndate(rs.getTimestamp("indate"));
    	    	pvo.setName(rs.getString("name"));   	    	pvo.setPrice1(rs.getInt("price1"));
    	    	pvo.setPrice2(rs.getInt("price2"));   	    	pvo.setPrice3(rs.getInt("price3"));
    	    	pvo.setImage(rs.getString("image"));   	    	pvo.setUseyn(rs.getString("useyn"));
    	        pvo.setBestyn(rs.getString("bestyn"));
				return pvo;
			}
	    }, key, startNum, endNum);
		return list;
	}
	
	
	
	
	
	
	public int getAllCount(String tablename, String fieldname, String key) {
		String sql = "select count(*) as count from " + tablename
				+ " where " + fieldname + " like '%'||?||'%'";
		List<Integer> list = template.query(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				int count = rs.getInt("count");
				return count;
			}
		}, key);
		return list.get(0);
	}
	
	
	
	
	public int workerCheck(String workId, String workPwd) {
		int result = 0;
		String sql = "select pwd from worker where id=?";
		List<String> list = null;
		list = template.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				String pwd = rs.getString("pwd");
				return pwd;
			}
		}, workId);
		// ???????????? ????????? -1, ????????? ????????? 1, ????????? ???????????? 0 ?????? result ?????? ???????????????
		if( list.size() == 0) result = -1;
		else if(workPwd.equals(list.get(0))) result = 1;
		else result = 0;
		
		return result;
	}
}






