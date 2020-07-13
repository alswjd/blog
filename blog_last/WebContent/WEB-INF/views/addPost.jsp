<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>add post</title>
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
	<div class="container"  style="margin-bottom: 250px;">
		<form action="${pageContext.request.contextPath}/AddPostServlet" method="post">
			<table class="table">
				<tbody>
					<tr>
						<td>subject :</td>
						<td> 
							<select name="subjectName">
								<option value="">subject</option>
								<c:forEach items="${subject}" var="s">
									<option value="${s.subjectName}">${s.subjectName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>post_title :</td>
						<td>
							<input type="text" name="postTitle">
						</td>
					</tr>
					<tr>
						<td>post_content :</td>
						<td>
							<textarea rows="8" cols="100" name="postContent"></textarea>
						</td>
					</tr>
				</tbody>
			</table>
			<div>
				<button type="submit">올리기</button>
			</div>
		</form>
	</div>
	<div class="jumbotron text-center" style="margin-bottom: 0"></div>
</body>
</html>