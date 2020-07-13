<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>select post list</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<style>
.fakeimg {
	height: 200px;
	background: #aaa;
}
</style>
</head>
<body>
<div>
	<!-- 상단의 메뉴 부분 include -->
	<jsp:include page="/WEB-INF/views/inc/side.jsp"></jsp:include>
</div>
<div class="container">
	<h3>POST</h3>
	<br>
	<div>
		<a href="${pageContext.request.contextPath}/AddPostServlet" class="btn btn-warning">글쓰기</a>
	</div>
	<br>
	<div class="container" style="margin-bottom: 250px;">
		<table class="table">
	    	<thead class="thead-light">
				<tr>
					<th>post_no</th>
					<th>member_id</th>
					<th>subject_name</th>
					<th>post_title</th>
					<th>post_date</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${post}" var="p">
					<tr>
						<td>${p.postNo}</td>
						<td>${p.memberId}</td>
						<td>${p.subjectName}</td>
						<td>
							<a href="${pageContext.request.contextPath}/SelectPostOneServlet?postNo=${p.postNo}">${p.postTitle}</a>
						</td>
						<td>${p.postDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- 페이징 -->
		<div align="center">
		 	
            <a href="${pageContext.request.contextPath}/SelectPostListServlet?currentPage=${1}">처음</a>               
          
			<c:if test="${currentPage > 1}">
				<a href="${pageContext.request.contextPath}/SelectPostListServlet?currentPage=${currentPage-1}">이전</a>
			</c:if>
				- ${currentPage} -
			<c:if test="${currentPage < lastPage}">
				<a href="${pageContext.request.contextPath}/SelectPostListServlet?currentPage=${currentPage+1}">다음</a>
			</c:if>
		 
            <a href="${pageContext.request.contextPath}/SelectPostListServlet?currentPage=${lastPage}">마지막</a>               
            
		</div>
	</div>
</div>
	<div class="jumbotron text-center" style="margin-bottom: 0"></div>
</body>
</html>