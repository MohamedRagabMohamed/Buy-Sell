<%@page import="java.io.Console"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modules.*"%>
<%@page import="Modules.Advertisement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "html">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Advertisement Details</title>
<!-- Bootstrap core CSS-->
<link href="./vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom fonts for this template-->
<link href="./vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- Custom styles for this template-->
<link href="./css/sb-admin.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https:////netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css">
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


html, body, #googleMap {
    height: 100%;
    width: 100%;
    margin: 0px;
    padding: 0px
    top:0px;
}


fieldset, label { margin: 0; padding: 0; }
body{ margin: 20px; }
h1 { font-size: 1.5em; margin: 10px; }

/****** Style Star Rating Widget *****/

.rating { 
  border: none;
  float: left;
}

.rating > input { display: none; } 
.rating > label:before { 
  margin: 5px;
  font-size: 1.25em;
  font-family: FontAwesome;
  display: inline-block;
  content: "\f005";
}

.rating > .half:before { 
  content: "\f089";
  position: absolute;
}

.rating > label { 
  color: #ddd; 
 float: right; 
}

/***** CSS Magic to Highlight Stars on Hover *****/

.rating > input:checked ~ label, /* show gold star when clicked */
.rating:not(:checked) > label:hover, /* hover current star */
.rating:not(:checked) > label:hover ~ label { color: #FFD700;  } /* hover previous stars in list */

.rating > input:checked + label:hover, /* hover current star when changing rating */
.rating > input:checked ~ label:hover,
.rating > label:hover ~ input:checked ~ label, /* lighten current selection */
.rating > input:checked ~ label:hover ~ label { color: #FFED85;  } 



</style>

</head>
<body>

	<jsp:include page="NavBar.jsp" />


	<div class="content-wrapper">
		<div class="container-fluid">

			<%
				out.println("l2aa");
				Advertisement myAd = (Advertisement) request.getAttribute("Advertisement");
				House myHouse = (House) request.getAttribute("House");
				String images = myHouse.getImages();
				out.println("l2aa");
				String uName = (String) request.getAttribute("UserName");
				ArrayList<String> usernames = new ArrayList<String>();
				ArrayList<Comment> comments = new ArrayList<Comment>();
				usernames = (ArrayList<String>) request.getAttribute("CommentUserNames");
				comments = (ArrayList<Comment>) request.getAttribute("Comments");
			%>
			.<br>
			<br>
			<div>
				  <h3><%=uName%><h3>
			</div>
			<div>
				<h5><%=myAd.getName()%><h5>
			</div>

			<%
				String [] read = myAd.getRate().split("#");
				Integer [] readInt = new Integer[read.length];
				for(int i = 0 ; i < read.length ; i++)
					readInt[i]=Integer.parseInt(read[i]);
				double rate = 0 , sum = 0;
				for(int i = 0 ; i < readInt.length ; i++)
				{
					rate+=(readInt[i]*(i+1));
					sum+=readInt[i];
					
				}
				rate = rate/sum;
				for (int i = 0; i < 5; i++) {

					if (rate-1 >= i) {
			%>
							<span class="fa fa-star checked"></span>
			<%
					} else{
						
					
			%>
							<span class="fa fa-star"></span>
			<%
					}
				}
			%>
			<br>
			<%
				String[] source = images.split("##");
				for (int i = 0; i < source.length; i++) {
					String data = source[i];
					//out.print(data);
			%>

			<a target="_blank" href=<%=data%>> <img src=<%=data%>
				alt="Fjords" width="300" height="200">
			</a>

			<%
				}
			%>
			<div class="card">
				<div class="card-body">
					<table class="table">
						<tbody>
							<tr>
								<th>Description :</th>
								<td><%=myHouse.getDescription()%></td>
							</tr>
							<tr>
								<th>Status :</th>
								<td><%=myHouse.getStatus()%></td>
							</tr>
							<tr>
								<th>Type :</th>
								<td><%=myHouse.getType()%></td>
							</tr>
							<tr>
								<th>Floor :</th>
								<td><%=myHouse.getFloor()%></td>
							</tr>
							<tr>
								<th>Longitude :</th>
								<td><%=myHouse.getLongitude()%></td>
							</tr>
							<tr>
								<th>latitude :</th>
								<td><%=myHouse.getLatitude()%></td>
							</tr>
						</tbody>
					</table>

				</div>
			</div>
			<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;
   				key=ABQIAAAAcl" type="text/javascript"></script>
			<script type="text/javascript">
				function ShowMap(latitude, longitude) {
			    console.log("This is latitude :" + latitude);
			    console.log("This is longitude :" + longitude);

			    var myCenter = new google.maps.LatLng(latitude, longitude);
			    var marker;

			    function initialize() {
			        var mapProp = {
			            center: myCenter,
			            zoom: 15,
			            mapTypeId: google.maps.MapTypeId.ROADMAP
			        };

			        var map = new google.maps.Map(document.getElementById("googleMap"), mapProp);

			        var marker = new google.maps.Marker({
			            position: myCenter,
			            animation: google.maps.Animation.BOUNCE
			        });

			        marker.setMap(map);
			    }

			    initialize();
			}
			
			
			</script>
			<form>
    			<input type="number" name="latitude" value="13.021816"/>
    			<input type="number" name="longitude" value="80.219424"/>
    			<input type="button" onclick="ShowMap(latitude.value,longitude.value)" value="ShowMap" />
			</form>
			<!--  <body onload="ShowMap(150,50)">
			--><div id="googleMap" ></div>
			
			<form action = "HouseDe">
			<h3>Rate this advertisement:<h3>
			<fieldset class="rating">
				<input type="radio" id="star5" name="rating" value="5" /> <label class="full" for="star5" title="Awesome - 5 stars"></label> 
				<!--<input type="radio" id="star4half" name="rating" value="4 and a half" /><label class="half" for="star4half" title="Pretty good - 4.5 stars"></label>
				--><input type="radio" id="star4" name="rating" value="4" /><label class="full" for="star4" title="very good - 4 stars"></label> 
				<!--<input type="radio" id="star3half" name="rating" value="3 and a half" /><label class="half" for="star3half" title="Meh - 3.5 stars"></label>
				--><input type="radio" id="star3" name="rating" value="3" /><label class="full" for="star3" title="good - 3 stars"></label> 
				<!--<input type="radio" id="star2half" name="rating" value="2 and a half" /><label class="half" for="star2half" title="Kinda bad - 2.5 stars"></label>
				--><input type="radio" id="star2" name="rating" value="2" /><label class="full" for="star2" title="bad - 2 stars"></label>
				<!--<input type="radio" id="star1half" name="rating" value="1 and a half" /><label class="half" for="star1half" title="Meh - 1.5 stars"></label> 
				--><input type="radio" id="star1" name="rating" value="1" /><label class="full" for="star1" title="very bad - 1 star"></label>
				<!-- <input type="radio" id="starhalf" name="rating" value="half" /><label class="half" for="starhalf" title="Sucks big time - 0.5 stars"></label>
			 --></fieldset>
			<input type="hidden" id="adId" name="rating" value=<%myAd.getAdvertisementId(); %>   />
			<input type="submit" value="Rate">
			</form>
			<br><br>
		
		<h3>Comments :<h3>
					<%
						//out.print("\n\n\na7aaa1\n\n\n\n" + comments.size() );
					out.print(comments.size());
						for (int i = 0; i < comments.size(); i++) {
							//out.print("a7aaa");
					%>
					
					<div class="card">
						<!--  <div class="card-body">  -->
						<table class="table">
							<tbody>
								<tr>
									<th><h5><%=usernames.get(i)%>
											:
											<h5></th>
									<td><h8><%=comments.get(i)%><h8></td>
								</tr>
						</table>

						<!--  </div> -->
					</div>

					<%
						}
					%>
					<div class="card">
						<form action="HouseDe" id="usrform">
							<textarea rows="1" cols="65" name="newComment" form="usrform"></textarea>
							<!--  <input type="text" name="newComment"> -->
							<br>
							<input type="submit" value="Comment">
						</form>

					</div>
		</div>
	</div>



</body>
</html>