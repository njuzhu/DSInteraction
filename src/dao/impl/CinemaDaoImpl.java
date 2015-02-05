package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Cinema;
import dao.CinemaDao;

public class CinemaDaoImpl implements CinemaDao{

	@Override
	public List<Cinema> findAll() {
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();

			String hql = "from model.Cinema";
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
	public Cinema find(String column, String value) {
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();

			String hql = "from model.Cinema as cinema where cinema."
					+ column + "='" + value + "'";
			Query query = session.createQuery(hql);
			List list = query.list();

			if ((list.size()) == 0)
				return null;
			else
				return (Cinema) list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
