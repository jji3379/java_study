package todo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import test.util.DBConnect;
import todo_dto.TodoDto;

public class TodoDao {
	//모든 할일 정보를 SELECT 해서 리턴하는 메소드
		public List<TodoDto> selectAll(){
			//TodoDto 객체를 누적시킬 ArrayList 객체 생성하기 
			List<TodoDto> list=new ArrayList<>();
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				conn=new DBConnect().getConn();
				//실행할 SELECT 문 작성
				String sql="SELECT num,content,TO_CHAR(regdate,'YYYY \"년\" MM \"월\" DD \"일\" AM HH:MI') AS regdate"
						+ " FROM todo"
						+ " ORDER BY num DESC";
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					//select 된 row 하나의 정보를 TodoDto 객체를 생성해서 담고 
					TodoDto dto=new TodoDto();
					dto.setNum(rs.getInt("num"));
					dto.setContent(rs.getString("content"));
					dto.setRegdate(rs.getString("regdate"));
					//새로 생성한 TodoDto 객체의 참조값을 ArrayList 객체에 누적시킨다.
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
		}//SelectAll END
		
		//할일 하나의 정보를 SELECT 해서 리턴하는 메소드
		public TodoDto select(int num) {
			//할일 하나의 정보를 담고 있는 TodoDto 객체를 담을 지역변수 만들기 
			TodoDto dto=null;
			//필요한 객체의 참조값을 담을 지역변수 만들기 
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				conn=new DBConnect().getConn();
				String sql="SELECT content,regdate FROM todo"
						+ " WHERE num=?";
				pstmt=conn.prepareStatement(sql);
				// ? 에 값 바인딩하기
				pstmt.setInt(1, num);
				//SELECT 문 수행하고 결과를 ResultSet 으로 받기
				rs=pstmt.executeQuery();
				if(rs.next()) {//만일 select 된 row 가 있다면
					//결과를 TodoDto 객체를 생성해서 담는다.
					dto=new TodoDto();
					dto.setNum(num);
					dto.setContent(rs.getString("content"));
					dto.setRegdate(rs.getString("regdate"));
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
		}//select END
		
		
		//1. 할일 하나의 정보를 저장하는 메소드를 만들어 보세요.
		// 메소드명 : insert
		// 리턴 type : 알아서
		// 메소드에 전달하는 인자의 type : TodoDto 
		public boolean insert(TodoDto dto) {
			Connection conn=null;
			PreparedStatement pstmt=null;
			int flag=0;
			try {
				conn=new DBConnect().getConn();
				String sql="INSERT INTO todo"
						+ " (num,content,regdate)"
						+ " VALUES(member_seq.NEXTVAL, ?, SYSDATE)";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, dto.getContent());
				
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
		
		}//Insert END
		
		//2. 할일 하나의 정보룰 수정하는 메소드를 만들어 보세요.
		// 메소드명 : update
		// 리턴 type : 알아서
		// 메소드에 전달하는 인자의 type : TodoDto 
		public boolean update(TodoDto dto) {
			Connection conn=null;
			PreparedStatement pstmt=null;
			int flag=0;
			try {
				conn=new DBConnect().getConn();
				//실행할 sql 문 작성
				String sql="UPDATE todo"
						+ " SET content=?, regdate=?"
						+ " WHERE num=?";
				pstmt=conn.prepareStatement(sql);
				//?에 값을 바인딩 할게 있으면 여기서 한다.
				pstmt.setString(1, dto.getContent());
				pstmt.setString(2, dto.getRegdate());
				pstmt.setInt(3, dto.getNum());
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
				
			
		}//Update END
		
		
		//할일 하나의 정보를 삭제하는 메소드
		public boolean delete(int num) {
			
			//필요한 참조값을 담을 지역 변수 미리 만들고 초기화 하기 
			Connection conn=null;
			PreparedStatement pstmt=null;
			int flag=0;
			try {
				//Connection 객체의 참조값 얻어오기
				conn=new DBConnect().getConn();
				//실행할 sql 문의 뼈대 준비하기
				String sql="DELETE FROM todo WHERE num=?";
				//PreparedStatement 객체의 참조값 얻어오기
				pstmt=conn.prepareStatement(sql);
				//? 에 값 바인딩하기
				pstmt.setInt(1, num);
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
		}//Delete END
}//TodoDao END
