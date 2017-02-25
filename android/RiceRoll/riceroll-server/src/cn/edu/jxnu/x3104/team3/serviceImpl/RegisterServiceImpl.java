package cn.edu.jxnu.x3104.team3.serviceImpl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.edu.jxnu.x3104.team3.bean.Admin;
import cn.edu.jxnu.x3104.team3.bean.User;
import cn.edu.jxnu.x3104.team3.bean.Register;
import cn.edu.jxnu.x3104.team3.dao.AdminDao;
import cn.edu.jxnu.x3104.team3.dao.AdminDaoImpl;
import cn.edu.jxnu.x3104.team3.dao.RegisterDao;
import cn.edu.jxnu.x3104.team3.dao.RegisterDaoImpl;
import cn.edu.jxnu.x3104.team3.dao.UserDao;
import cn.edu.jxnu.x3104.team3.dao.UserDaoImpl;
import cn.edu.jxnu.x3104.team3.db.HibernateUtil;
import cn.edu.jxnu.x3104.team3.service.RegisterService;
import cn.edu.jxnu.x3104.team3.tool.Constants;

public class RegisterServiceImpl implements RegisterService{

	@Override
	public int Register(User user) {
		UserDao userdao = new UserDaoImpl();
		RegisterDao registerdao = new RegisterDaoImpl();
		try{
			List<User> already = userdao.findByName(user.getUser_name());//检测重名
			if(already.isEmpty()){
				Register register = registerdao.findByType(0);	//调用注册信息类			
				SimpleDateFormat forId = new SimpleDateFormat("yyyyMMdd");//设置日期格式
				SimpleDateFormat forDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				String currentTime = forId.format(new Date());
				if(register.getRegister_date().equals(currentTime.trim())||     //检测日期
						register.getRegister_date()==currentTime.trim()){	
					//按格式生成并储存用户id
					user.setUser_id(currentTime.trim()+(
							String.format("%04d", (register.getRegister_num()+1))));
					//按格式生成并储存用户注册时间
					user.setUser_registerDate(forDate.format(new Date()));
					//更新每日注册用户量
			        register.setRegister_num(register.getRegister_num()+1);
			        registerdao.update(register);
				}else{
					user.setUser_id(currentTime.trim()+(
							String.format("%04d", 1)));
					user.setUser_registerDate(forDate.format(new Date()));
					register.setRegister_date(currentTime.trim());
			        register.setRegister_num(1);		        
			        registerdao.update(register);
				}
				if(userdao.save(user)){
					HibernateUtil.closeSession();
					return Constants.USER_REGISTER_SUCCESS;
				}else {
					HibernateUtil.closeSession();
					return Constants.USER_REGISTER_FAIL;
				}
			}
			else{
				HibernateUtil.closeSession();
				return Constants.USER_REGISTER_NAME_ERROR;				
			}
		}catch(Exception e){
			System.err.println("用户注册异常！");
			e.printStackTrace();
			HibernateUtil.closeSession();
			return Constants.USER_REGISTER_UNKNOW_ERROR;
		}
	}

	@Override
	public int Register(Admin admin) {
		AdminDao admindao = new AdminDaoImpl();
		RegisterDao registerdao = new RegisterDaoImpl();
		try{
			List<Admin> already = admindao.findByName(admin.getAdmin_name());//检测重名
			if(already==null||already.isEmpty()){
				Register register = registerdao.findByType(1);	//调用注册信息类			
					admin.setAdmin_id(String.format("%05d", (register.getRegister_num()+1)));;
					admin.setAdmin_addannouncement__authority(0);
					admin.setAdmin_addcondiment__authority(0);
					admin.setAdmin_addcookbook_authority(0);
					admin.setAdmin_addfoodmaterial__authority(0);
					admin.setAdmin_addrecommend__authority(0);
					admin.setAdmin_authorize_authority(0);
					//更新管理员数量
			        register.setRegister_num(register.getRegister_num()+1);			        
			        registerdao.update(register);
				if(admindao.save(admin)){
					HibernateUtil.closeSession();
					return Constants.ADMIN_REGISTER_SUCCESS;
				}else {
					HibernateUtil.closeSession();
					return Constants.ADMIN_REGISTER_FAIL;
				}
			}
			else{
				HibernateUtil.closeSession();
				return Constants.ADMIN_REGISTER_NAME_ERROR;				
			}
		}catch(Exception e){
			System.err.println("管理员注册异常！");
			e.printStackTrace();
			HibernateUtil.closeSession();
			return Constants.ADMIN_REGISTER_UNKNOW_ERROR;
		}
	}
}
