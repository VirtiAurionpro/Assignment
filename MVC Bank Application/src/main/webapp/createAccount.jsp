<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.aurionpro.dao.CustomerDao"%>
<%@ page import="com.aurionpro.model.Registration"%>
<%@ page import="com.aurionpro.model.Login"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
Login login3 = (Login) session.getAttribute("Login");
String identifier2 = login3.getUsername();
System.out.println(identifier2);
Registration customer = new CustomerDao().getCustomerByIdentifier(identifier2);
session.setAttribute("customer", customer);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Account</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">

<style>
body {
	background-color: #dee9ff;
}

.beneficiary-form {
	background-color: #fff;
	max-width: 600px;
	margin: auto;
	padding: 50px 70px;
	border-radius: 30px;
	box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.075);
}

.beneficiary-form .form-icon {
	text-align: center;
	background-color: #5891ff;
	border-radius: 50%;
	font-size: 40px;
	color: white;
	width: 100px;
	height: 100px;
	margin: auto;
	margin-bottom: 50px;
	line-height: 100px;
}

.beneficiary-form .item {
	border-radius: 20px;
	margin-bottom: 25px;
	padding: 10px 20px;
}

.beneficiary-form .create-account {
	border-radius: 30px;
	padding: 10px 20px;
	font-size: 18px;
	font-weight: bold;
	background-color: #5791ff;
	border: none;
	color: white;
	margin-top: 20px;
	width: 100%;
}

@media ( max-width : 576px) {
	.beneficiary-form {
		padding: 50px 20px;
	}
	.beneficiary-form .form-icon {
		width: 70px;
		height: 70px;
		font-size: 30px;
		line-height: 70px;
	}
}
</style>
</head>
<body>

	<div class="container-fluid">
		<div class="row">
			<%@ include file="customerWoAcc.jsp"%>
			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 py-4">
				<div class="beneficiary-form">
					<form action="CreateAccountController" method="post">
						<div class="form-icon">
							<span><i class="bi bi-bank"></i></span>
						</div>

						<div class="text-center">
							<p
								class="fs-5 px-3 py-2 text-dark rounded w-auto d-inline-block fw-semibold"
								style="background-color: #cce5ff; border: 1px solid #b8daff;">
								Account Details</p>
						</div>

						<div class="form-group">
							<label class="form-label">Name: ${customer.name}</label>
						</div>
						<div class="form-group">
							<label class="form-label">Email: ${customer.email}</label>
						</div>
						<div class="form-group">
							<label class="form-label">Date of Birth: ${customer.dob}</label>
						</div>
						<div class="form-group">
							<label class="form-label">Mobile No: ${customer.mobileNo}</label>
						</div>

						<div class="form-group">
							<input type="text" class="form-control item" id="pan_no"
								name="pan_no" maxlength="10" placeholder="PAN Number" required>
						</div>

						<div class="form-group">
							<input type="text" class="form-control item" id="aadhar_no"
								name="aadhar_no" maxlength="16"
								placeholder="Aadhar Number (No spaces)" required>
						</div>

						<div class="form-group">
							<select class="form-control item" id="account_type"
								name="account_type" required>
								<option value="" disabled selected>Select Account Type</option>
								<option value="savings">Savings</option>
								<option value="current">Current</option>
							</select>
						</div>

						<div class="form-group">
							<input type="number" class="form-control item" id="balance"
								name="balance" step="0.01" placeholder="Initial Balance"
								required>
						</div>

						<div class="form-group">
							<button type="submit" class="btn btn-block create-account">Create
								Account</button>
						</div>
					</form>
				</div>
			</main>
		</div>
	</div>
</body>
</html>
