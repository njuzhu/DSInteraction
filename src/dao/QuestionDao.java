package dao;

import model.Question;
import java.util.List;

public interface QuestionDao {
	public List<Question> getQuestionList();
    public Question findQuestionById(int ques_id);
}

