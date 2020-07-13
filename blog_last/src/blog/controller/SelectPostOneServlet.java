package blog.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.service.CommentService;
import blog.service.LikeyService;
import blog.service.PostService;
import blog.service.SubjectService;
import blog.vo.Comment;
import blog.vo.Member;
import blog.vo.Post;
import blog.vo.Subject;


@WebServlet("/SelectPostOneServlet")
public class SelectPostOneServlet extends HttpServlet {

	private SubjectService subjectService;
	private PostService postService;
	private CommentService commentService;
	private LikeyService likeyService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		System.out.println(postNo+"selectPostOne.doGet() postNo");
		
		//subjectList
		subjectService = new SubjectService();
		List<Subject> subjectList = subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
		
		//post
		postService = new PostService();
		Post post = postService.getPostOne(postNo);
		request.setAttribute("post", post);
		
		//commentList
		commentService = new CommentService();
		List<Comment> list = commentService.getCommentListByPostNo(postNo);
		request.setAttribute("comment", list);
		
		//관리자
		HttpSession session = request.getSession();
		Member loginMember = ((Member)session.getAttribute("loginMember"));
		request.setAttribute("loginMember", loginMember);
		
		//likey Count
		likeyService = new LikeyService();
		int likeyCount = likeyService.getLikeyCount(postNo);
		request.setAttribute("likeyCount",likeyCount);
		
		request.getRequestDispatcher("/WEB-INF/views/selectPostOne.jsp").forward(request, response);
	}


}
