package cn.edu.jxnu.x3104.team3.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import cn.edu.jxnu.x3104.team3.bean.FoodMaterial;
import cn.edu.jxnu.x3104.team3.db.HibernateUtil;

public class FoodMaterialDaoImpl implements FoodMaterialDao{

	@Override
	public boolean save(FoodMaterial foodMaterial) {
		Session session = HibernateUtil.getsSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.save(foodMaterial);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("存储食材信息失败!");
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}

	@Override
	public boolean delete(FoodMaterial foodMaterial) {
		Session session = HibernateUtil.getsSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.delete(foodMaterial);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("删除食材信息失败!");
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}

	@Override
	public boolean update(FoodMaterial foodMaterial) {
		Session session = HibernateUtil.getsSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.update(foodMaterial);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("修改食材信息失败!");
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}

	@Override
	public FoodMaterial findByName(String food_material_name) {
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
	public List<FoodMaterial> getAllFoodMaterial() {
		List<FoodMaterial> foodMaterials=null;
		try {
			Session session = HibernateUtil.getsSession();
			Transaction transaction = session.beginTransaction();
			transaction.begin();
			String hql = "from FoodMaterial order by food_material_id asc";
			foodMaterials = session.createQuery(hql).list();
		} catch (Exception e) {
			System.err.println("获取全部食材信息出错！");
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return foodMaterials;
	}

}
