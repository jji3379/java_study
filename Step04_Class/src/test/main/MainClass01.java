package test.main;

import test.mypac.Car;

public class MainClass01 {

	public static void main(String[] args) {
		Car car1=null; //Car type 의 참조값을 담을 수 있는 빈 지역 변수 car1 만들기
		
		car1=new Car(); //Car 클래스로 객체를 생성 (new)해서 그 생성된 객체 참조값을 car1에 대입하기
		//car1 지역변수에 있는 참조값을 이용
		car1.drive();
		car1.hothip();
	}
}
