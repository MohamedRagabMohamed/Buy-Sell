<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
<title>Edit Profile</title>
  <!-- Bootstrap core CSS-->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom fonts for this template-->
  <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <!-- Page level plugin CSS-->
  <link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
  <!-- Custom styles for this template-->
  <link href="css/sb-admin.css" rel="stylesheet">
</head>
<body>
  	<%
		if(session.getAttribute("userID")==null){
			response.sendRedirect("login.html");
			return;
		}
	%>
  <jsp:include page="../NavBar.jsp" />
  
  
  <div class="content-wrapper">
    <div class="container-fluid">
    	<form action="Profile" method="post">
    		<input type="text" name="id"  value=${user.userId }><br><br>
    		<b> First Name : </b> <input type="text" name="firstName"  value=${user.firstName }><br><br>
    		<b> Last  Name : </b> <input type="text" name="lastName"  value=${user.lastName }><br><br>
    		<b> Email      : </b> <input type="text" name="email"  value=${user.email }><br><br>
    		<b> Username   : </b> <input type="text" name="userName"  value=${user.userName }><br><br>
    		<b> Profile Pic: </b> <input type="text" name="profilePicture"  value=${user.profilePicture }><br><br>
    		<b> Address    : </b> <input type="text" name="address"  value=${user.address }><br><br>
    		<b> Phone No   : </b> <input type="text" name="phoneNumber"  value=${user.phoneNumber }><br><br>
    		<input type="submit">
    	</form>
    </div>
  </div>
    <!-- /.container-fluid-->
    <!-- /.content-wrapper-->
</body>
</html>