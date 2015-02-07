package service;

import java.util.List;

import model.Answer;

public interface AnswerService {
    public List<Answer> findAnswersByQid(int ques_id);
}
