package com.ezen.student.main;

import java.util.ArrayList;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.ezen.student.dto.EMSInformationService;
import com.ezen.student.dto.Student;
import com.ezen.student.service.StudentAllSelectService;
import com.ezen.student.service.StudentModifyService;
import com.ezen.student.service.StudentRegisterService;
import com.ezen.student.service.StudentSelectService;

public class MainClass {
	public static void main(String[] args) {
		String[] sNums = {"H39r8djakndfae32", "H39asdfaelu42o23", "H39iiemamca8w9h4", 
				  "H39lkmn754fghia7", "H39plo865cuy8k92", "H39mnbviiaed89q1", 
				  "H399omjjyv56t3d5", "H39lczaqwg644gj8", "H39ymbcsh74thgh2", 
				  "H39lesvj7544vf89"};
		String[] sIds = {"rabbit", "hippo", "raccoon", "elephant", "lion", 
			    "tiger", "pig", "horse", "bird", "deer"};
		String[] sPws = {"96539", "94875", "15284", "48765", "28661", 
				"60915", "30028", "29801", "28645", "28465"};
		String[] sNames = {"agatha", "barbara", "chris", "doris", "elva", 
				  "fiona", "holly", "jasmin", "lena", "melissa"};
		int[] sAges = {19, 22, 20, 27, 19, 21, 19, 25, 22, 24};
		String[] sGenders = {"M", "W", "W", "M", "M", "M", "W", "M", "W", "W"};
		String[] sMajors = {"English Literature", "Korean Language and Literature", 
		"French Language and Literature", "Philosophy", "History", 
		"Law", "Statistics", "Computer", "Economics", "Public Administration"};
		
		GenericXmlApplicationContext ctx 
		= new GenericXmlApplicationContext("classpath:applicationContext.xml");	
		StudentRegisterService rg 
		= ctx.getBean("registerService", StudentRegisterService.class);
		for (int j = 0; j < sNums.length; j++) {
			Student student = new Student(sNums[j], sIds[j], sPws[j], sNames[j], 
					sAges[j], sGenders[j], sMajors[j]);
			//rg.register(student);
		}
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
		
		StudentModifyService mds 
		= ctx.getBean("modifyService", StudentModifyService.class);
	
		mds.update(new Student("H39r8djakndfae32", "userid", "12345", 
					"agatha", 25, "W", "Korean Laguage"));
		StudentSelectService ss
		= ctx.getBean("selectService", StudentSelectService.class);
	
		Student mstd = ss.select("H39r8djakndfae32");
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
	}
}
