package com.ezen;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.ezen.battery.ChargeBattery;
import com.ezen.battery.NormalBattery;
import com.ezen.toys.ElectronicCar;
import com.ezen.toys.ElectronicRadio;
import com.ezen.toys.ElectronicRobot;

public class MainClass {

	public static void main(String[] args) {
		
		/*ElectronicCar carToy = new ElectronicCar();
		
		NormalBattery nbatt = new NormalBattery();  // 노멀 베터리 객체 생성
		ElectronicRadio radioToy = new ElectronicRadio( nbatt );  // 생성자에 전달인수로 전달
		ChargeBattery cbatt1 = new ChargeBattery();  // 충전 배터리 객체 생성
		radioToy.setBattery( cbatt1 );   // 멤버메서드의 전달인수로 전달
		
		ChargeBattery cbatt2 = new ChargeBattery();
		ElectronicRobot robotToy	= new ElectronicRobot();
		robotToy.setBattery( cbatt2 );*/
		
		
		// 위처럼 본 클래스의 생성자 메서드에 다른 클래스의 실객체가 전달되어야 본클래스 객체가 만들어지는 형태를
		// 의존, 의존 주입 이라고 합니다.
		
		// 자바 문법으로 의존 주입을 한다면 위에 처럼 객체 생성시 의존 주입이 이루어지지만
		// 스프링컨테이너에서의 의존주입은  컨테이너에 bean 만들어지는 시점에 모두 구현해 놓고 시작합니다.
		// 이를 객체 조립이라고  칭합니다.
		
		GenericXmlApplicationContext ctx 
		= new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		//ElectronicCar carToy = new ElectronicCar();
		ElectronicCar carToy = ctx.getBean("car", ElectronicCar.class);
		
		//NormalBattery nbatt = new NormalBattery();  // 노멀 베터리 객체 생성
		//ElectronicRadio radioToy = new ElectronicRadio( nbatt );  // 생성자에 전달인수로 전달
		// 조립된 객체를 꺼내어 사용하는 대표적인 예
		ElectronicRadio radioToy = ctx.getBean("radio", ElectronicRadio.class);
		
		//ElectronicRobot robotToy	= new ElectronicRobot();
		ElectronicRobot robotToy = ctx.getBean("robot", ElectronicRobot.class);
		
		NormalBattery nbat = ctx.getBean("nBattery", NormalBattery.class);
		ChargeBattery cbat = ctx.getBean("cBattery", ChargeBattery.class);
		radioToy.setBattery(nbat);
		robotToy.setBattery(cbat);
		
	}

}
