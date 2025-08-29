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
<title>Transaction</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="customerWithAcc.jsp"%>
			<main
				class="col-md-9 ms-sm-auto col-lg-10 d-flex justify-content-center align-items-center min-vh-100">
				<form action="TransactionController" method="post"
					class="p-4 bg-light rounded shadow"
					style="width: 100%; max-width: 600px;">

					<input type="hidden" name="accountNo"
						value="${beneficiaryPayment.beneficiaryAccount_no}">

					<h4 class="mb-4 text-center">
						<i class="bi bi-person-circle me-2"></i> Beneficiary Details
					</h4>

					<div class="mb-3 d-flex align-items-center">
						<i class="bi bi-person-fill fs-5 me-2"></i> <label
							class="form-label flex-grow-1 mb-0">Name:</label> <span>${beneficiaryPayment.beneficiaryName}</span>
					</div>

					<div class="mb-3 d-flex align-items-center">
						<i class="bi bi-credit-card-2-front fs-5 me-2"></i> <label
							class="form-label flex-grow-1 mb-0">Account Number:</label> <span>${beneficiaryPayment.beneficiaryAccount_no}</span>
					</div>

					<div class="mb-3 d-flex align-items-center">
						<i class="bi bi-bank fs-5 me-2"></i> <label
							class="form-label flex-grow-1 mb-0">Bank Name:</label> <span>${beneficiaryPayment.beneficiaryBank_name}</span>
					</div>

					<div class="mb-3 d-flex align-items-center">
						<i class="bi bi-code-slash fs-5 me-2"></i> <label
							class="form-label flex-grow-1 mb-0">IFSC Code:</label> <span>${beneficiaryPayment.beneficiaryIfsc_code}</span>
					</div>

					<div class="mb-3 d-flex align-items-center">
						<i class="bi bi-tag fs-5 me-2"></i> <label
							class="form-label flex-grow-1 mb-0">Nickname:</label> <span>${beneficiaryPayment.beneficiaryNickname}</span>
					</div>

					<div class="mb-3">
						<label for="amount" class="form-label"> <i
							class="bi bi-cash-coin me-2"></i> Amount:
						</label> <input type="number" class="form-control" name="amount"
							id="amount" required min="1" placeholder="Enter amount">
					</div>

					<button type="submit" class="btn btn-primary w-100">
						<i class="bi bi-send-fill me-1"></i> Submit
					</button>
				</form>
			</main>

		</div>
	</div>
</body>
</html>