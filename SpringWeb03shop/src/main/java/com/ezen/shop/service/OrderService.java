package com.ezen.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.shop.dao.OrderDao;
import com.ezen.shop.dto.CartVO;
import com.ezen.shop.dto.OrderVO;

@Service
public class OrderService {

	@Autowired
	OrderDao odao;
	
	public List<Integer> selectSeqOrderIng(String id){
		List<Integer> list = odao.selectSeqOrderIng(id);
		return list;
	}
	public List<Integer> oseqListAll(String id){
		List<Integer> list = odao.oseqListAll(id);
		return list;
	}
	
	
	
	public List<OrderVO> listOrderById(String id, String result, int oseq){
		List<OrderVO> list =  odao.listOrderById(id, result, oseq);
		return list;
	}
	
	
	public int insertOrder(List<CartVO> cartList, String id) {
		int oseq = 0;	
		odao.insertOders(id);
		oseq = odao.LookupMaxOseq();
		for( CartVO cvo : cartList) {
			odao.insertOderDetail(cvo, oseq);
			odao.updateCart(cvo.getCseq());
		}
		return oseq;
	}
}
