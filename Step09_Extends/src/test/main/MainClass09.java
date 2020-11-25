package test.main;

import test.auto.Car;
import test.auto.Engine;

public class MainClass09 {
	public static void main(String[] args) {
		//default 생성자가 없으므로 아래와 같은 방법으로는 객체 생성 불가
		//Car car1=new car();
		Car car1=new Car(new Engine());
		car1.drive();
		//car1.engine(); 안됨 protected 여서  패키지가 달라서 안보임
	}
}
