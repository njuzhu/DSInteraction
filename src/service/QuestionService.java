package service;

import java.util.List;

import model.Question;

public interface QuestionService {
    public List<Question> getQuestionList();
    public Question findQuestionById(int ques_id);
    public List<Question> getQuestionSearchList(String keyword);
	public void deleteQuestionById(int ques_id);
	public int addQuestion(Question question);
	public boolean updateQuestion(Question question);
}
