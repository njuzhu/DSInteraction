package action;

import java.util.List;

import model.Question;
import service.QuestionService;

public class QuestionSearchAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Question question;
	private QuestionService questionService;
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public QuestionService getQuestionService() {
		return questionService;
	}
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	
	public String execute(){
		
		List<Question> questionSearchList = questionService.getQuestionSearchList(question.getKeyword());
		
		if(questionSearchList != null){
			this.request().getSession().setAttribute("questionSearchList", questionSearchList);
			System.out.println("show questionSearchList success");
			return SUCCESS;
		}
		
		return "notExist";
	}
	

}
