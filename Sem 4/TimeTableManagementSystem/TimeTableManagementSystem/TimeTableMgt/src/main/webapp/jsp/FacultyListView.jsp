<%@page import="in.co.time.table.bean.FacultyBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<body  style="background-image:url('img/National-Institute-Of-Technology-Surathkal.jpg'); background-size: cover;"  class="img-responsive">


<div class="body"> 
		<form action="FacultyListCtl" method="POST">
<%@ include file="Header.jsp" %>

			<%
				String err = (String) request.getAttribute("err");
			%>



			<div class="container-fluid modal-content">

				<div class="row">

					<div class="col-sm-4"></div>

					<div class="col-sm-4" align="center" style="color: white">
						<h1>Faculty List</h1>
					</div>

					<div class="col-sm-4"></div>

				</div>

				<hr>
				<br>

				<div class="form-group row">


					<div class="row">

						<div class="col-sm-4"></div>

						<div class="col-sm-4" align="center">

							<div align="center">

								<b><font color="red"><h4><%=(err!=null)?err:""%>
											</h4></font></b> 
							</div>

						</div>

						<div class="col-sm-4"></div>
					</div>
				</div>

				<div class="row">


					<div class="col-xs-12" align="center" style="overflow-x: auto">

						<table  class="table  table-bordered table-hover">

							<thead style="background-color: black; color: white;">

								<tr>
									<%
										List list=(List)request.getAttribute("list");
										int index=1;
									%>
									
									<th >S.No.</th>
									<th >Faculty Name</th>
									<th >Department</th>

									
								</tr>
							</thead>
							<tbody style="color: #fcf8e3;  background-color: rgba(0,0,0,.5);">
								<%
								
									Iterator it = list.iterator();
										if(list!=null){
									while (it.hasNext()) {

										FacultyBean	dto =(FacultyBean) it.next();
								%>
								<tr>
									<td ><%=index++%></td>
									<td ><%=dto.getFacultyName()%></td>
									<td ><%=dto.getDepartment()%></td>
									
								</tr>
								<%
									}}
								%>
							</tbody>
						</table>
					</div>
				</div>

			</div>
			
			
		</form>

 <div  style="width: 100px; height: 322px; "></div>
	<%@include file="Footer.jsp"%>
	
	</div>
</body>
</html>