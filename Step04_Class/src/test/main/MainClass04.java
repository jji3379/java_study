package test.main;

import test.mypac.Member;

public class MainClass04 {
	public static void main(String[] args) {
		/*
		 *  회원 한명의 정보
		 * 	번호 : 1
		 * 	이름 : 김구라
		 * 	주소 : 노랑진
		 * 	이라고 가정하고 해당 정보를 Member 객체에 담는 코드를 작성해 보세요.
		 */
		Member mem1=new Member();
		mem1.num=1;
		mem1.name="김구라";
		mem1.addr="노량진";
		
		
		//mem1 에 들어 있는 참조값을 이용햇
		//콘솔창에 아래와 같은 형식으로 출력해 보세요.
		//번호 : 1, 이름 : 김구라, 주소 : 노량진
		System.out.println("번호 :" +mem1.num +", 이름 :" +mem1.name+", 주소 :" +mem1.addr );
		
		 /*
	       *위의 작업은 javascript 에서 아래의 작업과 같다
	       *
	       * let mem1={};
	       * mem.num=1;
	       * mem.name="김구라";
	       * mem.addr="노량진";
	       *
	       */

		mem1.showInfo();
	}
}
