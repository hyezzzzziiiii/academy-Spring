package com.ezen.student.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ezen.student.dao.StudentDao;
import com.ezen.student.service.StudentAllSelectService;
import com.ezen.student.service.StudentModifyService;
import com.ezen.student.service.StudentRegisterService;
import com.ezen.student.service.StudentSelectService;

@Configuration
public class StudentConfig02 {

	//StudentConfig01 sc01 = new StudentConfig01(); X
	@Autowired
	StudentDao sdao;
	
	@Bean
	public StudentModifyService modifyService() {
		return new StudentModifyService( sdao );
	}
	
	@Bean
	public StudentSelectService selectService() {
		return new StudentSelectService( sdao );
	}
}
