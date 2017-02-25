package cn.edu.jxnu.x3104.team3.dao;

import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.jxnu.x3104.team3.bean.Feedback;
import cn.edu.jxnu.x3104.team3.db.HibernateUtil;

public class FeedbackDaoImpl implements FeedbackDao{

	@Override
	public boolean save(Feedback feedback) {
		Session session = HibernateUtil.getsSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.save(feedback);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("存储反馈信息失败!");
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}

	@Override
	public boolean delete(Feedback feedback) {
		Session session = HibernateUtil.getsSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.delete(feedback);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("删除反馈信息失败!");
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}

	@Override
	public boolean update(Feedback feedback) {
		Session session = HibernateUtil.getsSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.update(feedback);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("修改反馈信息失败!");
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}

	@Override
	public Feedback findByIf(String feedback_keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> findByIf(int currentPageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
