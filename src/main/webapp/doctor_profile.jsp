
<%@page import="hms.entity.Docters"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Doctor Profile</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	
	background-color: #f8f9fa;
}

.profile-section {
	background: white;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	margin-top: 30px;
}

.profile-header {
	text-align: center;
	margin-bottom: 20px;
}
.navbar {
            background-color: #004080; /* Dark blue color */
        }
</style>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark">
    <a class="navbar-brand" href="#">Healthcare Management System</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="#">Dashboard</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Appointments</a>
            </li>
            <div class="dropdown">
                <button class="btn dropdown-toggle text-white" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                   <%=session.getAttribute("name") %>
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <a class="dropdown-item" href="doctorprofile">Profile</a>
                    <a class="dropdown-item" href="doctorlogout">Logout</a>
                </div>
            </div>
        </ul>
    </div>
</nav>
	<!-- Profile Section -->
	<div class="container">
		<div class="profile-section">
			<div class="profile-header">
				<h2>Doctor Profile</h2>
				<%
				Docters doctors = (Docters) session.getAttribute("doctor");
				%>
			</div>
			<form action="update_doctor.jsp" method="post">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label for="doctorId">Doctor ID</label> <input type="text"
								class="form-control" name="doctorId"
								value="<%=doctors.getId()%>" readonly>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="firstName">First Name</label> <input type="text"
								class="form-control" name="firstName" id="firstName"
								placeholder="Enter First Name"
								value="<%=doctors.getFirstname()%>" readonly>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="lastName">Last Name</label> <input type="text"
								class="form-control" name="lastName" id="lastName"
								placeholder="Enter Last Name"
								value="<%=doctors.getLastname()%>" readonly>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="email">Email</label> <input type="text"
								class="form-control" name="email" id="email"
								value="<%=doctors.getEmail()%>" readonly>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="phoneNumber">Phone Number</label> <input type="text"
								class="form-control" name="phoneNumber" id="phoneNumber"
								placeholder="Enter Phone Number"
								value="<%=doctors.getPhonenumber()%>" readonly>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="specialization">Specialization</label> <input
						type="text" class="form-control" name="specialization"
						id="specialization" value="<%=doctors.getSpec()%>" readonly>
				</div>
				
			</form>
		</div>
	</div>

	<!-- Bootstrap JS and dependencies -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
