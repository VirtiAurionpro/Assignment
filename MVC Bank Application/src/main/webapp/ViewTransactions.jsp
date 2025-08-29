<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.aurionpro.dao.TransactionDao"%>
<%@ page import="com.aurionpro.dao.AccountDao"%>
<%@ page import="com.aurionpro.model.Account"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Transaction</title>
</head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
<style>
.tables {
	border-width: 2px;
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
<%
AccountDao accountDao2 = new AccountDao();
Map<String, Account> transactionAccounts = accountDao2.getAlltransactionAccounts();
session.setAttribute("transactionAccounts", transactionAccounts);
%>
<body>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="admin.jsp"%>
			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 py-4">
				<div class="text-center tables">
					<table border="1" cellpadding="10" cellspacing="0"
						class="table table-bordered table-striped">
						<thead class="table-head">
							<tr>
								<th>Account Number</th>
								<th>Account Holder Name</th>
								<th>Account Opening Date</th>
								<th>Account Balance</th>
								<th>View PassBook</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="ta" items="${transactionAccounts.entrySet()}">
								<input type="hidden" name="accountNumber" value="${ta.key}">
								<tr>
									<td>${ta.key}</td>
									<td>${ta.value.name}</td>
									<td>${ta.value.opening_date}</td>
									<td>${ta.value.balance}</td>
									<td><a href="ViewPassbook.jsp?accountNumber=${ta.key}">
											<button type="button" class="btn btn-success btn-icon">
												<i class="bi bi-eye"></i>
											</button>
									</a></td>

								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</main>
		</div>
	</div>

</body>
</html>