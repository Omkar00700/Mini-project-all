<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body  style="background-image:url('img/lg.jpg'); background-size: cover;"  class="img-responsive">

	 <%@ include file="Header.jsp"%> 

	
		<% String rmsg=(String)request.getAttribute("emsg");
		String email=(String)request.getAttribute("email");
		String pass=(String)request.getAttribute("pass");	
		String log=(String)request.getAttribute("log");
		String loginId=(String)request.getAttribute("loginId");
		String password=(String)request.getAttribute("password");
		 %>


<div class="body">
		<div class="modal-dialog  orsform_modal-dialog" >
			<div class="modal-content">


				<div class="modal-heading">

					<h2 class="text-center">Login</h2>

						<center>
					<b><font class="text-center" color="red"> <%=(rmsg!=null)?rmsg:""%></font></b>

					<b><font color="black"> <%=(log!=null)?log:""%></font></b>
				</center>
				</div>

				<hr>


				<div class="modal-body">
					<form action="LoginCtl" role="form" method="post" class="form">

					

						<div class="form-group">
							<label for="login"> Login Id</label>
							<div class="input-group">
								<span class="input-group-addon"> <span
									class="glyphicon glyphicon-user"></span>
								</span> <input type="text" class="form-control" placeholder="User Name"
									name="login"
									value="<%=(email!=null)?email:""%>" />
							</div>
							<b><font color="red"> <%=(loginId!=null)?loginId:""%></font></b>
						</div>


						<div class="form-group">
							<label for="password"> Password</label>
							<div class="input-group">
								<span class="input-group-addon"> <span
									class="glyphicon glyphicon-lock"></span>
								</span> <input type="password" class="form-control"
									placeholder="Password" name="pwd"
									value="<%=(pass!=null)?pass:""%>" />

							</div>
							<b><font color="red"> <%=(password!=null)?password:""%></font></b>
						</div>

						<div class="form-group text-center">
							<input type="submit" class="btn btn-success btn-lg"	name="operation" value="SignIn">
							 <input	type="submit" class="btn btn-success btn-lg" name="operation" value="SignUp"><br> 
						</div>

					</form>
				</div>


			</div>
			

		</div>
		
				
		 <div  style="width: 100px; height: 258px; "></div>

   <%@ include file="Footer.jsp"%>  
  

</div>
</body>
</html>