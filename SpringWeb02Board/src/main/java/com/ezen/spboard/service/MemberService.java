package com.ezen.spboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spboard.dao.MemberDao;
import com.ezen.spboard.dto.SpMember;

@Service
public class MemberService {

	@Autowired
	MemberDao mdao;
	
	
	public int memberModify(SpMember sm) {
		int result = mdao.memberUpdate(sm);
		return result;
	}
	
	
	
	
	public void memberRegister(SpMember sm) {
		mdao.memberInsert(sm);
	}
	
	
	
	
	public int confirmID(String id) {
		int result = mdao.confirmId(id);
		return result;
	}
	
	
	
	public SpMember getMember(String id) {
		SpMember sdto = null;
		sdto = mdao.getMember(id);
		return sdto;
	}
}
