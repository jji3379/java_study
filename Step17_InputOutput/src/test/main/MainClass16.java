package test.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainClass16 {
	public static void main(String[] args) {
		/*	FileinputStream 객체를 이용해서
		 *	c:/myFolder/1.jpg 에서 byte 데이터를 읽어들인 다음
		 * 
		 * 	FileOutputStream 객체를 이용해서
		 * 	c:/myFolder/copied.jpg 파일에 출력하기
		 */
		//FileInputStream type의 참조값을 담을 지역변수 미리 만들기
		FileInputStream fis=null; 
		//FilOutputStream type의 참조값을 담을 지역변수 미리 만들기
		FileOutputStream fos=null;
		try {
			//파일로 부터 byte 알갱이를 읽어들일 수 있는 객체 생성
			 fis=new FileInputStream(new File("c:/myFolder/1.jpg"));
			fos=new FileOutputStream(new File("c:/myFolder/copied.jpg"));
			while(true) {
				int data=fis.read(); //1byte 읽어오기
				System.out.println(data);
				if(data==-1) {//더이상 읽을 데이터가 없으면
					break;	  //while 반복문 탈출
				}
				fos.write(data);
				fos.flush();
			}
			
			System.out.println("파일을 성공적으로 복사 했습니다.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {//예외가 발생하던 안하던 실행이 보장되는 블럭
			try {
				//마무리 작업 (새로 open 한 스트림을 닫아 주어야 한다.)
				fis.close(); //안하면 깨짐
				fos.close();
			}catch(Exception e) {}			
		}
	}
}
