package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.PlayList;
import dao.PlayListDao;

public class PlayListDaoImpl implements PlayListDao{

	@Override
	public PlayList find(int filmSchedule_id) {
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();

			String hql = "from model.PlayList as playList where playList.filmSchedule_id='" + filmSchedule_id + "'";
			Query query = session.createQuery(hql);
			List list = query.list();

			if ((list.size()) == 0)
				return null;
			else
				return (PlayList)list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
