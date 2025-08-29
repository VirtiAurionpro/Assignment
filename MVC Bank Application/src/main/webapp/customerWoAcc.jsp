<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.aurionpro.model.Login"%>
<%@ page import="com.aurionpro.dao.AccountDao"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Dashboard</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">

<style>
body {
	background-color: #f5f7fa;
}

.sidebar {
	background-color: #1f2d3d;
	color: #fff;
	padding: 1.5rem;
	min-height: 100vh;
}

.sidebar h3 {
	color: #fff;
	margin-bottom: 2rem;
}

.sidebar a {
	color: #adb5bd;
	text-decoration: none;
	display: flex;
	align-items: center;
	gap: 0.75rem;
	padding: 0.75rem 1rem;
	border-radius: 0.375rem;
	margin-bottom: 0.5rem;
	transition: background 0.2s;
}

.sidebar a:hover, .sidebar a.active {
	background-color: #34495e;
	color: #fff;
}

.offcanvas-start {
	width: 250px;
}
</style>
</head>
<body>
	<%
	Login login = (Login) session.getAttribute("Login");
	if (login == null) {
		response.sendRedirect("login.jsp");
		return;
	}
	System.out.println(login == null);
	String identifier = login.getUsername();
	session.setAttribute("RegistrationId", identifier);
	%>
	<nav class="navbar navbar-dark bg-dark d-md-none px-3">
		<button class="btn btn-outline-light" type="button"
			data-bs-toggle="offcanvas" data-bs-target="#sidebarMenu"
			aria-controls="sidebarMenu">
			<i class="bi bi-list"></i>
		</button>
		<span class="navbar-brand ms-2">Customer Dashboard</span>
	</nav>
	<div class="offcanvas offcanvas-start d-md-none" tabindex="-1"
		id="sidebarMenu" aria-labelledby="sidebarLabel">
		<div class="offcanvas-header">
			<h5 class="offcanvas-title" id="sidebarLabel">Menu</h5>
			<button type="button" class="btn-close" data-bs-dismiss="offcanvas"
				aria-label="Close"></button>
		</div>
		<div class="offcanvas-body p-0">
			<div class="sidebar">
				<h3 class="d-none d-md-block">Customer Dashboard</h3>
				<a href="customerWithAcc.jsp" class="active"> <i
					class="bi bi-house-fill me-2"></i> Home
				</a> <a href="createAccount.jsp"> <i class="bi bi-people-fill me-2"></i>
					Create Account
				</a> <a href="MakePayment.jsp"> <i
					class="bi bi-credit-card-2-front me-2"></i> Apply for Credit Card
				</a> 
				
				<!-- 
				<a href="ViewPassbook.jsp"> <i class="bi bi-journal-text me-2"></i>
					Subscribe to out newsletter
				</a> -->
				
				<a href="Profile.jsp"> <i class="bi bi-person-circle me-2"></i>
					Manage Profile
				</a> <a href="login.jsp"> <i class="bi bi-box-arrow-right me-2"></i>
					Logout
				</a>
			</div>
		</div>
	</div>

	<nav class="col-md-3 col-lg-2 d-md-block bg-dark sidebar">
		<div class="position-sticky">
			<h3 class="text-center text-white mt-3">Customer Dashboard</h3>

			<div class="text-center my-4">
				<i class="bi bi-person-circle"
					style="font-size: 100px; color: white;"></i>
				<h4 style="color: white;"><%=identifier%></h4>
			</div>

			<a href="customerWithAcc.jsp" class="active"> <i
				class="bi bi-house-fill me-2"></i> Home
			</a> <a href="createAccount.jsp"> <i class="bi bi-people-fill me-2"></i>
				Create Account
			</a> <a href="ApplyCreditCard.jsp"> <i
				class="bi bi-credit-card-2-front me-2"></i> Apply for Credit Card
			</a> <!-- 
				<a href="ViewPassbook.jsp"> <i class="bi bi-journal-text me-2"></i>
					Subscribe to out newsletter
				</a> --> <a href="Profile.jsp"> <i class="bi bi-person-circle me-2"></i>
				Manage Profile
			</a> <a href="login.jsp"> <i class="bi bi-box-arrow-right me-2"></i>
				Logout
			</a>
		</div>
	</nav>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>