package test.main;

import test.mypac.Rect;

public class MainClass06 {
	public static void main(String[] args) {
		/*
		 *	가로 세로의 길이가 100과 100 
		 *  가로 세로의 길이가 200과 100
		 *  가로 세로의 길이가 300과 200
		 *  인 사각형 3개가 있다.
		 *  
		 *  각각 대응되는 Rect 객체를 3개 생성해서 참조값을
		 *  rect1, rect2, rect3 라는 지역변수에 담아 보세요.
		 */
		Rect rect1=new Rect(100, 100);
		Rect rect2=new Rect(200, 100);
		Rect rect3=new Rect(300, 200);
		
		//2. rect1, rect2, rect3에 담긴 참조값을 배열에 순서대로 담아 보세요.
		// 배열의 참조값이 담길 지역 변수의 이름은 rects 로 선언하세요.
		
		Rect[] rects=new Rect[3];
		rects[0]=rect1;
		rects[1]=rect2;
		rects[2]=rect3;
		//3. rects 배열에 담긴 값을 순서대로 참조해서 .getArea() 메소드를 호출한 다음
		// 리턴되는 값을 이용해서 사각형의 넓이를 순서대로 콘솔창에 출력해 보세요.
		for(int i=0; i<rects.length; i++) {
			int S=rects[i].getArea();
			System.out.println(S);
		}
	}
}
