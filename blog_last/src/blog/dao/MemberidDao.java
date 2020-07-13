package blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import blog.commons.DBUtil;
import blog.vo.Member;
import blog.vo.Memberid;

public class MemberidDao {
	
	//멤버 탈퇴시 삭제된 아이디를 추가한다
	public void insertMemberid(Connection conn, String memberid) throws SQLException{
		PreparedStatement stmt = null;
		String sql = "INSERT INTO blog_memberid(memberid, memberid_date) VALUES(?,now())";
		
		int row = 0;
		//stmt 의 예외가 발생하면 종료시켜주도록 하는 게 필요하기 때문에 finally절이 필요함
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberid);
			stmt.executeUpdate();
		}finally {
			stmt.close();
		}
	}
}
