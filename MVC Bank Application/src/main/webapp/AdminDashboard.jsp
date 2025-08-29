<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.aurionpro.dao.AccountDao"%>
<%@ page import="com.aurionpro.dao.CustomerDao"%>
<%@ page import="com.aurionpro.dao.CreditDao"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<style type="text/css">
.bg-c-blue {
	background: linear-gradient(45deg, #4099ff, #73b4ff);
}

.bg-c-green {
	background: linear-gradient(45deg, #2ed8b6, #59e0c5);
}

.bg-c-yellow {
	background: linear-gradient(45deg, #FFB64D, #ffcb80);
}

.bg-c-pink {
	background: linear-gradient(45deg, #FF5370, #ff869a);
}
</style>
</head>
<%
AccountDao accountDao = new AccountDao();
CreditDao creditdao = new CreditDao();
CustomerDao customerDao = new CustomerDao();
int count = accountDao.getNotApprovedAccounts();
int count3 = accountDao.getApprovedAccounts();
int count4 = customerDao.getActiveCustomers();
int count2 = creditdao.getNotApprovedRequests();
%>
<body>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="admin.jsp"%>

			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 py-4">
				<h1 class="mb-4 fw-bold">
					Welcome back, <span class="text-primary">Admin</span>
				</h1>

				<div class="row g-4 mb-4">
					<div class="col-sm-6 col-lg-3 d-flex">
						<div class="card shadow-sm h-100 w-100 bg-c-blue">
							<div class="card-body">
								<h6 class="text-muted">Pending Accounts</h6>
								<h3 class="fw-semibold"><%=count%></h3>
								<!--  
								<span class="badge bg-success"> <i
									class="bi bi-arrow-up-right-circle me-1"></i> +12% this month
								</span>
								-->
							</div>
						</div>
					</div>

					<div class="col-sm-6 col-lg-3 d-flex">
						<div class="card shadow-sm h-100 w-100 bg-c-green">
							<div class="card-body">
								<h6 class="text-muted">Pending Card Requests</h6>
								<h3 class="fw-semibold"><%=count2%></h3>
								<!--  
								<span class="badge bg-success"> <i
									class="bi bi-arrow-up-right-circle me-1"></i> +12% this month
								</span>
								-->
							</div>
						</div>
					</div>

					<div class="col-sm-6 col-lg-3 d-flex">
						<div class="card shadow-sm h-100 w-100 bg-c-yellow">
							<div class="card-body">
								<h6 class="text-muted">Total Customers</h6>
								<h3 class="fw-semibold"><%=count4%></h3>
								<!--  
								<span class="badge bg-success"> <i
									class="bi bi-arrow-up-right-circle me-1"></i> +12% this month
								</span>
								-->
							</div>
						</div>
					</div>

					<div class="col-sm-6 col-lg-3 d-flex">
						<div class="card shadow-sm h-100 w-100 bg-c-pink">
							<div class="card-body">
								<h6 class="text-muted">Total Accounts</h6>
								<h3 class="fw-semibold"><%=count3%></h3>
								<!--  
								<span class="badge bg-success"> <i
									class="bi bi-arrow-up-right-circle me-1"></i> +12% this month
								</span>
								-->
							</div>
						</div>
					</div>
				</div>
			</main>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>