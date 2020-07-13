<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>select Post one</title>
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
	
	<div class="container" >
	<h3 >Post 상세보기</h3>
	<br>
		<!-- 좋아요 버튼 -->
		<div>
			<c:if test="${loginMember == null}">
				좋아요	
			</c:if>
			<c:if test="${loginMember != null}">
				<a href="${pageContext.request.contextPath}/AddLikeyServlet?postNo=${post.postNo}">좋아요</a>
			</c:if>
			${likeyCount} 개
		</div>
		<table class="table">
			<tr>
				<td>Post_no</td>
				<td>${post.postNo}</td>
			</tr>
			<tr>
				<td>member_id</td>
				<td>${post.memberId}</td>
			</tr>
			<tr>
				<td>subject_name</td>
				<td>${post.subjectName}</td>
			</tr>
			<tr>
				<td>post_title</td>
				<td>${post.postTitle}</td>
			</tr>
			<tr>
				<td>Post_content</td>
				<td>${post.postContent}</td>
			</tr>
			<tr>
				<td>Post_date</td>
				<td>${post.postDate}</td>
			</tr>
		</table>
	<!-- 관리자만 수정 삭제 가능 -->
	<c:if test="${loginMember.memberLevel == 0}">
		<div align=right>
			<a href="${pageContext.request.contextPath}/UpdatePostServlet?postNo=${post.postNo}" class="btn btn-warning">수정하기</a>
			<a href="${pageContext.request.contextPath}/DeletePostServlet?postNo=${post.postNo}" class="btn btn-warning">삭제하기</a>
		</div>
	</c:if>
	<br>
	<div class="container">
		<form action="${pageContext.request.contextPath}/AddCommentServlet" method="post">
			<div class="input-group mb-3">
				<input type="hidden" name="postNo" value="${post.postNo}">
			    <input type="text" class="form-control" placeholder="Comment"  name="commentContent">
				<div class="input-group-append">
					<button class="btn btn-secondary" type="submit">입력</button>  
				</div>
  			</div>
		</form>
	</div>
	<div class="container" style="padding-right: 700px;" >
		<!-- 댓글 리스트(페이징) -->
		<c:forEach items="${comment}" var="comment">
			<table >
				<tr>
					<td>${comment.memberId}  :&nbsp;&nbsp;</td>
					<td>${comment.commentContent}</td>
				</tr>
			</table>
		</c:forEach>
	</div>
</div>
	<div class="jumbotron text-center" style="margin-bottom: 0"></div>
</body>
</html>