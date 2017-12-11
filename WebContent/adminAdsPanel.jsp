<%@page import="Controller.AdvertisementController"%>
<%@page import="java.io.Console"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modules.User"%>
<%@page import="Modules.Advertisement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport"
			content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta name="description" content="">
		<meta name="author" content="">
		<title>Admin-Panel-AdsView</title>
		<!-- Bootstrap core CSS-->
		<link href="./vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<!-- Custom fonts for this template-->
		<link href="./vendor/font-awesome/css/font-awesome.min.css"
			rel="stylesheet" type="text/css">
		<!-- Custom styles for this template-->
		<link href="./css/sb-admin.css" rel="stylesheet">
		<style type="text/css">
			body {
    					padding-top: 3cm;
				 }
		</style>
	</head>
<body>

	<jsp:include page="./NavBar.jsp" />

	
	<div class="content-wrapper">
		<div class="container-fluid">
			<div class="card">
				<div class="card-body">
					<table id="added-advertisements" class="table">
							<tr>
								<th>Advertisement Name</th>
								<th>Suspend Advertisement</th>
								<th>Delete Advertisement</th>
							</tr>
					</table>
					<button type="button" id="btn1">Load</button>
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript">
  
  $("document").ready(function(){
	  $("#btn1").on("click", getAllActiveAds);
	  function getAllActiveAds()
	  {
		  $.ajax({
		        url: "activeAdsSevlet",
		        type: 'GET',
		        
		        success: function (data) {
		            $("tr:has(td)").remove();
		            console.log(data);  // for testing only
		            //var res=$.parseJSON(data);
		            var values = [];
		            values = data;
		 
		            $.each(values, function (index, ad) {
		 
		                var td_nameField = $("<td/>");
	                    var span = $("<span class='label label-info' style='margin:4px;padding:4px' />");
	                    span.text(ad.name);
	                    td_nameField.append(span);
	                    
	                    var td_suspendField = $("<td/>");
	                    var suspendButton = $("<button type=\"text\" > <span class=\"glyphicon glyphicon-minus-sign\"> </span> </button>");
	                    suspendButton.id('suspendButton' + index);
	                    td_suspendField.append(suspendButton);

		 
		                var td_deleteField = $("<td/>");
	                    var delButton = $("<button type=\"text\" > <span class=\"glyphicon glyphicon-remove-sign\"> </span> </button>");
	                    delButton.id('delButton' + index);
	                    td_deleteField.append(delButton);
	                    
	                    
		 
		                $("#added-advertisements").append($('<tr/>')
		                        .append($('<td/>').html("<a href='"+555555555555+"'>"+a7aaaaaaaaaaaaaaa+"</a>"))
		                        .append(td_suspendField)
		                        .append(td_deleteField)
		                );
		 
		            }); 
		        },
		        error:function(data,status,er) {
		            alert("error: "+data+" status: "+status+" er:"+er);
		        }
		    });
	  }
	  
	  
  });
  
 </script>

</body>
</html>