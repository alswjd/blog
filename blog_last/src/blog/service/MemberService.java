package blog.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import blog.commons.DBUtil;
import blog.dao.MemberDao;
import blog.dao.MemberidDao;
import blog.vo.Member;
//service 영역에서는 트렌젝션 / 페이징 등 dao나 controller 에서 처리하지 않은 것은 모두 서비스가 실행함
public class MemberService {

	private MemberDao memberDao;
	private MemberidDao memberidDao;
	
	//level update
	public Member levelUpdate(Member member) {
		this.memberDao = new MemberDao();
		memberDao.updateMemberLevel(member);
		return member;
	}
	
	//관리자용 멤버관리
	public Map<String, Object> getMemberList(int currentPage, int rowPerPage){
		Map<String, Object> map = new HashMap<String, Object>();
		Connection conn = null;
		List<Member> member = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			this.memberDao = new MemberDao();
			//페이징
			int beginRow = (currentPage-1)*rowPerPage;  //before dao 호출
			
			member = memberDao.selectMemberList(conn, beginRow, rowPerPage);
			count = memberDao.countMember(conn);
			
			int lastPage = count / rowPerPage;	//after dao 호출
			if(count % rowPerPage != 0) {
				lastPage += 1;
			}
			
			//리던값이 두개가 됨 근데 리턴은 하나밖에 못 함 그래서 Map()함수 사용
			map.put("member",member);
			map.put("lastPage",lastPage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return map; //map 으로 리턴
	}
	
	//회원가입 아이디 중복 되는지 여부 - select 는 트렌젝션과 상관이 없음 
	public boolean addMember(Member member) {
		memberDao = new MemberDao();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			if(memberDao.selectMemberId(conn, member.getMemberId())) {//== true 일 때는 생략 가능 - if 문 안에 조건은 무조건 T/F니까
				memberDao.insertMember(conn, member);
				return true; //if문 안에가 실행되면 T리턴
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	//회원탈퇴
	public boolean removeMember(Member member) {
		boolean tf = false;
		Connection conn = null;
		memberDao = new MemberDao();
		memberidDao = new MemberidDao();
		
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);//자동으로 커밋 못하도록 설정시켜놓음
			
			
			int row = memberDao.deleteMember(conn, member);
			if(row == 1) {//삭제 성공했을 경우
				memberidDao.insertMemberid(conn, member.getMemberId());
				tf = true;
			}
			
			conn.commit(); //커밋 시켜줌
			//만약 두개의 getConnection을 실행하다가 insertMemberid메소드에서 오류가 발생되면 더이상 코드가 진행되지 않기 때문에
			//conn.commit(); 코드까지 실행되지 않는다
			
		}catch (Exception e) {
			try {
				//그래서 catch절에서 롤백을 실행시켜주도록 설정하고 다시 conn.setAutoCommit(false);로 돌아가서 두개의 메소드 모두 실행 취소
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			DBUtil.close(null, null, conn);
		}
		
		return tf;
	}
}
