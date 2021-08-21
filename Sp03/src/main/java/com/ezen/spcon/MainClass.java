package com.ezen.spcon;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		//TransportationWalk trans = new TransportationWalk();
		//trans.move();
		
		// 생성된 클래스의 인스턴스를 미리 만들어서 저장해두고 필요하다면 꺼내어 쓰는 방식으로 변환
		// 만들어진 인스턴스의 보관장소를 스프링 컨테이너 라고 부릅니다.
		// 현재의 스프링 컨테이너 : applicationContext.xml 
		
		// 생성된 스프링 컨테이너에서 빈을 꺼내 쓸수 있는 도구 객체를 생성
		GenericXmlApplicationContext ctx = 
				new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		// 레퍼런스 변수를 선언하고, 스프링컨테이너에서 객체를 꺼내다 선언된 변수에 저장합니다
		TransportationWalk trans = ctx.getBean("tWalk", TransportationWalk.class);
		
		trans.move();
		
		ctx.close();
		
		// 요점 : 스프링 컨테이너에 프로젝트에서 사용되는 클래스의 객체를 넣어두고 필요시 꺼내다 쓰는 기능을 이용합니다
		// 아직까지 일반 자바 프로젝트에서 사용하는 new TransportationWalk() 와 차이점은 없어 보입니다.
		// 다음 예제에서 new TransportationWalk() 와 차별화된 객체 사용의 예를 알아보겟습니다.
		// 현재의 두 사용법에 차이점이 있다면 new TransportationWalk() 는 사용할때 마다 새로운 인스턴스가생성되고
		// ctx.getBean() 은 싱글톤 방식 처럼 하나의 객체가 계속 이용된다는 점이 다릅니다.
		
		// ctx.getBean() 을 이용하여 두개의 서로 인스턴스가 다른 객체를 사용해야 한다면
		// applicationContext.xml 에 
		// <bean class="com.ezen.spcon.TransportationWalk" id="tWalk2"></bean>
		// 와 같이 또하나의 bean 을 생성해야 합니다.
	}
}
