<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Nominee</title>
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
	<div class="container-fluid">
		<div class="row">
			<%@ include file="customerWithAcc.jsp"%>
			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 py-4">

				<div class="beneficiary-form">
					<form action="AddNomineeController" method="post">
						<div class="form-icon">
							<span><i class="bi bi-person-fill"></i></span>
						</div>
						<div class="text-center">
							<p
								class="fs-5 px-3 py-2 text-dark rounded w-auto d-inline-block fw-semibold"
								style="background-color: #cce5ff; border: 1px solid #b8daff;">
								Nominee Details</p>
						</div>

						<div class="form-group">
							<input type="text" class="form-control item" id="name"
								name="name" placeholder="Nominee Name" required>
						</div>

						<div class="form-group">
							<input type="text" class="form-control item" id="relation"
								name="relation" placeholder="Relation with Nominee" required>
						</div>

						<div class="form-group">
							<input type="text" class="form-control item" id="age" name="age"
								placeholder="Nominee Age" required>
						</div>

						<div class="form-group">
							<input type="text" class="form-control item" id="identification"
								name="identification"
								placeholder="Nominee Identification Number" required>
						</div>

						<div class="form-group">
							<input type="text" class="form-control item" id="mobile"
								name="mobile" placeholder="Nominee Mobile Number" required>
						</div>

						<div class="form-group">
							<button type="submit" class="btn btn-block create-account">Add</button>
						</div>
					</form>
				</div>
			</main>
		</div>
	</div>

</body>
</html>