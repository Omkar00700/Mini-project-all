package in.co.time.table.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.time.table.bean.UserBean;

public class UserModel {
	
public long nextpk() throws Exception{
		
		long pk=0;
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/timetable","root","root");
		PreparedStatement stmt=conn.prepareStatement("select max(Id) from st_user");
		ResultSet rs= stmt.executeQuery();
		
		while(rs.next()){
			pk=rs.getLong(1);
		}
		
		conn.close();
		stmt.close();
		
		return pk+1;
	}
	
	
	public long add(UserBean bean) throws Exception{
	
		long pk=0;
			
		pk=nextpk();
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/timetable","root","root");
		PreparedStatement stmt=conn.prepareStatement("insert into st_user values(?,?,?,?,?,?)");
		stmt.setLong(1,pk);
		stmt.setString(2,bean.getFirstName());
		stmt.setString(3,bean.getLameName());
		stmt.setString(4,bean.getEmailId());
		stmt.setString(5,bean.getPassword());
		stmt.setLong(6,bean.getPhoneNo());
		
		int i= stmt.executeUpdate();
		if(i>0){
			System.out.println("Data is Succsessfully Inserted");
		}
		
		return pk;
		
	}
	
	public List list() throws Exception{
			
		UserBean bean=new UserBean();
		String sql="select * from st_User ";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/timetable","root","root");
		PreparedStatement stmt=conn.prepareStatement(sql);
		ResultSet rs= stmt.executeQuery();
		ArrayList list=new ArrayList();
		
		while (rs.next()) {
			
			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLameName(rs.getString(3));
			bean.setEmailId(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setPhoneNo(rs.getLong(6));
			
			list.add(bean);
		}
		
		conn.close();
		stmt.close();

		return list;
	}
	
	
	public UserBean authentication(String login,String pwd) throws Exception{
	
		UserBean bean=null;
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/timetable","root","root");
		PreparedStatement stmt=conn.prepareStatement("select * from  st_user where email=? and password=?");
		stmt.setString(1,login);
		stmt.setString(2,pwd);
		
		ResultSet rs= stmt.executeQuery();
		
		while (rs.next()) {
			bean=new UserBean();
			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLameName(rs.getString(3));
			bean.setEmailId(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setPhoneNo(rs.getLong(6));
			
		}
		
		conn.close();
		stmt.close();
		return bean;
		
		
	}

}
