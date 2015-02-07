package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Question;
import dao.QuestionDao;

public class QuestionDaoImpl implements QuestionDao{

	@Override
	public Question findQuestion(int question_id) {
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();

			String hql = "from model.Question as question where question.id=" + question_id;
			Query query = session.createQuery(hql);
			List list = query.list();

			if ((list.size()) == 0)
				return null;
			else
				return (Question)list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
