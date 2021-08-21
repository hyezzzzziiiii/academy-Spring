package com.ezen.student.main;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.ezen.student.dto.Student;
import com.ezen.student.service.StudentModifyService;
import com.ezen.student.service.StudentSelectService;

public class MainCalssUseImport {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx 
		=	new GenericXmlApplicationContext("classpath:appCtxImport.xml");
		
		StudentModifyService mds  
		= ctx.getBean("modifyService", StudentModifyService.class);
		mds.update(new Student("H39lesvj7544vf89", "two", "55555", 
				"melissa", 70, "W", "Korean Language"));
		StudentSelectService ss
		= ctx.getBean("selectService", StudentSelectService.class);
		Student mstd = ss.select("H39lesvj7544vf89");
		System.out.print("sNum:" + mstd.getsNum() + "\n");
		System.out.print("|sId:" + mstd.getsId() + "\n");
		System.out.print("|sPw:" + mstd.getsPw() + "\n");
		System.out.print("|sName:" + mstd.getsName() + "\n");
		System.out.print("|sAge:" + mstd.getsAge() + "\n");
		System.out.print("|sGender:" + mstd.getsGender() + "\n");
		System.out.print("|sMajor:" + mstd.getsMajor() + "\n\n");
	}

}
