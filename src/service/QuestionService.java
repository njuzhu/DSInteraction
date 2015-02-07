package service;

import java.util.List;

import model.Question;

public interface QuestionService {
    public List<Question> getQuestionList();
    public Question findQuestionById(int ques_id);
}

