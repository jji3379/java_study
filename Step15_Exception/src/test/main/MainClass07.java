package test.main;

import java.util.Random;

/*
 *	프로그래머가 직접 예외 클래스를 정의하고
 *	필요한 시점에 예외를 발생 시킬수도 있을까? 
 *
 *	Exception or RuntimeException 클래스를 상속 받으면 가능하다!
 */
public class MainClass07 {
	public static void main(String[] args) {
		System.out.println("main 메소드가 시작 되었습니다.");
		
		Random ran=new Random();
		int ranNum=ran.nextInt(3);
		if(ranNum==0) {//우연히 0이 나오면
			//HeadacheException 발생 시키기
			throw new HeadacheException("으악 머리 아퍼 ㄷㄷ");
		}
		System.out.println("main 메소드가 종료 되었습니다.");
	}
	//머리아픈 Exception
	static class HeadacheException extends RuntimeException{
		//예외 메세지를 String type 으로 전달 받는 생성자
		public HeadacheException(String msg) {
			//부모 생성자에 전달해야 한다.
			super(msg); //내가 하고 싶은 작업이 부모에 있으니까 super로 부모에 보내줘야한다.
		}
		
	}
}
