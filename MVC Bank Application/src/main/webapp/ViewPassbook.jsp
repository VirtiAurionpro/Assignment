<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.aurionpro.dao.TransactionDao"%>
<%@ page import="com.aurionpro.model.Transaction"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.aurionpro.model.Login"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Passbook</title>
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
</head>

<%
String accountNumber = (String) request.getParameter("accountNumber");
if (accountNumber != null) {
	session.setAttribute("AccountNo", accountNumber);
}

String AccountNo = (String) session.getAttribute("AccountNo");
TransactionDao transactionDao = new TransactionDao();

String from = request.getParameter("from");
String to = request.getParameter("to");
System.out.println("to" + to);

boolean hasFrom = from != null && !from.isEmpty();
boolean hasTo = to != null && !to.isEmpty();
if (hasFrom || hasTo) {
	List<Transaction> transactions = transactionDao.getTimetransactions(AccountNo, from, to);
	request.setAttribute("transactions", transactions);
} else {
	List<Transaction> transactions = transactionDao.getAlltransactions(AccountNo);
	request.setAttribute("transactions", transactions);
}

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
			if ("customer".equalsIgnoreCase(login2.getRole())) {
			%>
			<%@ include file="customerWithAcc.jsp"%>
			<%
			} else {
			%>
			<%@ include file="admin.jsp"%>
			<%
			}
			%>
			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 py-4">
				<c:choose>
					<c:when test="${empty transactions}">
						<div class="text-center">
							<h2>No Transactions Found</h2>
						</div>
					</c:when>
					<c:otherwise>

						<form action="ViewPassbook.jsp" method="get"
							class="row justify-content-evenly my-4">
							<div class="col-3">
								<label for="from">From:</label> <input class="form-control"
									type="date" name="from" id="from">
							</div>
							<div class="col-3">
								<label for="from">To:</label> <input class="form-control"
									type="date" name="to" id="to">
							</div>
							<div class="col-1 mt-4">
								<button type="submit" class="btn btn-info">Go</button>
							</div>
						</form>
						<div class="text-center tables">
							<table border="1" cellpadding="10" cellspacing="0"
								class="table table-bordered table-striped">
								<thead class="table-head">
									<tr>
										<th>Transaction ID</th>
										<th>Source Account</th>
										<th>Destination Account</th>
										<th>Amount</th>
										<th>Transaction Type</th>
										<th>Transaction Date</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="t" items="${transactions}">
										<tr>
											<td>${t.transaction_id}</td>
											<td>${t.sourceAcc}</td>
											<td>${t.desticationAcc}</td>
											<td>${t.amount}</td>
											<td>${t.transactionType}</td>
											<td>${t.transactionDate}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</c:otherwise>
				</c:choose>
			</main>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
