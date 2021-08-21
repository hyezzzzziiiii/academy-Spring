package first;

public class MainClass {

	public static void main(String[] args) {
		/*Calculator c;  // 인터페이스의 레퍼런스 변수
		CalMul cm = new CalMul();  // 인터페이스를 implements 한 클래스 객체
		c = cm;
		int result = c.cal(30, 4);
		System.out.println("Mul. result : " + result);
		
		CalDiv cv = new CalDiv();  // 인터페이스를 implements 한 클래스 객체
		c = cv;
		result = c.cal(30, 4);
		System.out.println("Div. result : " + result);
		
		CalSum cs = new CalSum();  // 인터페이스를 implements 한 클래스 객체
		c = cs;
		result = c.cal(30, 4);
		System.out.println("Sum. result : " + result);
		
		CalSub csub = new CalSub();  // 인터페이스를 implements 한 클래스 객체
		c = csub;
		result = c.cal(30, 4);
		System.out.println("Sub. result : " + result); */
		
		PublicCalculator cal  = new PublicCalculator(30, 20, new CalSum());
		cal.result();
		cal  = new PublicCalculator(30, 20, new CalSub());
		cal.result();
		cal  = new PublicCalculator(30, 20, new CalMul());
		cal.result();
		cal  = new PublicCalculator(30, 20, new CalDiv());
		cal.result();
		
		// 요점 
		// : 프로젝트에 필요한 클래스들을 생성하고 각 객체(인스턴스)를 다른 클래스의 생성자의 인수로 전달하는 예제를 확인
		//   이에 필요한 클래스들의 관리가 현재는 수동으로 이루어 지고 있으나, 스프링 프레임 웍에서 제공하는
		//   클래스의 자동 관리의 필요성을 확인하고 다음 예제로 넘어가기 위한 단계로 이해하시면 됩니다.
		
	}
}
