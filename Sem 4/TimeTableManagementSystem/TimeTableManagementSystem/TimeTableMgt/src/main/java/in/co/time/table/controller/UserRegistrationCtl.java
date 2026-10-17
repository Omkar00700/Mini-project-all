package in.co.time.table.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.time.table.bean.UserBean;
import in.co.time.table.model.UserModel;

/**
 * Servlet implementation class UserRegistrationCtl
 */
@WebServlet(name="UserRegistrationCtl",urlPatterns={"UserRegistrationCtl"})
public class UserRegistrationCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static boolean validate(HttpServletRequest request){
		boolean pass=true;
		
		if("".equalsIgnoreCase(request.getParameter("fName"))||request.getParameter("fName")==null){
			request.setAttribute("FName","First Name is Required");
			pass=false;
		}
		
		if("".equalsIgnoreCase(request.getParameter("lName"))||request.getParameter("lName")==null){
			request.setAttribute("LName","Last Name  is Required");
			pass=false;
		}
		
		if("".equalsIgnoreCase(request.getParameter("email"))||request.getParameter("email")==null){
			request.setAttribute("email","Email Id is Required");
			pass=false;
		}
		
		if("".equalsIgnoreCase(request.getParameter("pwd"))||request.getParameter("pwd")==null){
			request.setAttribute("password","Password is Required");
			pass=false;
		}
		
		if("".equalsIgnoreCase(request.getParameter("phone"))||request.getParameter("phone")==null){
			request.setAttribute("phoneNo","Phone No is Required");
			pass=false;
		}
		
		
		return pass;
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("jsp/UserRegistration.jsp");
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
			
		UserBean bean=new UserBean();
		bean.setFirstName(request.getParameter("fName"));
		bean.setLameName(request.getParameter("lName"));
		bean.setEmailId(request.getParameter("email"));
		bean.setPassword(request.getParameter("pwd"));
		long phone=Long.parseLong(request.getParameter("phone"));
		bean.setPhoneNo(phone);
		
		UserModel model=new UserModel();
		try {
		long pk=model.add(bean);
	
		if(pk>0) {
			request.setAttribute("bean",bean);
			request.setAttribute("succ","Data is Succsessfully Saved");
			RequestDispatcher rd=request.getRequestDispatcher("jsp/UserRegistration.jsp");
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
