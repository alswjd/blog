package blog.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blog.commons.DBUtil;
import blog.dao.CommentDao;
import blog.vo.Comment;

public class CommentService {	

	private CommentDao commentDao;
	
	//select comment
	public List<Comment> getCommentListByPostNo(int postNo){
		List<Comment> list = new ArrayList<Comment>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			commentDao = new CommentDao();
			list = commentDao.selectCommentListByPostNo(conn, postNo);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	//insertComment
	public void addComment(Comment comment) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			commentDao = new CommentDao();
			commentDao.insertComment(conn, comment);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
