<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.aurionpro.dao.BeneficiaryDao"%>
<%@ page import="com.aurionpro.model.Beneficiary"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
<meta charset="UTF-8">
<title>Edit Beneficiary</title>
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
	<%
	String id = request.getParameter("id");
	BeneficiaryDao beneficiaryDao = new BeneficiaryDao();
	Beneficiary beneficiary = beneficiaryDao.getBeneficiaryByID(id);
	%>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="customerWithAcc.jsp"%>
			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 py-4">
				<div class="beneficiary-form">
					<form action="EditBeneficiaryController" method="post">
						<input type="hidden" value="<%=beneficiary.getBeneficiaryId()%>"
							name="id">

						<div class="form-icon">
							<span><i class="bi bi-person-fill"></i></span>
						</div>
						<div class="text-center">
							<p
								class="fs-5 px-3 py-2 text-dark rounded w-auto d-inline-block fw-semibold"
								style="background-color: #cce5ff; border: 1px solid #b8daff;">Edit
								Beneficiary Details</p>
						</div>
						<div class="form-group">
							<input type="text" class="form-control item" id="name"
								name="name" placeholder="Beneficiary Name:"
								value="<%=beneficiary.getBeneficiaryName()%>">
						</div>

						<div class="form-group">
							<input type="text" class="form-control item" id="accountNo"
								name="accountNo" placeholder="Beneficiary Account Number"
								value="<%=beneficiary.getBeneficiaryAccount_no()%>">
						</div>

						<div class="form-group">
							<input type="text" class="form-control item"
								id="confirmaccountNo" name="confirmaccountNo"
								placeholder="Re-Type Account Number"
								value="<%=beneficiary.getBeneficiaryAccount_no()%>">
						</div>

						<div class="form-group">
							<input type="text" class="form-control item" id="bankname"
								name="bankname" placeholder="Bank Name"
								value="<%=beneficiary.getBeneficiaryBank_name()%>">
						</div>

						<div class="form-group">
							<input type="text" class="form-control item" id="ifsc"
								name="ifsc" placeholder="Bank IFSC Code"
								value="<%=beneficiary.getBeneficiaryIfsc_code()%>">
						</div>

						<div class="form-group">
							<input type="text" class="form-control item" id="mobile"
								name="mobile" placeholder="Beneficiary Mobile Number:"
								value="<%=beneficiary.getBeneficiaryMobile_no()%>">
						</div>

						<div class="form-group">
							<input type="email" class="form-control item" id="email"
								name="email" placeholder="Beneficiary Email:"
								value="<%=beneficiary.getBeneficiaryEmail()%>">
						</div>

						<div class="form-group">
							<input type="text" class="form-control item" id="nickname"
								name="nickname" placeholder="Beneficiary Nickname:"
								value="<%=beneficiary.getBeneficiaryNickname()%>">
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-block create-account">Update</button>
						</div>
					</form>
				</div>
			</main>
		</div>
	</div>

</body>
</html>
