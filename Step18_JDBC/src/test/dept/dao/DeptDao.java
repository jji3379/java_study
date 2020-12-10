package test.dept.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.dept.dto.DeptDto;
import test.util.DBConnect;

public class DeptDao {
	public List<DeptDto> selectAll(){
		//DeptDto 객체를 누적시킬 ArrayList 객체 생성하기 
		List<DeptDto> list=new ArrayList<>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=new DBConnect().getConn();
			//실행할 SELECT 문 작성
			String sql="SELECT deptno,dname,loc"
					+ " FROM dept"
					+ " ORDER BY deptno DESC";
			pstmt=conn.prepareStatement(sql);
			//? 에 바인딩 할게 있으면 하고 아님 말고
			rs=pstmt.executeQuery();
			while(rs.next()) {
				//select 된 row 하나의 정보를 DeptDto 객체를 생성해서 담고 
				DeptDto dto=new DeptDto();
				dto.setDeptno(rs.getInt("deptno"));
				dto.setDname(rs.getString("dname"));
				dto.setLoc(rs.getString("loc"));
				
				//새로 생성한 DeptDto 객체의 참조값을 ArrayList 객체에 누적시킨다.
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
	
	//회원 한명의 정보를 SELECT 해서 리턴하는 메소드
	public DeptDto select(int deptno) {
		//회원한명의 정보를 담고 있는 MemberDto 객체를 담을 지역변수 만들기 
		DeptDto dto=null;
		//필요한 객체의 참조값을 담을 지역변수 만들기 
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=new DBConnect().getConn();
			String sql="SELECT dname,loc FROM member"
					+ " WHERE deptno=?";
			pstmt=conn.prepareStatement(sql);
			// ? 에 값 바인딩하기
			pstmt.setInt(1, deptno);
			//SELECT 문 수행하고 결과를 ResultSet 으로 받기
			rs=pstmt.executeQuery();
			if(rs.next()) {//만일 select 된 row 가 있다면
				//결과를 DeptDto 객체를 생성해서 담는다.
				dto=new DeptDto();
				dto.setDeptno(deptno);
				dto.setDname(rs.getString("dname"));
				dto.setLoc(rs.getString("loc"));
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
		
		return dto;
	}
	
	
	//1. 회원 한명의 정보를 저장하는 메소드를 만들어 보세요.
	// 메소드명 : insert
	// 리턴 type : 알아서
	// 메소드에 전달하는 인자의 type : DeptDto 
	public boolean insert(DeptDto dto) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int flag=0;
		try {
			conn=new DBConnect().getConn();
			String sql="INSERT INTO dept"
					+ " (deptno,dname,loc)"
					+ " VALUES(?, ?, ?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getDeptno());
			pstmt.setString(2, dto.getDname());
			pstmt.setString(3, dto.getLoc());
			//INSERT 문 수행하기  ( 1개의 row 가 추가 되었으므로 1 이 러턴된다)
			flag=pstmt.executeUpdate();
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
	//2. 회원 한명의 정보룰 수정하는 메소드를 만들어 보세요.
	// 메소드명 : update
	// 리턴 type : 알아서
	// 메소드에 전달하는 인자의 type : DeptDto 
	public boolean update(DeptDto dto) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int flag=0;
		try {
			conn=new DBConnect().getConn();
			//실행할 sql 문 작성
			String sql="UPDATE dept"
					+ " SET dname=?, loc=?"
					+ " WHERE deptno=?";
			pstmt=conn.prepareStatement(sql);
			//?에 값을 바인딩 할게 있으면 여기서 한다.
			pstmt.setString(1, dto.getDname());
			pstmt.setString(2, dto.getLoc());
			pstmt.setInt(3, dto.getDeptno());
			flag=pstmt.executeUpdate();
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
	
	
	//회원 한명의 정보를 삭제하는 메소드
	public boolean delete(int deptno) {
		
		//필요한 참조값을 담을 지역 변수 미리 만들고 초기화 하기 
		Connection conn=null;
		PreparedStatement pstmt=null;
		int flag=0;
		try {
			//Connection 객체의 참조값 얻어오기
			conn=new DBConnect().getConn();
			//실행할 sql 문의 뼈대 준비하기
			String sql="DELETE FROM dept WHERE deptno=?";
			//PreparedStatement 객체의 참조값 얻어오기
			pstmt=conn.prepareStatement(sql);
			//? 에 값 바인딩하기
			pstmt.setInt(1, deptno);
			//sql 문 실행하고 변화된 row  의 갯수 리턴 받기
			flag=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//마무리 작업
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
}
