<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>update Post</title>
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
	<h3>Post 수정하기</h3>
	<br>
	<form method="post" action="${pageContext.request.contextPath}/UpdatePostServlet">
		<input type="hidden" name="postNo" value="${post.postNo}">
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
				<td>
					<select name="subjectName">
						<option value="">${post.subjectName}</option>
						<c:forEach items="${subjectList}" var="sub">
							<option value="${sub.subjectName}">${sub.subjectName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>post_title</td>
				<td><input type="text" name="postTitle" value="${post.postTitle}"></td>
			</tr>
			<tr>
				<td>Post_content</td>
				<td><input type="text" name="postContent" value="${post.postContent}"></td>
			</tr>
			<tr>
				<td>Post_date</td>
				<td><input type="text" name="postDate" value="${post.postDate}" readonly="readonly"></td>
			</tr>
		</table>
		<div align=right>
			<button type="submit"  class="btn btn-warning">수정하기</button>
		</div>
		<br>
	</form>
	</div>
	
	<div class="jumbotron text-center" style="margin-bottom: 0"></div>
</body>
</html>