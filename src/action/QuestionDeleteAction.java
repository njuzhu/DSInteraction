package action;

import java.util.List;

import model.Question;
import service.QuestionService;

public class QuestionDeleteAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private QuestionService questionService;
	public QuestionService getQuestionService() {
		return questionService;
	}
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	
	public String execute(){
		int ques_id = Integer.parseInt(request().getParameter("question_id"));
		questionService.deleteQuestionById(ques_id);
		List<Question> questionList = questionService.getQuestionList();
		if(questionList != null){
			this.request().getSession().setAttribute("questions", questionList);
			return SUCCESS;
		}
		return ERROR;
	}
}
