package in.co.time.table.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.time.table.bean.SubjectBean;

public class SubjectModel {

	
	public long nextpk() throws Exception{
		
		long pk=0;
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/timetable","root","root");
		PreparedStatement stmt=conn.prepareStatement("select max(Id) from st_subject");
		ResultSet rs= stmt.executeQuery();
		
		while(rs.next()){
			pk=rs.getLong(1);
		}
		
		conn.close();
		stmt.close();
		
		return pk+1;
	}
	
	
	public long add(SubjectBean bean) throws Exception{
	
		long pk=0;
			
		pk=nextpk();
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/timetable","root","root");
		PreparedStatement stmt=conn.prepareStatement("insert into st_subject values(?,?,?)");
		stmt.setLong(1,pk);
		stmt.setString(2,bean.getSubjectName());
		stmt.setLong(3,bean.getSubjectCode());
		
		int i= stmt.executeUpdate();
		if(i>0){
			System.out.println("Data is Succsessfully Inserted");
		}
		
		return pk;
		
	}
	
	public List list() throws Exception{
			
		SubjectBean bean=null;
		String sql="select * from st_Subject limit 0,10";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/timetable","root","root");
		PreparedStatement stmt=conn.prepareStatement(sql.toString());
		ResultSet rs= stmt.executeQuery();
		ArrayList list=new ArrayList();
		
		while (rs.next()) {
			bean=new SubjectBean();
			bean.setId(rs.getLong(1));
			bean.setSubjectName(rs.getString(2));
			bean.setSubjectCode(rs.getLong(3));
			
			list.add(bean);
		}
		
		conn.close();
		stmt.close();

		return list;
	}
	
	public SubjectBean findByPk(long id) throws Exception{
		
		SubjectBean bean=null;
		String sql="select * from st_Subject where id=?";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/timetable","root","root");
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setLong(1, id);
		ResultSet rs= stmt.executeQuery();
	
		
		while (rs.next()) {
			bean=new SubjectBean();
			bean.setId(rs.getLong(1));
			bean.setSubjectName(rs.getString(2));
			bean.setSubjectCode(rs.getLong(3));
			
			
		}
		
		conn.close();
		stmt.close();

		return bean;
	}
	
}
