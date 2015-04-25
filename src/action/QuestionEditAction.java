package action;

import java.util.List;

import model.Answer;
import model.Question;
import service.AnswerService;
import service.QuestionService;

public class QuestionEditAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private QuestionService questionService;
	private AnswerService answerService;
	public AnswerService getAnswerService() {
		return answerService;
	}
	public void setAnswerService(AnswerService answerService) {
		this.answerService = answerService;
	}
	public QuestionService getQuestionService() {
		return questionService;
	}
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	
	public String execute(){
		int ques_id = Integer.parseInt(request().getParameter("question_id"));
		this.request().getSession().setAttribute("question_id", ques_id);
		//System.out.println("question_id"+ques_id);
		Question question = questionService.findQuestionById(ques_id);
		List<Answer> answers = answerService.findAnswersByQid(ques_id);
		this.request().getSession().setAttribute("question", question);
		//System.out.println("questions"+question.getContent());
		this.request().getSession().setAttribute("answers", answers);
		//System.out.println(answers.size());
		return SUCCESS;
	}
}
