package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.CinemaHall;
import dao.CinemaHallDao;

public class CinemaHallDaoImpl implements CinemaHallDao{

	@Override
	public List<CinemaHall> find(int cinema_id) {
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();

			String hql = "from model.CinemaHall as cinemaHall where cinemaHall.cinema_id='" + cinema_id + "'";
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

}
