package in.co.time.table.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.time.table.bean.FacultyBean;



public class FacultyModel {

	
public long nextpk() throws Exception{
		
		long pk=0;
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/timetable","root","root");
		PreparedStatement stmt=conn.prepareStatement("select max(Id) from st_faculty");
		ResultSet rs= stmt.executeQuery();
		
		while(rs.next()){
			pk=rs.getLong(1);
		}
		
		conn.close();
		stmt.close();
		
		return pk+1;
	}
	
	
	public long add(FacultyBean bean) throws Exception{
	
		long pk=0;
			
		pk=nextpk();
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/timetable","root","root");
		PreparedStatement stmt=conn.prepareStatement("insert into st_faculty values(?,?,?)");
		stmt.setLong(1,pk);
		stmt.setString(2,bean.getFacultyName());
		stmt.setString(3,bean.getDepartment());
		
		int i= stmt.executeUpdate();
		if(i>0){
			System.out.println("Data is Succsessfully Inserted");
		}
		
		return pk;
		
	}
	
	public List list() throws Exception{
			
		FacultyBean bean=null;
		String sql="select * from st_Faculty limit 0,10";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/timetable","root","root");
		PreparedStatement stmt=conn.prepareStatement(sql);
		ResultSet rs= stmt.executeQuery();
		ArrayList list=new ArrayList();
		
		while (rs.next()) {
			bean=new FacultyBean();
			bean.setId(rs.getLong(1));
			bean.setFacultyName(rs.getString(2));
			bean.setDepartment(rs.getString(3));
			
			list.add(bean);
		}
		
		conn.close();
		stmt.close();

		return list;
	}
	
	public FacultyBean findByPk(long pk) throws Exception{
		
		FacultyBean bean=null;
		String sql="select * from st_Faculty where id=?";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/timetable","root","root");
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setLong(1,pk);
		ResultSet rs= stmt.executeQuery();

		
		while (rs.next()) {
			bean=new FacultyBean();
			bean.setId(rs.getLong(1));
			bean.setFacultyName(rs.getString(2));
			bean.setDepartment(rs.getString(3));
		}
		
		conn.close();
		stmt.close();

		return bean;
	}
}
