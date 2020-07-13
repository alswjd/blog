package blog.service;

import java.util.List;

import blog.dao.SubjectDao;
import blog.vo.Subject;

public class SubjectService {
	private SubjectDao subjectDao;
	
	public List<Subject> getSubjectListAll(){
		subjectDao = new SubjectDao();
		List<Subject> subjectList = subjectDao.selectSubjectListAll();
		
		return subjectList;
	}
}
