package blog.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.dao.*;
import blog.service.MemberService;
import blog.service.SubjectService;
import blog.vo.Member;
import blog.vo.Subject;
@WebServlet("/DeleteMemberServlet")
public class DeleteMemberServlet extends HttpServlet {
	
	//service
	private MemberService memberService;
	private SubjectService subjectService;
	
	//비밀번호를 다시 입력하는 삭제 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//subject
		this.subjectService = new SubjectService();
		List<Subject> subjectList = subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
		
		//회원 정보를 가지고옴
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginMember")==null) {
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;
		}
		
		String memberId = ((Member)(session.getAttribute("loginMember"))).getMemberId();
		
		//model
		MemberDao memberDao = new MemberDao();
		Member member = memberDao.selectMemberOne(memberId);
		
		request.setAttribute("member", member);
		request.getRequestDispatcher("/WEB-INF/views/deleteMember.jsp").forward(request, response);
	}

	//탈퇴 액션 - request로 비밀번호를 가지고 와서 비교하고 실행
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request
		String memberPw = request.getParameter("memberPw");
		System.out.println(memberPw+"<-----deleteMember.doPost()   memberPw");
		String memberId = request.getParameter("memberId");
		
		//model
		Member member2 = new Member();
		member2.setMemberId(memberId);
		member2.setMemberPw(memberPw);
		
		MemberDao memberDao = new MemberDao();
		Member returnMember = memberDao.selectMemberOne(member2);
		
		
		//member 테이블 삭제도 하고 memberid 테이블 입력도 실행해야 함 - (컴퓨터는 동시에 할 수는 없음)
		//트렌젝션 이용 - 두 기능을 실행 하지만 둘 중의 하나만 실패해도 두가지 기능을 모두 실행하지 않음(롤백 시킴)
		
		//memberDao = new MemberDao(); - service에서 해주기 때문에 따로 호출 필요없음
		//memberidDao = new MemberidDao();
		
		//service
		memberService = new MemberService();
		
		//session안에는 보통 비번을 넣어두지 않는다
		Member member = (Member)(request.getSession().getAttribute("loginMember"));
		//비밀번호 - 폼에서 입력받는 비밀번호를 따로 받아와서 저장함
		member.setMemberPw(memberPw);
		
		//service - 트렌젝션 처리 할 때, 페이징 할 때 필요함
		boolean tf = memberService.removeMember(member);
		
		/*아래에 두개가 묶여서 트렌젝션으로 실행되어야 함 - get connection 이 두번 호출 되어서 불가능. 그래서 blog.service계층이 개입되어서 사용해야 된다.
			int row = memberDao.deleteMember(member);
			if(row == 1) {//삭제 성공했을 경우
				memberidDao.insertMemberid(member.getMemberId());
			}
		*/
		
		if(tf == true) {
			//비밀번호가 맞다면
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", returnMember);
			response.sendRedirect(request.getContextPath()+"/LogoutServlet");
			System.out.println("탈퇴성공");
		}else {
			//비밀번호가 틀렸다면
			response.sendRedirect(request.getContextPath()+"/DeleteMemberServlet");
			System.out.println("탈퇴 실패 - 비밀번호가 일치하지 않습니다.");
		}
	}

}
