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
import blog.service.MemberService;
import blog.service.SubjectService;
import blog.vo.Member;
import blog.vo.Subject;


@WebServlet("/AddMemberServlet")
public class AddMemberServlet extends HttpServlet {
	
	private SubjectService subjectService;
	private MemberService memberService;
	
	//form
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//menu
		subjectService = new SubjectService();
		List<Subject> subjectList = subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
		
		//session
		HttpSession session = request.getSession();
		Member returnMember = (Member)session.getAttribute("loginMember");
		if(returnMember != null) {
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;
		}
		
		request.getRequestDispatcher("/WEB-INF/views/addMember.jsp").forward(request, response);
	}

	//action
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//encoding
		request.setCharacterEncoding("utf-8");
		
		//request
		String memberId = request.getParameter("memberId");
		System.out.println(memberId+"<--AddMemberServlet.doPost() memberId");
		String memberPw = request.getParameter("memberPw");
		System.out.println(memberPw +"<--AddMemberServlet.doPost() memberPw");
		
		//model
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		//웹 주소에서 강제로 페이지를 이동했을 때 로그인 멤버 값이 있으면 홈을 보여줌
		HttpSession session = request.getSession();
		Member returnMember = (Member)session.getAttribute("loginMember");
		if(returnMember != null) {
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;
		}
		
		//service
		this.memberService = new MemberService(); //DI 를 사용하면 이 객체를 따로 안 만들어도 상관 없다. - 나중에 배움ㅎ
		boolean flag = memberService.addMember(member);
		if(flag) {//== true 생략 가능 - if문 안에 조건은 T/F 중 하나
			//성공
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
		}else {
			//실패
			response.sendRedirect(request.getContextPath()+"/AddMemberServlet");
		}
	
	}

}
