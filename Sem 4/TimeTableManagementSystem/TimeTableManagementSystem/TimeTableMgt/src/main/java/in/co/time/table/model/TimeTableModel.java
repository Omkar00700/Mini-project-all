package in.co.time.table.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import in.co.time.table.bean.FacultyBean;
import in.co.time.table.bean.SubjectBean;
import in.co.time.table.bean.TimeTableBean;

public class TimeTableModel {

	public long nextpk() throws Exception {

		long pk = 0;
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/timetable", "root", "root");
		PreparedStatement stmt = conn.prepareStatement("select max(Id) from st_timetable");
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			pk = rs.getLong(1);
		}

		conn.close();
		stmt.close();

		return pk + 1;
	}

	public long add(TimeTableBean bean) throws Exception {

		long pk = 0;

		FacultyModel fModel = new FacultyModel();
		FacultyBean fbean = fModel.findByPk(bean.getFacultyId());
		bean.setFacultyName(fbean.getFacultyName());

		SubjectModel smodel = new SubjectModel();
		SubjectBean sBean = smodel.findByPk(bean.getSubjectId());
		bean.setSubjectName(sBean.getSubjectName());

		pk = nextpk();

		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/timetable", "root", "root");
		PreparedStatement stmt = conn.prepareStatement("insert into st_timetable values(?,?,?)");
		stmt.setLong(1, pk);
		stmt.setString(2, bean.getFacultyName());
		stmt.setString(3, bean.getSubjectName());

		int i = stmt.executeUpdate();
		if (i > 0) {
			System.out.println("Data is Succsessfully Inserted");
		}

		return pk;

	}

	public List findByFacultyName() throws Exception {

		TimeTableBean bean = null;
		String sql = "select Faculty_Name,Subject_Name from st_timetable";

		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/timetable", "root", "root");
		PreparedStatement stmt = conn.prepareStatement(sql.toString());
		ResultSet rs = stmt.executeQuery();
		ArrayList list = new ArrayList();

		while (rs.next()) {
			list.add(rs.getString(1) + "(" + rs.getString(2).toUpperCase() + ")");
		}

		conn.close();
		stmt.close();

		return list;
	}

	public static void main(String[] args) throws Exception {
		TimeTableModel model = new TimeTableModel();
		List list = model.findByFacultyName();
		for (int i = 0; i < list.size(); i++) {
			String random = (String) list.get(new Random().nextInt(list.size()));

			System.out.println(random);
		}

	}
}
