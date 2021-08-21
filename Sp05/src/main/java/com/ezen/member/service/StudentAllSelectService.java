package com.ezen.member.service;

import java.util.ArrayList;

import com.ezen.member.dao.StudentDao;
import com.ezen.member.dto.Student;

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
