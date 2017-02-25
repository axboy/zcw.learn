package cn.edu.jxnu.x3104.team3.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.jxnu.x3104.team3.bean.Recommend;
import cn.edu.jxnu.x3104.team3.db.HibernateUtil;

public class RecommendDaoImpl implements RecommendDao{

	@Override
	public boolean save(Recommend recommend) {
		Session session = HibernateUtil.getsSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.save(recommend);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("存储每日推荐失败!");
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}

	@Override
	public boolean delete(Recommend recommend) {
		Session session = HibernateUtil.getsSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.delete(recommend);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("删除每日推荐失败!");
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}

	@Override
	public boolean update(Recommend recommend) {
		Session session = HibernateUtil.getsSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.update(recommend);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("更新每日推荐失败!");
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}

	@Override
	public Recommend findById(String recommend_id) {
		Recommend recommend=null;
		Session session = HibernateUtil.getsSession();
		recommend = (Recommend) session.get(
				Recommend.class, recommend_id);
		return recommend;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Recommend> findByPage(int currentPageNum, int pageSize) {
		List<Recommend> recommends = new ArrayList<Recommend>();
		List<Recommend> allRecommend = null;
		try {
			Session session = HibernateUtil.getsSession();
			Transaction transaction = session.beginTransaction();
			transaction.begin();
			String hql = "from Recommend order by recommend_id desc";
			allRecommend = session.createQuery(hql).list();
			if(allRecommend.size()>=currentPageNum*pageSize){
				for(int i=(currentPageNum-1)*pageSize;i<(currentPageNum-1)*pageSize+5;i++){
					recommends.add(allRecommend.get(i));
				}
			}else if(allRecommend.size()<currentPageNum*pageSize&&
					allRecommend.size()>(currentPageNum-1)*pageSize){
				for(int i=(currentPageNum-1)*pageSize;i<allRecommend.size();i++){
					recommends.add(allRecommend.get(i));
				}
			}else{
				recommends=null;
			}				
		} catch (Exception e) {
			System.err.println("获取全部推荐出错！");
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return recommends;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Recommend> getAllRecommend() {
		List<Recommend> recommends=null;
		try {
			Session session = HibernateUtil.getsSession();
			Transaction transaction = session.beginTransaction();
			transaction.begin();
			String hql = "from Recommend order by recommend_id asc";
			recommends = session.createQuery(hql).list();
		} catch (Exception e) {
			System.err.println("获取全部推荐出错！");
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return recommends;
	}


}
