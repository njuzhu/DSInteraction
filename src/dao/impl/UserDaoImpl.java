package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration; 

import dao.UserDao;
import model.User;

public class UserDaoImpl implements UserDao{
	
	private UserDaoImpl(){
		
	}

	public void save(User user){		
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			session.save(user);
			tx.commit();
			session.close();
			sessionFactory.close();
			//this.getHibernateTemplate().save(user);
			System.out.println("ok");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public User find(String column,String value){
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();

			String hql = "from model.User as us where us."
					+ column + "='" + value + "'";
			Query query = session.createQuery(hql);
			List list = query.list();

			if ((list.size()) == 0)
				return null;
			else
				return (User) list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean updateByUserid(User user){
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			session.update(user);
			tx.commit();
			session.close();
			sessionFactory.close();
			//this.getHibernateTemplate().update(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public List<User> find(String keyword) {
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();

			String hql = "from model.User as usr where usr.name like '%" + keyword + "%' or usr.email like '%" + keyword + "%'";
			Query query = session.createQuery(hql);
			List list = query.list();

			if ((list.size()) == 0)
				return null;
			else
				return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateUserPoint(int usr_id, int new_point) {
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session=sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			
			String hql = "update user u set u.point='" + new_point + "' where u.id='" + usr_id + "'";
			SQLQuery query = session.createSQLQuery(hql);
			query.executeUpdate();
			
			tx.commit();
			session.close();
			sessionFactory.close();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public User getUser(int uid) {
		try{			
			Configuration cfg = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = cfg.buildSessionFactory();
			Session session = sessionFactory.openSession();
			
			String	hql = "from model.User as usr where usr.id='"+uid+"'";			
			Query query = session.createQuery(hql);
			
			List list = query.list();
			
			if(list.size() == 0){
				return null;
			}else{
				return (User)list.get(0);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
