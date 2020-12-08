package test.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import test.member.dto.MemberDto;
import test.util.DBConnect;

/*
 *	Scanner 객체를 이용해서 검색할 회원의 번호를 입력 받아서 
 * 	입력받은 숫자를 이용해서 SELECT 문을 수행하고
 * 	결과값을 MemberDto 객체를 생성해서 담아 보세요.
 * 
 * 	결과가 없다면 MemberDto 객체를 생성하지 마세요 (null 인 상태로 두기)
 */
public class MainClass07 {
	public static void main(String[] args) {
		Scanner scan= new Scanner(System.in);
		System.out.println("검색할 회원번호 입력 : ");
		int num=scan.nextInt();
		
		//검색된 회원 정보가 담길 MemberDto 객체의 참조값을 답을 지역변수
		MemberDto dto=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			//Connection 객체의 참조값 얻어오기
			DBConnect DBC = new DBConnect();
			conn=DBC.getConn();		
			//실행할 SELECT 문의 뼈대 준비하기
			String sql="SELECT name,addr FROM member" //num 뽑을 필요가 없었음
					+ " WHERE num=?";
			//PreparedStatement 객체
			pstmt=conn.prepareStatement(sql);
			//?에 값 바인딩해서 SELECT 문 완성하기
			pstmt.setInt(1, num);	
			//쿼리문(SELECT) 수행하고 결과를 ResultSet 으로 받아오기
			rs=pstmt.executeQuery();
			/*
			 *	member table 에서 num 은 PRIMARY KEY 값이다 
			 * 	따라서 SELECT 된 결과 row 의 갯수는
			 * 	0이거나 1이 된다.
			 */
			
			if(rs.next()) {
				//현재 커서가 위치한 곳에서 name 칼럼의 값을 문자로 얻어내기
				String name=rs.getString("name");
				//현재 커서가 위치한 곳에서 addr 칼럼의 값을 문자로 얻어내기
				String addr=rs.getString("addr");
				//MemberDto 객체를 생성해서
				dto = new MemberDto();
				//setter 메소드를 이용해서 값을 담는다.
				dto.setNum(num);
				//ResultSet 객체에 있는 값도 얻어와서 담아 준다.
				dto.setName(name);
				dto.setAddr(addr);
			}else {
				System.out.println(num+ "번 회원은 존재하지 않습니다.");
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
		//위의 try~catch~finally 절이 수행된 후에 SELECT 된 결과가 있는지 없는지를
		//여기에서 쉽게 판별할 수 있는 방법이 있나요?
		if(dto==null) {
			System.out.println(num+ "번 회원은 존재하지 않습니다.");
		}else {
			System.out.println("번호 :"+dto.getNum()+
					", 이름 :"+dto.getName()+", 주소 :"+dto.getAddr());
		}
	}		
}
