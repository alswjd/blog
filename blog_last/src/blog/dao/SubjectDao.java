package blog.dao;

import java.sql.*;
import java.util.*;

import blog.commons.DBUtil;
import blog.vo.Subject;

public class SubjectDao {
	//list
	public List<Subject> selectSubjectListAll(){
		String sql = "SELECT subject_name FROM blog_subject ORDER BY subject_name ASC";
		
		List<Subject> list = new ArrayList<Subject>(); //size 는 0이다 밖에서 선언해줘도 됨(보통)
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Subject s = new Subject();
				s.setSubjectName(rs.getString("subject_name"));
				list.add(s);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(rs, stmt, conn);
		}
		
		return list;
	}
	
	//insert
}
