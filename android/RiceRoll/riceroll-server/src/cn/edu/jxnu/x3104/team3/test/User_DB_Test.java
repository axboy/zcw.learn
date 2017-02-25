package cn.edu.jxnu.x3104.team3.test;

import java.util.Date;
import java.text.SimpleDateFormat;

import org.junit.Test;

import cn.edu.jxnu.x3104.team3.bean.User;
import cn.edu.jxnu.x3104.team3.dao.UserDao;
import cn.edu.jxnu.x3104.team3.dao.UserDaoImpl;

public class User_DB_Test {

	@Test
	public void testSave() {
		UserDao userDao = new UserDaoImpl();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		try{
			User user = new User();
			user.setUser_id("201505240003");
			user.setUser_name("zz");
			user.setUser_password("001001a");
			user.setUser_registerDate(df.format(new Date()));
			user.setUser_phonenumber("18270839489");
			user.setUser_address("6#N418");
			userDao.save(user);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
