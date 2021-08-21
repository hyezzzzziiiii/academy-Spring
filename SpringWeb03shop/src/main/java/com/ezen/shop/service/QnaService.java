package com.ezen.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.shop.dao.QnaDao;
import com.ezen.shop.dto.QnaVO;

@Service
public class QnaService {

	@Autowired
	QnaDao qdao;
	
	
	public void insertQna(QnaVO qvo, String id) {
		qdao.insertQna(qvo, id);
	}
	
	
	
	public QnaVO getQna(int qseq) {
		QnaVO qvo = qdao.getQna(qseq);
		return qvo;
	}
	
	
	
	public List<QnaVO> listQna(String id){
		List<QnaVO> list = qdao.listQna(id);
		return list;
	}
}
