package com.ezen.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.shop.dao.AdminDao;
import com.ezen.shop.dto.MemberVO;
import com.ezen.shop.dto.OrderVO;
import com.ezen.shop.dto.Paging;
import com.ezen.shop.dto.ProductVO;
import com.ezen.shop.dto.QnaVO;

@Service
public class AdminService {

	@Autowired
	AdminDao adao;
	
	public int workerCheck(String workId, String workPwd) {
		int result = adao.workerCheck(workId, workPwd); 
		return result;
	}
	
	
	public void updateQna(QnaVO qvo) {
		adao.updateQna(qvo);
	}
	
	
	
	public void  updateOrderResult(String odseq) {
		adao.updateOrderResult(odseq);
	}
	
	
	public void updateProduct(ProductVO pvo) {
		adao.updateProduct(pvo);
	}
	
	
	public void insertProduct(ProductVO pvo) {
		adao.insertProduct(pvo);
	}
	
	public List<QnaVO> listQna(Paging paging, String key){
		List<QnaVO> list = adao.listQna(paging, key);
		return list;
	}
	
	public List<MemberVO> listMember(Paging paging, String key){
		List<MemberVO> list = adao.listMember(paging, key);
		return list;
	}
	
	public List<ProductVO> listProduct(Paging paging, String key){
		List<ProductVO> list = adao.listProduct(paging, key);
		return list;
	}
	
	public List<OrderVO> listOrder(Paging paging, String key){
		List<OrderVO> list = adao.listOrder(paging, key);
		return list;
	}
	
	public int getAllCount(String tablename, String fieldname, String key) {
		int count = adao.getAllCount(tablename, fieldname, key);
		return count;
	}
	
	
	
}
