package in.co.time.table.controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.time.table.bean.TimeTableBean;
import in.co.time.table.model.TimeTableModel;

/**
 * Servlet implementation class TimeTableListCtl
 */
@WebServlet(name = "TimeTableListCtl", urlPatterns = { "TimeTableListCtl" })
public class TimeTableListCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TimeTableModel model = new TimeTableModel();
		String facultyList = null;

		try {

			List list1 = model.findByFacultyName();
			if (list1 != null && list1.size() > 0) {
				request.setAttribute("facultyName", list1);
				RequestDispatcher rd = request.getRequestDispatcher("jsp/TimeTableList.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("err", "Record Not Found");
				RequestDispatcher rd = request.getRequestDispatcher("jsp/FacultyListView.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
