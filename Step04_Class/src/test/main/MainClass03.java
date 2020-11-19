package test.main;

import test.mypac.MyUtil;

public class MainClass03 {
	public static void main(String[] args) {
		//MyUtil 클래스에 정의된 static 메소드 호출하기
		MyUtil.playMusic(); //static 있으면 new 없어도 바로 나옴, static 없으면 new로 호출 해야함
		MyUtil.owner="김구라"; //MyUtil 클래스에 정으된 static 필드 참조해서 값 대입하기
		System.out.println("System 클래스에서 out객체 안의 println() 호출");
	}
}
