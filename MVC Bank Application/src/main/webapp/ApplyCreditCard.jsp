<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.aurionpro.model.Login"%>
<%@ page import="com.aurionpro.service.CustomerService"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Apply for Credit Card</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
<style>
.credit-form {
	background-color: #fff;
	max-width: 600px;
	margin: auto;
	padding: 50px 70px;
	border-radius: 30px;
	box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.075);
}

.credit-form .form-icon {
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

.credit-form .item {
	border-radius: 20px;
	margin-bottom: 15px;
	margin-top: 15px;
	padding: 10px 20px;
}

.credit-form .create-account {
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
<%
Login login2 = (Login) session.getAttribute("Login");
if (login2 == null) {
	response.sendRedirect("login.jsp");
	return;
}
%>
<body>
	<div class="container-fluid">
		<div class="row">
			<%
			String username = login2.getUsername();
			CustomerService customerService = new CustomerService();
			if (customerService.checkAccountExist(username)) {
			%>
			<%@ include file="customerWithAcc.jsp"%>
			<%
			} else {
			%>
			<%@ include file="customerWoAcc.jsp"%>
			<%
			}
			%>
			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 py-4">

				<div class="credit-form">
					<form action="ApplyCreditCardController" method="post"
						enctype="multipart/form-data">
						<div class="form-icon">
							<span><i class="bi bi-credit-card"></i></span>
						</div>
						<div class="text-center">
							<p
								class="fs-5 px-3 py-2 text-dark rounded w-auto d-inline-block fw-semibold"
								style="background-color: #cce5ff; border: 1px solid #b8daff;">
								Enter Details</p>
						</div>

						<div class="form-group">
							<label class="item" for="kycDocument">KYC Document</label> <input
								type="file" class="form-control" id="kycDocument"
								name="kycDocument" required>
						</div>

						<div class="form-group">
							<label class="item" for="addressProof">Address Proof</label> <input
								type="file" class="form-control" id="addressProof"
								name="addressProof" required>
						</div>

						<div class="form-group">
							<label class="item" for="incomeProof">Income Proof</label> <input
								type="file" class="form-control" id="incomeProof"
								name="incomeProof" required>
						</div>

						<div class="form-group">
							<label class="item" for="panOrForm60">PAN / Form 60</label> <input
								type="file" class="form-control" id="panOrForm60"
								name="panOrForm60" required>
						</div>

						<div class="form-group">
							<label class="item" for="photograph">Photograph</label> <input
								type="file" class="form-control" id="photograph"
								name="photograph" required>
						</div>

						<div class="form-group">
							<button type="submit" class="btn btn-block create-account">Submit</button>
						</div>
					</form>
				</div>
			</main>
		</div>
	</div>
</body>
</html>