package com.ezen.member.service;

import com.ezen.member.dao.StudentDao;
import com.ezen.member.dto.Student;

public class StudentSelectService {
	private StudentDao sdao;
	
	public StudentSelectService(StudentDao sdao) {
		this.sdao = sdao;
	}
	
	public Student select(String sNum) {
		Student std = sdao.select(sNum);
		return std;
	}
}
