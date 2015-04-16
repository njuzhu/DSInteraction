package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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

	@Override
	public List<Race> getRaceList() {
		// TODO Auto-generated method stub
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();

			String hql = "from model.Race order by id";
			Query query = session.createQuery(hql);
			List list = query.list();

			if ((list.size()) == 0)
				return null;
			else{
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Race> getRaceSearchList(String name) {
		// TODO Auto-generated method stub
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();

			String hql = "from model.Race as race where race.name like'%"+name+"%'";
			Query query = session.createQuery(hql);
			List list = query.list();

			if ((list.size()) == 0)
				return null;
			else{
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addRace(Race race) {
		// TODO Auto-generated method stub
		try {
			Configuration cfg = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = cfg.buildSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(race);
			session.flush();
			transaction.commit();
			
			int id = race.getId();	
			System.out.println("存入的question的主键为："+session.save(race));
			
			session.close();
			sessionFactory.close();
				
			System.out.println("question saved");
			return id;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void deleteRace(Race race) {
		// TODO Auto-generated method stub
		try {
			Configuration cfg = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = cfg.buildSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(race);
			transaction.commit();
			session.close();
			sessionFactory.close();
			System.out.println("race deleted");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean updateRace(Race race) {
		// TODO Auto-generated method stub
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			session.update(race);
			tx.commit();
			session.close();
			sessionFactory.close();
			
			System.out.println("race updated successfully");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
