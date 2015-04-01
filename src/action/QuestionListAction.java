package action;

import java.util.List;
import model.Question;
import service.QuestionService;

public class QuestionListAction extends BaseAction{

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
		List<Question> questionList = questionService.getQuestionList();
		
		if(questionList != null){
			this.request().getSession().setAttribute("questions", questionList);
			System.out.println("show all questions success");
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

}
