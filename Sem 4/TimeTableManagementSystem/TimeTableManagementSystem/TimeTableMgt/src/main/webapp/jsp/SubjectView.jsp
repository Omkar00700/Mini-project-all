<%@page import="in.co.time.table.bean.SubjectBean"%>
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
			String nameValidate=(String)request.getAttribute("subjectName"); 
			String codeValidate=(String)request.getAttribute("subjectCode"); 
				SubjectBean b=(SubjectBean)request.getAttribute("bean");
				String s="";
				long l=0;
				if(b!=null){
					s=b.getSubjectName();
					l=b.getSubjectCode();
				}
			%>
				
				<h2 class="text-center">Add Subject</h2>

				<br>
				<center>
					<b><font class="text-center" color="black"><%=(msg!=null)?msg:""%></font></b>

					
				</center>
			</div>
			<hr />

			<div class="modal-body">

				<form action="SubjectCtl" role="form" method="post" class="form">
					
					
					<label  for="subjectName">Subject Name</label>

					<div class="form-group">

						<div class="input-group">

							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-user"></span>
							</span> <input type="text" class="form-control" name="name"
								placeholder="Enter Subject Name "
								value="<%=s%>" />
						</div>
						<b><font color="red"> <%=(nameValidate!=null)?nameValidate:""%></font></b>
					</div>




					<label for="description">Subject Code</label>
					<div class="form-group">

						<div class="input-group">

							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-list-alt"></span>
							</span> <input type="text" class="form-control" name="code"
								placeholder="Enter Subject Code"
								value="<%=(l>0)?l:""%>" />
						</div>
						<b><font color="red"> <%=(codeValidate!=null)?codeValidate:""%></font></b>
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