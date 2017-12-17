<%@page import="java.util.ArrayList"%>
<%@page import="Modules.User"%>
<%@page import="Modules.Advertisement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Home</title>
<!-- Bootstrap core CSS-->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom fonts for this template-->
<link href="vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- Page level plugin CSS-->
<link href="vendor/datatables/dataTables.bootstrap4.css"
	rel="stylesheet">
<!-- Custom styles for this template-->
<link href="css/sb-admin.css" rel="stylesheet">

<style>
div.gallery:hover {
	border: 1px solid #777;
}

div.gallery img {
	width: 100%;
	height: auto;
}

div.desc {
	padding: 15px;
	text-align: center;
}

.checked {
	color: orange;
}
</style>
</head>
<body>
	<%
		if(session.getAttribute("userID")==null){
			response.sendRedirect("login.html");
		}
	%>
	<jsp:include page="../NavBar.jsp" />


	<div class="content-wrapper">
		<div class="container-fluid">
			<div class="card mb-3">
				<div class="card-header">
					<i class="fa fa-table"></i> Data Table Example
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<table class="table table-bordered" id="example" width="100%"
							cellspacing="0">
							<thead>
								<tr>
									<th>Name</th>
									<th>Type</th>
									<th>Description</th>
									<th>Floor</th>
									<th>Size</th>
									<th>Status</th>
									<th>Rate</th>
									<th>View</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th>Name</th>
									<th>Type</th>
									<th>Description</th>
									<th>Floor</th>
									<th>Size</th>
									<th>Status</th>
									<th>Rate</th>
								</tr>
							</tfoot>
							<tbody>
								<%
									ArrayList<Advertisement> ads = (ArrayList<Advertisement>) request.getAttribute("ads");
									for (int i = 0; i < ads.size(); i++) {
								%>
								<tr>
									<td>
										<%
											out.print(ads.get(i).getName());
										%>
									</td>
									<td>
										<%
											out.print(ads.get(i).getType());
										%>
									</td>
									<td>
										<%
											out.print(ads.get(i).getHouse().getDescription());
										%>
									</td>
									<td>
										<%
											out.print(ads.get(i).getHouse().getFloor());
										%>
									</td>
									<td>
										<%
											out.print(ads.get(i).getHouse().getSize());
										%>
									</td>
									<td>
										<%
											out.print(ads.get(i).getHouse().getStatus());
										%>
									</td>
									<td>
										<%
											String[] read = ads.get(i).getRate().split("#");
												Integer[] readInt = new Integer[read.length];
												for (int j = 0; j < read.length; j++)
													readInt[j] = Integer.parseInt(read[j]);
												double rate = 0, sum = 0;
												for (int j = 0; j < readInt.length; j++) {
													rate += (readInt[j] * (j + 1));
													sum += readInt[j];

												}
												rate = rate / sum;
												for (int j = 0; j < 5; j++) {

													if (rate - 1 >= j) {
														%> <span class="fa fa-star checked"></span> <%
 													} else {
 														%> <span class="fa fa-star"></span> <%
 													}
 											}
 										%>
									</td>
									<td>
									<a href=<%
										String tmp = "/IA-Project/HouseDe?adId="+ads.get(i).getAdvertisementId();
										out.print(tmp);
									%>> <span class="fa fa-fw fa-film"></span>
									</a></td>
								</tr>

								<%
									}
								%>

							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /.container-fluid-->
	<!-- /.content-wrapper-->
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script type="text/javascript"
		src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							// Setup - add a text input to each footer cell
							$('#example tfoot th')
									.each(
											function() {
												var title = $(this).text();
												$(this)
														.html(
																'<input type="text" placeholder="Search '+title+'" />');
											});

							// DataTable
							var table = $('#example').DataTable();

							// Apply the search
							table
									.columns()
									.every(
											function() {
												var that = this;

												$('input', this.footer())
														.on(
																'keyup change',
																function() {
																	if (that
																			.search() !== this.value) {
																		that
																				.search(
																						this.value)
																				.draw();
																	}
																});
											});

							$('#example tbody').on( 'click', 'tr', function () {
							    console.log( table.row( this ).data() );
							} );
						});
	</script>
	<script src="./vendor/datatables/jquery.dataTables.js"></script>
	<script src="./vendor/datatables/dataTables.bootstrap4.js"></script>
</body>
</html>