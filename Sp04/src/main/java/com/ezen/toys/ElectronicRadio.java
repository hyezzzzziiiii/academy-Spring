package com.ezen.toys;

import com.ezen.battery.Battery;

public class ElectronicRadio {
	private Battery bat;
	
	//public ElectronicRadio() {}
	
	public ElectronicRadio(Battery bat) {
		this.bat = bat;
	} // 구매와 동시에 새 배터리를 장착할 수 있는 기능이 있습니다.
	
	public void setBattery(Battery battery) {
		this.bat = battery;
	} // 추후 배터리를 새로 교체 할수 있는 기능이 잇습니다.
} 
// 배터리가 동봉되어 사용자가 장난감 사용시점에 장착후 사용할수 있게 합니다.
