package action;

import java.util.List;

import service.AnswerService;
import service.QuestionService;
import model.Answer;
import model.Question;

public class QuestionDetailAction extends BaseAction{

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
	public AnswerService getAnswerService() {
		return answerService;
	}
	public void setAnswerService(AnswerService answerService) {
		this.answerService = answerService;
	}
	private AnswerService answerService;
	public String execute(){
		int ques_id = Integer.parseInt(request().getParameter("question_id"));
		Question question = questionService.findQuestionById(ques_id);
		List<Answer> answers = answerService.findAnswersByQid(ques_id);
		this.request().getSession().setAttribute("question", question);
		this.request().getSession().setAttribute("answers", answers);
		return SUCCESS;
		
	}
	

}
