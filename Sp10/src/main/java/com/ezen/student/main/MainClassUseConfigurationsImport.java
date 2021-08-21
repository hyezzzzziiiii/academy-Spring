package com.ezen.student.main;

import java.util.ArrayList;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ezen.student.configuration.StudentConfigImport;
import com.ezen.student.dto.Student;
import com.ezen.student.service.StudentAllSelectService;

public class MainClassUseConfigurationsImport {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(StudentConfigImport.class);
		
		StudentAllSelectService ass 
		= ctx.getBean("allSelectService", StudentAllSelectService.class);
	
		ArrayList<Student> list = ass.allSelect();
		for(int j=0; j<list.size(); j++) {
			System.out.print("| sNum : " + list.get(j).getsNum());
			System.out.print("| sId : " + list.get(j).getsId());
			System.out.print("| sPw : " + list.get(j).getsPw());
			System.out.print("| sName :" + list.get(j).getsName());
			System.out.print("| sAge : " + list.get(j).getsAge());
			System.out.print("| sGender : " + list.get(j).getsGender());
			System.out.print("| sMajor : " + list.get(j).getsMajor() + "\n");
		}
	}
}
