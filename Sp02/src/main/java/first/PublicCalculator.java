package first;

public class PublicCalculator {
	int fNum, sNum;
	Calculator cal;
	
	// fn에는 첫번째 정수, sn 에는 두번째 정수가 전달, c 에는  new CalSum(), new CalSub(), newCalMul(),
	// new CalDiv() 중 하나가 전달됩니다
	PublicCalculator(int fn, int sn, Calculator c){
		this.fNum = fn;
		this.sNum = sn;
		this.cal = c;
	}  // 전달된 인수들은 각 멤버변수들에 저장
	
	public void result() {
		int value = cal.cal(this.fNum, this.sNum);
		System.out.println("result : " + value);
	}
}
