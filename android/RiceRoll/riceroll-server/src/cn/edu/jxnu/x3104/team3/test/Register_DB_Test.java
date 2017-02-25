package cn.edu.jxnu.x3104.team3.test;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import cn.edu.jxnu.x3104.team3.bean.Register;
import cn.edu.jxnu.x3104.team3.bean.User;
import cn.edu.jxnu.x3104.team3.dao.RegisterDao;
import cn.edu.jxnu.x3104.team3.dao.RegisterDaoImpl;
import cn.edu.jxnu.x3104.team3.dao.UserDao;
import cn.edu.jxnu.x3104.team3.dao.UserDaoImpl;
import cn.edu.jxnu.x3104.team3.db.HibernateUtil;


public class Register_DB_Test {

	@Test
	public void testUpdate() {
		UserDao userdao = new UserDaoImpl();
		RegisterDao registerdao = new RegisterDaoImpl();
		User user = new User();
		user.setUser_name("呵呵");
		user.setUser_password("Nie12341");
		user.setUser_phonenumber("18270838492");
		user.setUser_address("6#N410");
		try{
			List<User> already = userdao.findByName(user.getUser_name());
			System.out.println(already);
			if(already.isEmpty()){
				Register register = registerdao.findByType(0);
				SimpleDateFormat forId = new SimpleDateFormat("yyyyMMdd");//设置日期格式				
				SimpleDateFormat forDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				String currentTime = forId.format(new Date());
				if(register.getRegister_date().equals(currentTime.trim())||
						register.getRegister_date()==currentTime.trim()){					
					user.setUser_id(currentTime.trim()+(String.format("%04d", (register.getRegister_num()+1))));
					user.setUser_registerDate(forDate.format(new Date()));
			        register.setRegister_num(register.getRegister_num()+1);
			        registerdao.update(register);
				}else{
					user.setUser_id(currentTime.trim()+(String.format("%04d", (register.getRegister_num()+1))));
					user.setUser_registerDate(forDate.format(new Date()));
					register.setRegister_date(currentTime.trim());
			        register.setRegister_num(register.getRegister_num()+1);		        
			        registerdao.update(register);
				}
				if(userdao.save(user)){
					closeSession();
				}
			}
			else{
				System.out.println("fuck you!!!");
			}
		}catch(Exception e){
			System.err.println("用户注册异常！");
			e.printStackTrace();
	}
	}

	@Test
	public void testFindByType() {
		RegisterDao registerdao = new RegisterDaoImpl();
		try{
			Register register = registerdao.findByType(0);
			System.out.println(register.getRegister_type());
			System.out.println(register.getRegister_date());
			System.out.println(register.getRegister_num());
			closeSession();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 关闭数据库会话
	 */
	private void closeSession() {
		HibernateUtil.closeSession();

	}
}
