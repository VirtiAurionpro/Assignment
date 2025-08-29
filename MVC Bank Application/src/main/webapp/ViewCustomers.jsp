<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.aurionpro.dao.CustomerDao"%>
<%@ page import="com.aurionpro.model.Registration"%>
<%@ page import="com.aurionpro.model.Login"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<title>Customers</title>
<%
CustomerDao customerDao = new CustomerDao();
List<Registration> customers = customerDao.getAllCustomers();
session.setAttribute("customers", customers);
System.out.println(customers.size());
Login login = (Login) session.getAttribute("Login");
if (login == null) {
	response.sendRedirect("login.jsp");
	return;
}
%>
</head>
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
								<th>Customer Name</th>
								<th>Customer Email</th>
								<th>Customer DOB</th>
								<th>Customer Age</th>
								<th>Customer Mobile Number</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="c" items="${customers}">
								<tr>
									<td>${c.name}</td>
									<td>${c.email}</td>
									<td>${c.dob}</td>
									<td>${c.age }</td>
									<td>${c.mobileNo}</td>
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