package test.main;

import test.mypac.Car;

public class MainClass05 {
	public static void main(String[] args) {
		//1.Car type 을 담을 수 있는 방3개 짜리 배열객체를 생성한 다음
		//참조값을 cars 라는 지역 변수에 담아 보세요.
		
		//2. cars 배열에 Car 객체를 생성해서 순서대로 담아 보세요.(총 3개)
		
		//3. cars 배열에 저장된 Car 객체의 참조값을 순서대로 참조해서 
		//.drive() 메소드를 호출해 보세요. (저는 콘솔창에 무언가를 출력하라고 하지 않았습니다.)
		
		Car[] cars=new Car[3];
		cars[0]=new Car();
		cars[1]=new Car();
		cars[2]=new Car();
		
		for(int i=0; i<cars.length; i++) {
			cars[i].dirve();
		}
	}
}