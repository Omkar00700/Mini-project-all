<%@page import="java.util.Random"%>
<%@page import="java.util.RandomAccess"%>
<%@page import="java.util.Collections"%>
<%@page import="in.co.time.table.bean.TimeTableBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body
	style="background-image: url('img/National-Institute-Of-Technology-Surathkal.jpg'); background-size: cover;"
	class="img-responsive">


	<div class="body">
		<form action="TimeTableListCtl" method="POST">
			<%@ include file="Header.jsp"%>

			<%
				String err = (String) request.getAttribute("err");
			%>


			<div class="container-fluid modal-content">

				<div class="row">

					<div class="col-sm-4"></div>

					<div class="col-sm-4" align="center" style="color: white">
						<h1 style="color: black;">Time List Table</h1>
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

								<b><font color="red"><h4><%=(err != null) ? err : ""%>
										</h4></font></b>
							</div>

						</div>

						<div class="col-sm-4"></div>
					</div>
				</div>

				<div class="row">


					<div class="col-xs-12" align="center" style="overflow-x: auto">

						<table class="table  table-bordered table-hover">

							<thead style="background-color: black; color: white;">

								<tr>

									<th>Day</th>
									<th>09:00 AM to 10:00 AM</th>
									<th>10:00 AM to 11:00 AM</th>
									<th>11:00 AM to 12:00 PM</th>
									<th>12:00 PM to 01:00 PM</th>
									<th>01:00 PM to 02:00 PM</th>
									<th>02:00 PM to 03:00 PM</th>


								</tr>
							</thead>
							<tbody
								style="color: #fcf8e3; background-color: rgba(0, 0, 0, .5);">



								<%
									List list1 = (List) request.getAttribute("facultyName");

									for (int i = 1; i <= 6; i++) {
										String Name1 = (String) list1.get(new Random().nextInt(list1.size()));
										String Name2 = (String) list1.get(new Random().nextInt(list1.size()));
										String Name3 = (String) list1.get(new Random().nextInt(list1.size()));
										String Name4 = (String) list1.get(new Random().nextInt(list1.size()));
										String Name5 = (String) list1.get(new Random().nextInt(list1.size()));
										String Name6 = (String) list1.get(new Random().nextInt(list1.size()));

										if (i == 1) {
								%><tr>
									<td>Monday</td>
									<%
										} else if (i == 2) {
									%><td>Tuesday</td>
									<%
										} else if (i == 3) {
									%><td>Wednesday</td>
									<%
										} else if (i == 4) {
									%><td>Thusday</td>
									<%
										} else if (i == 5) {
									%><td>Fariday</td>
									<%
										} else if (i == 6) {
									%><td>Saturday</td>
									<%
										}
									%>
									<td><%=Name1%></td>
									<td><%=Name2%></td>
									<td><%=Name3%></td>
									<td><%=Name4%></td>
									<td><%=Name5%></td>
									<td><%=Name6%></td>
								</tr>

								<%
									}
								%>

							</tbody>
						</table>
					</div>
				</div>

			</div>


		</form>

		<div style="width: 100%; height: 200px;"></div>
		<%@include file="Footer.jsp"%>

	</div>

</body>
</html>