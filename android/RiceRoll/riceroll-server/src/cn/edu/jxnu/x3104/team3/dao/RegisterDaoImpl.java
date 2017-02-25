package cn.edu.jxnu.x3104.team3.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.jxnu.x3104.team3.bean.Register;
import cn.edu.jxnu.x3104.team3.db.HibernateUtil;

public class RegisterDaoImpl implements RegisterDao{

	@Override
	public boolean update(Register register) {
		Session session = HibernateUtil.getsSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.update(register);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("修改注册信息失败!");
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}

	@Override
	public Register findByType(int register_type) {
		Session session = HibernateUtil.getsSession();
		Register register = (Register) session.get(
				Register.class, register_type);
		return register;
	}


}
