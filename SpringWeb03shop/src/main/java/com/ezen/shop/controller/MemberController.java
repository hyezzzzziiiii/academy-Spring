package com.ezen.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.shop.dao.MemberDao;
import com.ezen.shop.dto.AddressVO;
import com.ezen.shop.dto.MemberVO;
import com.ezen.shop.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService ms;
	
	
	@RequestMapping("resetPw")
	public String certificationNum(Model model, HttpServletRequest request,
			@RequestParam("id") String id, @RequestParam("pwd") String pwd ) {
		MemberVO mvo = new MemberVO();
		mvo.setId( id);
		mvo.setPwd( pwd );
		ms.resetPw(mvo);
		return "member/resetPwComplete";
	}
	
	
	
	
	
	// 아이찾기 인증번호 확인
	@RequestMapping("findIdCertificationNum")
	public String certificationNum(Model model, HttpServletRequest request,
			@RequestParam("name") String name, @RequestParam("phone") String phone,
			@RequestParam("id") String id, @RequestParam("inputNum") String inputNum) {
		
		MemberVO mvo = new MemberVO();
		mvo.setId(id);
		mvo.setName(name);
		mvo.setPhone(phone);
		if(inputNum.equals("0000")) {
			model.addAttribute("member", mvo);
			return "member/viewId";
		}else {
			return "member/findId_CertificationNumber";
		}
	}
	
	// 비밀번호 찾기 인증번호 확인
	@RequestMapping("findPwCertificationNum")
	public String findPwCertificationNum(Model model, HttpServletRequest request,
			@RequestParam("name") String name, @RequestParam("phone") String phone,
			@RequestParam("id") String id, @RequestParam("inputNum") String inputNum) {
		
		MemberVO mvo = new MemberVO();
		mvo.setId(id);
		mvo.setName(name);
		mvo.setPhone(phone);
		model.addAttribute("member", mvo);
		if(inputNum.equals("0000")) 
			return "member/resetPassword";
		else 
			return "member/findPw_CertificationNumber";
	}
	
	//아이디 찾기_이름 전화번호 검색
	@RequestMapping("lookupNamePhone")
	public String lookupNamePhone(Model model, HttpServletRequest request,
			@RequestParam("name") String name, @RequestParam("phone") String phone ) {
		MemberVO mvo = ms.confirmNamePhone(name, phone);
		if(mvo==null) {
			model.addAttribute("msg", "이름과 전화번호가 일치하는 회원이 없습니다");
			model.addAttribute("name", name);
			model.addAttribute("phone", phone);
			return "member/findIdForm";
		}else {
			model.addAttribute("member", mvo);
			return "member/findId_CertificationNumber";
		}
		
	}
	//비밀번호 찾기_아이디, 이름 전화번호 검색
	@RequestMapping("lookupIdNamePhone")
	public String lookupIdNamePhone(Model model, HttpServletRequest request,
			@RequestParam("name") String name, @RequestParam("phone") String phone,
			@RequestParam("id") String id) {
		MemberVO mvo = ms.confirmIdNamePhone(id, name, phone);
		if(mvo==null) {
			model.addAttribute("msg", "이름과 전화번호가 일치하는 회원이 없습니다");
			model.addAttribute("id", id);
			model.addAttribute("name", name);
			model.addAttribute("phone", phone);
			return "member/findIdForm";
		}else {
			model.addAttribute("member", mvo);
			return "member/findPw_CertificationNumber";
		}
	}
	
	// 아이디 찾기_이름, 전화번호 입력창으로 이동
	@RequestMapping("findIdForm")
	public String find_id_form(Model model, HttpServletRequest request) {
		return "member/findIdForm";
	}
	
	// 비번 찾기_아이디, 이름, 전화번호 입력창으로 이동
	@RequestMapping("findPwForm")
	public String findPwForm(Model model, HttpServletRequest request) {
		return "member/findPwForm";
	}
	
	
	
	
	
	// 팝업창 최초
	@RequestMapping("findIdPw")
	public String find_id_pw(Model model, HttpServletRequest request) {
		return "member/findIdPwForm";
	}
	
	
	
	
	
	@RequestMapping(value = "memberUpdate", method = RequestMethod.POST)
	public String member_Update(Model model, HttpServletRequest request) {
		// 파라미터들을 모두 MemberVO 객체에 저장합니다
		MemberVO mvo = new MemberVO();
		mvo.setId(request.getParameter("id"));
		mvo.setPwd(request.getParameter("pwd"));
		mvo.setName(request.getParameter("name"));
		mvo.setEmail(request.getParameter("email"));
		mvo.setZip_num(request.getParameter("zip_num"));
		mvo.setAddress(request.getParameter("addr1") + " " +
														request.getParameter("addr2"));
		mvo.setPhone(request.getParameter("phone"));
		// ms.memberUpdate(mvo) 를 호출합니다 
		ms.memberUpdate(mvo);
		// and ~ mdao.memberUpdate(mvo)를 mdao 에서 호출합니다
		// mdao 에서 탬플릿을 이용하여 회원 정보를 업데이트 합니다		
		// loginUser 세션 정보를 업데이트 합니다.
		HttpSession session=request.getSession();
		session.setAttribute("loginUser", mvo);
		return "redirect:/";
	}
	
	
	
	@RequestMapping(value = "memberEditForm")
	public String member_Edit_Form(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		
		String addr = mvo.getAddress();  // 주소 추출
		int k1 = addr.indexOf(" ");  // 첫번째 공백의 위치 찾음
		int k2 = addr.indexOf(" ", k1+1);  // 첫번째 공백 위치의 다음위치부터 두번째 공백 위치 찾음
		int k3 = addr.indexOf(" ", k2+1);  // 두번째 공백위치 다음 위치부터 세번째 공백 위치 찾음
		// 서울시 마포구 대현동 115-15  세번째 공백 위치  k3값 -> 11 (0부터 시작)
		String addr1 = addr.substring(0, k3); // 맨앞부터 세번째 공백 위치 바로 전까지... 주소 앞부분
		String addr2 = addr.substring(k3+1);  // 세번째 공백 뒷글자부터 맨끝까지...주소 뒷부분
		
		request.setAttribute("member", mvo);
		request.setAttribute("addr1", addr1);
		request.setAttribute("addr2", addr2);
		
		return "member/memberUpdateForm";
	}
	
	
	@RequestMapping(value = "join", method=RequestMethod.POST)
	public String join(Model model, HttpServletRequest request) {
		MemberVO mvo = new MemberVO();
		mvo.setId(request.getParameter("id"));
		mvo.setPwd(request.getParameter("pwd"));
		mvo.setName(request.getParameter("name"));
		mvo.setEmail(request.getParameter("email"));
		mvo.setPhone(request.getParameter("phone"));
		mvo.setZip_num(request.getParameter("zip_num"));
		mvo.setAddress(request.getParameter("addr1") + " " + request.getParameter("addr2"));
		ms.insertMember(mvo);
		return "member/login";
	}
	
	
	
	
	
	@RequestMapping("findZipNum")
	public String find_zip(Model model, HttpServletRequest request) {
		String dong=request.getParameter("dong");
		if(dong!=null && dong.trim().equals("")==false){
			List<AddressVO> addressList = ms.slelectAddressByDong(dong);
			model.addAttribute("addressList", addressList);
		}
		return "member/findZipNum";
	}
	
	
	
	
	@RequestMapping("idCheckForm")
	public String id_check_form(Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
		int result = ms.confirmID(id);
		model.addAttribute("result", result);
		model.addAttribute("id", id);
		return "member/idcheck";
	}
	
	
	
	
	@RequestMapping(value="joinForm", method=RequestMethod.POST)
	public String join_form(Model model, HttpServletRequest request) {
		return "member/joinForm";
	}
	
	
		
	@RequestMapping("contract")
	public String contract(Model model, HttpServletRequest request) {
		return "member/contract";
	}
	
	
	@RequestMapping("logout")
	public String logout(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("loginUser");
		return "redirect:/";
	}
	
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		MemberVO mvo = ms.getMember(id);
		if( mvo!=null) {
			if(mvo.getPwd()!=null) {
				if(mvo.getPwd().equals(pwd)) {
					HttpSession session = request.getSession();
					session.setAttribute("loginUser", mvo);
					return "redirect:/";
				}else {
					model.addAttribute("message", "비번이 맞지 않습니다");
					return "member/login";
				}
			}else {
				model.addAttribute("message", "비밀번호 오류. 관리자에게 문의하세요");
				return "member/login";
			}
		}else {
			model.addAttribute("message", "ID가 없습니다");
			return "member/login";
		}
	}
	
	
	
	@RequestMapping("loginForm")
	public String login_form(Model model, HttpServletRequest request) {
		return "member/login";
	}
}
