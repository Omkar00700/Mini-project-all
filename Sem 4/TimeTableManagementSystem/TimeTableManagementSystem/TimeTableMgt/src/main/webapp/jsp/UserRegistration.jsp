<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<body  style="background-image:url('img/dept_pic.jpg'); background-size: cover;"  class="img-responsive">

<div style="min-height: 588px;">
	<%@ include file="Header.jsp"%>
		
		
<div class="body"> 
 	
 	<div class="modal-dialog orsform_modal-dialog" >
 	
		<div class="modal-content">
			<div class="modal-heading">
			<%String msg=(String)request.getAttribute("succ");
			String firstValidate=(String)request.getAttribute("FName");
			String lastValidate=(String)request.getAttribute("LName");
			String emailValidate=(String)request.getAttribute("email");
			String passValidate=(String)request.getAttribute("password");
			String phoneValidate=(String)request.getAttribute("phoneNo");
			UserBean b=(UserBean)request.getAttribute("bean");
			String f="";
			String n="";
			String e="";
			String p="";
			long l=0;
			if(b!=null){
				f=b.getFirstName();
				n=b.getLameName();
				e=b.getEmailId();
				p=b.getPassword();
				l=b.getPhoneNo();
			}
			%>

				
				<h2 class="text-center">User Registration</h2>

				<br>
				<center>
					<b><font class="text-center" color="black"><%=(msg!=null)?msg:""%></font></b>

					
				</center>
			</div>
			<hr />

			<div class="modal-body">

				<form action="UserRegistrationCtl" role="form" method="post" class="form">
					
					
					<label  for="frstName">First Name</label>

					<div class="form-group">

						<div class="input-group">

							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-user"></span>
							</span> <input type="text" class="form-control" name="fName"
								placeholder="Enter First Name "
								value="<%=f%>" />
						</div>
						<b><font color="red"> <%=(firstValidate!=null)?firstValidate:""%></font></b>
					</div>
					
					<label  for="lastName">Last Name</label>

					<div class="form-group">

						<div class="input-group">

							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-user"></span>
							</span> <input type="text" class="form-control" name="lName"
								placeholder="Enter Last Name "
								value="<%=n%>" />
						</div>
						<b><font color="red"> <%=(lastValidate!=null)?lastValidate:""%></font></b>
					</div>

				<label  for="email">Email Id</label>

					<div class="form-group">

						<div class="input-group">

							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-user"></span>
							</span> <input type="text" class="form-control" name="email"
								placeholder="Enter Email Id"
								value="<%=e%>" />
						</div>
						<b><font color="red"> <%=(emailValidate!=null)?emailValidate:""%></font></b>
					</div>


					<label for="password">Password</label>
					<div class="form-group">

						<div class="input-group">

							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-list-alt"></span>
							</span> <input type="password" class="form-control" name="pwd"
								placeholder="Enter Password"
								value="<%=p%>" />
						</div>
						<b><font color="red"> <%=(passValidate!=null)?passValidate:""%></font></b>
					</div>
					
					<label  for="phoneNo">Phone No</label>

					<div class="form-group">

						<div class="input-group">

							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-user"></span>
							</span> <input type="text" class="form-control" name="phone"
								placeholder="Enter Phone No "
								value="<%=(l>0)?l:""%>" />
						</div>
						<b><font color="red"> <%=(phoneValidate!=null)?phoneValidate:""%></font></b>
					</div>


					<div class="form-group text-center">

						<input type="submit" class="btn btn-success btn-lg"
							 value="Submit"> &emsp;
						
						<input type="reset" class="btn btn-success btn-lg"
							 value="Reset">
						
					</div>


				</form>
			</div>
		</div>
	</div>
	 <div  style="width: 100px; height: 228px; "></div>
		
	<%@ include file="Footer.jsp"%>
	
	</div>
</body>
</html>