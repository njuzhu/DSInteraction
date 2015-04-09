package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Race;
import dao.RaceDao;

public class RaceDaoImpl implements RaceDao{

	@Override
	public Race findRace(int race_id) {
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();

			String hql = "from model.Race as race where race.id=" + race_id;
			Query query = session.createQuery(hql);
			List list = query.list();

			if ((list.size()) == 0)
				return null;
			else
				return (Race)list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
