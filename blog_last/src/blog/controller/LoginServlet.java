package blog.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.dao.MemberDao;
import blog.dao.SubjectDao;
import blog.service.SubjectService;
import blog.vo.Member;
import blog.vo.Subject;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
	
	private SubjectService subjectService;
	private MemberDao memberDao;
	
	//로그인 폼
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 받아옴
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginMember")!=null) {
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;
		}
		
		//상단바 메뉴
		subjectService = new SubjectService();
		List<Subject> subjectList = this.subjectService.getSubjectListAll();
		
		request.setAttribute("subjectList", subjectList);
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}
	
	//로그인 액션(인증)
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//encoding
		request.setCharacterEncoding("utf-8");
		
		//request
		String memberId = request.getParameter("memberId");
		System.out.println(memberId);
		String memberPw = request.getParameter("memberPw");
		System.out.println(memberPw);
		
		//model
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		this.memberDao = new MemberDao();
		Member returnMember = memberDao.selectMemberOne(member);
		
		//view
		//if() - HttpSession 이용
		if(returnMember.getMemberId() == null) {
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
			System.out.println("실패");
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", returnMember);
			System.out.println("성공");
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
		}
		
	}
	
}
