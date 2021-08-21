package com.ezen.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.shop.dto.ProductVO;
import com.ezen.shop.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	ProductService ps;
	
	
	
	@RequestMapping("productDetail")
	public String product_detail(Model model,  @RequestParam("pseq") String pseq) {
		ProductVO pd = ps.getProduct(pseq);
		model.addAttribute("productVO", pd);
		return "product/productDetail";
	}
	
	
	
	
	
	// ModelAndView : model 에 addAttribute 로 저장할 내용과 이동할 jsp 파일의 이름을 동시에 저장하고
	//     리턴하여 전달값과 이동페이지를 한번에 다룰수 있게하는 클래스 입니다.
	// @RequestParam : 메서드의 전달인수 앞에 붙여서 사용하며, 파라미터의 값을 request.getParameter
	//     메서를 사용하지 않고 전달받아 사용할 수 있게 해줍니다. 
	//     - 전달인수 갯수 만큼 매개변수를 만들고 모든 매개변수에 적용할수 있습니다
	@RequestMapping("catagory")
	public ModelAndView catagory(Model model, @RequestParam("kind") String kind) {
		List<ProductVO> list = null;
		//String kind = request.getParameter("kind");
		list = ps.getKindList(kind);
		ModelAndView mav = new ModelAndView();
		//model.addAttribute("productKindList", list);
		// return "product/productKind";
		mav.addObject("productKindList", list);
		mav.setViewName("product/productKind");
		return mav;  // 동시에 저장내용들고, 페이지로 이동
	}
	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String indes(Model model) {
		
		List<ProductVO> nlist = ps.getNewList();
		List<ProductVO> blist = ps.getBestList();
		
		model.addAttribute("newProductList", nlist);
		model.addAttribute("bestProductList", blist);
		
		return "index";
	}
}
