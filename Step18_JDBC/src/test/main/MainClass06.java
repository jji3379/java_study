package test.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import test.util.DBConnect;

/*
 *	Scanner 객체를 이용해서 검색할 회원의 번호를 입력 받아서 
 * 	DB 에서 SELECT 하고
 * 	결과값이 있으면 해당회원의 정보를 콘솔창에 출력하고
 * 	결과값이 없으면 해당 회원은 존재하지 않습니다. 라고 출력되는 프로그래밍을 해보세요.
 *	hint : String sql="SELECT num,name,addr FROM member WHERE num=?";
 *	
 *	예)
 *	검색할 회원번호 입력 :
 *	801
 *	801번 회원은 존재하지 않습니다.
 *
 *	예)
 *	검색할 회원번호 입력 :
 *	850
 *	번호 : 850, 이름 : 톰캣, 주소 : 건물 옥상
 */
public class MainClass06 {
	public static void main(String[] args) {
		Scanner scan= new Scanner(System.in);
		System.out.println("검색할 회원번호 입력 : ");
		int numput=scan.nextInt();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			//Connection 객체의 참조값 얻어오기
			DBConnect DBC = new DBConnect();
			conn=DBC.getConn();		
			//실행할 SELECT 문의 뼈대 준비하기
			String sql="SELECT num,name,addr FROM member" //num 뽑을 필요가 없었음
					+ " WHERE num=?";
			//PreparedStatement 객체
			pstmt=conn.prepareStatement(sql);
			//?에 값 바인딩해서 SELECT 문 완성하기
			pstmt.setInt(1, numput);	
			//쿼리문(SELECT) 수행하고 결과를 ResultSet 으로 받아오기
			rs=pstmt.executeQuery();
			/*
			 *	member table 에서 num 은 PRIMARY KEY 값이다 
			 * 	따라서 SELECT 된 결과 row 의 갯수는
			 * 	0이거나 1이 된다.
			 */
			
			if(rs.next()) {
				//현재 커서가 위치한 곳에서 num 칼럼의 값을 정수로 얻어내기
				int num=rs.getInt("num");
				//현재 커서가 위치한 곳에서 name 칼럼의 값을 문자로 얻어내기
				String name=rs.getString("name");
				//현재 커서가 위치한 곳에서 addr 칼럼의 값을 문자로 얻어내기
				String addr=rs.getString("addr");
				System.out.println("번호 : "+num+" 이름 : "+name+" 주소 : "+addr);
			}else {
				System.out.println(numput+ "번 회원은 존재하지 않습니다.");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
				if(rs!=null)rs.close();
			} catch (Exception e) {}
		}
	}
}
