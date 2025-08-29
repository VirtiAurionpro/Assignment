<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.aurionpro.model.Login"%>
<%@ page import="com.aurionpro.model.Credit"%>
<%@ page import="com.aurionpro.model.Account"%>
<%@ page import="com.aurionpro.model.Nominee"%>
<%@ page import="com.aurionpro.model.Registration"%>
<%@ page import="com.aurionpro.service.CustomerService"%>
<%@ page import="com.aurionpro.dao.CustomerDao"%>
<%@ page import="com.aurionpro.dao.CreditDao"%>
<%@ page import="com.aurionpro.dao.NomineeDao"%>

<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Profile Details</title>
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
<%
Login login2 = (Login) session.getAttribute("Login");
String username = login2.getUsername();
CustomerDao customerDao = new CustomerDao();
CustomerService customerService = new CustomerService();
Registration customer = customerDao.getCustomerByIdentifier(username);
String AccountNo = (String) session.getAttribute("AccountNo");

List<Credit> cards = new CreditDao().getAllCards(username);
List<Account> accounts = new AccountDao().getAllAccountsbyUsername(username);
List<Nominee> nominees = new NomineeDao().getAllNominees(AccountNo);

boolean accountExist = customerService.checkAccountExist(username);
session.setAttribute("allUserAccounts", accounts);
session.setAttribute("allUserCards", cards);
session.setAttribute("allUserNominees", nominees);
%>
<body>
	<div class="container-fluid">
		<div class="row">
			<%
			if (accountExist) {
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
				<div class="beneficiary-form">
					<form action="UpdateProfileController" method="post">
						<input type="hidden" value="<%=customer.getRegistration_id()%>"
							name="id">

						<div class="form-icon">
							<span><i class="bi bi-person-fill"></i></span>
						</div>
						<div class="text-center" style="position: relative; top: -20px;">
							<p
								class="fs-5 px-3 py-2 text-dark rounded w-auto d-inline-block fw-semibold"
								style="background-color: #cce5ff; border: 1px solid #b8daff;">
								Profile Details</p>
						</div>
						<div class="form-group">
							<input type="text" class="form-control item" id="name"
								name="name" placeholder="Customer Name:"
								value="<%=customer.getName()%>">
						</div>

						<div class="form-group">
							<input type="text" class="form-control item" id="email"
								name="email" placeholder="Customer email:"
								value="<%=customer.getEmail()%>">
						</div>

						<div class="form-group">
							<input type="text" class="form-control item" id="mobileNo"
								name="mobileNo" placeholder="Customer Mobile No:"
								value="<%=customer.getMobileNo()%>">
						</div>

						<div class="form-group">
							<input type="date" class="form-control item" id="dob" name="dob"
								placeholder="Customer Date of Birth:"
								value="<%=customer.getDob()%>">
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-block create-account">Update</button>
						</div>
					</form>


					<form class="mt-4">
						<div class="form-icon">
							<span><i class="bi bi-bank2"></i></span>
						</div>
						<div class="text-center" style="position: relative; top: -20px;">
							<p
								class="fs-5 px-3 py-2 text-dark rounded w-auto d-inline-block fw-semibold"
								style="background-color: #cce5ff; border: 1px solid #b8daff;">
								Account Details</p>
						</div>
						<c:choose>
							<c:when test="${empty allUserAccounts}">
								<div class="text-center">
									<h2>No Accounts</h2>
								</div>
							</c:when>
							<c:otherwise>
								<c:forEach var="au" items="${allUserAccounts}">

									<div class="form-group">
										<input type="text" class="form-control item"
											value="${au.accountNo}" readonly>
									</div>

									<div class="form-group">
										<input type="text" class="form-control item"
											value="${au.customerID}" readonly>
									</div>

									<div class="form-group">
										<input type="text" class="form-control item"
											value="${au.accountType}" readonly>
									</div>

									<div class="form-group">
										<input type="number" class="form-control item"
											value="${au.balance}" readonly>
									</div>

									<div class="form-group">
										<input type="date" class="form-control item"
											value="${au.opening_date}" readonly>
									</div>
								</c:forEach>
					</form>
					</c:otherwise>
					</c:choose>

					<form class="mt-4">
						<div class="form-icon">
							<span><i class="bi bi-person-add"></i></span>
						</div>
						<div class="text-center" style="position: relative; top: -20px;">
							<p
								class="fs-5 px-3 py-2 text-dark rounded w-auto d-inline-block fw-semibold"
								style="background-color: #cce5ff; border: 1px solid #b8daff;">
								Nominee Details</p>
						</div>
						<c:choose>
							<c:when test="${empty allUserNominees}">
								<div class="text-center">
									<h2>No Nominees</h2>
								</div>
							</c:when>
							<c:otherwise>
								<c:forEach var="n" items="${allUserNominees}">

									<div class="form-group">
										<input type="text" class="form-control item"
											value="${n.nominee_name}" readonly>
									</div>

									<div class="form-group">
										<input type="text" class="form-control item"
											value="${n.relation}" readonly>
									</div>

									<div class="form-group">
										<input type="number" class="form-control item"
											value="${n.nominee_age}" readonly>
									</div>

									<div class="form-group">
										<input type="text" class="form-control item"
											value="${n.nominee_identification_no}" readonly>
									</div>

									<div class="form-group">
										<input type="text" class="form-control item"
											value="${n.nominee_mobile_no}" readonly>
									</div>
								</c:forEach>
					</form>
					</c:otherwise>
					</c:choose>


					<form>
						<div class="form-icon">
							<span><i class="bi bi-credit-card-2-front"></i></span>
						</div>
						<div class="text-center" style="position: relative; top: -20px;">
							<p
								class="fs-5 px-3 py-2 text-dark rounded w-auto d-inline-block fw-semibold"
								style="background-color: #cce5ff; border: 1px solid #b8daff;">
								Card Details</p>
						</div>
						<c:choose>
							<c:when test="${empty allUserCards}">
								<div class="text-center">
									<h2>No Credit Cards Issued</h2>
								</div>
							</c:when>
							<c:otherwise>
								<c:forEach var="ac" items="${allUserCards}">
									<div class="form-group">
										<input type="text" class="form-control item"
											value="${ac.creditCardNo}">
									</div>

									<div class="form-group">
										<input type="text" class="form-control item"
											value="${ac.cardExpiry}">
									</div>

									<div class="form-group">
										<input type="text" class="form-control item"
											value="${ac.customer.name}">
									</div>
								</c:forEach>
					</form>
					</c:otherwise>
					</c:choose>
				</div>
			</main>
		</div>
	</div>

</body>
</html>