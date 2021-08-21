package com.ezen.spboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.spboard.dto.SpMember;
import com.ezen.spboard.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService ms;
	

	
	
	
	@RequestMapping("/logout")
	public String mem_logout(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		//return "loginform";
		return "redirect:/";
	}
	
	
	
	@RequestMapping(value="/memberEdit", method=RequestMethod.POST)
	public String mem_edit(Model model, HttpServletRequest request) {
		
		// Spmember 에 전달인수를 모두 담고
		SpMember sm = new SpMember();
		sm.setId(request.getParameter("id"));
		sm.setPw(request.getParameter("pw"));
		sm.setName(request.getParameter("name"));
		sm.setPhone1(request.getParameter("phone1"));
		sm.setPhone2(request.getParameter("phone2"));
		sm.setPhone3(request.getParameter("phone3"));
		sm.setEmail(request.getParameter("email"));
		
		// MemberService 의 memberModify 메서드를 호출 
		// (MemberService 에서는 MemberDao 의 memberUpdate 호출
		int result = ms.memberModify(sm);
		
		// 세션에 새로운 로그인정보를 다시 저장
		HttpSession session = request.getSession();
		if(result==1) session.setAttribute("loginUser", sm);
		// redirect:/main 로 리턴
		return "redirect:/main";
	}	
	
	
	
	@RequestMapping("/memberEditForm")
	public String mem_edit_form(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if( session.getAttribute("loginUser") == null) return "loginform";
		return "memberEditForm";
	}
	
	
	
	
	@RequestMapping(value="/memberJoin", method=RequestMethod.POST)
	public String memJoin(Model model, HttpServletRequest request) {
		
		SpMember sm = new SpMember();
		sm.setId(request.getParameter("id"));
		sm.setPw(request.getParameter("pw"));
		sm.setName(request.getParameter("name"));
		sm.setPhone1(request.getParameter("phone1"));
		sm.setPhone2(request.getParameter("phone2"));
		sm.setPhone3(request.getParameter("phone3"));
		sm.setEmail(request.getParameter("email"));
		
		ms.memberRegister(sm);
		
		return "loginform";
	}
	
	
	
	
	@RequestMapping("/idcheck")
	public String idcheck(Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
		int result = ms.confirmID(id);
		model.addAttribute("result", result);
		model.addAttribute("id", id);
		return "idcheck";
	}	
	
	
	
	
	@RequestMapping("/memberJoinForm")
	public String join_form(Model model, HttpServletRequest request) {
		return "memberJoinForm";
	}
	
	
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(Model model, HttpServletRequest request) {
		// 최종 목적지는  loginform.jsp 이되, 로그인 성공하면 main.jsp 로 url 이 수정 될 예정입니다
		String url = "loginform";   
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		SpMember sdto = ms.getMember(id);
		if(sdto != null) {
			if( sdto.getPw().equals(pw) ) {
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", sdto);
				url = "redirect:/main";  // main.jsp 로 안가고  Request:"main" 을 찾아 갑니다
			}else {
				model.addAttribute("message", "비밀번호를 확인하세요");
			}
		}else {
			model.addAttribute("message", "id를 확인하세요");
		}
		return url;
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		if( session.getAttribute("loginUser") == null)	
			return "loginform";
		else 
			return "main";
	}
}
