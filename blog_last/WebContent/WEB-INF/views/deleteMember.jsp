<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Delete Member</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/png" href="images/icons/favicon.ico" />
<link rel="stylesheet" type="text/css" href="CSS_selectMember/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="CSS_selectMember/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="CSS_selectMember/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<link rel="stylesheet" type="text/css" href="CSS_selectMember/vendor/animate/animate.css">
<link rel="stylesheet" type="text/css" href="CSS_selectMember/vendor/css-hamburgers/hamburgers.min.css">
<link rel="stylesheet" type="text/css" href="CSS_selectMember/vendor/animsition/css/animsition.min.css">
<link rel="stylesheet" type="text/css" href="CSS_selectMember/vendor/select2/select2.min.css">
<link rel="stylesheet" type="text/css" href="CSS_selectMember/vendor/daterangepicker/daterangepicker.css">
<link rel="stylesheet" type="text/css" href="CSS_selectMember/css/util.css">
<link rel="stylesheet" type="text/css" href="CSS_selectMember/css/main.css">

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
	<div class="container" align="center">	
		<div class="wrap-login100 p-t-50 p-b-90">
			<form class="login100-form validate-form flex-sb flex-w" method="post" action="${pageContext.request.contextPath}/DeleteMemberServlet">
				<span class="login100-form-title p-b-51">Delete Member Page</span>
				<div class="wrap-input100 validate-input m-b-16" data-validate="Username is required">
					<input class="input100" type="text" name="memberId" placeholder=" ${member.memberId}" readonly="readonly">
				</div>
				<div class="wrap-input100 validate-input m-b-16" data-validate="Password is required">
					<input class="input100" type="password" name="memberLevel" placeholder=" ${member.memberLevel}" readonly="readonly">
				</div>
				<div class="wrap-input100 validate-input m-b-16" data-validate="Password is required">
					<input class="input100" type="password" name="memberPw" placeholder="your password">
				</div>
				<div class="container-login100-form-btn m-t-17">
					<button type="submit" style="background-color: #6c757d;" class="login100-form-btn">회원탈퇴</button>
				</div>
			</form>
		</div>
	</div>
	<div class="jumbotron text-center" style="margin-bottom: 0"></div>
</body>
</html>


