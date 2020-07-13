package blog.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.dao.SubjectDao;
import blog.service.SubjectService;
import blog.vo.Subject;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {

	//private SubjectDao subjectDao; - service
	
	private SubjectService subjectService;
	
	//홈 사이트 보여줌
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//상단 메뉴
		subjectService = new SubjectService();
		List<Subject> subjectList = this.subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
		
		request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
	}

}
