package test.main;

import test.mypac.Member;

public class MainClass05 {
	public static void main(String[] args) {
		/*
		 *	두명의 회원정보를 2개의 Member 객체를 생성해서 담아 보세요. 
		 */
		Member mem1=new Member();
		Member mem2=new Member();
		
		mem1.num=1;
		mem2.num=2;
		mem1.name="김구라";
		mem2.name="해골";
		mem1.addr="노량진";
		mem2.addr="행신동";
		
		mem1.showInfo();
		mem2.showInfo();
	}
}
