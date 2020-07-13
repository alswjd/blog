package blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import blog.commons.DBUtil;
import blog.vo.Post;

public class PostDao {
	//update post
	public void updatePost(Connection conn, Post post)  {
		System.out.println(post.getPostNo() + "<---postno");
		System.out.println(post.getSubjectName()+"<---sub");
		String sql = "UPDATE blog_post SET post_title=?, post_content=?,subject_name=?, post_date=now() WHERE post_no=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
	
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, post.getPostTitle());
			stmt.setString(2, post.getPostContent());
			stmt.setString(3, post.getSubjectName());
			stmt.setInt(4,post.getPostNo());
			rs = stmt.executeQuery();
			if(rs.next()) {
				post =  new Post();
				post.setPostTitle(rs.getString("post_title"));
				post.setPostContent(rs.getString("post_content"));
				post.setPostNo(rs.getInt("post_no"));
				post.setSubjectName(rs.getString("subject_name"));
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//delete post 
	public void deletePost(Connection conn, int postNo) {
		String sql = "DELETE FROM blog_post WHERE post_no=?";
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
	
	
	//post one
	public Post selectPostOne(Connection conn, int postNo) throws SQLException {
		String sql = "SELECT * FROM blog_post WHERE post_no=?";
		Post post = new Post();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, postNo);
			rs = stmt.executeQuery();			
			if(rs.next()) {
				post.setPostNo(rs.getInt("post_no"));
				post.setMemberId(rs.getString("member_id"));
				post.setSubjectName(rs.getString("subject_name"));
				post.setPostTitle(rs.getString("post_title"));
				post.setPostContent(rs.getString("post_content"));
				post.setPostDate(rs.getString("post_date"));
				
			}
		}finally {
			DBUtil.close(rs, stmt, null);
		}
		return post;
	}
	
	//insert Post
	public Post insertPost(Connection conn, Post post) {
		String sql = "INSERT INTO blog_post(member_id, subject_name, post_title, post_content,post_date) VALUES(?,?,?,?,now())";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Post p = new Post();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, post.getMemberId());
			stmt.setString(2, post.getSubjectName());
			stmt.setString(3, post.getPostTitle());
			stmt.setString(4, post.getPostContent());
			rs = stmt.executeQuery();
			if(rs.next()) {
				p.setMemberId(rs.getString("member_id"));
				p.setSubjectName(rs.getString("subject_name"));
				p.setPostTitle(rs.getString("post_title"));
				p.setPostContent(rs.getString("post_content"));
				p.setPostDate(rs.getString("post_date"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(rs, stmt, null);
		}
		
		return p;
	}
	
	//subject 에 따라 나타나는 Post 리스트
	public List<Post> selectPostBySubject(Connection conn, String subjectName, int beginRow, int rowPerPage) throws SQLException{
		String sql = "SELECT * FROM blog_post WHERE subject_name=? LIMIT ?,?";
		List<Post> list = new ArrayList<Post>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, subjectName);
			stmt.setInt(2, beginRow);
			stmt.setInt(3, rowPerPage);
			rs = stmt.executeQuery();			
			while(rs.next()) {
				Post p = new Post();
				p.setPostNo(rs.getInt("post_no"));
				p.setMemberId(rs.getString("member_id"));
				p.setSubjectName(rs.getString("subject_name"));
				p.setPostTitle(rs.getString("post_title"));			
				p.setPostDate(rs.getString("post_date"));
				list.add(p);
			}
		}finally {
			DBUtil.close(rs, stmt, null);
		}
		return list;
	}
	
	//last page (count(*))
		public int countPostBySubject(Connection conn, String subjectName) throws SQLException{
			int count = 0;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String sql = "SELECT COUNT(*) FROM blog_post WHERE subject_name=?";
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, subjectName);
				rs = stmt.executeQuery();
				while(rs.next()) {
					count = rs.getInt("count(*)");
				}
			}finally {
				rs.close();
				stmt.close();
			}
			
			return count;
		}
	
	//post list
	public List<Post> selectPostListAll(Connection conn, int beginRow, int rowPerPage) throws SQLException{
		String sql = "SELECT * FROM blog_post LIMIT ?,?";
		List<Post> list = new ArrayList<Post>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();			
			while(rs.next()) {
				Post p = new Post();
				p.setPostNo(rs.getInt("post_no"));
				p.setMemberId(rs.getString("member_id"));
				p.setSubjectName(rs.getString("subject_name"));
				p.setPostTitle(rs.getString("post_title"));			
				p.setPostDate(rs.getString("post_date"));
				list.add(p);
			}
		}finally {
			DBUtil.close(rs, stmt, null);
		}
		return list;
	}
	
	
	//last page (count(*))
	public int countPost(Connection conn) throws SQLException{
		int count = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM blog_post";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				count = rs.getInt("count(*)");
			}
		}finally {
			rs.close();
			stmt.close();
		}
		
		return count;
	}
	
}
