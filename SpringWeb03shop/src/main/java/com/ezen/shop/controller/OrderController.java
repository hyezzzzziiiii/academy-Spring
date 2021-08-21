package com.ezen.shop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.shop.dto.CartVO;
import com.ezen.shop.dto.MemberVO;
import com.ezen.shop.dto.OrderVO;
import com.ezen.shop.service.CartService;
import com.ezen.shop.service.OrderService;

@Controller
public class OrderController {

	 @Autowired
	 OrderService os;
	 
	 @Autowired
	 CartService cs;
	 
	 
	 
	 @RequestMapping("orderDetail")
		public ModelAndView order_detail(Model model, HttpServletRequest request,
				@RequestParam("oseq") int oseq) {
			HttpSession session = request.getSession();
		    MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		    ModelAndView mav = new ModelAndView();
		    if (loginUser == null)	mav.setViewName("member/login");
		    else {
		    	List<OrderVO> orderList = os.listOrderById(loginUser.getId(), "%", oseq);	
		    	int totalPrice=0;
		    	for(OrderVO ovo :orderList) totalPrice+=ovo.getPrice2()*ovo.getQuantity();
		    	
		    	mav.addObject("orderDetail", orderList.get(0));
				mav.addObject("orderList", orderList);
				mav.addObject("totalPrice", totalPrice);
				mav.setViewName("mypage/orderDetail");
		    }
		    return mav;
	 }
	 
	 
	 
	 
	 
	@RequestMapping("orderAll")  // 총주문내역
	public ModelAndView order_all(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		ModelAndView mav = new ModelAndView();
		if (mvo == null) mav.setViewName("member/login");
		else {
			List<Integer> oseqList	= os.oseqListAll(mvo.getId());
			ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();
			for (int oseq : oseqList) {
				List<OrderVO> orderListAll = os.listOrderById(mvo.getId(), "%", oseq);
				OrderVO ovo = orderListAll.get(0);
				ovo.setPname(ovo.getPname() + " 포함 " + orderListAll.size() + " 건");
				int totalPrice = 0;
				for (OrderVO ovop : orderListAll) 
			          totalPrice += ovop.getPrice2() * ovop.getQuantity();
				orderList.add(ovo);
			}			
			mav.addObject("title", "총 주문 내역");
			mav.addObject("orderList", orderList);
			mav.setViewName("mypage/mypage");
		}
		return mav;
	}
	@RequestMapping("myPage")  // 진행중인 주문 내역
	public String mypage(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
	    MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
	    if(mvo==null) return "member/login";
	    else {	
	    	// mypage.jsp 전달될 별도 리스트 생성
	    	ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();
	    	//1. 현재 로그인 유저의 아이디로 미처리된 주문 번호들(orders 테이블에서 조회)을 
	    	//    리스트로 받습니다(중복제거)
	    	List<Integer> oseqList = os.selectSeqOrderIng(mvo.getId());
			//2. 리턴 받은 리스트의 주문 번호를 하나씩 꺼내서(반복실행문 사용), 
			//    해당 주문 번호의 주문 내역을 리스트로 받습니다
	    	for( int oseq : oseqList ) {
	    		List<OrderVO> orderListIng	= os.listOrderById(mvo.getId(), "1", oseq);
	    		//3. 리턴 받은 주문 상세내역중 맨 첫번째 상품의 이름을 "가나다 포함 X건" 으로 변경합니다
	    		OrderVO ovo = orderListIng.get(0);
	    		ovo.setPname(ovo.getPname() + " 포함 " + orderListIng.size() + " 건");
	    		int totalPrice = 0;
	    		for (OrderVO ovo1 : orderListIng) 
		              totalPrice += ovo1.getPrice2() * ovo1.getQuantity();
	    		ovo.setPrice2(totalPrice);
	    		//4. 그 첫번째 상품을 orderList(별도 생성된 리스트) 라는 리스트에 담습니다.
	            orderList.add(ovo);
	    	}
	    	model.addAttribute("title", "진행 중인 주문 내역");
	    	model.addAttribute("orderList", orderList);
	    }
	    return "mypage/mypage";
	}
		    
	 
	 
	 
	 
	 @RequestMapping("orderList")
	public String order_list(Model model, HttpServletRequest request, 
			@RequestParam("oseq") int oseq ) {
		HttpSession session = request.getSession();
	    MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
	    if (mvo == null) {
	    	return "member/login";
	    }else {
	    	List<OrderVO> list	= os.listOrderById(mvo.getId(), "1", oseq);
	    	int totalPrice = 0;
	    	for(OrderVO ovo : list)
		          totalPrice+=ovo.getPrice2() * ovo.getQuantity();
	    	model.addAttribute("orderList", list);
	    	model.addAttribute("totalPrice", totalPrice);
	    }
		return "mypage/orderList";
	}
	 
	 @RequestMapping("orderInsert")
	 public String order_insert(Model model, HttpServletRequest request) {
		 HttpSession session = request.getSession();
		 MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		 int oseq = 0;
		 if (mvo == null) {
			    	return "member/login";
		 }else {
			  	List<CartVO> cartList = cs.listCart(mvo.getId());
			   	oseq = os.insertOrder(cartList, mvo.getId());
		 }
			return "redirect:/orderList?oseq="+oseq;
	  }
}
