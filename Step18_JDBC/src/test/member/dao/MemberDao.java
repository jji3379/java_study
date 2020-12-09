package test.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import test.util.DBConnect;

/*
 *	회원정보(member 테이블) 의 내용을 
 * 	select 
 * 	insert
 * 	update
 * 	delete
 * 	작업을 할 객체를 생성할 클래스 설계하기
 */
public class MemberDao {
	//회원 한명의 정보를 삭제하는 메소드
	public boolean delete(int num) {
		//삭제할 회원의 번호
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
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(pstmt!=null)pstmt.close();
					if(conn!=null)conn.close();
					
				} catch (Exception e) {}
			}
			if (flag>0) {
				return true;
			} else {
				return false;
			}
	}
}
