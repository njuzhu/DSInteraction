package service.impl;

import java.util.List;

import dao.AnswerDao;
import dao.QuestionDao;
import model.Question;
import service.QuestionService;

public class QuestionServiceImpl implements QuestionService{
    private QuestionDao questionDao;
    private AnswerDao answerDao;
    
	public QuestionDao getQuestionDao() {
		return questionDao;
	}

	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}
    
	public AnswerDao getAnswerDao() {
		return answerDao;
	}

	public void setAnswerDao(AnswerDao answerDao) {
		this.answerDao = answerDao;
	}

	@Override
	public List<Question> getQuestionList() {
		List<Question> questionList = questionDao.getQuestionList();
		try {
			return questionList;
		} catch (Exception e) {
			System.out.println("no questionList");
			return null;
		}
				
	}

	@Override
	public Question findQuestionById(int ques_id) {
		Question question = questionDao.findQuestionById(ques_id);
		if(question != null){
		    return question;
		}
		System.out.println("no question!!!!");
		return null;
	}

	@Override
	public List<Question> getQuestionSearchList(String keyword) {
		List<Question> questionSearchList = questionDao.getQuestionSearchList(keyword);
		try {
			return questionSearchList;
		} catch (Exception e) {
			System.out.println("no questionSearchList");
			return null;
		}
	}

	@Override
	public void deleteQuestionById(int ques_id) {
		Question question = questionDao.findQuestionById(ques_id);
		questionDao.deleteQuestion(question);
		
	}

	@Override
	public int addQuestion(Question question) {	
		return questionDao.addQuestion(question);
	}

	@Override
	public boolean updateQuestion(Question question) {
		// TODO Auto-generated method stub
		return questionDao.updateQuestion(question);
	}

}