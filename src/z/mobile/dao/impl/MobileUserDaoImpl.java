package z.mobile.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import z.mobile.dao.MobileUserDao;
import z.mobile.model.MobileUser;

public class MobileUserDaoImpl implements MobileUserDao {
	@Override
	public void addMobileUser(MobileUser mobileUser) {
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			session.save(mobileUser);
			tx.commit();
			session.close();
			sessionFactory.close();
			//this.getHibernateTemplate().save(user);
//			System.out.println("z.mobile.dao.impl.UserDaoImpl---addUser---ok");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateMobileUser(MobileUser mobileUser) {
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			session.update(mobileUser);
			tx.commit();
			session.close();
			sessionFactory.close();
			//this.getHibernateTemplate().update(user);
//			System.out.println("z.mobile.dao.impl.UserDaoImpl---updateUser---ok");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public MobileUser getMobileUser(String column,String value) {
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();

			String hql = "from z.mobile.model.MobileUser as user where user."
					+ column + "='" + value + "'";
			Query query = session.createQuery(hql);
			@SuppressWarnings("rawtypes")
			List list = query.list();

			if ((list.size()) == 0)
				return null;
			else
				return (MobileUser) list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean checkAccountByEmail(String email, String password) {
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();

			String hql = "from z.mobile.model.MobileUser as user where user.email='"
					+ email + "' and user.password='" + password + "'";
			Query query = session.createQuery(hql);
			@SuppressWarnings("rawtypes")
			List list = query.list();

			if ((list.size()) == 0)
				return false;
			else
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
