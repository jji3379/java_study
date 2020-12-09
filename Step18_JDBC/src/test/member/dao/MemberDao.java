package test.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.member.dto.MemberDto;
import test.util.DBConnect;

/*
 *	회원정보(member 테이블) 의 내용을 
 * 	select 
 * 	insert
 * 	update
 * 	delete	clear
 * 	작업을 할 객체를 생성할 클래스 설계하기
 * 
 * 	- 필요한 객체를 담을 지역변수를 선언하는 위치도 중요하다.
 * 	- 필요한 객체를 생성하는 위치도 중요하다.
 * 
 */
public class MemberDao {
	//모든 회원의 정보를 SELECT 해서 리턴하는 메소드
	public List<MemberDto> selectAll(){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		//MemberDto 객체를 누적시킬 ArrayList 객체 생성하기
		List<MemberDto> list=new ArrayList<>();
		try {
			conn=new DBConnect().getConn();
			
			String sql="SELECT num, name, addr"
					+ " FROM member"
					+ " ORDER BY num ASC";
			pstmt=conn.prepareStatement(sql);
			//? 에 값 바인딩 할게 있으면 하고 아님 말고 // 여기선 안함
			
			rs=pstmt.executeQuery();
			//list=new ArrayList<>(); // 이득이 없음 위에서 한번에 하는게 좋음 
			while(rs.next()) {	//MemberDto는 여러개 필요
				MemberDto dto = new MemberDto();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setAddr(rs.getString("addr"));
				//새로 생성한 MemberDto 객체의 참조값을 ArrayList 객체에 누적시킨다.
				list.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {}
		}
		return list;
	}
	
	//회원 한명의 정보를 <<SELECT>> 해서 리턴하는 메소드를  
	public MemberDto select(int num) {
		//회원한명의 정보를 담고 있는 MemberDto 객체를 담을 지역변수 만들기
		MemberDto dto=null;
		//필요한 객체의 참조값을 담을 지역변수 만들기
		Connection conn=null; //지역변수는 선언만 하면 안만들어짐
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=new DBConnect().getConn();
			String sql="SELECT name, addr FROM member"
					+ " WHERE num=?";
			pstmt=conn.prepareStatement(sql);
			//?에  값 바인딩하기
			pstmt.setInt(1, num);
			//SELECT 문 수행하고 결과를 ResultSet 으로 받기
			rs=pstmt.executeQuery();
			
			if(rs.next()) {//만일 select 된 row 가 있다면
				//결과를 MemberDto 객체를 생성해서 담는다.
				dto=new MemberDto();
				dto.setNum(num);
				dto.setName(rs.getString("name"));
				dto.setAddr(rs.getString("addr"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {}
		}
		return dto; //참조 데이터 타입이니까 0 넣으면 안됨 
	}
	
	//1.회원 한명의 정보를  <<저장>> 하는 메소드
	// 메소드명 : insert
	// 리턴 type : 알아서
	// 메소드에 전달하는 인자의 type : MemberDto
	public boolean insert(MemberDto dto) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int flag=0;
		try {
			conn=new DBConnect().getConn();
			//INSERT 해당 코드
			String sql="INSERT INTO member"
					+ " (num, name, addr)"
					+ " VALUES(member_seq.NEXTVAL, ?, ?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
			
			//INSERT 문 수행하기 (1개의 row 가 추가 되었으므로 1이 리턴된다)
			flag=pstmt.executeUpdate();
			//INSERT 문 수행하기
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {}
		}
		if(flag>0) {
			return true;
		}else {
			return false;
		}
	}
	
	//2.회원 한명의 정보를 <<수정>> 하는 메소드
	// 메소드명 : update
	// 리턴 type : 알아서
	// 메소드에 전달하는 인자의 type : MemberDto
	public boolean update(MemberDto dto) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int flag=0;
		try {
			conn=new DBConnect().getConn();
			
			//UPDATE 해당 코드
			String sql="UPDATE member"
					+ " SET name=?, addr=?"
					+ " WHERE num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
			pstmt.setInt(3, dto.getNum());
			
			//INSERT 문 수행하기 (1개의 row 가 추가 되었으므로 1이 리턴된다)
			flag=pstmt.executeUpdate();
			//INSERT 문 수행하기
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {}
		}
		if(flag>0) {
			return true;
		}else {
			return false;
		}
		
	}

	//회원 한명의 정보를 <<삭제>> 하는 메소드
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
				pstmt.setInt(1, num);	
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
