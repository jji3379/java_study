package test.main;

import test.mypac.Remocon;

public class MainClass04 {
	public static void main(String[] args) {
		Remocon r1=new Remocon() { //Class : name? implements Remocon{ }
			@Override
			public void up() {
				System.out.println("채널을 올려요");
			}
			@Override
			public void down() {
				System.out.println("채널을 내려요");
			}			
		};
		//r1 지역변수에 담긴값을 3번 사용해 보기
		useRemocon(r1);
		
		//Remocon type 의 참조값을 한번만 (일회성)으로 사용하기
		useRemocon(new Remocon() {
			@Override
			public void up() {
				System.out.println("커튼을 올려요");
			}
			
			@Override
			public void down() {
				System.out.println("커튼을 내려요");
			}
		});
	}

	public static void useRemocon(Remocon r) {
		r.up();
		r.down();
	}
}