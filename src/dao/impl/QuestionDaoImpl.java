package dao.impl;

import java.util.List;

import model.Question;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dao.QuestionDao;

public class QuestionDaoImpl implements QuestionDao{

	@Override
	public List<Question> getQuestionList() {
		try {
			Configuration config = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();

			String hql = "from model.Question order by id";
			Query query = session.createQuery(hql);
			List list = query.list();

			if ((list.size()) == 0)
				return null;
			else{
//			    for(int i=0;i<list.size();i++){
//			    	Question question = (Question)list.get(i);
//			    	System.out.println(question.getId());
//			    }
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Question findQuestionById(int ques_id) {
		try{			
			Configuration cfg = new Configuration().configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = cfg.buildSessionFactory();
			Session session = sessionFactory.openSession();
			
			String	hql = "from model.Question as ques where ques.id='"+ques_id+"'";			
			Query query = session.createQuery(hql);
			
			List list = query.list();
			
			if(list.size() == 0){
				return null;
			}else{
				return (Question)list.get(0);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}



}
