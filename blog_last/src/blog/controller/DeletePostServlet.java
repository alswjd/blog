package blog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.service.PostService;
import blog.service.SubjectService;
import blog.vo.Member;

@WebServlet("/DeletePostServlet")
public class DeletePostServlet extends HttpServlet {
	
	private SubjectService subjectService;
	private PostService postService;
	
	//정말로 삭제하시겠습니까?
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		System.out.println(postNo+"<DeletePostServlet  postNo");
		
		//인증 - 관리자만
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember == null || loginMember.getMemberLevel() >=10) {
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;
		}
		
		request.setAttribute("postNo", postNo);
		request.getRequestDispatcher("/WEB-INF/views/deletePost.jsp").forward(request, response);
	}

	//delete action
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		
		System.out.println(postNo+"<DeletePostServlet.doPost() postNo");
		
		//service
		postService = new PostService();
		postService.deletePost(postNo);
		
		//response
		response.sendRedirect(request.getContextPath()+"/SelectPostListServlet");
	}
}
