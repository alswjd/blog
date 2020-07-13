package blog.service;

import java.sql.Connection;
import java.sql.SQLException;

import blog.commons.DBUtil;
import blog.dao.LikeyDao;
import blog.vo.Likey;

public class LikeyService {
	private LikeyDao likeyDao;
	
	//좋아요 개수 추가
	public void addLikey(Likey likey) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			likeyDao = new LikeyDao();
			if(likeyDao.isInsertLikey(conn, likey)) {//트렌젝션은 아님 - select는 트렌젝션과 상관 없음 : 데이터 값이 변화하는 쿼리가 아니기 때문에 
													//insert . update . delete 만 트렌젝션에 영향을 미침
				System.out.println("좋아요 실행");
				likeyDao.insertLikey(conn, likey);
			}else {
				System.out.println("이미 좋아요 실행되었습니다. 실행하지 않습니다.");
			}
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
	
	//좋아요 개수
	public int getLikeyCount(int postNo) {
		Connection conn = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			likeyDao = new LikeyDao();
			count = likeyDao.selectLikeyCount(conn, postNo);
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
		return count;
	}
}
