package blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blog.vo.Comment;

public class CommentDao {
	
	public void deleteComment(Connection conn, int postNo) {
		String sql = "DELETE FROM blog_comment WHERE post_no=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, postNo);
			stmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	//comment List
	public List<Comment> selectCommentListByPostNo(Connection conn, int postNo) throws SQLException{
		List<Comment> list = new ArrayList<Comment>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM blog_comment WHERE post_no=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, postNo);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Comment c = new Comment();
				c.setCommentNo(rs.getInt("comment_no"));
				c.setCommentContent(rs.getString("comment_content"));
				c.setMemberId(rs.getString("member_id"));
				list.add(c);
			}
		}finally {
			stmt.close();
			rs.close();
		}
		return list;
	}
	
	
	//insert Comment
	public void insertComment(Connection conn, Comment comment) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "INSERT INTO blog_comment(post_no, member_id,comment_content,comment_date) VALUES(?,?,?,NOW())";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, comment.getPostNo());
			stmt.setString(2, comment.getMemberId());
			stmt.setString(3, comment.getCommentContent());
			stmt.executeUpdate();
		}finally {
			stmt.close();
		}
	}
}
