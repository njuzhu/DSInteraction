package dao;


import java.util.List;

import model.Question;

public interface QuestionDao {
    public List<Question> getQuestionList();
    public Question findQuestionById(int ques_id);
    public List<Question> getQuestionSearchList(String keyword);
	public void deleteQuestion(Question question);
	public int addQuestion(Question question);
}

