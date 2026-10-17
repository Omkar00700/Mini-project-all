<%@page import="in.co.time.table.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="img/favicon.png" rel="icon">

<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script	src="js/jquery.min.js"></script>

<script	src="js/bootstrap.min.js"></script>



<link rel="stylesheet" href="css/rohan.css">
<link href="img/favicon.png" rel="icon">

<link rel="stylesheet"	href="css/font-awesome.min.css">

<link rel="stylesheet" href="css/Bootstrap.css">
<link rel="stylesheet" href="css/rohan.css">


<style type="text/css">


.body {
 	
	
    min-height: calc(100%)  ;
	padding-top: 80px;
 	position: relative; 
 	top: 0;
 	margin-top: 0;
}

.orsform_modal-dialog {
	margin-top:0;
	margin-bottom: 0;
}

.footer {
	position: absolute;
	bottom: 0;
	width: 100%;
}

</style>
</head>
<%
		UserBean userDto = (UserBean) session.getAttribute("user");

		boolean userLoggedIn = userDto != null;

		String welcomeMsg = "Hi, ";

		if (userLoggedIn) {
			welcomeMsg += userDto.getFirstName().toUpperCase() ;
		} else {
			welcomeMsg += "Guest";
		}
	%>


	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">


			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>

			<a href="WelcomeCtl"><img
				style="background-color: #A9A9A9"
				src="img/nitk-logo.png" width="120" height="50">
			</a>

		</div>

	

		<div>
			<div class="collapse navbar-collapse" id="myNavbar">
				


				
				


				<ul class="nav navbar-nav">
					<li class="#"><a class="#" href="#"></a>
						<ul class="#">


							<li></li>
							<li></li>
						</ul></li>
				</ul>

	<%if(userLoggedIn) {%>
				<ul class="nav navbar-nav">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#"><b>Home</b> </a>
					</li>
				</ul>


				<ul class="nav navbar-nav">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#"><b>Time Table</b><span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="TimeTableCtl"><span
									class="glyphicon glyphicon-plus"></span>Add TimeTable</a></li>
							<li><a href="TimeTableListCtl"><span
									class="glyphicon glyphicon-list-alt"></span>TimeTable List</a></li>
						</ul>
					</li>
				</ul>
				<ul class="nav navbar-nav">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#"><b>Subject</b> <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="SubjectCtl"><span
									class="glyphicon glyphicon-plus"></span>Add Subject</a></li>
							<li><a href="SubjectListCtl"><span
									class="glyphicon glyphicon-list-alt"></span>Subject List</a></li>
						</ul></li>
				</ul>
				
				<ul class="nav navbar-nav">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#"><b>Faculty</b> <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="FacultyCtl"><span
									class="glyphicon glyphicon-plus"></span>Add Faculty</a></li>
							<li><a href="FacultyListCtl"><span
									class="glyphicon glyphicon-list-alt"></span>Faculty List</a></li>
						</ul></li>
				</ul>
				
				<ul class="nav navbar-nav">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#"><b>Contact Us</b></a>
					</li>
				</ul>
				<ul class="nav navbar-nav">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#"><b>About Us</b> </a>
					</li>
				</ul>
				
				
						
						
		<%} %>			
				
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#"><b><%=welcomeMsg%></b></a>
						<%if(userLoggedIn){ %>
						<li><a style="color: red" href="LoginCtl?operation=Logout">
									<span style="color: blue" class="glyphicon glyphicon-log-out"></span>
									LogOut
							</a></li>
							<%}else{ %>
							<li><a style="color:blue;" href="LoginCtl">
									<span style="color: blue" class="glyphicon glyphicon-log-in"></span>
									Login
							</a></li>
							<%} %>
						</ul>
			</div>
		
			
			


			




		</div>

	</div>
	</nav>
</html>