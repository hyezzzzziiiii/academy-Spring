package com.ezen.student.service;

import com.ezen.student.dao.StudentDao;
import com.ezen.student.dto.Student;

public class StudentModifyService {
	private StudentDao sdao;
	
	public StudentModifyService(StudentDao sdao) {
		this.sdao = sdao;
	}
	
	public void update(Student student) {
		sdao.update(student);
	}
}
