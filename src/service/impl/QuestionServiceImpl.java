package service.impl;

import service.QuestionService;
import dao.QuestionDao;
import model.Question;

public class QuestionServiceImpl implements QuestionService{
	private QuestionDao questionDao;

	@Override
	public Question searchQuestion(int question_id) {
		Question question = questionDao.findQuestion(question_id);
		return question;
	}

	public QuestionDao getQuestionDao() {
		return questionDao;
	}

	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}

}
