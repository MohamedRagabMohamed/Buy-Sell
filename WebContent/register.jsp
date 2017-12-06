<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html>

<html lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
  <title>Sign Up</title>
  <!-- Bootstrap core CSS-->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom fonts for this template-->
  <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <!-- Custom styles for this template-->
  <link href="css/sb-admin.css" rel="stylesheet">
  
  <!-- JQUERY Library-->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <!-- sign-up manipulation-->
  <!-- <script src="C:\Users\maka\git\Buy-Sell\WebContent\js\signup.js"></script>-->
</head>

<body class="bg-dark">
  <div class="container">
    <div class="card card-register mx-auto mt-5">
      <div class="card-header">Register an Account</div>
      <div class="card-body">
        <form name="registerform" action="signupServlet" method="post" onsubmit="return ValidateSubmit()">
        
          <div class="form-group">
          	<div class="form-row">
              <div class="col-md-6">
                <label for="username">Username</label>
                <input class="form-control" id="username" type="text" aria-describedby="nameHelp" placeholder="Enter Username" required>
                <p id="usernameValidfield"> </p>
              </div>
              <input type="hidden" id="validflag" value = "false">
            </div>
            <div class="form-row">
              <div class="col-md-6">
                <label for="firstname">First name</label>
                <input class="form-control" id="firstname" type="text" aria-describedby="nameHelp" placeholder="Enter first name" required>
                <p id="firstnameValidfield"> </p>
              </div>
            </div>
			<div class="form-row">
              <div class="col-md-6">
                <label for="lastname">Last name</label>
                <input class="form-control" id="lastname" type="text" aria-describedby="nameHelp" placeholder="Enter last name" required>
                <p id="lastnameValidfield"> </p>
              </div>
            </div>
            <div class="form-row">
              <div class="col-md-6">
                <label for="address">Address</label>
                <input class="form-control" id="address" type="text" placeholder="Enter Address">
                <p id="addressValidfield"> </p>
              </div>
            </div>
            <div class="form-row">
              <div class="col-md-6">
                <label for="phone">Phone Number</label>
                <input class="form-control" id="phone" type="tel"  placeholder="Enter Phone Number">
                <p id="lastnameValidfield"> </p>
              </div>
            </div>
          </div>
          
          <div class="form-group">
          	<div class="form-row">
              <div class="col-md-6">
                <label for="email">Email address</label>
                <input class="form-control" id="email" type="email" aria-describedby="emailHelp" placeholder="Enter email" required>
              </div>
              <div class="col-md-6">
                <label for="confirmemail">Confirm Email address</label>
                <input class="form-control" name="confirmemail" id="confirmemail" type="email" aria-describedby="emailHelp" placeholder="Confirm email" required>
              </div>
              <p id="emailValidfield">
            </div>
          </div>
          
          <div class="form-group">
          	<div class="form-row">
              <div class="col-md-6">
                <label for="password">Password</label>
                <input class="form-control" id="password" type="password" placeholder="Password" required>
              </div>
              <div class="col-md-6">
                <label for="confirmpassword">Confirm password</label>
                <input class="form-control" name="confirmpassword" id="confirmpassword" type="password" placeholder="Confirm password" required>
              </div>
              <p id="passwordValidfield">
            </div>
          </div>
          <button class="btn btn-primary btn-block" type="submit" id="submitButton">Register</button>
        </form>
        <div class="text-center">
          <a class="d-block small mt-3" href="login.html">Login Page</a>
          <a class="d-block small" href="forgot-password.html">Forgot Password?</a>
        </div>
      </div>
    </div>
  </div>
  
  <script type="text/javascript">
  
  $("document").ready(function() {
		$("#username").on("blur", validateuserName);
		function validateuserName()
		{
			var uname = $('#username').val();
			var flag;
			$.post("usernameAjaxServlet", {
				username: uname
			}, flag = function(response, status){
				if(status === 'success'){
					if(response === 'true'){
						$("#usernameValidfield").html("Valid Username").css("color","green");
						$("#validflag").val(true);
					}
					else{
						$("#usernameValidfield").html("Not Valid Username").css("color","red");
						$("#validflag").val(false);
					}
				}
				else{
					console.log('signup Status :: ', status);
				}

		    });
			return flag;
		}
		
		//////////////////////////////////////////////////////////////////////////////////////
		
		$("#confirmemail").on("blur", checkMail);
		function checkMail()
		{
			var email1 = $('#email').val();
			var email2 = $('#confirmemail').val();
			if(email1 === email2)
			{
				$("#emailValidfield").html("Email Fields matched successfully").css("color","green");
				return true;
			}
			else{
				$("#emailValidfield").html("ERROR! Entered Email doesn't match the upper field").css("color","red");
				document.registerform.confirmemail.focus();
				return false;
			}
		}
		
		//////////////////////////////////////////////////////////////////////////////////////
		
		$("#confirmpassword").on("blur", checkPassword);
		function checkPassword()
		{
			var pass1 = $('#password').val();
			var pass2 = $('#confirmpassword').val();
			if(pass1 === pass2)
			{
				$("#passwordValidfield").html("Password Fields matched successfully").css("color","green");
				return true;
			}
			else{
				$("#passwordValidfield").html("ERROR! Entered Password doesn't match the upper field").css("color","red");
				document.registerform.confirmpassword.focus();
				return false;
			}
		}
		
		//////////////////////////////////////////////////////////////////////////////////////
		
		function ValidateSubmit() {
			var check1 = checkPassword();
			var check2 = checkMail();
			var check3 = $("#validflag").val();
			console.log("check1 ::" , check1);
			console.log("check2 ::" , check2);
			console.log("check3 ::" , check3);
			if(check1 == true && check2 == true && check3 == 'true')
			{
				console.log("mo salah");
				return true;
			}
			else
			{
				return false;
			}
			
		}
		
		
		
		
	});
  
  </script>
  
  
  <!-- Bootstrap core JavaScript-->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
</body>

</html>