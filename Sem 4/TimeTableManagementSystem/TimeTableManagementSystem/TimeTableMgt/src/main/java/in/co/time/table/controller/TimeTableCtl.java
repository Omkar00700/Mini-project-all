package in.co.time.table.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.time.table.bean.FacultyBean;
import in.co.time.table.bean.TimeTableBean;
import in.co.time.table.model.FacultyModel;
import in.co.time.table.model.SubjectModel;
import in.co.time.table.model.TimeTableModel;

/**
 * Servlet implementation class TimeTableCtl
 */
@WebServlet(name = "TimeTableCtl", urlPatterns = { "TimeTableCtl" })
public class TimeTableCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if ("-----Select-----".equalsIgnoreCase(request.getParameter("facultyId"))) {
			request.setAttribute("FacultyName", "Faculty Name is Required");
			pass = false;
		}

		if ("-----Select-----".equalsIgnoreCase(request.getParameter("subjectId"))) {
			request.setAttribute("SubjectName", "Subject Name is Required");
			pass = false;
		}

		return pass;

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SubjectModel sModel = new SubjectModel();
		FacultyModel fModel = new FacultyModel();

		try {
			List sList = sModel.list();
			List fList = fModel.list();

			request.setAttribute("facultyList", fList);
			request.setAttribute("subjectList", sList);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("jsp/TimeTableView.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean b = validate(request);
		if (b == false) {
			doGet(request, response);
		} else {

			long facultyId = Long.parseLong(request.getParameter("facultyId"));
			long subjectId = Long.parseLong(request.getParameter("subjectId"));

			TimeTableModel model = new TimeTableModel();
			TimeTableBean bean = new TimeTableBean();

			bean.setFacultyId(facultyId);
			bean.setSubjectId(subjectId);

			try {
				long i = model.add(bean);
				if (i > 0) {
					SubjectModel sModel = new SubjectModel();
					FacultyModel fModel = new FacultyModel();
					List sList = sModel.list();
					List fList = fModel.list();

					request.setAttribute("facultyList", fList);
					request.setAttribute("subjectList", sList);

					request.setAttribute("bean", bean);
					request.setAttribute("succ", "Data is Succsessfully Saved");
					RequestDispatcher rd = request.getRequestDispatcher("jsp/TimeTableView.jsp");
					rd.forward(request, response);
				} else {

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
