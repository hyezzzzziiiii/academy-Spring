package com.ezen.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.shop.dao.MemberDao;
import com.ezen.shop.dto.AddressVO;
import com.ezen.shop.dto.MemberVO;

@Service
public class MemberService {

	@Autowired
	MemberDao mdao;
	
	
	public void resetPw(MemberVO mvo) {
		mdao.resetPw(mvo);
	}
	
	
	
	public MemberVO confirmIdNamePhone(String id, String name, String phone) {
		return mdao.confirmIdNamePhone( id, name,  phone);
	}
	public MemberVO confirmNamePhone(String name, String phone) {
		return mdao.confirmNamePhone( name,  phone); 
	}
	
	
	
	
	public void memberUpdate(MemberVO mvo) {
		mdao.updateMember(mvo);
	}
	
	
	
	
	public void insertMember(MemberVO mvo) {
		mdao.insertMember(mvo);
	}
	
	
	public List<AddressVO> slelectAddressByDong(String dong){
		List<AddressVO> list = null;
		list = mdao.slelectAddressByDong(dong);
		return list;
	}
	
	
	
	public int confirmID(String id) {
		int result = mdao.confirmID(id);
		return result;
	}
	
	
	
	public MemberVO getMember(String id) {
		MemberVO mvo = mdao.getMember(id);
		return mvo;
	}
}
