package test.main;

import test.member.dao.MemberDao;

/*
 *	member 테이블에서 
 * 	회원번호가 801 번 회원의 정보를 삭제하는 기능을 완성해 보기
 *	hint
 *	new DBConnect().getConn();	
 */
public class MainClass05 {
	public static void main(String[] args) {
		MemberDao dao = new MemberDao();
		dao.delete(801);
		
		new MemberDao().delete(801);
	}
}

