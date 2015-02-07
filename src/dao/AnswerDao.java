package dao;

import java.util.List;

import model.Answer;

public interface AnswerDao {
    public List<Answer> findAnswers(int question_id);
}
