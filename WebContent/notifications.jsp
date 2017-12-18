<%@page import="java.io.Console"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modules.User"%>
<%@page import="Modules.Advertisement"%>
<%@ page import="Controller.NotificationController" %>
<%@ page import="Modules.Notification" %>
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
<title>Notifications</title>
<!-- Bootstrap core CSS-->
<link href="./vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom fonts for this template-->
<link href="./vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- Custom styles for this template-->
<link href="./css/sb-admin.css" rel="stylesheet">
</head>
<body>

	<%
		out.println(session.getAttribute("userID"));
		if(session.getAttribute("userID")==null){
			response.sendRedirect("login.html");
			return;
		}
	%>
	<jsp:include page="/NavBar.jsp" />
	

	<div class="content-wrapper">
		<div class="container-fluid">
			<div class="card">
				<div class="card-body">
					<h6>..</h6>
					<h4 class="card-title">My Notifications :</h4>
					<div class="card-body">
						<table class="table">
							<tbody>
								<tr>
									<th>Notification</th>
									<th>Link</th>
								</tr>
								<%
						    		// get user ID from the session
						    		
						    		String userID=request.getSession().getAttribute("userID").toString();
						    			
									NotificationController controller=new NotificationController();
									//out.println("USER_ID : "+userID);
	    							ArrayList<Notification>list=controller.getNotifications(userID);
	    				    		for(Notification notification : list){
	    					    		out.println("<tr>");
	    					    		out.println("<td>"+notification.getNotification());
	    					    		out.println("</td>");
	    					    		String str="<a href=\"http://localhost:8080/IA-Project/HouseDe?adId="+notification.getAdvID()+"\""+">view</a>";
	    					    		out.println("<td>"+str);
	    					    		out.println("</td>");
	    					    		out.println("</tr>");
	    				    		}
								%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>



</body>
</html>