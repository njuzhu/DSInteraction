package service.impl;

import java.util.List;

import dao.AnswerDao;
import model.Answer;
import service.AnswerService;

public class AnswerServiceImpl implements AnswerService{
    private AnswerDao answerDao;
	public AnswerDao getAnswerDao() {
		return answerDao;
	}
	public void setAnswerDao(AnswerDao answerDao) {
		this.answerDao = answerDao;
	}
	@Override
	public List<Answer> findAnswersByQid(int ques_id) {
		List<Answer> answers = answerDao.findAnswers(ques_id);
		if(answers.isEmpty()){
			System.out.println("no answers");
			return null;
		}
		return answers;
	}
	
	@Override
	public boolean addAnswers(Answer answer) {
		// TODO Auto-generated method stub
		return answerDao.addAnswers(answer);
	}

}
