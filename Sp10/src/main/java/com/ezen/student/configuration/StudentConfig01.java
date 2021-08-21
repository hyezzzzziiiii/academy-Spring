package com.ezen.student.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ezen.student.dao.StudentDao;
import com.ezen.student.dto.DataBaseConnectionInfo;

@Configuration
public class StudentConfig01 {

	@Bean
	public DataBaseConnectionInfo dbConnectionfo() {
		DataBaseConnectionInfo temp = new DataBaseConnectionInfo();
		temp.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
		temp.setUserId("scott");
		temp.setUserPw("tiger");
		temp.setDriver("oracle.jdbc.driver.OracleDriver");
		return temp;
	}
	
	@Bean
	public StudentDao studentDao() {
		return new StudentDao( dbConnectionfo() );
	}
}
