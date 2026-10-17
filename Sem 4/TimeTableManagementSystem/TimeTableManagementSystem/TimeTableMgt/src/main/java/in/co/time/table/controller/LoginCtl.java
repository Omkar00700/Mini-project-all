package in.co.time.table.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.UserException;

import in.co.time.table.bean.UserBean;
import in.co.time.table.model.UserModel;

/**
 * Servlet implementation class LoginCtl
 */
@WebServlet(name="LoginCtl",urlPatterns={"LoginCtl"})
public class LoginCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static boolean validate(HttpServletRequest request){
		boolean pass=true;
		
		if("".equalsIgnoreCase(request.getParameter("login"))||request.getParameter("login")==null){
			request.setAttribute("loginId","Login Id is Required");
			pass=false;
		}
		
		if("".equalsIgnoreCase(request.getParameter("pwd"))||request.getParameter("pwd")==null){
			request.setAttribute("password","Password is Required");
			pass=false;
		}
		
		
		return pass;
		
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession(true);
		String op=request.getParameter("operation");
		
		if("Logout".equalsIgnoreCase(op)){
			session = request.getSession(false);
			session.invalidate();
			request.setAttribute("log","User Succsessfully Logout");
			RequestDispatcher rd=request.getRequestDispatcher("jsp/LoginView.jsp");
			rd.forward(request, response);
			
		}
		
		RequestDispatcher rd=request.getRequestDispatcher("jsp/LoginView.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean b=validate(request);
		if(b==false){
			String op=request.getParameter("operation");
			if("SignUp".equalsIgnoreCase(op)) {
				response.sendRedirect("UserRegistrationCtl");
			}else{
			doGet(request, response);
			}
		}else{
		HttpSession session=request.getSession(true);
	
		String login=request.getParameter("login");
		String pwd=request.getParameter("pwd");
		String op=request.getParameter("operation");
		
		UserModel model=new UserModel();
		UserBean bean=null;
		try {
			if("SignIn".equalsIgnoreCase(op)) {
			 bean= model.authentication(login, pwd);
			System.out.println(bean);
			if(bean!=null){
			session.setAttribute("user",bean);
			session.setMaxInactiveInterval(10 * 6000);
			
			response.sendRedirect("WelcomeCtl");
			}else{
				request.setAttribute("email",login);
				request.setAttribute("pass",pwd);
			request.setAttribute("emsg","Invalid Login Id Password");
			RequestDispatcher rd=request.getRequestDispatcher("jsp/LoginView.jsp");
			rd.forward(request, response);
			}
			
			}else if("SignUp".equalsIgnoreCase(op)) {
				response.sendRedirect("UserRegistrationCtl");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		}

}
