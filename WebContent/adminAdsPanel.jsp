<%@page import="Controller.AdvertisementController"%>
<%@page import="java.io.Console"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modules.User"%>
<%@page import="Modules.Advertisement"%>
<%@page import="java.sql.SQLException"%>
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
		    h2 {
  					padding-left: 1cm;
		         }
		</style>
	</head>
<body>

	<jsp:include page="./NavBar.jsp" />
	
	<%
		AdvertisementController adControl = new AdvertisementController();
		ArrayList<Advertisement> activeAds = new ArrayList<Advertisement>();
		
		try {
			activeAds = adControl.getAciveAds();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	%>

	
	<div class="content-wrapper">
		<div class="container-fluid">
			<div class="card">
				<h2>Admin Panel - System Advertisements</h2>
				<div class="card-body">
					<table id="added-advertisements" class="table">
							<tr>
								<th>Advertisement Name</th>
								<th>Suspend Advertisement</th>
								<th>Delete Advertisement</th>
								<th>Suspension State</th>
							</tr>
							<%
								
								for(int i = 0 ; i < activeAds.size() ; i++)
								{
									Advertisement currAd = activeAds.get(i);
									
									String delbtnID = " id = \"delBtn" + i + "\" ";
									String susbtnID = " id = \"susBtn" + i + "\" ";
									String btnValue = " value = \"" + currAd.getAdvertisementId() + "\" " ;
									String delbtnAttr = delbtnID + btnValue;
									String susbtnAttr = susbtnID + btnValue;
									
									out.println("<tr>");
									out.println("<td>" + "<p>" + currAd.getName() + "</p>" + "</td>");
									out.println("<td>" + "<button " + susbtnAttr + " type=\"text\" class=\"btn btn-primary btn-sm\">Suspend</button>" + "</td>");
									out.println("<td>" + "<button " + delbtnAttr + " type=\"text\" class=\"btn btn-primary btn-sm\">Delete </button>" + "</td>");
									out.println("</tr>");
								}
							
							%>
					</table>
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript">
  
  $("document").ready(function(){
	  
	  $("body").on('click', ":button", function() {
		  
    		var currentBtnId = $(this).attr('id');
    		var currentAdId = $(this).attr('value');
    		console.log("btnID :: ", currentBtnId);
    		console.log("AdID :: ", currentAdId);
    		var delSubString = "delBtn";
    		var susSubString = "susBtn";
    		
    		if(currentBtnId.indexOf(delSubString) !== -1) // delbuttonCase
    		{
    			$.ajax({
    		        url: "adminDelAdServlet",
    		        type: 'POST',
    		        data: {adID: currentAdId},
    		        
    		        success: function (response) {
    		        	if(response === 'true'){
    		        		$("#"+currentBtnId).closest("tr").remove();
    					}
    		        	else{
    		        		alert('Request Success ... BUT == false');
    		        	}
    		        },
    		        error:function(data,status,er) {
    		            alert("Request Failed !!!\n" + "error: "+data+" status: "+status+" er:"+er);
    		        }
    		    });
    			
    		}
    		else if(currentBtnId.indexOf(susSubString) !== -1) // susbuttonCase
    		{
    			$.ajax({
    		        url: "adminsuspendADServlet",
    		        type: 'POST',
    		        data: {adID: currentAdId},
    		        
    		        success: function (response) {
    		        	if(response === 'true'){
    		        		//alert("Advertisement Suspended successfully !!!");
    		        		$("#"+currentBtnId).closest('tr').append('<td>'+ "(SUSPENDED)" +'</td>');
    					}
    		        	else{
    		        		alert('Request Success ... BUT == false');
    		        		
    		        	}
    		        },
    		        error:function(data,status,er) {
    		        	alert("Request Failed !!!\n" + "error: "+data+" status: "+status+" er:"+er);
    		        }
    		    });
    			
    		}
    		else{
    			alert('mys7sh kdaaaaaaaaaaaaaa');
    		}
	  
	  });
	  
	  
  });
  
 </script>

</body>
</html>