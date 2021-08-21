package com.ezen.student.service;

import java.util.ArrayList;

import com.ezen.student.dao.StudentDao;
import com.ezen.student.dto.Student;

public class StudentAllSelectService {
	private StudentDao sdao;
	
	public StudentAllSelectService(StudentDao sdao) {
		this.sdao = sdao;
	}
	
	public ArrayList<Student> allSelect() {
		ArrayList<Student> list = sdao.selectAll();
		return list;
	}
}
