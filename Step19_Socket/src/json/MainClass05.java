package json;

import java.io.ObjectInputStream.GetField;

import org.json.JSONArray;

public class MainClass05 {
	public static void main(String[] args) {
		/*
		 *	인터넷으로 부터 다운받은 문자열이 JSON 형식인 경우도 많이 발생한다.
		 *	해당 문자열에서 원하는 정보를 추출하는 연습을 해 보자 
		 */
		String info="[10, 20, 30, 40, 50]";
		//위의 문자열에서 숫자만 순서대로 추출해서 콘솔창에 순서대로 출력할 수 있을까?
		JSONArray nums = new JSONArray(info);
		for (int i = 0; i < nums.length(); i++) {
			System.out.println(i+"번째 인덱스: "+nums.getInt(i));
		}
	}
}
