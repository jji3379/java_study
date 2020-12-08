package test.main;

import java.sql.Connection;
import java.sql.PreparedStatement;

import test.util.DBConnect;

/*
 *	member 테이블에서 
 * 	회원번호가 801 번 회원의 정보를 삭제하는 기능을 완성해 보기
 *	hint
 *	new DBConnect().getConn();	
 */
public class MainClass05 {
	public static void main(String[] args) {
		//삭제할 회원의 번호
		int num=805;
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		int flag=0;
		try {
			DBConnect DBC = new DBConnect();
			conn=DBC.getConn();		
			//미완성의 DELETE 문
			String sql="DELETE FROM member"
					+ " WHERE num=?";
			pstmt=conn.prepareStatement(sql);
			//?에 순서대로 값을 바인딩 하기
			//바인딩 => binding => 연결하기 => 붙이기 => ???
			pstmt.setInt(1, num);	// 첫번째 ? 에 addr 고양이
			//완성된 sql 문을 수행하고 변화된 row 의 개수를 리턴 받는다.
			flag=pstmt.executeUpdate();	//INSERT, UPDATE, DELETE 는 execute Update
			System.out.println("회원 정보를 삭제 했습니다");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
				
			} catch (Exception e) {}
		}
		if (flag>0) {
			System.out.println("작업(DELETE) 성공");
		} else {
			System.out.println("작업(DELETE) 실패");
		}
	}
}

