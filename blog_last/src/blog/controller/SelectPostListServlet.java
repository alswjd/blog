package blog.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.service.MemberService;
import blog.service.PostService;
import blog.service.SubjectService;
import blog.vo.Member;
import blog.vo.Subject;

@WebServlet("/SelectPostListServlet")
public class SelectPostListServlet extends HttpServlet {
	
	private PostService postService;
	private SubjectService subjectService;
	private final int ROW_PER_PAGE = 8; //절대 바꾸지 못 하게 함 final
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인증 session
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		//member가 로그인 되어있지 않거나 레벨이 9보다 크면 홈으로 보낸다.
		if(loginMember == null || loginMember.getMemberLevel() >= 10) { //or문은 앞에 조건문이 참이면 뒤에 조건문은 실행하지 않아서 예외가 생겨도 실행하지 않음
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;
		}
		
		//상단메뉴
		this.subjectService = new SubjectService();
		List<Subject> subjectList = subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
		
		//페이징
		int currentPage = 1;
		if(request.getParameter("currentPage")!=null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		request.setAttribute("currentPage", currentPage);
		
		//List<Post>
		postService = new PostService();
		
		//Map() 사용 - 페이징
		Map<String , Object> map = postService.getPostListAll(currentPage, ROW_PER_PAGE);
		request.setAttribute("post", map.get("post"));
		request.setAttribute("lastPage", map.get("lastPage"));
		request.getRequestDispatcher("/WEB-INF/views/selectPostList.jsp").forward(request, response);
		
	}


}
