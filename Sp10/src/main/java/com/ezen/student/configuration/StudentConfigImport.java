package com.ezen.student.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.ezen.student.dao.StudentDao;
import com.ezen.student.dto.DataBaseConnectionInfo;

@Configuration
@Import({StudentConfig02.class, StudentConfig03.class})
public class StudentConfigImport {

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
		return new StudentDao(  dbConnectionfo()  );
	}
}
