package action;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import model.Answer;
import model.Question;
import service.AnswerService;
import service.QuestionService;

//编辑后保存新的问题
public class QuestionSaveAction extends BaseAction{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private QuestionService questionService;
	private Question question;
	private AnswerService answerService;
	private Answer answerA;
	private Answer answerB;
	private Answer answerC;
	private Answer answerD;
	
	private File upload;
	private File uploadA;
	private File uploadB;
	private File uploadC;
	private File uploadD;
	//提交过来的file的名字
    private String uploadFileName;
    private String uploadAFileName;
    private String uploadBFileName;
   	private String uploadCFileName;
    private String uploadDFileName;
    //提交过来的file的MIME类型
    private String uploadContentType;
    private String uploadAContentType;
    private String uploadBContentType;
    private String uploadCContentType;
    private String uploadDContentType;
	
	@Override
    public String execute() throws Exception
    {
		int question_id = (int)this.request().getSession().getAttribute("question_id");
		String realpath = ServletActionContext.getServletContext().getRealPath("/upload");
		//更新问题
		if (upload != null) {
			uploadFileName = getNo(uploadFileName);
            File savefile = new File(new File(realpath), uploadFileName);
            if (!savefile.getParentFile().exists())
                savefile.getParentFile().mkdirs();
            FileUtils.copyFile(upload, savefile);
            question.setUpload(uploadFileName);
        } else {
			uploadFileName = (String) request().getAttribute("uploadFileName");
			question.setUpload(uploadFileName);
		}
		acquireRadio();
		boolean isUpdated = questionService.updateQuestion(question);
		String fileAName = (String) request().getAttribute("uploadAFileName");
		String fileBName = (String) request().getAttribute("uploadBFileName");
		String fileCName = (String) request().getAttribute("uploadCFileName");
		String fileDName = (String) request().getAttribute("uploadDFileName");
		answerImgHandle(uploadA, uploadAFileName, realpath, answerA, fileAName);
		answerImgHandle(uploadB, uploadBFileName, realpath, answerB, fileBName);
		answerImgHandle(uploadC, uploadCFileName, realpath, answerC, fileCName);
		answerImgHandle(uploadD, uploadDFileName, realpath, answerD, fileDName);
		if(isUpdated && isUpdated(answerA, question_id) && isUpdated(answerB, question_id) && 
				isUpdated(answerC, question_id) && isUpdated(answerD, question_id)) {
		    return SUCCESS;
		}
		return null;
		
    }
	//处理答案图片
	public void answerImgHandle(File file, String uploadName, String realPath, Answer answer,String fileName) throws IOException {
		if(file != null) {
			uploadName = getNo(uploadName);
			File savefile = new File(new File(realPath), uploadName);
			if(!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();
			FileUtils.copyFile(file, savefile);
			answer.setUpload(uploadName);
			//System.out.println(uploadName);
		} else {
			//System.out.println(fileName);
			answer.setUpload(fileName);
		}
	}
	//更新答案
	public boolean isUpdated(Answer answer, int question_id) {
		answer.setQuestion_id(question_id);
		contentHandle(answer);
		return answerService.updateAnswer(answer);
	}
	//答案内容处理，多个content,去掉“，”    
    public void contentHandle(Answer answer) {
    	String content = answer.getContent();
    	int i = content.indexOf(",");
    	content = content.substring(0,i) + content.substring(i+1);
    	answer.setContent(content);
    }
    //获取radio选中值，将对应答案的isRight字段设为1
    public void acquireRadio() {
    	String rightAnswer = this.request().getParameter("radio");
    	//System.out.println(rightAnswer);
    	switch (rightAnswer) {
		case "answerA":
			answerA.setIsRight(1);
			answerB.setIsRight(0);
			answerC.setIsRight(0);
			answerD.setIsRight(0);
			break;
		case "answerB":
			answerB.setIsRight(1);
			answerA.setIsRight(0);
			answerC.setIsRight(0);
			answerD.setIsRight(0);
			break;
		case "answerC":
			answerC.setIsRight(1);
			answerA.setIsRight(0);
			answerB.setIsRight(0);
			answerD.setIsRight(0);
			break;
		case "answerD":
			answerD.setIsRight(1);
			answerA.setIsRight(0);
			answerB.setIsRight(0);
			answerC.setIsRight(0);
			break;
		default:
			break;
		}
	}
  //文件名处理,来解决中文名字无法得到的问题
  	public static String getNo(String filename){	
  		if(filename != null){
  			String suffix = filename.substring(filename.lastIndexOf("."));
  			SimpleDateFormat sdf=new SimpleDateFormat("yyMMddhhmmss");  
  			String nowDate=sdf.format(new java.util.Date());
  			String iRandom = Math.round(Math.random()*900 )+100 +""; 
  			return nowDate + iRandom + suffix;
  		}
  		return null;
  		 
  	}
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
	public AnswerService getAnswerService() {
		return answerService;
	}
	public void setAnswerService(AnswerService answerService) {
		this.answerService = answerService;
	}
	public Answer getAnswerA() {
		return answerA;
	}
	public void setAnswerA(Answer answerA) {
		this.answerA = answerA;
	}
	public Answer getAnswerB() {
		return answerB;
	}
	public void setAnswerB(Answer answerB) {
		this.answerB = answerB;
	}
	public Answer getAnswerC() {
		return answerC;
	}
	public void setAnswerC(Answer answerC) {
		this.answerC = answerC;
	}
	public Answer getAnswerD() {
		return answerD;
	}
	public void setAnswerD(Answer answerD) {
		this.answerD = answerD;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public File getUploadA() {
		return uploadA;
	}
	public void setUploadA(File uploadA) {
		this.uploadA = uploadA;
	}
	public File getUploadB() {
		return uploadB;
	}
	public void setUploadB(File uploadB) {
		this.uploadB = uploadB;
	}
	public File getUploadC() {
		return uploadC;
	}
	public void setUploadC(File uploadC) {
		this.uploadC = uploadC;
	}
	public File getUploadD() {
		return uploadD;
	}
	public void setUploadD(File uploadD) {
		this.uploadD = uploadD;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getUploadAFileName() {
		return uploadAFileName;
	}
	public void setUploadAFileName(String uploadAFileName) {
		this.uploadAFileName = uploadAFileName;
	}
	public String getUploadBFileName() {
		return uploadBFileName;
	}
	public void setUploadBFileName(String uploadBFileName) {
		this.uploadBFileName = uploadBFileName;
	}
	public String getUploadCFileName() {
		return uploadCFileName;
	}
	public void setUploadCFileName(String uploadCFileName) {
		this.uploadCFileName = uploadCFileName;
	}
	public String getUploadDFileName() {
		return uploadDFileName;
	}
	public void setUploadDFileName(String uploadDFileName) {
		this.uploadDFileName = uploadDFileName;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getUploadAContentType() {
		return uploadAContentType;
	}
	public void setUploadAContentType(String uploadAContentType) {
		this.uploadAContentType = uploadAContentType;
	}
	public String getUploadBContentType() {
		return uploadBContentType;
	}
	public void setUploadBContentType(String uploadBContentType) {
		this.uploadBContentType = uploadBContentType;
	}
	public String getUploadCContentType() {
		return uploadCContentType;
	}
	public void setUploadCContentType(String uploadCContentType) {
		this.uploadCContentType = uploadCContentType;
	}
	public String getUploadDContentType() {
		return uploadDContentType;
	}
	public void setUploadDContentType(String uploadDContentType) {
		this.uploadDContentType = uploadDContentType;
	}
}
