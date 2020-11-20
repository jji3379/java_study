package test.mypac;
/*
 *	static 자원을 포함하는 용도로 사용해 보기
 */
public class MyUtil {
	//non static 필드
	public int version;
	
	//static 필드
	public static String owner;
	
	public static void playMusic() {
		System.out.println("음악을 틀어요!");
		int a=this.version; // static 영역에서 static 아닌 친구 못부름
		String c=MyUtil.owner;
	}
}
