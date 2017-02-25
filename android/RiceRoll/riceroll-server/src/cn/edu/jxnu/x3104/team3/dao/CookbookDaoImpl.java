package cn.edu.jxnu.x3104.team3.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import cn.edu.jxnu.x3104.team3.bean.Cookbook;
import cn.edu.jxnu.x3104.team3.db.HibernateUtil;

public class CookbookDaoImpl implements CookbookDao{

	@Override
	public boolean save(Cookbook cookbook) {
		Session session = HibernateUtil.getsSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.save(cookbook);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("存储菜谱信息失败!");
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}

	@Override
	public boolean delete(Cookbook cookbook) {
		Session session = HibernateUtil.getsSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.delete(cookbook);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("删除菜谱信息失败!");
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}

	@Override
	public boolean update(Cookbook cookbook) {
		Session session = HibernateUtil.getsSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.update(cookbook);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("修改菜谱信息失败!");
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}


	@Override
	public Cookbook findById(String cookbook_id) {
		Cookbook cookbook=null;
		Session session = HibernateUtil.getsSession();
		cookbook = (Cookbook) session.get(
				Cookbook.class, cookbook_id);
		return cookbook;
	}

	@Override
	public Map<String, Object> findByPage(int currentPageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cookbook> findByKeyword(String cookbook_keyword) {
		Session session = HibernateUtil.getsSession();
		Criteria criteria = session.createCriteria(Cookbook.class);
		criteria.add(Restrictions.like("cookbook_keyword", cookbook_keyword, MatchMode.ANYWHERE));
		@SuppressWarnings("unchecked")
		List<Cookbook> cookbooks = criteria.list();
		return cookbooks;
	}

	@Override
	public List<Cookbook> findByKeyword(List<String> cookbook_keyword) {
		Session session = HibernateUtil.getsSession();
		Criteria criteria = session.createCriteria(Cookbook.class);
		String sql = "cookbook_food_material_id like '%";
		for(int i=0;i<cookbook_keyword.size();i++){
			if(i!=cookbook_keyword.size()-1)
				sql = sql + cookbook_keyword.get(i)+"%' and cookbook_food_material_id like'%";
			else
				sql = sql +cookbook_keyword.get(i)+"%'";
		}
		criteria.add(Restrictions.sqlRestriction(sql));
		@SuppressWarnings("unchecked")
		List<Cookbook> cookbooks = criteria.list();
		return cookbooks;
	}

	@Override
	public List<Cookbook> findByName(String cookbook_name) {
		Session session = HibernateUtil.getsSession();
		Criteria criteria = session.createCriteria(Cookbook.class);
		criteria.add(Restrictions.like("cookbook_name", cookbook_name, MatchMode.ANYWHERE));
		@SuppressWarnings("unchecked")
		List<Cookbook> cookbooks = criteria.list();
		return cookbooks;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cookbook> getAllCookbook() {
		List<Cookbook> cookbooks=null;
		try {
			Session session = HibernateUtil.getsSession();
			Transaction transaction = session.beginTransaction();
			transaction.begin();
			String hql = "from Cookbook order by cookbook_id asc";
			cookbooks = session.createQuery(hql).list();
		} catch (Exception e) {
			System.err.println("获取全部菜谱信息出错！");
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return cookbooks;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cookbook> findByPage(int currentPageNum, String hql) {
		List<Cookbook> cookbooks = new ArrayList<Cookbook>();
		List<Cookbook> allCookbook = null;
		try {
			Session session = HibernateUtil.getsSession();
			Transaction transaction = session.beginTransaction();
			transaction.begin();
			allCookbook = session.createQuery(hql).list();
			if(allCookbook.size()>=currentPageNum*6){
				for(int i=(currentPageNum-1)*6;i<(currentPageNum-1)*6+5;i++){
					cookbooks.add(allCookbook.get(i));
				}
			}else if(allCookbook.size()<currentPageNum*6&&
					allCookbook.size()>(currentPageNum-1)*6){
				for(int i=(currentPageNum-1)*6;i<allCookbook.size();i++){
					cookbooks.add(allCookbook.get(i));
				}
			}else{
				cookbooks=null;
			}				
		} catch (Exception e) {
			System.err.println("分页获取菜谱出错！");
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return cookbooks;
	}

}
