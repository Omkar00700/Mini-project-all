package in.co.time.table.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.time.table.bean.FacultyBean;
import in.co.time.table.bean.SubjectBean;
import in.co.time.table.model.FacultyModel;
import in.co.time.table.model.SubjectModel;

/**
 * Servlet implementation class FacultyCtl
 */
@WebServlet(name="FacultyCtl",urlPatterns={"FacultyCtl"})
public class FacultyCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static boolean validate(HttpServletRequest request){
		boolean pass=true;
		
		if("".equalsIgnoreCase(request.getParameter("name"))||request.getParameter("name")==null){
			request.setAttribute("facultyName","Faculty Name is Required");
			pass=false;
		}
		
		if("".equalsIgnoreCase(request.getParameter("department"))||request.getParameter("department")==null){
			request.setAttribute("depart","Department is Required");
			pass=false;
		}
		
		
		return pass;
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("jsp/FacultyView.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean b=validate(request);
		if(b==false){
			doGet(request, response);
		}else{
		
		String name=request.getParameter("name");
		String department=request.getParameter("department");
		
		FacultyModel model=new FacultyModel();
		
		FacultyBean bean=new FacultyBean();
		
		bean.setFacultyName(name);
		bean.setDepartment(department);

		try {
			long i=model.add(bean);
			if(i>0) {
				request.setAttribute("bean", bean);
				request.setAttribute("succ","Data is Succsessfully Saved");
				RequestDispatcher rd=request.getRequestDispatcher("jsp/FacultyView.jsp");
				rd.forward(request, response);
			}else {
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
}
