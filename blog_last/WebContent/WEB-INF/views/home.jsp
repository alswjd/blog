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

	<div class="container" style="margin-top: 30px" >
		<div class="row">
			<div class="col-sm-3">
				
				<h2>About Me</h2>
				<h5>Photo of me:</h5>
				<div class="fakeimg">Fake Image</div>
				<p>Some text about me in culpa qui officia deserunt mollit
					anim..</p>
			</div>
			<div class="col-sm-9">
		
				<h2>TITLE HEADING</h2>
				<h5>Title description, Dec 7, 2017</h5>
				<div class="fakeimg">Fake Image</div>
				<p>Some text..</p>
				<p>Sunt in culpa qui officia deserunt mollit anim id est laborum
					consectetur adipiscing elit, sed do eiusmod tempor incididunt ut
					labore et dolore magna aliqua. Ut enim ad minim veniam, quis
					nostrud exercitation ullamco.</p>
				<br>
				<h2>TITLE HEADING</h2>
				<h5>Title description, Sep 2, 2017</h5>
				<div class="fakeimg">Fake Image</div>
				<p>Some text..</p>
				<p>Sunt in culpa qui officia deserunt mollit anim id est laborum
					consectetur adipiscing elit, sed do eiusmod tempor incididunt ut
					labore et dolore magna aliqua. Ut enim ad minim veniam, quis
					nostrud exercitation ullamco.</p>
			</div>
		</div>
	</div>

	<div class="jumbotron text-center" style="margin-bottom: 0"></div>
</body>
</html>
