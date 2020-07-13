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


@WebServlet("/UpdateMemberServlet")
public class UpdateMemberServlet extends HttpServlet {
	
	private SubjectService subjectService;
	private MemberService memberService;
	
	//레벨 수정 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//페이지 볼 수 있는 자격
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember == null || loginMember.getMemberLevel() >= 10) {
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;
		}
		
		//상단바 메뉴
		this.subjectService = new SubjectService();
		List<Subject> subjectList = subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
		
		//request
		String memberId = request.getParameter("memberId");
		System.out.println(memberId + "UPdateMemberLevel.doGet() memberId");
		
		//model
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberLevel(member.getMemberLevel());
		
		MemberDao memberDao = new MemberDao();
		member = memberDao.selectMemberOne(memberId);
		
		request.setAttribute("member", member);
		request.getRequestDispatcher("/WEB-INF/views/updateMemberLevel.jsp").forward(request, response);
	}

	//레벨 수정 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request
		String memberId = request.getParameter("memberId");
		System.out.println(memberId+"updateMemberLevel.doPost()  memberId");
		int memberLevel = Integer.parseInt(request.getParameter("memberLevel"));
		
		//model
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberLevel(memberLevel);
		
		this.memberService = new MemberService();
		member = memberService.levelUpdate(member);
		
		response.sendRedirect(request.getContextPath()+"/SelectMemberListServlet");
	}

}
