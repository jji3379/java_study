package test.mypac;

public class MyWeapon extends Weapon{ //1. 추상클래스를 상속받으면
	
	//2. 미완성의 추상 메소드를 반드시 오버라이드 해야한다.
	//부모가 미완성으로 추상으로 해놨으면 자식이 꼭 오버라이드 해야된다.
	@Override
	public void attack() {
		System.out.println("코로나를 공격해요~");
	}
}
