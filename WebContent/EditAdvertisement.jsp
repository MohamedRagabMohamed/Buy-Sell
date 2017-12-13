<!DOCTYPE html>
<%@page import="Modules.House"%>
<%@page import="Modules.Advertisement"%>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Update an Advertise</title>
<!-- Bootstrap core CSS-->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom fonts for this template-->
<link href="vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- Custom styles for this template-->
<link href="css/sb-admin.css" rel="stylesheet">
</head>

<body class="bg-dark">
	<div class="container">
		<div class="card card-register mx-auto mt-5">
			<div class="card-header">Update an Advertise</div>
			<div class="card-body">
				<form action="UpdateAdvertisementServlet">
<%-- 				<% --%>
<!--  					Advertisement advertisement = new Advertisement(); -->
<!--  					House house = new House(); -->
		
<!--  					advertisement = (Advertisement)request.getAttribute("advertisement"); -->
<!--  					house = (House)request.getAttribute("house"); -->
<%-- 				%> --%>
					<div class="form-group">
						<label>Advertisement Name</label> <input class="form-control"
							name="advertisementName" type="text" value=${advertisement.name }>
					</div>
					<div class="form-group">
						<label>Advertisement Type</label> <input class="form-control"
							name="advertisementType" type="text" value=${advertisement.type }>
					</div>
					<div class="card-header">House Details</div>
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">
								<label>Size</label> <input class="form-control" name="size"
									type="text" value="${house.size }">
							</div>
							<div class="col-md-6">
								<label>Floor</label> <input class="form-control" name="floor"
									type="text" value="${house.floor }">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label>Description</label> <input class="form-control"
							name="description" type="text" value="${house.description }">
					</div>
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">
								<label>Status</label> <input class="form-control" name="status"
									type="text" value="${house.status }">
							</div>
							<div class="col-md-6">
								<label>Type</label> <input class="form-control" name="houseType"
									type="text" value="${house.type }">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label>Add Images</label> <input class="form-control"
							name="images" type="text" value="${house.images }">
					</div>
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">
								<label>Longitude</label> <input class="form-control"
									name="longitude" type="text" value="${house.longitude }">
							</div>
							<div class="col-md-6">
								<label>Latitude</label> <input class="form-control"
									name="latitude" type="text" value="${house.latitude }">
							</div>
						</div>
					</div>
					<input class="btn btn-primary btn-block" type="submit" value="Update">
				</form>
			</div>
		</div>
	</div>
	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>
</body>

</html>
