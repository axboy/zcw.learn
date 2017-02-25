package cn.edu.jxnu.x3104.team3.serviceImpl;

import java.util.List;

import cn.edu.jxnu.x3104.team3.bean.Admin;
import cn.edu.jxnu.x3104.team3.bean.User;
import cn.edu.jxnu.x3104.team3.dao.AdminDao;
import cn.edu.jxnu.x3104.team3.dao.AdminDaoImpl;
import cn.edu.jxnu.x3104.team3.dao.UserDao;
import cn.edu.jxnu.x3104.team3.dao.UserDaoImpl;
import cn.edu.jxnu.x3104.team3.db.HibernateUtil;
import cn.edu.jxnu.x3104.team3.service.LoginService;
import cn.edu.jxnu.x3104.team3.tool.Constants;

public class LoginServiceImpl implements LoginService{

	@Override
	public int Login(String user_name, String user_password,int type) {
		try{		
			if(type==0){
				UserDao userdao = new UserDaoImpl();
				List<User> users = userdao.findByName(user_name);
				if(users.isEmpty()){
					HibernateUtil.closeSession();
					return Constants.USER_LOGIN_NAME_INEXISTENCE;
				}else if(users.get(0).getUser_password()!=null&&
						users.get(0).getUser_password().equals(user_password)){
					HibernateUtil.closeSession();
					return Constants.USER_LOGIN_SUCCESS;
				}else{
					HibernateUtil.closeSession();
					return Constants.USER_LOGIN_PASSWORD_ERROR;
				}
			}else if(type==1){
				AdminDao adminDao = new AdminDaoImpl();
				List<Admin> admin = adminDao.findByName(user_name);
				if(!admin.isEmpty()&&admin!=null){
					if(admin.get(0).getAdmin_password()!=null&&admin.get(0).getAdmin_password().equals(user_password)){
						HibernateUtil.closeSession();
						return Constants.ADMIN_LOGIN_SUCCESS;
					}else{
						HibernateUtil.closeSession();
						return Constants.ADMIN_LOGIN_PASSWORD_ERROR;
					}
				}else{
					HibernateUtil.closeSession();
					return Constants.ADMIN_NAME_INEXISTENCE;
				}
			}else{
				HibernateUtil.closeSession();
				return Constants.LOGIN_UNKNOWTYPE_ERROR;
			}	
		}catch(Exception e){
			System.err.println("用户登录异常");
			e.printStackTrace();
			HibernateUtil.closeSession();
			System.out.println("数据库查找异常");
			return Constants.LOGIN_UNKNOW_ERROR;
		}
	}



}
