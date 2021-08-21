package first;

public class CalDiv implements Calculator{

	@Override
	public int cal(int firstNum, int secondNum) {
		int result = firstNum / secondNum;
		return result;
	}

}
