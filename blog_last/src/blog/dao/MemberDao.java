package blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blog.commons.DBUtil;
import blog.vo.Member;
import blog.vo.Subject;

public class MemberDao {
	
	//페이징 - 마지막 페이지
	public int countMember(Connection conn) throws SQLException {
		int count = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM blog_member";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1); //rs.getInt("COUNT(*)");
			}
		}finally {
			rs.close();
			stmt.close();
		}
		
		return count;
	}
	
	//관리자 용 멤버 관리 / 페이징
	public List<Member> selectMemberList(Connection conn, int beginRow, int rowPerPage) throws SQLException{
		List<Member> list = new ArrayList<Member>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT member_id, member_level FROM blog_member LIMIT ?,?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Member m = new Member();
				m.setMemberId(rs.getString("member_id"));
				m.setMemberLevel(rs.getInt("member_level"));
				list.add(m);
			}
		}finally {
			rs.close();
			stmt.close();
		}
		
		return list;
	}
	
	//before sign up - 회원가입 전에 id 사용 여부
	public boolean selectMemberId(Connection conn, String memberId) throws SQLException {
		boolean flag = true;
		PreparedStatement stmt = null;
		//String sql1 = "SELECT member_id FROM member WEHRE member_id=?";
		//String sql2 = "SELECT memberid FROM memberid WEHRE memberid=?"; - 두개의 쿼리를 하나로 합친다 - UNION 사용 (열과 열의 더하기) / JOIN(행과 행의 더하기)
																		//UNION, SubQuery, JOIN 알기
		String sql = "SELECT mi FROM (SELECT member_id mi FROM blog_member UNION SELECT memberid mi FROM blog_memberid) t WHERE t.mi = ? ";
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				flag = false; //데이터 값이 있다면 false - 회원가입 아이디로 적합하지 않음
			}
		}finally {
			rs.close();
			stmt.close();
		}
		
		return flag; //회원가입이 가능한 id는 T 를 리턴한다.
	}
	
	//after sign up
	public void insertMember(Connection conn, Member member) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "INSERT INTO blog_member(member_id, member_pw,member_level,member_date) VALUES(?,?,10,now())";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			stmt.executeUpdate();
		}finally {
			stmt.close();
		}
	}
	
	//delete
	public int deleteMember(Connection conn, Member member) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "DELETE FROM blog_member WHERE member_id=? AND member_pw=?";
		
		int row = 0;
		//stmt 의 예외가 발생하면 종료시켜주도록 하는 게 필요하기 때문에 finally절이 필요함
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2,member.getMemberPw());
			
			row = stmt.executeUpdate();
		}finally {
			stmt.close();
		}
		
		return row; //삭제한 행의 수
	}
	
	//개인정보
	public Member selectMemberOne(String memberId) {
		String sql = "SELECT * FROM blog_member WHERE member_id=?";
		
		Member member = new Member();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				member.setMemberId(rs.getString("member_id"));
				member.setMemberLevel(rs.getInt("member_level"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(rs, stmt, conn);
		}
		
		return member;
	}
	
	
	//login
	public Member selectMemberOne(Member member) {
		String sql = "SELECT member_id, member_level FROM blog_member WHERE member_id=? AND member_pw=?";
		
		Member returnMember = new Member();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				returnMember = new Member();
				returnMember.setMemberId(rs.getString("member_id"));
				returnMember.setMemberLevel(rs.getInt("member_level"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(rs, stmt, conn);
		}
		
		return returnMember;
	}

	//level update
	public void updateMemberLevel(Member member) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "UPDATE blog_member SET member_level=? WHERE member_id=?";
		
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, member.getMemberLevel());
			stmt.setString(2, member.getMemberId());
			stmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
}
