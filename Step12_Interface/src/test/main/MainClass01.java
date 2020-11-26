package test.main;

import test.mypac.MyRemocon;
import test.mypac.Remocon;

public class MainClass01 {
	public static void main(String[] args) {
		//Remocon r1=null; //데이터 타입의 역할
		// Remocon r1=new Remocon(); 단독 객체 생성 불가
	    //SF : static Final, 단 수정 불가. 읽기 전용.
		
		//단독 객체 생성 불가
		Remocon r1=new MyRemocon();
		//Remocon 인터페이스에 정의된 메소드 호출하기
		r1.up();
		r1.down();
		//Remocon 인터페이스에 정의된 static final 상수 참조하기
		System.out.println(Remocon.COMPANY);
		//Remocon.COMPANY="삼성" //final 필드임으로 수정불가
	}
}
