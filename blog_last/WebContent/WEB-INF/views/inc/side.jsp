<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>side</title>
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

	<div class="jumbotron text-center" style="margin-bottom: 0">
		<h1>Home</h1>
		<p>What a wonderful BLOG!</p>
	</div>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/HomeServlet">HOME &nbsp;&nbsp;&nbsp;</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">
				<c:forEach items="${subjectList}" var="s">
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/SelectPostBySubjectServlet?subjectName=${s.subjectName}">${s.subjectName}&nbsp;&nbsp;&nbsp;</a></li>
				</c:forEach>
			</ul>
			<div align="right" >
				<!-- 로그아웃 된 상태 -->
				<c:if test="${loginMember==null}">
					<a href="${pageContext.request.contextPath}/LoginServlet" class="btn btn-outline-warning btn-sm">Login</a>&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath}/AddMemberServlet" class="btn btn-outline-warning btn-sm">SignUp</a>&nbsp;&nbsp;
				</c:if>
				<!-- 로그인 된 상태 -->
				<c:if test="${loginMember!=null}">
					<a href="${pageContext.request.contextPath}/SelectMemberServlet" class="btn btn-outline-warning btn-sm">${loginMember.memberId}
						's page</a>&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath}/LogoutServlet" class="btn btn-outline-warning btn-sm">Logout</a>&nbsp;&nbsp;
				</c:if>
				<!-- 관리자용 로그인  관리자:0번 / 일반회원은 :10번-->
				<c:if test="${loginMember != null && loginMember.memberLevel < 10 }">
					<a href="${pageContext.request.contextPath}/AdminServlet" class="btn btn-outline-warning btn-sm">MANAGER</a>&nbsp;&nbsp;
				</c:if>
			</div>
		</div>
	</nav>

</body>
</html>