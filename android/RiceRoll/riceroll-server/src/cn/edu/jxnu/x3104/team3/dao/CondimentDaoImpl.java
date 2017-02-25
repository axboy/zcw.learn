package cn.edu.jxnu.x3104.team3.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.jxnu.x3104.team3.bean.Condiment;
import cn.edu.jxnu.x3104.team3.db.HibernateUtil;

public class CondimentDaoImpl implements CondimentDao{

	@Override
	public boolean save(Condiment condiment) {
		Session session = HibernateUtil.getsSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.save(condiment);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("存储调味品信息失败!");
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}

	@Override
	public boolean delete(Condiment condiment) {
		Session session = HibernateUtil.getsSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.delete(condiment);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("删除调味品信息失败!");
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}

	@Override
	public boolean update(Condiment condiment) {
		Session session = HibernateUtil.getsSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.update(condiment);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("修改调味品信息失败!");
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}

	@Override
	public Condiment findByName(String condiment_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> findByPage(int currentPageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Condiment> getAllCondiment() {
		List<Condiment> condiments=null;
		try {
			Session session = HibernateUtil.getsSession();
			Transaction transaction = session.beginTransaction();
			transaction.begin();
			String hql = "from Condiment order by condiment_id asc";
			condiments = session.createQuery(hql).list();
		} catch (Exception e) {
			System.err.println("获取全部调料信息出错！");
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return condiments;
	}
	

}
