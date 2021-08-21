package com.ezen.student.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ezen.student.dao.StudentDao;
import com.ezen.student.dto.DataBaseConnectionInfo;
import com.ezen.student.service.StudentAllSelectService;
import com.ezen.student.service.StudentModifyService;
import com.ezen.student.service.StudentRegisterService;
import com.ezen.student.service.StudentSelectService;

@Configuration
public class StudentConfig {
	// 해당 클래스의 new 인스턴스를 생성하고 리턴해주는 메서들을 만들어서 스프링컨테이너 형태의 운영을 합니다
	@Bean
	public DataBaseConnectionInfo dbConnectionfo() {
		// DataBaseConnectionInfo 형태의 객체인스턴스를 리턴해주는 메서드
		DataBaseConnectionInfo temp = new DataBaseConnectionInfo();
		temp.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
		temp.setUserId("scott");
		temp.setUserPw("tiger");
		temp.setDriver("oracle.jdbc.driver.OracleDriver");
		return temp;
	}
	
	@Bean
	public StudentDao studentDao() {
		return new StudentDao(  dbConnectionfo()  );
	}
	@Bean
	public StudentRegisterService registerService() {
		return new StudentRegisterService( studentDao() );
	}
	@Bean
	public StudentAllSelectService allSelectService() {
		return new StudentAllSelectService( studentDao() );
	}
	
	@Bean
	public StudentModifyService modifyService() {
		return new StudentModifyService(studentDao());
	}
	@Bean
	public StudentSelectService selectService() {
		return new StudentSelectService(studentDao());
	}
}
