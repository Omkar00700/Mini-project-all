package in.co.time.table.test;

import in.co.time.table.bean.UserBean;
import in.co.time.table.model.UserModel;

public class UserModelTest {

	public static void main(String[] args) throws Exception {
		TestAdd();
		
	}

	public static void TestAdd() throws Exception {
		UserBean bean=new UserBean();
		UserModel model=new UserModel();
		
		bean.setFirstName("Hariom");
		bean.setLameName("Mukati");
		bean.setEmailId("Hariom@gmail.com");
		bean.setPassword("123");
		bean.setPhoneNo(55855812);
		
		long pk=model.add(bean);
		System.out.println(pk);
		
	}
}
