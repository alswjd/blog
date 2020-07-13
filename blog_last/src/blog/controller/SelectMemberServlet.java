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

@WebServlet("/SelectMemberServlet")
public class SelectMemberServlet extends HttpServlet {
	
	private MemberDao memberDao;
	private SubjectService subjectService;
	
	//회원 정보 나오는 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//메뉴
		this.subjectService = new SubjectService();
		List<Subject> subjectList = this.subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
		
		
		//session 인증
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginMember")==null) {
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;
		}
		
		//현재 접속되어 있는 아이디를 가지고 옴
		String memberId = ((Member)(session.getAttribute("loginMember"))).getMemberId(); //처음에 받아올 떄 오브젝트 형으로 받아오기 때문에 에러 -> 형변환 필요 -> 형을 변환시켰기 때문에 get메소드도 불러올 수 있다
		
		//model
		memberDao = new MemberDao();
		Member member = memberDao.selectMemberOne(memberId);
		
		request.setAttribute("member", member);
		request.getRequestDispatcher("/WEB-INF/views/selectMember.jsp").forward(request, response);
	}

}
