package com.ezen.member.dao;

import com.ezen.member.service.StudentAllSelectService;
import com.ezen.member.service.StudentModifyService;
import com.ezen.member.service.StudentRegisterService;
import com.ezen.member.service.StudentSelectService;

public class StudentAssembler {
	private StudentDao sdao;
	private StudentRegisterService rs;
	private StudentAllSelectService sa; 
	private StudentModifyService sm;
	private StudentSelectService ss;
	
	public StudentAssembler() {
		sdao = new StudentDao();
		rs = new StudentRegisterService(sdao);
		sa = new StudentAllSelectService(sdao);
		sm = new StudentModifyService( sdao );
		ss = new StudentSelectService(sdao);
	}  // sdao 와 rs 를 조립 :  rs 만 있다면 레코드 추가가 가능한 상태

		
	public StudentModifyService getSm() {
		return sm;
	}

	public void setSm(StudentModifyService sm) {
		this.sm = sm;
	}

	public StudentSelectService getSs() {
		return ss;
	}



	public void setSs(StudentSelectService ss) {
		this.ss = ss;
	}



	public StudentAllSelectService getSa() {
		return sa;
	}

	public void setSa(StudentAllSelectService sa) {
		this.sa = sa;
	}

	public StudentRegisterService getRs() {
		return rs;
	}

	public void setRs(StudentRegisterService rs) {
		this.rs = rs;
	}
	
	
}
