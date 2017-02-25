package cn.edu.jxnu.x3104.team3.db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;


/**
 *负责Hibernate的启动，也负责完成存储和访问SessionFactory的工作。
 * 
 */
 
public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	
	//创建线程局部变量threadLocal，用来保存成年Hibernate的Session
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

	//使用静态代码块初始化Hibernate
	static{
		try{
			Configuration cfg = new Configuration().configure();
			//读取配置文件hibernate.cfg.xml
			sessionFactory = cfg.buildSessionFactory();
			/*关闭json对象的循环引用*/
			JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.DisableCircularReferenceDetect.getMask();
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	/**
	 * 获得 SessionFactory
	 * 
	 * @return SessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * 获得Session
	 * 
	 * @return Session
	 * @throws HibernateException
	 */
	public static Session getsSession() throws HibernateException {
		Session session = (Session) threadLocal.get();
		if (session == null || !session.isOpen()) {
			if (sessionFactory == null) {
				rebuildSessionFactory();
			}
			session = (sessionFactory != null) ? sessionFactory.openSession()
					: null;
			threadLocal.set(session);
		}
		return session;
	}

	/**
	 * 关闭Session
	 * 
	 * @throws HibernateException
	 */
	public static void closeSession() throws HibernateException {
		Session session = (Session) threadLocal.get();
		threadLocal.set(null);
		if (session != null) {
			session.close();
		}
	}

	/**
	 * 重建SessionFactory
	 */
	public static void rebuildSessionFactory() {
		try {
			Configuration configuration = new Configuration().configure();
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			System.err.println("创建SessFactory出现错误！");
			e.printStackTrace();
		}

	}

	/**
	 * 强制关闭SessionFactory
	 */
	public static void shutdown() {
		getSessionFactory().close();
	}


}
