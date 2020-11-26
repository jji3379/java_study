package test.mypac;

public class MyRemocon implements Remocon{ // 인터페이스는 extends 아니고 implements

	@Override
	public void up() {
		System.out.println("볼륨을 올려요");
	}

	@Override
	public void down() {
		System.out.println("볼륨을 내려요");
	} 

}
