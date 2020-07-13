package blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import blog.vo.Likey;

public class LikeyDao {
	
	//이미 좋아요를 누른 사람
	//언제가 T를 반환할 것인지 잘 정해야 함
	//좋아요를 누를 수 있다면 T
	public boolean isInsertLikey(Connection conn, Likey likey) throws SQLException {
		String sql = "SELECT * FROM blog_likey WHERE post_no = ? AND member_id=?";
		boolean flag = true;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, likey.getPostNo());
			stmt.setString(2, likey.getMemberId());
			rs = stmt.executeQuery();
			if(rs.next()) { //좋아요를 누를 수 없다면 리턴값이 F가 나옴
				flag = false;
			}
		}finally {
			rs.close();
			stmt.close();
		}
		
		return flag;
	}
	
	
	//likey 추가
	public void insertLikey(Connection conn, Likey likey) throws SQLException {
		String sql = "INSERT INTO blog_likey(post_no, member_id,likey_ck,likey_date) VALUES(?,?,1,now())";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, likey.getPostNo());
			stmt.setString(2, likey.getMemberId());
			stmt.executeUpdate();
		}finally {
			stmt.close();
		}
	}
	
	//count (cK) 체크 값
	public int selectLikeyCount(Connection conn, int postNo) throws SQLException {
		int count = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT SUM(likey_ck) FROM blog_likey WHERE post_no=?";
		//String sql = "SELECT COUNT(likey_ck) FROM likey WHERE post_no=?"; 도 상관 없음
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, postNo);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("SUM(likey_ck)");
			}
		}finally {
			rs.close();
			stmt.close();
		}
		
		return count;
	}
	
}
