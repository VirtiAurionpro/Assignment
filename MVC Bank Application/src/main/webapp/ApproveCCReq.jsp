<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.aurionpro.service.CreditService"%>
<%@ page import="com.aurionpro.model.Credit"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.*"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	rel="stylesheet">
<meta charset="UTF-8">
<title>Approve Credit Card Request</title>
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
<%
CreditService creditService = new CreditService();
List<Credit> pendingRequests = creditService.getRequest();
session.setAttribute("pendingRequests", pendingRequests);
%>
<body>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="admin.jsp"%>
			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 py-4">

				<c:choose>
					<c:when test="${empty pendingRequests}">
						<div class="text-center">
							<h2>No Pending Requests</h2>
						</div>
					</c:when>
					<c:otherwise>
						<div class="text-center tables">
							<table border="1" cellpadding="10" cellspacing="0"
								class="table table-bordered table-striped">
								<thead class="table-head">
									<tr>
										<th>Customer Name</th>
										<th>Customer Email</th>
										<th>Customer Age</th>
										<th>Application Date</th>
										<th>KYC</th>
										<th>Address Proof</th>
										<th>Income Proof</th>
										<th>Pan/Form 60</th>
										<th>Photograph</th>
										<th>Status</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="p" items="${pendingRequests}">
										<tr>
											<td>${p.customer.name}</td>
											<td>${p.customer.email}</td>
											<td>${p.customer.age}</td>
											<td>${p.application_date}</td>
											<td><a
												href="${pageContext.request.contextPath}/ViewDocumentController?id=${p.application_id}&type=kyc"
												target="_blank">
													<button type="button" class="btn btn-success btn-icon">
														<i class="bi bi-eye"></i>
													</button>
											</a></td>
											<td><a
												href="${pageContext.request.contextPath}/ViewDocumentController?id=${p.application_id}&type=address"
												target="_blank">
													<button type="button" class="btn btn-success btn-icon">
														<i class="bi bi-eye"></i>
													</button>
											</a></td>
											<td><a
												href="${pageContext.request.contextPath}/ViewDocumentController?id=${p.application_id}&type=income"
												target="_blank">
													<button type="button" class="btn btn-success btn-icon">
														<i class="bi bi-eye"></i>
													</button>
											</a></td>
											<td><a
												href="${pageContext.request.contextPath}/ViewDocumentController?id=${p.application_id}&type=pan"
												target="_blank">
													<button type="button" class="btn btn-success btn-icon">
														<i class="bi bi-eye"></i>
													</button>
											</a></td>
											<td><a
												href="${pageContext.request.contextPath}/ViewDocumentController?id=${p.application_id}&type=photo"
												target="_blank">
													<button type="button" class="btn btn-success btn-icon">
														<i class="bi bi-eye"></i>
													</button>
											</a></td>
											<td>
												<form action="ApproveCreditController" method="post"
													style="display: inline;">
													<input type="hidden" name="applicationNo"
														value="${p.application_id}">
													<button type="submit" class="btn btn-success btn-icon">
														<i class="fa-solid fa-check"></i>
													</button>
												</form>
											</td>
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
</body>
</html>