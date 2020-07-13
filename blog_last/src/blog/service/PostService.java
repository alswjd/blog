package blog.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import blog.commons.DBUtil;
import blog.dao.CommentDao;
import blog.dao.PostDao;
import blog.vo.Post;

public class PostService {
	
	private PostDao postDao;
	private CommentService commentService;
	private CommentDao commentDao;
	
	//delete post
	public void deletePost(int postNo) {
		Connection conn = null;
		postDao = new PostDao();
		commentDao = new CommentDao();
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);//자동으로 커밋 못 하도록 설정
			
			//commentService.getCommentListByPostNo(postNo);
			commentDao.deleteComment(conn, postNo);
			postDao.deletePost(conn, postNo);
			
			conn.commit();
		}catch (Exception e) {
			try {
				conn.rollback();
			}catch (SQLException e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	//update post
	public void updateNewPost(Post post)  {
		
		Connection conn = null;
		try {
			postDao = new PostDao();
			conn = DBUtil.getConnection();
			postDao.updatePost(conn, post);
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
	
	//post one
	public Post getPostOne(int postNo) {
		Post post = null;
		Connection conn = null;
		try {
			postDao = new PostDao();
			conn = DBUtil.getConnection();
			post = postDao.selectPostOne(conn, postNo);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(null, null, conn);
		}
		return post;
	}
	
	
	//insertPost
	public void AddPost(Post post) {
		postDao = new PostDao();
		Post p = new Post();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			p = postDao.insertPost(conn, post);
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
	
	/*
	//subject 클릭 시 나오는 게시글들
	public List<Post> getPostBySubject(String subjectName){
		List<Post> list = null;
		Connection conn = null;
		try {
			postDao = new PostDao();
			conn = DBUtil.getConnection();
			list = postDao.selectPostBySubject(conn, subjectName);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(null, null, conn);
		}
		return list;
	}
	*/
	
	//post by subject paging
	public Map<String, Object> getPostBySubject(String subjectName, int currentPage, int rowPerPage){
		Map<String, Object> map = new HashMap<String, Object>();
		Connection conn = null;
		List<Post> post = new ArrayList<Post>();
		postDao = new PostDao();
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			int beginRow = (currentPage-1)*rowPerPage;
			post = postDao.selectPostBySubject(conn, subjectName, beginRow, rowPerPage);
			count = postDao.countPostBySubject(conn, subjectName);
			
			int lastPage = count / rowPerPage;
			if(count % rowPerPage != 0) {
				lastPage += 1;
			}
			
			map.put("post",post);
			map.put("lastPage",lastPage);
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
		
		return map;
	}
	
	
	//페이징
	public Map<String , Object> getPostListAll(int currentPage, int rowPerPage){
		Map<String, Object> map = new HashMap<String, Object>();
		Connection conn = null;
		List<Post> list = new ArrayList<Post>();
		postDao = new PostDao(); 
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			int beginRow = (currentPage-1)*rowPerPage;
			list = postDao.selectPostListAll(conn, beginRow, rowPerPage);
			count = postDao.countPost(conn);
			
			int lastPage = count / rowPerPage;
			if(count % rowPerPage != 0) {
				lastPage +=1;
			}
			
			map.put("post",list);
			map.put("lastPage",lastPage);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
}
