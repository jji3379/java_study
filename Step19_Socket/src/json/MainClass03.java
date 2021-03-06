package json;

import org.json.JSONObject;

public class MainClass03 {
	public static void main(String[] args) {
		/*
		 * 이전 예제와 같은
		 * {"num":1, "name":"김구라", "isMan":true} 형식의 문자열을
		 * 라이브러리의 보움을 받지 않고 만드는 코드는 아래와 같다.
		 */
		//아래 변수의 값을 참조해서 JSON 문자열을 만든다면
		int num=1;
		String name="김구라";
		boolean isMan=true;
		
		String result1="{\"num\":"+num+",\"name\":\""+name+"\",\"isMan\":"+isMan+"}";
		System.out.println("result1:"+result1);
	
		JSONObject obj=new JSONObject();
		obj.put("num", num);
		obj.put("name", name);
		obj.put("isMan", isMan);
		
		String result2=obj.toString();
		System.out.println("result2:"+result2);
	}
}
