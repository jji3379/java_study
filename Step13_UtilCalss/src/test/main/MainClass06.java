package test.main;

import java.util.ArrayList;
import java.util.List;

import test.mypac.MemberDto;

public class MainClass06 {
	public static void main(String[] args) {
		//MemeberDto 객체의 참조값을 담을 수 있는 ArrayList 객체를 생성해서
		//ArrayList 객체의 참조값을 List type 지역변수 Members에 담기
		List<MemberDto> members=new ArrayList<MemberDto>();
		//3명의 회원 정보를 members 배열에 담아 보세요.
	
		/*	MemeberDto mem1=new MemberDto();
		 * 	mem1.setNum(1);
		 * 	mem1.setName(1);
		 * 	mem1.setAddr(1);
		 * 	members.add(mem1);
		 */
		
		MemberDto mem1=new MemberDto(1,"고양이", "강남");
		members.add(mem1);
		MemberDto mem2=new MemberDto(2,"길냥이", "역삼");
		members.add(mem2);
		MemberDto mem3=new MemberDto(3,"뚱냥이", "삼성");
		members.add(mem3);
		
		//members.add(new MemberDto(1,"고양이","강남"));
		//members.add(new MemberDto(2,"뚱냥이","역삼"));
		//members.add(new MemberDto(3,"길냥이","삼성"));
		
		/*
		 *	members 에 들어 있는 참조값을 이용해서 
		 * 	반복문 돌면서 아래와 같은 형식으로 회원 정보를 출력해 보세요.
		 * 	
		 * 	번호는 1 이름은 김구라 주소는 노량진
		 * 	번호는 2 이름은 해골 주소는 행신동
		 * 	번호는 3 이름은 원숭이 주소는 상도동
		 */
		
		for(int i=0; i<members.size(); i++) {
			String info="번호는"+members.get(i).getNum()+
					" 이름은"+members.get(i).getName()+
					" 주소는"+members.get(i).getAddr();
			System.out.println(info);
		}
		
		System.out.println("-----------------------");
		for(int i=0; i<members.size(); i++) {
			MemberDto tmp=members.get(i);
			String info="번호는"+tmp.getNum()+
					" 이름은"+tmp.getName()+
					" 주소는"+tmp.getAddr();
			System.out.println(info);
		}
		System.out.println("-----------------------");
		//확장 for 문을 사용하면
		for(MemberDto tmp:members) { //위에랑 같은 거임 이걸 더 많이 씀 //members's Generic Type
			String info="번호는"+tmp.getNum()+
					" 이름은"+tmp.getName()+
					" 주소는"+tmp.getAddr();
			System.out.println(info);
		}
	}
}