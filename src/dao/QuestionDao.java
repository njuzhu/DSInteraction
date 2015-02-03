package dao;


import java.util.List;

import model.Question;

public interface QuestionDao {
    public List<Question> getQuestionList();
    public Question findQuestionById(int ques_id);
}
