package com.ezen.spboard.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spboard.dao.BoardDao;
import com.ezen.spboard.dto.Paging;
import com.ezen.spboard.dto.ReplyVO;
import com.ezen.spboard.dto.SpBoard;

@Service
public class BoardService {

	@Autowired
	BoardDao bdao;
	
	
	public void addReply(ReplyVO rvo) {
		bdao.addReply(rvo);
	}
	
	
	public void deleteReply(String num) {
		bdao.deleteReply(num) ;
	}
	
	
	
	
	public void boardDeleate(String num) {
		bdao.boardDelete(num);
	}
	
	
	public void boardUpdate(SpBoard svo) {
		bdao.boardUpdate(svo);
	}
	
	
	
	
	public SpBoard getBoard_edit(String num) {
		return bdao.getBoard(num);
	}
	
	
	
	
	public void boardInsert(SpBoard sb) {
		bdao.boardInsert(sb);
	}
	
	
	
	public ArrayList<ReplyVO> selectReply(String num){
		ArrayList<ReplyVO> list = bdao.selectReply(num);
		return list;
	}
	
	
	public SpBoard boardView(String num) {
		SpBoard sb = null;
		bdao.increaseReadcount(num);  // 게시물의 조회수를 +1 하는 메서드 호출
		sb = bdao.getBoard(num);   // 게시물의 내용을 조회후 SpBoard 형태로 리턴하는 메서드 호출
		return sb;
	}
	
	public int getAllCount() {
		int count = bdao.getAllCount();
		return count;
	}
	
	
	public ArrayList<SpBoard> selectBoardAll(Paging paging){
		ArrayList<SpBoard> list = null;
		list = bdao.selectBoardAll(paging);
		return list;
	}
}
