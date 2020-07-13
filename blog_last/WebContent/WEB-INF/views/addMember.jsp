<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Sign Up</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="icon" type="image/png" href="images/icons/favicon.ico">

<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">

<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" type="text/css" href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">

<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">

<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">

<link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">

<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">

<link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">

<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">

</head>
<body>
<body>
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
				<form class="login100-form validate-form flex-sb flex-w" method="post" action="${pageContext.request.contextPath}/AddMemberServlet">		
				<span class="login100-form-title p-b-32">Sign up</span>
				<span class="txt1 p-b-11"> User ID </span>
					<div class="wrap-input100 validate-input m-b-36"
						data-validate="Username is required">
						<input class="input100" type="text" name="memberId"> <span class="focus-input100"></span>
					</div>
					<span class="txt1 p-b-11"> Password </span>
					<div class="wrap-input100 validate-input m-b-12"
						data-validate="Password is required">
						<span class="btn-show-pass"> <i class="fa fa-eye"></i>
						</span> <input class="input100" type="password" name="memberPw"> <span class="focus-input100"></span>
					</div>
					<div class="container-login100-form-btn">
						<button class="login100-form-btn" type="submit">YES</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="dropDownSelect1"></div>

	<script type="text/javascript" async=""
		src="https://www.google-analytics.com/analytics.js"></script>
	<script src="vendor/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>

	<script src="vendor/animsition/js/animsition.min.js"
		type="text/javascript"></script>

	<script src="vendor/bootstrap/js/popper.js" type="text/javascript"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"
		type="text/javascript"></script>

	<script src="vendor/select2/select2.min.js" type="text/javascript"></script>

	<script src="vendor/daterangepicker/moment.min.js"
		type="text/javascript"></script>
	<script src="vendor/daterangepicker/daterangepicker.js"
		type="text/javascript"></script>

	<script src="vendor/countdowntime/countdowntime.js"
		type="text/javascript"></script>

	<script src="js/main.js" type="text/javascript"></script>

	<script async=""
		src="https://www.googletagmanager.com/gtag/js?id=UA-23581568-13"
		type="text/javascript"></script>
	<script type="text/javascript">
		window.dataLayer = window.dataLayer || [];
		function gtag() {
			dataLayer.push(arguments);
		}
		gtag('js', new Date());

		gtag('config', 'UA-23581568-13');
	</script>

<div class="jumbotron text-center" style="margin-bottom: 0"></div>
</body>

</html>