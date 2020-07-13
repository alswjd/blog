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


@WebServlet("/UpdatePostServlet")
public class UpdatePostServlet extends HttpServlet {
	
	private PostService postService;
	private SubjectService subjectService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//수정할 때 가지고 오는 post No
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		
		//인증 - 관리자만
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember == null || loginMember.getMemberLevel() >=10) {
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;
		}
		
		//post service
		postService = new PostService();
		Post post = postService.getPostOne(postNo);
		request.setAttribute("post", post);
		
		//상단바 메뉴
		subjectService = new SubjectService();
		List<Subject> subjectList = subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
	
		request.getRequestDispatcher("/WEB-INF/views/updatePost.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//encoding
		request.setCharacterEncoding("utf-8");
		
		//request
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		System.out.println(postNo + "updatePostServlet.doPost() postNo");
		String postTitle = request.getParameter("postTitle");
		String postContent = request.getParameter("postContent");
		String postDate = request.getParameter("postDate");
		String subjectName = request.getParameter("subjectName");
		System.out.println(subjectName+ "<--subject control");
		
		//model
		Post post = new Post();
		post.setPostNo(postNo);
		post.setPostTitle(postTitle);
		post.setPostContent(postContent);
		post.setPostDate(postDate);
		post.setSubjectName(subjectName);
		
		//포스트 수정
		postService = new PostService();
		postService.updateNewPost(post);
		
		response.sendRedirect(request.getContextPath()+"/SelectPostOneServlet?postNo="+postNo);
	}

}
