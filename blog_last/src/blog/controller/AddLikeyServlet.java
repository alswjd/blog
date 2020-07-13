package blog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.service.LikeyService;
import blog.vo.Likey;
import blog.vo.Member;

@WebServlet("/AddLikeyServlet")
public class AddLikeyServlet extends HttpServlet {
	
	private LikeyService likeService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//encoding
		request.setCharacterEncoding("utf-8");
		
		//인증 - 모든 회원 가능
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember == null) {
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
			return;
		}
		
		//request
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		String memberId = loginMember.getMemberId();
		
		//model
		Likey likey = new Likey();
		likey.setPostNo(postNo);
		likey.setMemberId(memberId);
		
		//service
		likeService = new LikeyService();
		likeService.addLikey(likey);
		
		response.sendRedirect(request.getContextPath()+"/SelectPostOneServlet?postNo="+postNo);
	}

}
