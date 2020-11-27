package test.main;

import test.mypac.Drill;

public class MainClass05 {
	public static void main(String[] args) {
		useDrill(new Drill() {
			@Override
			public void hole() {
				System.out.println("바닥에 구멍 3개 뚫어요");
			}
		});
		
		// () 위의 hole(),  {} hole() 옆에 {},
		// ( )는 useDrill( ) 이거 
		useDrill(()->{ //위의 내용 요약 버전, 메소드 하나인 경우에 사용
			System.out.println("천장에 구멍을 2개 뚫어요");
		});
		
		//풀어쓴 경우
		Drill d1=()->{
			System.out.println("아무데나 구멍을 뚫어요");
		};
		useDrill(d1);
	}
	
	public static void useDrill(Drill d) {
		d.hole();
	}
}