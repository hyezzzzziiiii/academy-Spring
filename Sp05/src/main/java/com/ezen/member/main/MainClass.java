package com.ezen.member.main;

import java.util.ArrayList;

import com.ezen.member.dao.StudentAssembler;
import com.ezen.member.dto.Student;
import com.ezen.member.service.StudentAllSelectService;
import com.ezen.member.service.StudentModifyService;
import com.ezen.member.service.StudentRegisterService;
import com.ezen.member.service.StudentSelectService;

public class MainClass {

	public static void main(String[] args) {
		
		String[] sNums = {"H39r8djakndfae32", "H39asdfaelu42o23", "H39iiemamca8w9h4", 
			"H39lkmn754fghia7", "H39plo865cuy8k92", "H39mnbviiaed89q1",  "H399omjjyv56t3d5", 
			"H39lczaqwg644gj8", "H39ymbcsh74thgh2",  "H39lesvj7544vf89"};	
		
		String[] sIds = {"rabbit", "hippo", "raccoon", "elephant", "lion",  "tiger", "pig", 
									"horse", "bird", "deer"};
		
		String[] sPws = {"96539", "94875", "15284", "48765", "28661", "60915", "30028", 
										"29801", "28645", "28465"};
		
		String[] sNames = {"agatha", "barbara", "chris", "doris", "elva", "fiona", "holly", 
								"jasmin", "lena", "melissa"};
		
		int[] sAges = {19, 22, 20, 27, 19, 21, 19, 25, 22, 24};
		
		String[] sGenders = {"M", "W", "W", "M", "M", "M", "W", "M", "W", "W"};
		
		String[] sMajors = {"English Literature",	"Korean Literature",	"French Literature", 
				"Philosophy", "History",	"Law", "Statistics", "Computer", "Economics", "Public Admin"};	
		
		//Student std = new Student(sNums[0], sIds[0], sPws[0], sNames[0], 
		//		sAges[0], sGenders[0], sMajors[0]);
		
		/*StudentRegisterService rs = new StudentRegisterService();
		rs.register(std); */
		
		//StudentDao sdao = new StudentDao();
		//StudentRegisterService rs = new StudentRegisterService( sdao );
		//rs.register(std);
		
		StudentAssembler assembler = new StudentAssembler();
		StudentRegisterService rs = assembler.getRs();
		//rs.register(std);
		for (int i = 0; i < sNums.length; i++) {
			Student std = new Student(sNums[i], sIds[i], sPws[i], sNames[i], 
					sAges[i], sGenders[i], sMajors[i]);
			//rs.register(std);
		}
		
		// 저장된 데이터를 모두 읽어 와서 화면에 출력
		StudentAllSelectService sa = assembler.getSa();
		ArrayList<Student> list = sa.allSelect();
		for(int j=0; j<list.size(); j++) {
			System.out.print("| sNum : " + list.get(j).getsNum() + "\t");
			System.out.print("| sId : " + list.get(j).getsId() + "\t");
			System.out.print("| sPw : " + list.get(j).getsPw() + "\t");
			System.out.print("| sName :" + list.get(j).getsName() + "\t");
			System.out.print("| sAge : " + list.get(j).getsAge() + "\t");
			System.out.print("| sGender : " + list.get(j).getsGender() + "\t");
			System.out.print("| sMajor : " + list.get(j).getsMajor() + "\n");
		}
		
		StudentModifyService sm = assembler.getSm();
		Student uStd = new Student("H39r8djakndfae32", "userid", "12345", 
				"agatha", 25, "W", "Korean Laguage");
		sm.update(uStd);
		
		StudentSelectService ss = assembler.getSs();
		Student mStd = ss.select("H39r8djakndfae32");
		System.out.print("sNum:" + mStd.getsNum() + "\n");
		System.out.print("|sId:" + mStd.getsId() + "\n");
		System.out.print("|sPw:" + mStd.getsPw() + "\n");
		System.out.print("|sName:" + mStd.getsName() + "\n");
		System.out.print("|sAge:" + mStd.getsAge() + "\n");
		System.out.print("|sGender:" + mStd.getsGender() + "\n");
		System.out.print("|sMajor:" + mStd.getsMajor() + "\n\n");
		
	}

}
