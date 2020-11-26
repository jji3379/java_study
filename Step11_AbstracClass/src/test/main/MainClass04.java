package test.main;

import test.mypac.Weapon;

public class MainClass04 {
	public static void main(String[] args) {
		/*
		 *	Weapon 추상클래스를 상속 받은 익명의 local inner 클래스를 이용해서
		 *	Weapon type 의 참조값을 얻어내서 w1 이라는 Weapon type 의
		 *	지역변수에 담기 
		 */
		
		Weapon w1=new Weapon() { 
			// 1.이름이 없는 익명의 클래스
			// 2. extends Weapon 된 클래스
			// 3. new Weapon()은 이름이 없는 클래스의 생성자를 호출했다고 간주한다. 
			@Override
			public void attack() {
				System.out.println("아무거나 공격해요!");
			}
		};
		useWeapon(w1);
	}
	
	public static void useWeapon(Weapon w) {
		w.prepare();
		w.attack();
	}
}
