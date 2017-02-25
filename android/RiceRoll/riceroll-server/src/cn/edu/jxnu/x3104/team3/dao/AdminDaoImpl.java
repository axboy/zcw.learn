package cn.edu.jxnu.x3104.team3.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import cn.edu.jxnu.x3104.team3.bean.Admin;
import cn.edu.jxnu.x3104.team3.db.HibernateUtil;

public class AdminDaoImpl implements AdminDao{

	@Override
	public boolean save(Admin admin) {
		Session session = HibernateUtil.getsSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.save(admin);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("存储管理员信息失败!");
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}

	@Override
	public boolean delete(Admin admin) {
		Session session = HibernateUtil.getsSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.delete(admin);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("删除管理员信息失败!");
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}

	@Override
	public boolean update(Admin admin) {
		Session session = HibernateUtil.getsSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.update(admin);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("修改管理员信息失败!");
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Admin> findByPage(int currentPageNum, int pageSize) {
		List<Admin> admins = new ArrayList<Admin>();
		List<Admin> allAdmin = null;
		try {
			Session session = HibernateUtil.getsSession();
			Transaction transaction = session.beginTransaction();
			transaction.begin();
			String hql = "from Admin order by admin_id";
			allAdmin = session.createQuery(hql).list();
			for(int i=0;i<3;i++){
				allAdmin.remove(0);
			}			
			if(allAdmin.size()>=currentPageNum*pageSize){
				for(int i=(currentPageNum-1)*pageSize;i<(currentPageNum-1)*pageSize+pageSize;i++){
					admins.add(allAdmin.get(i));
				}
			}else if(allAdmin.size()<currentPageNum*pageSize&&
					allAdmin.size()>(currentPageNum-1)*pageSize){
				for(int i=(currentPageNum-1)*pageSize;i<allAdmin.size();i++){
					admins.add(allAdmin.get(i));
				}
			}else{
				admins=null;
			}				
		} catch (Exception e) {
			System.err.println("分页获取管理员信息出错！");
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return admins;
	}

	@Override
	public List<Admin> findByName(String admin_name) {
		Session session = HibernateUtil.getsSession();
		Criteria criteria = session.createCriteria(Admin.class);
		criteria.add(Restrictions.like("admin_name", admin_name, MatchMode.EXACT));
		@SuppressWarnings("unchecked")
		List<Admin> admins = criteria.list();
		return admins;
	}

	

}
