package cn.edu.jxnu.x3104.team3.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import cn.edu.jxnu.x3104.team3.bean.User;
import cn.edu.jxnu.x3104.team3.db.HibernateUtil;

public class UserDaoImpl implements UserDao{

	@Override
	public boolean save(User user) {
		Session session = HibernateUtil.getsSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.save(user);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("存储用户信息失败!");
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}

	@Override
	public boolean delete(User user) {
		Session session = HibernateUtil.getsSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.delete(user);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("删除用户信息失败!");
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}

	@Override
	public boolean update(User user) {
		Session session = HibernateUtil.getsSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.update(user);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("修改用户信息失败!");
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}

	@Override
	public List<User> findByName(String user_name) {
		Session session = HibernateUtil.getsSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.like("user_name", user_name, MatchMode.EXACT));
		@SuppressWarnings("unchecked")
		List<User> users = criteria.list();
		return users;
	}

	@Override
	public Map<String, Object> findByName(int currentPageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getUserNun() {
		List<User> users=null;
		try {
			Session session = HibernateUtil.getsSession();
			Transaction transaction = session.beginTransaction();
			transaction.begin();
			String hql = "from User order by user_id asc";
			users = session.createQuery(hql).list();
		} catch (Exception e) {
			System.err.println("获取全部用户信息出错！");
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return users.size();
	}

}
