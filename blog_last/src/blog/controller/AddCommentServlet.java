package blog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.service.CommentService;
import blog.vo.Comment;
import blog.vo.Member;

@WebServlet("/AddCommentServlet")
public class AddCommentServlet extends HttpServlet {
	
	private CommentService commentService;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//encoding
		request.setCharacterEncoding("utf-8");
		
		//인증 - 모든 회원 가능
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember == null) {
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
			return;
		}
		
		//request&session 분석하여 Comment 입력해야 함
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		String memberId = loginMember.getMemberId();
		String commentContent = request.getParameter("commentContent");
		
		//model
		Comment comment = new Comment();
		comment.setPostNo(postNo);
		comment.setMemberId(memberId);
		comment.setCommentContent(commentContent);
		
		//Service
		this.commentService = new CommentService();
		commentService.addComment(comment);
		
		//redirect
		response.sendRedirect(request.getContextPath()+"/SelectPostOneServlet?postNo="+postNo); 	//
	}
	

}
