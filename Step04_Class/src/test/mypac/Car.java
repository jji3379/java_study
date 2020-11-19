package test.mypac;
/*
 * [ 클래스의 용도 ] 
 * 
 *  1. 객체의 설계도 역할
 *  	- 객체는 값의 저장소(Field) 와 기능(Method)으로 이루어져 있다.
 *  	- 객체의 설계도 역할이라는 의미는 해당 클래스로 객체를 생성했을 때
 *  	    그 객체가 어떤 저장소와 어떤 기능을 갖게 할지 정할 수 있다라는 의미이다.
 *  2. Data Type의 역할
 *  3. static 자원들을 감싸고 있는 역할
 */

public class Car {
	
	//필드(저장소) 정의하기
	public String name; //public : 어디서든 접근
	public int cc;
	
	public void drive() { //달리는 메소드 (기능, 함수)
		System.out.println("달려요!");
	}
	public void hothip() {
		System.out.println("엉덩이를 따뜻하게 해요");
	}
}
