package cn.edu.jxnu.x3104.team3.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.jxnu.x3104.team3.bean.Count;
import cn.edu.jxnu.x3104.team3.db.HibernateUtil;

public class CountDaoImpl implements CountDao{

	@Override
	public boolean save(Count count) {
		Session session = HibernateUtil.getsSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.save(count);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("存储统计信息失败!");
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}

	@Override
	public Count findByType(int count_type) {
		Session session = HibernateUtil.getsSession();
		Count count = (Count) session.get(
				Count.class, count_type);
		return count;
	}

	@Override
	public boolean update(Count count) {
		Session session = HibernateUtil.getsSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.update(count);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("修改统计信息失败!");
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}



}
