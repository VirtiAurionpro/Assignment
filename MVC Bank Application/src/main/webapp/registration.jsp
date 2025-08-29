<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
<style>
.main-content {
	width: 50%;
	border-radius: 20px;
	box-shadow: 0 5px 5px rgba(0, 0, 0, .4);
	margin: 5em auto;
	display: flex;
}

.company__info {
	background-color: #008080;
	border-top-left-radius: 20px;
	border-bottom-left-radius: 20px;
	display: flex;
	flex-direction: column;
	justify-content: center;
	color: #fff;
}

.fa-android {
	font-size: 3em;
}

@media screen and (max-width: 640px) {
	.main-content {
		width: 90%;
	}
	.company__info {
		display: none;
	}
	.registration_form {
		border-top-left-radius: 20px;
		border-bottom-left-radius: 20px;
	}
}

@media screen and (min-width: 642px) and (max-width:800px) {
	.main-content {
		width: 70%;
	}
}

.row>h2 {
	color: #008080;
}

.registration_form {
	background-color: #fff;
	border-top-right-radius: 20px;
	border-bottom-right-radius: 20px;
	border-top: 1px solid #ccc;
	border-right: 1px solid #ccc;
}

form {
	padding: 0 2em;
}

.form__input {
	width: 100%;
	border: 0px solid transparent;
	border-radius: 0;
	border-bottom: 1px solid #aaa;
	padding: 1em .5em .5em;
	padding-left: 2em;
	outline: none;
	margin: 1.5em auto;
	transition: all .5s ease;
}

.form__input:focus {
	border-bottom-color: #008080;
	box-shadow: 0 0 5px rgba(0, 80, 80, .4);
	border-radius: 4px;
}

.btn {
	transition: all .5s ease;
	width: 70%;
	border-radius: 30px;
	color: #008080;
	font-weight: 600;
	background-color: #fff;
	border: 1px solid #008080;
	margin-top: 1.5em;
	margin-bottom: 1em;
}

.btn:hover, .btn:focus {
	background-color: #008080;
	color: #fff;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row main-content bg-success text-center">
			<div class="col-md-4 text-center company__info">
				<span class="company__logo">
					<h2>
						<span class="fa fa-android"></span> <i class="bi bi-bank"></i>
					</h2>
				</span>
				<h4 class="company_title">ABC Bank</h4>
			</div>
			<div class="col-md-8 col-xs-12 col-sm-12 registration_form">
				<div class="container-fluid">
					<div class="row">
						<h2>Register</h2>
					</div>
					<div class="row">
						<form action="RegistrationController" method="post"
							class="form-group">
							<div class="row">
								<input type="text" name="name" id="name" class="form__input"
									placeholder="Full Name" required>
							</div>
							<div class="row">
								<input type="email" name="email" id="email" class="form__input"
									placeholder="Email" required>
							</div>
							<div class="row">
								<input type="text" name="mobile" id="mobile" class="form__input"
									placeholder="Mobile Number" required>
							</div>
							<div class="row">
								<input type="date" name="dob" id="dob" class="form__input"
									placeholder="Date of Birth" required>
							</div>
							<div class="row">
								<input type="password" name="password" id="password"
									class="form__input" placeholder="Enter Password" required>
							</div>
							<div class="row">
								<input type="password" name="confirmpassword"
									id="confirmpassword" class="form__input"
									placeholder="Re-enter Password" required>
							</div>
							<div class="row">
								<input type="submit" value="Register" class="btn">
							</div>
							<div class="row mt-3">
								<p>
									Already have an account? <a href="login.jsp">Login Here</a>
								</p>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
