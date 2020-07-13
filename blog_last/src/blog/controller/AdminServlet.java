package blog.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.service.SubjectService;
import blog.vo.Member;
import blog.vo.Subject;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	
	private SubjectService subjectService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		//member가 로그인 되어있지 않거나 레벨이 9보다 크면 홈으로 보낸다.
		if(loginMember == null || loginMember.getMemberLevel() >= 10) { //or문은 앞에 조건문이 참이면 뒤에 조건문은 실행하지 않아서 예외가 생겨도 실행하지 않음
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;
		}
		//메뉴
		this.subjectService = new SubjectService();
		List<Subject> subjectList = subjectService.getSubjectListAll();
		
		request.setAttribute("subjectList", subjectList);
		request.getRequestDispatcher("/WEB-INF/views/admin.jsp").forward(request, response);
	}
}
