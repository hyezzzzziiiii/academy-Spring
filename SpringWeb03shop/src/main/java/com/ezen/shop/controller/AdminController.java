package com.ezen.shop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.shop.dto.MemberVO;
import com.ezen.shop.dto.OrderVO;
import com.ezen.shop.dto.Paging;
import com.ezen.shop.dto.ProductVO;
import com.ezen.shop.dto.QnaVO;
import com.ezen.shop.service.AdminService;
import com.ezen.shop.service.ProductService;
import com.ezen.shop.service.QnaService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class AdminController {
	
	@Autowired
	AdminService as;
	
	@Autowired
	ProductService ps;
	
	@Autowired
	QnaService qs;
	
	@Autowired
	ServletContext context;
	
	
	
	
	@RequestMapping("adminQnaRepsave")
	public String admin_qna_repsave(Model model, HttpServletRequest request) {
		String qseq = request.getParameter("qseq");
		String reply =request.getParameter("reply");
		QnaVO qvo = new QnaVO();
		qvo.setQseq(Integer.parseInt(qseq));
		qvo.setReply(reply);
		as.updateQna(qvo);
		return "redirect:/adminQnaDetail?qseq=" + qseq;
	}
	
	
	
	
	
	
	
	@RequestMapping("adminQnaDetail")
	public ModelAndView admin_qna_detail(@RequestParam("qseq") int qseq, 
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		QnaVO qvo = qs.getQna(qseq);
		mav.addObject("qnaVO", qvo);
		mav.setViewName("admin/qna/qnaDetail");
		return mav;
	}
	
	
	
	
	
	
	@RequestMapping("orderSave")
	public String admin_Order_save(@RequestParam("result") String[] resultArr, 
			HttpServletRequest request) {
		for(String odseq : resultArr) as.updateOrderResult(odseq);
		return "redirect:/adminOrderList";
	}
	
	
	
	
	
	@RequestMapping("productUpdate")
	public String product_update(Model model, HttpServletRequest request) {
		String savePath = context.getRealPath("resources/product_images");
		MultipartRequest multi=null;
		ProductVO pvo = new ProductVO();
		try {
			multi = new MultipartRequest(request, savePath, 5*1024*1024,
					"UTF-8", new DefaultFileRenamePolicy()  );
			pvo.setPseq(Integer.parseInt(multi.getParameter("pseq")));
		    pvo.setKind(multi.getParameter("kind"));
		    pvo.setName(multi.getParameter("name"));
		    pvo.setPrice1(Integer.parseInt(multi.getParameter("price1")));
		    pvo.setPrice2(Integer.parseInt(multi.getParameter("price2")));
		    pvo.setPrice3(Integer.parseInt(multi.getParameter("price2"))
		        - Integer.parseInt(multi.getParameter("price1")));
		    pvo.setContent(multi.getParameter("content"));
		    pvo.setUseyn(multi.getParameter("useyn"));
		    pvo.setBestyn(multi.getParameter("bestyn"));
		    if(multi.getFilesystemName("image")==null)
		    	pvo.setImage(multi.getParameter("nonmakeImg"));
		    else
		    	pvo.setImage(multi.getFilesystemName("image"));
		} catch (IOException e) { e.printStackTrace();	}
		as.updateProduct(pvo);
		return "redirect:/adminProductDetail?pseq=" + multi.getParameter("pseq");
	}
	
	
	
	
	
	@RequestMapping("productUpdateForm")
	public ModelAndView product_update_form(Model model, HttpServletRequest request,
			@RequestParam("pseq") String pseq) {
		ModelAndView mav = new ModelAndView();
		ProductVO pvo =  ps.getProduct(pseq);
		model.addAttribute("productVO", pvo);
		String kindList[] = { "Heels", "Boots", "Sandals", "Slipers", "Shcakers", "Sale" };    
		mav.addObject("kindList", kindList);
		mav.setViewName("admin/product/productUpdate");
		
		return mav;
		
	}
	
	
	
	
	
	@RequestMapping("adminProductDetail")
	public ModelAndView product_detail(HttpServletRequest request, 
			@RequestParam("pseq") String pseq) {
		
		ModelAndView mav = new ModelAndView();
		ProductVO pvo = ps.getProduct(pseq);
		mav.addObject("productVO", pvo);
		String kindList[] = { "0", "Heels", "Boots", "Sandals", "Slipers", "Shcakers", "Sale" };
		int index = Integer.parseInt(pvo.getKind());
		mav.addObject("kind", kindList[index]);
		mav.setViewName("admin/product/productDetail");
		return mav;
	}
	
	
	
	
	@RequestMapping("productWrite")
	public String product_write(HttpServletRequest request) {
		
		String savePath = context.getRealPath("resources/product_images");
		try {
			MultipartRequest multi = new MultipartRequest(request, savePath, 
					5*1024*1024 , "UTF-8", new DefaultFileRenamePolicy());
			ProductVO pvo = new ProductVO();
			pvo.setKind(multi.getParameter("kind"));
		    pvo.setName(multi.getParameter("name"));
		    pvo.setPrice1(Integer.parseInt(multi.getParameter("price1")));
		    pvo.setPrice2(Integer.parseInt(multi.getParameter("price2")));
		    pvo.setPrice3(Integer.parseInt(multi.getParameter("price2"))
		    	        - Integer.parseInt(multi.getParameter("price1")));
		    pvo.setContent(multi.getParameter("content"));
		    pvo.setImage(multi.getFilesystemName("image"));
		    as.insertProduct(pvo);
		} catch (IOException e) { e.printStackTrace();  }
		return "redirect:/productList";		
	}
	
	
	
	
	@RequestMapping("productWriteForm")
	public ModelAndView product_write_form( HttpServletRequest request) {
		String kindList[] = { "Heels", "Boots", "Sandals", "Shcakers", "Slipers",  "Sale" };
		ModelAndView mav = new ModelAndView();
		mav.addObject("kindList", kindList);
		mav.setViewName("admin/product/productWriteForm");
		return mav;
	}
	
	
	
	
	
	@RequestMapping("adminQnaList")
	public ModelAndView adminQnaList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("workId");
		int page=1;
		if(id==null)
			mav.setViewName("redirect:/admin");
		else {
			if( request.getParameter("first")!=null ) {
				session.removeAttribute("page");
				session.removeAttribute("key");
			}
			String key = "";
			if( request.getParameter("key") != null ) {
				key = request.getParameter("key");
				session.setAttribute("key", key);
			} else if( session.getAttribute("key")!= null ) {
				key = (String)session.getAttribute("key");
			} else {
				session.removeAttribute("key");
				key = "";
			} 
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
			int count = as.getAllCount("qna", "subject", key);
			paging.setTotalCount(count);
			List<QnaVO> qnaList = as.listQna(paging, key);
			mav.addObject("paging", paging);
			mav.addObject("key", key);
			mav.addObject("qnaList", qnaList);
			mav.setViewName("admin/qna/qnaList");
		}
		return mav;
	}
	
	
	
	
	
	@RequestMapping("memberList")
	public ModelAndView memberList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("workId");
		int page=1;
		if(id==null)
			mav.setViewName("redirect:/admin");
		else {
			if( request.getParameter("first")!=null ) {
				session.removeAttribute("page");
				session.removeAttribute("key");
			}
			String key = "";
			if( request.getParameter("key") != null ) {
				key = request.getParameter("key");
				session.setAttribute("key", key);
			} else if( session.getAttribute("key")!= null ) {
				key = (String)session.getAttribute("key");
			} else {
				session.removeAttribute("key");
				key = "";
			} 
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
			int count = as.getAllCount("member", "name", key);
			paging.setTotalCount(count);
			List<MemberVO> memberList = as.listMember(paging, key);
			mav.addObject("paging", paging);
			mav.addObject("key", key);
			mav.addObject("memberList", memberList);
			mav.setViewName("admin/member/memberList");
		}
		return mav;
	}
	
	
	
	
	
	@RequestMapping("adminOrderList")
	public ModelAndView orderList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("workId");
		int page=1;
		if(id==null)
			mav.setViewName("redirect:/admin");
		else {
			if( request.getParameter("first")!=null ) {
				session.removeAttribute("page");
				session.removeAttribute("key");
			}
			String key = "";
			if( request.getParameter("key") != null ) {
				key = request.getParameter("key");
				session.setAttribute("key", key);
			} else if( session.getAttribute("key")!= null ) {
				key = (String)session.getAttribute("key");
			} else {
				session.removeAttribute("key");
				key = "";
			} 
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
			int count = as.getAllCount("order_view", "mname", key);
			paging.setTotalCount(count);
			List<OrderVO> orderList = as.listOrder(paging, key);
			mav.addObject("paging", paging);
			mav.addObject("key", key);
			mav.addObject("orderList", orderList);
			mav.setViewName("admin/order/orderList");
		}
		return mav;
	}
	
	
	
	
	
	@RequestMapping("productList")
	public ModelAndView product_list(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("workId");
		int page=1;
		if(id==null)
			mav.setViewName("redirect:/admin");
		else {
			if( request.getParameter("first")!=null ) {
				session.removeAttribute("page");
				session.removeAttribute("key");
			}
			String key = "";
			if( request.getParameter("key") != null ) {
				key = request.getParameter("key");
				session.setAttribute("key", key);
			} else if( session.getAttribute("key")!= null ) {
				key = (String)session.getAttribute("key");
			} else {
				session.removeAttribute("key");
				key = "";
			} 
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
			int count = as.getAllCount("product", "name", key);
			paging.setTotalCount(count);
			List<ProductVO> productList = as.listProduct(paging, key);
			mav.addObject("paging", paging);
			mav.addObject("key", key);
			mav.addObject("productList", productList);
			mav.setViewName("admin/product/productList");
		}
		return mav;
	}
	
	
	
	
	
	
	
	@RequestMapping("adminLogin")
	public ModelAndView admin_login(Model model, HttpServletRequest request,
			@RequestParam("workId") String workId,
			@RequestParam("workPwd") String workPwd) {
		ModelAndView mav = new ModelAndView();
		String msg = "";
		// result 값이 1이면 정상 로그인, 0이면 비밀번호 오류, -1 이면 아이디 없음
		int result = as.workerCheck(workId, workPwd);
		if(result == 1) {
    		HttpSession session = request.getSession();
    		session.setAttribute("workId", workId);
    		mav.setViewName("redirect:/productList");
	    } else if (result == 0) {
	        	msg = "비밀번호를 확인하세요.";
	        	mav.addObject("message", msg);
	        	mav.setViewName("admin/adminLoginForm");
	    } else if (result == -1) {
	    		msg = "아이디를 확인하세요.";
	    		mav.addObject("message", msg);
	    		mav.setViewName("admin/adminLoginForm");
	    }		
		return mav;
	}
	
	
	
	@RequestMapping("/admin")
	public String admin() {
		return "admin/adminLoginForm";
	}
}
