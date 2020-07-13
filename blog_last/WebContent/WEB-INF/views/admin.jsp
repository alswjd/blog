<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>HOME</title>
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
	<h3>관리자 메뉴</h3>
	<br>
	<ol>
		<li><a href="${pageContext.request.contextPath}/SelectMemberListServlet" class="btn btn-warnibg">멤버관리 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</a></li>
		<li><a href="${pageContext.request.contextPath}/SelectPostListServlet" class="btn btn-warnibg">포스팅 관리</a></li>
	</ol>
	<br>
	<br>
	<br>
</div>
	<div class="jumbotron text-center" style="margin-bottom: 0"></div>
</body>
</html>