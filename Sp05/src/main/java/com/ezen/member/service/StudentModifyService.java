package com.ezen.member.service;

import com.ezen.member.dao.StudentDao;
import com.ezen.member.dto.Student;

public class StudentModifyService {
	private StudentDao sdao;
	
	public StudentModifyService(StudentDao sdao) {
		this.sdao = sdao;
	}
	
	public void update(Student student) {
		sdao.update(student);
	}
}
