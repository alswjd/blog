package blog.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.service.PostService;
import blog.service.SubjectService;
import blog.vo.Member;
import blog.vo.Post;
import blog.vo.Subject;


@WebServlet("/AddPostServlet")
public class AddPostServlet extends HttpServlet {
	
	private SubjectService subjectService;
	private PostService postService;
	
	//게시글 입력하는 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		//member가 로그인 되어있지 않거나 레벨이 9보다 크면 홈으로 보낸다.
		if(loginMember == null || loginMember.getMemberLevel() >= 10) { //or문은 앞에 조건문이 참이면 뒤에 조건문은 실행하지 않아서 예외가 생겨도 실행하지 않음
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;
		}
		//상단바 메뉴
		this.subjectService = new SubjectService();
		List<Subject> subjectList = subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
		request.getRequestDispatcher("/WEB-INF/views/addPost.jsp").forward(request, response);
	}

	//입력 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//encoding
		request.setCharacterEncoding("utf-8");
		
		//request
		String subjectName = request.getParameter("subjectName");
		System.out.println(subjectName +"AddPostServlet.doPost()  subjectName");
		String postTitle = request.getParameter("postTitle");
		String postContent = request.getParameter("postContent");
		
		//model
		HttpSession session = request.getSession();
		String memberId = ((Member)(session.getAttribute("loginMember"))).getMemberId();
		
		Post post = new Post();
		post.setSubjectName(subjectName);
		post.setPostTitle(postTitle);
		post.setPostContent(postContent);
		post.setMemberId(memberId);
		
		//service
		this.postService = new PostService();
		postService.AddPost(post);
		
		response.sendRedirect(request.getContextPath()+"/SelectPostListServlet");
	}

}
