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
  
  <jsp:include page="../NavBar.jsp" />
  
  
  <div class="content-wrapper">
    <div class="container-fluid">
    	<h1>sidohd</h1>
    	<form action="Profile" method="post">
    		<input type="text" name="firstName"  value=${user.firstName }><b> First Name</b><br><br>
    		<input type="submit">
    	</form>
    </div>
  </div>
    <!-- /.container-fluid-->
    <!-- /.content-wrapper-->
</body>
</html>