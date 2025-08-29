<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.aurionpro.dao.BeneficiaryDao"%>
<%@ page import="com.aurionpro.model.Beneficiary"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Make Payment</title>
<style>
.tables {border-width-2px;
	border-radius: 10px;
}

.table-head {
	text-align: center;
	background-color: #5891ff;
	color: white;
	margin: auto;
	margin-bottom: 50px;
}

.tables table {
	background-color: #fff;
	border-collapse: separate;
	border-spacing: 0;
	width: 90%;
	margin: 30px auto;
	box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.075);
	border-radius: 15px;
	overflow: hidden;
}

.tables th, .tables td {
	padding: 15px;
	text-align: center;
	border: none;
}

.tables thead {
	background-color: #5791ff;
	color: white;
	font-weight: bold;
}

/*
.tables tbody tr:nth-child(even) {
    background-color: #f2f6ff;
}

.tables tbody tr:hover {
    background-color: #e0ebff;
    transition: background-color 0.3s ease;
}
*/
.tables a {
	color: #5791ff;
	text-decoration: none;
	font-weight: bold;
}

.tables a:hover {
	text-decoration: underline;
	opacity: 0.8;
}
</style>
</head>
<body>
	<%
	String AccountNo = (String) session.getAttribute("AccountNo");
	BeneficiaryDao beneficiaryDao = new BeneficiaryDao();
	List<Beneficiary> beneficiaries = beneficiaryDao.getAllBeneficiaries(session);
	request.setAttribute("beneficiaries", beneficiaries);
	%>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="customerWithAcc.jsp"%>
			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 py-4">
				<c:choose>
					<c:when test="${empty beneficiaries}">
						<div class="text-center">
							<h2>You have not added any beneficiaries yet</h2>
							<h2>Please add one before trying to make a payment</h2>
							<a href="AddBeneficiary.jsp?id=${b.beneficiaryId}"
								class="btn btn-secondary">Add Beneficiary</a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="text-center tables">
							<table border="1" cellpadding="10" cellspacing="0"
								class="table table-bordered table-striped">
								<thead class="table-head">
									<tr>
										<th>Account No</th>
										<th>Name</th>
										<th>Bank Name</th>
										<th>IFSC Code</th>
										<th>Nickname</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="b" items="${beneficiaries}">
										<tr>
											<td>${b.beneficiaryAccount_no}</td>
											<td>${b.beneficiaryName}</td>
											<td>${b.beneficiaryBank_name}</td>
											<td>${b.beneficiaryIfsc_code}</td>
											<td>${b.beneficiaryNickname}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<form action="PaymentController" method="post">
							<label>Select Account No:</label> <select name="accountNo"
								id="accountNo" class="form-control">
								<c:forEach var="b" items="${beneficiaries}">
									<option value="${b.beneficiaryAccount_no}">
										${b.beneficiaryAccount_no}</option>
								</c:forEach>
							</select>
							<button class="btn btn-primary mt-2">Send Money</button>
						</form>

					</c:otherwise>
				</c:choose>
			</main>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>