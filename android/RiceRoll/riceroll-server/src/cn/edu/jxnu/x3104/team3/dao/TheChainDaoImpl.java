package cn.edu.jxnu.x3104.team3.dao;

import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.jxnu.x3104.team3.bean.TheChain;
import cn.edu.jxnu.x3104.team3.db.HibernateUtil;

public class TheChainDaoImpl implements TheChainDao{


	@Override
	public boolean save(TheChain theChain) {
		Session session = HibernateUtil.getsSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.save(theChain);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("存储外链信息失败!");
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}

	@Override
	public boolean delete(TheChain theChain) {
		Session session = HibernateUtil.getsSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.delete(theChain);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("删除外链信息失败!");
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}

	@Override
	public boolean update(TheChain theChain) {
		Session session = HibernateUtil.getsSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.update(theChain);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("修改外链信息失败!");
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}

	@Override
	public TheChain findByIf(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> findByIf(int currentPageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
