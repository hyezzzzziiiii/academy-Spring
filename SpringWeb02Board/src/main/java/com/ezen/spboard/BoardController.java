package com.ezen.spboard;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.spboard.dto.Paging;
import com.ezen.spboard.dto.ReplyVO;
import com.ezen.spboard.dto.SpBoard;
import com.ezen.spboard.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	BoardService bs;
	
	
	@RequestMapping("/addReply")
	public String add_reply(Model model, HttpServletRequest request) {
		String boardnum = request.getParameter("boardnum");
		ReplyVO rvo = new ReplyVO();
		
		rvo.setUserid(request.getParameter("userid"));
		rvo.setContent(request.getParameter("reply"));
		rvo.setBoardnum( Integer.parseInt(boardnum) );
		
		bs.addReply(rvo);
		
		return "redirect:/boardView?num=" + boardnum;
	}
	
	@RequestMapping("/deleteReply")
	public String reply_delete(Model model, HttpServletRequest request) {
		String num = request.getParameter("num");
		String boardnum = request.getParameter("boardnum");
		
		bs.deleteReply(num);
		
		return "redirect:/boardView?num=" + boardnum;
	}
	
	
	@RequestMapping("/boardDelete")
	public String board_delete(Model model, HttpServletRequest request) {
		String num = request.getParameter("num");
		bs.boardDeleate(num);
		return "redirect:/main";
	}
	
	
	
	
	@RequestMapping(value="boardUpdate",method = RequestMethod.POST)
	public String board_update(Model model, HttpServletRequest request) {
		SpBoard sb = new SpBoard();
		int num = Integer.parseInt(request.getParameter("num"));
		sb.setNum(num);
		// 파라미터를 Dto 에 저장
		sb.setPass(request.getParameter("pass"));
		sb.setUserid(request.getParameter("userid"));
		sb.setEmail(request.getParameter("email"));
		sb.setTitle(request.getParameter("title"));
		sb.setContent(request.getParameter("content"));
		// Dto를 전송하여 게시물 수정
		bs.boardUpdate(sb);
		// 보고 있던 게시물 번호를 이용해서  boardView 로 이동
		return "redirect:/boardView?num=" + num;
	}
	
	
	
	@RequestMapping("boardDeleteForm")
	public String board_delete_form(Model model, HttpServletRequest request) {
		String num = request.getParameter("num");
		SpBoard sb = bs.getBoard_edit(num);
		model.addAttribute("num", num);
		model.addAttribute("board", sb);
		return "boardCheckPassForm";
	}
	
	
	
	
	
	@RequestMapping("boardUpdateForm")
	public String board_update_form(Model model, HttpServletRequest request) {
		String num = request.getParameter("num");
		model.addAttribute("num", num);
		return "boardCheckPassForm";
	}
	
	
	
	@RequestMapping("/boardEdit")
	public String board_edit(Model model, HttpServletRequest request) {
		String num = request.getParameter("num");
		String pass = request.getParameter("pass");
		SpBoard sb = bs.getBoard_edit(num);
		model.addAttribute("num", num);
		if(pass.equals(sb.getPass()) ) return "boardCheckPass";
		else {
			model.addAttribute("message", "비밀번호가 맞지 않습니다. 확인해주세요");
			return "boardCheckPassForm";
		}
	}
	
	@RequestMapping("/boardEditForm")
	public String board_edit_form(Model model, HttpServletRequest request) {
		String num = request.getParameter("num");
		model.addAttribute("num", num);
		return "boardCheckPassForm";
	}
	
	
	
	
	
	
	@RequestMapping(value="boardWrite", method = RequestMethod.POST)
	public String board_write(Model model, HttpServletRequest request) {
		
		// 전송된 파라미터들을 dto 에 저장
		SpBoard sb = new SpBoard();
		sb.setPass(request.getParameter("pw"));
		sb.setUserid(request.getParameter("userid"));
		sb.setEmail(request.getParameter("email"));
		sb.setTitle(request.getParameter("title"));
		sb.setContent(request.getParameter("content"));
		
		// bs.boardInsert()  실행 -> Dao의 메서드 : bdao.boardInsert() 
		bs.boardInsert(sb);
		
		// main 으로 이동
		return "redirect:/main";
	}
	
	
	
	
	
	@RequestMapping("/boardWriteForm")
	public String write_form(Model model, HttpServletRequest request) {
		String url = "boardWriteForm";
		HttpSession session = request.getSession();
		if( session.getAttribute("loginUser") == null)	url="loginform";		
		return url;
	}
	
	
	
	
	
	
	@RequestMapping("/boardView")
	public String boardView( Model model, HttpServletRequest request ) {
		String num = request.getParameter("num");
		SpBoard sb = bs.boardView(num);
		
		ArrayList<ReplyVO> list = bs.selectReply(num);
		
		model.addAttribute("board", sb);
		model.addAttribute("replyList", list);
		return "boardView";
	}
	
	
	@RequestMapping("/main")
	public String main(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int page = 1;  // 기본페이지 1페이지 설정
		if( session.getAttribute("loginUser") == null)	
			return "loginform";
		else {			
			// 세션과 파라미터를 이용하여 방금 선택한 페이지나 보던 페이지를 설정
			if( request.getParameter("page") != null ) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			} else if( session.getAttribute("page")!= null ) {
				page = (int) session.getAttribute("page");
			} else {
				page = 1;
				session.removeAttribute("page");
			}
			Paging paging = new Paging();
			paging.setPage(page);
			int count = bs.getAllCount();  // 총 레코드 갯수 계산
			paging.setTotalCount(count);  // 페이징 객체에 적용
			ArrayList<SpBoard> list = bs.selectBoardAll( paging ); // 표시할 페이지의 게시물(10개) 검색
			model.addAttribute("paging", paging); 
			model.addAttribute("boardList", list);
		}
		return "main";
	}
}
