package test.main;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.member.dto.MemberDto;
import test.util.DBConnect;

/*
 *	member 테이블에 있는 회원 목록을 SELECT 하고
 * 	num 에 대해서 오름차순 정렬을 해서
 * 	List<MemberDto> 형태로 만들어 보세요.
 */
public class MainClass08 {
	public static void main(String[] args) {
		List<MemberDto> list=new ArrayList<>();
		
		//검색된 회원 정보가 담길 MemberDto 객체의 참조값을 답을 지역변수
		MemberDto dto=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			//Connection 객체의 참조값 얻어오기
			DBConnect DBC = new DBConnect();
			conn=DBC.getConn();		
			String sql="SELECT num,name, addr FROM member" 
					+ " ORDER BY num ASC";
			//PreparedStatement 객체
			pstmt=conn.prepareStatement(sql);
			//쿼리문(SELECT) 수행하고 결과를 ResultSet 으로 받아오기
			rs=pstmt.executeQuery();
			/*
			 *	member table 에서 num 은 PRIMARY KEY 값이다 
			 * 	따라서 SELECT 된 결과 row 의 갯수는
			 * 	0이거나 1이 된다.
			 */
			
			while(rs.next()) {
				//MemberDto 객체를 생성해서
				dto = new MemberDto();
				//커서가 위치한 곳의 데이터를 읽어와서 MemberDto 객체를 생성해서 담고
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setAddr(rs.getString("addr"));
				//생성된 MemberDto 객체의 참조값을 ArrayList 객체에 누적 시킨다.
				list.add(dto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (Exception e) {}
		}
		
		//위의 코드를 수행한 후의 배열의 방의 개수 참조해 보기
		int size=list.size();
		
		showInfo(list);
		System.out.println("main 메소드가 종료 됩니다.");
	}
	
	public static void showInfo(List<MemberDto> list) {
		for(MemberDto tmp:list) {
			System.out.println(tmp.getNum()+" | "+tmp.getName()+" | "+
					" | "+tmp.getAddr());
		}
	}
}
