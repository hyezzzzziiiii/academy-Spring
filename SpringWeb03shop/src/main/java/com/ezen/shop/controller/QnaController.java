package com.ezen.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.shop.dto.MemberVO;
import com.ezen.shop.dto.QnaVO;
import com.ezen.shop.service.QnaService;

@Controller
public class QnaController {

	@Autowired
	QnaService qs;
	
	
	@RequestMapping("qnaWrite")
	public ModelAndView qna_write(Model model ,  HttpServletRequest request,
			@RequestParam("subject") String subject, @RequestParam("content") String content) {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		ModelAndView mav = new ModelAndView();
	    if (mvo == null) mav.setViewName("member/login");
	    else {
	    	QnaVO qvo = new QnaVO();
	    	qvo.setSubject(request.getParameter("subject"));
	    	qvo.setContent(request.getParameter("content"));
	    	qs.insertQna(qvo, mvo.getId());
	    }
		mav.setViewName("redirect:/qnaList");
		return mav;
	}
	
	
	
	
	
	
	@RequestMapping("qnaWriteForm")
	public String qna_writre_form(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
	    MemberVO mvo= (MemberVO) session.getAttribute("loginUser");    	    
	    if (mvo == null) return "member/login";
	    return "qna/qnaWrite";
	}
	
	
	
	
	
	
	@RequestMapping("qnaView")
	public ModelAndView qna_view(Model model, HttpServletRequest request,
			@RequestParam("qseq") int qseq) {
		HttpSession session = request.getSession();
	    MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
	    ModelAndView mav = new ModelAndView();
		if (mvo == null) mav.setViewName("member/login");
		else {
			QnaVO qvo = qs.getQna(qseq);
			mav.addObject("qnaVO", qvo);
			mav.setViewName("qna/qnaView");
		}
		return mav;
	}
	
	
	
	
	@RequestMapping("qnaList")
	public ModelAndView qna_list(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
	    MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
	    ModelAndView mav = new ModelAndView();
	    if (mvo == null) mav.setViewName("member/login");
	    else {
	    	List<QnaVO> list = qs.listQna(mvo.getId());
	    	mav.addObject("qnaList", list);
	    	mav.setViewName("qna/qnaList");
	    }
		return mav;
	}
}
