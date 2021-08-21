package com.ezen.student.main;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.ezen.student.dto.EMSInformationService;
import com.ezen.student.dto.Student;
import com.ezen.student.service.StudentModifyService;
import com.ezen.student.service.StudentSelectService;

public class MainClass {

	public static void main(String[] args) {
		
		// 앞선 프로젝트(Sp06) 를 참고 : 레코드 추가 부분 생략
		
		String[] appCtxs = {"classpath:appCtx01.xml", "classpath:appCtx02.xml", 
				"classpath:appCtx03.xml"};
		GenericXmlApplicationContext ctx = 
				new GenericXmlApplicationContext(appCtxs);
		
		StudentModifyService mds  
		= ctx.getBean("modifyService", StudentModifyService.class);
		mds.update(new Student("H39lesvj7544vf89", "one", "54321", 
				"melissa", 60, "W", "English Language"));
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
		
		EMSInformationService eis 
		= ctx.getBean("informationService", EMSInformationService.class);
		eis.outputEMSInformation();
		
		ctx.close();
	}
}
