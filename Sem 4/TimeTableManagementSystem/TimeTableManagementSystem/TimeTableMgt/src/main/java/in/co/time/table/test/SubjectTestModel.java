package in.co.time.table.test;

import java.util.Iterator;
import java.util.List;

import in.co.time.table.bean.SubjectBean;
import in.co.time.table.model.SubjectModel;

public class SubjectTestModel {

	
	public static void main(String[] args) throws Exception {
		//testAdd();
		testList();
	}

	public static void testList() throws Exception {
		SubjectModel model=new SubjectModel();
		
		
		List list= model.list();
		
		Iterator it=list.iterator();
		
		while (it.hasNext()) {
			SubjectBean bean = (SubjectBean) it.next();
			
			System.out.println("Id========"+bean.getId());
			System.out.println("Name======"+bean.getSubjectName());
			System.out.println("Code======"+bean.getSubjectCode());
		}
		
	}

	public static void testAdd() throws Exception {
		SubjectModel model=new SubjectModel();
		SubjectBean bean=new SubjectBean();
		
		bean.setSubjectName("Maths");
		bean.setSubjectCode(1005);
		
		model.add(bean);
		
	}
}
