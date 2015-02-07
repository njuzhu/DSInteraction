package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.FilmSchedule;
import dao.FilmScheduleDao;

public class FilmScheduleDaoImpl implements FilmScheduleDao{

	@Override
	public List<FilmSchedule> find(int cinemaHall_id) {
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();

			String hql = "from model.FilmSchedule as filmSchedule where filmSchedule.cinemaHall_id=" + cinemaHall_id;
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
