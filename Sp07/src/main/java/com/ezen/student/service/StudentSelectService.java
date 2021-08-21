package com.ezen.student.service;

import com.ezen.student.dao.StudentDao;
import com.ezen.student.dto.Student;

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
