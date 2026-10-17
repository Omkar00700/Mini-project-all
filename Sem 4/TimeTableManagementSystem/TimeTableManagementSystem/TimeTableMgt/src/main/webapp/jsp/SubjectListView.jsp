<%@page import="in.co.time.table.bean.SubjectBean"%>
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
<body  style="background-image:url('img/National-Institute-Of-Technology-Surathkal.jpg'); background-size: cover;"  class="img-responsive">


<div class="body"> 
		<form action="SubjectListCtl" method="POST">
<%@ include file="Header.jsp" %>
			
<%String err=(String)request.getAttribute("err"); %>



			<div class="container-fluid modal-content">

				<div class="row">

					<div class="col-sm-4"></div>

					<div class="col-sm-4" align="center" style="color: white">
						<h1 style=" color: black;">Subject  List</h1>
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
									<th >Subject Name</th>
									<th >Subject Code</th>

									
								</tr>
							</thead>
							<tbody style="color: #fcf8e3;  background-color: rgba(0,0,0,.5);">
								<%
								if(list!=null){
									Iterator it = list.iterator();
										
									while (it.hasNext()) {

										SubjectBean	dto =(SubjectBean) it.next();
								%>
								<tr>
									<td ><%=index++%></td>
									<td ><%=dto.getSubjectName()%></td>
									<td ><%=dto.getSubjectCode()%></td>
									
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

 <div  style="width: 100%; height: 322px; "></div>
	<%@include file="Footer.jsp"%>
	
	</div>
</body>
</html>