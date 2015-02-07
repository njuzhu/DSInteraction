package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Answer;
import dao.AnswerDao;

public class AnswerDaoImpl implements AnswerDao{

	@Override
	public List<Answer> findAnswers(int question_id) {
		try{			
			Configuration cfg = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = cfg.buildSessionFactory();
			Session session = sessionFactory.openSession();
			
			String	hql = "from model.Answer ans where ans.question_id='"+question_id+"'";			
			Query query = session.createQuery(hql);
			
			List list = query.list();
			
			if(list.size() == 0){
				return null;
			}else{
				return list;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
