package in.co.time.table.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.time.table.model.FacultyModel;

/**
 * Servlet implementation class FacultyListCtl
 */
@WebServlet(name = "FacultyListCtl", urlPatterns = { "FacultyListCtl" })
public class FacultyListCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FacultyListCtl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		FacultyModel model = new FacultyModel();
		try {
			List list = model.list();

			if (list != null && list.size() > 0) {

				request.setAttribute("list", list);
				RequestDispatcher rd = request.getRequestDispatcher("jsp/FacultyListView.jsp");
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
