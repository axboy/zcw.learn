package cn.edu.jxnu.x3104.team3.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.jxnu.x3104.team3.bean.Cookie;
import cn.edu.jxnu.x3104.team3.db.HibernateUtil;

public class CookieDaoImpl implements CookieDao{

	@Override
	public boolean save(Cookie cookie) {
		Session session = HibernateUtil.getsSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.save(cookie);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("存储cookie信息失败!");
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}

	@Override
	public boolean delete(Cookie cookie) {
		Session session = HibernateUtil.getsSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.delete(cookie);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("删除cookie信息失败!");
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}

	@Override
	public boolean update(Cookie cookie) {
		Session session = HibernateUtil.getsSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.update(cookie);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("修改cookie信息失败!");
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}

	@Override
	public Cookie findByToken(String token) {
		Session session = HibernateUtil.getsSession();
		Cookie cookie = (Cookie) session.get(Cookie.class, token);
		return cookie;
	}



}
