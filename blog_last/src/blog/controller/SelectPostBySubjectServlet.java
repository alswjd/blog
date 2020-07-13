package blog.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.service.PostService;
import blog.service.SubjectService;
import blog.vo.Subject;


@WebServlet("/SelectPostBySubjectServlet")
public class SelectPostBySubjectServlet extends HttpServlet {
	
	private SubjectService subjectService;
	private PostService postService;
	private final int ROW_PER_PAGE = 8; //절대 바꾸지 못 하게 함 final
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request
		String subjectName = request.getParameter("subjectName");
		
		//상단메뉴
		this.subjectService = new SubjectService();
		List<Subject> subjectList = subjectService.getSubjectListAll();
		request.setAttribute("subjectName", subjectName);
		request.setAttribute("subjectList", subjectList);
		
		//페이징
		int currentPage = 1;
		if(request.getParameter("currentPage")!= null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		request.setAttribute("currentPage",currentPage);
		
		//메뉴에 따른 포스트 보이기
		this.postService = new PostService();
		//Map()
		Map<String,Object> map = postService.getPostBySubject(subjectName, currentPage, ROW_PER_PAGE);
		request.setAttribute("post", map.get("post"));
		request.setAttribute("lastPage", map.get("lastPage"));
		request.getRequestDispatcher("/WEB-INF/views/selectPostBySubject.jsp").forward(request, response);
	}


}
