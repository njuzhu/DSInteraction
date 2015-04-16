package action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import model.Answer;
import model.Question;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import service.AnswerService;
import service.QuestionService;


public class QuestionAddAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	private QuestionService questionService;
	private AnswerService answerService;
	private Question question;
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
		
		uploadFileName = getNo(uploadFileName);
		question.setUpload(uploadFileName);
		int question_id = questionService.addQuestion(question);
		String realpath = ServletActionContext.getServletContext().getRealPath("/upload");
//		System.out.println("fileName: "+uploadFileName);
//		System.out.println("fileNameA: "+uploadAFileName);
//		System.out.println("realpath: "+realpath);
//		System.out.println("file: "+upload.toString());
//		System.out.println("uploadContentType: "+uploadContentType);
		
		//System.out.println("suffix"+suffix);
		//System.out.println("getNo"+getNo());
		
        if (upload != null) {
            File savefile = new File(new File(realpath), uploadFileName);
            if (!savefile.getParentFile().exists())
                savefile.getParentFile().mkdirs();
            FileUtils.copyFile(upload, savefile);
        }
        acquireRadio();
        if(answerA != null) {
        	answerA.setQuestion_id(question_id);
        	uploadAFileName = getNo(uploadAFileName);
        	if(uploadA != null){
        		File savefile = new File(new File(realpath), uploadAFileName);
                if (!savefile.getParentFile().exists())
                    savefile.getParentFile().mkdirs();
                FileUtils.copyFile(uploadA, savefile);
                answerA.setUpload(uploadAFileName);
        	}
        	contentHandle(answerA);
        	boolean isAnsAdded = answerService.addAnswers(answerA);
        	if(isAnsAdded) System.out.println("answerA is added successfully");
        }
        if(answerB != null) {
        	answerB.setQuestion_id(question_id);
        	uploadBFileName = getNo(uploadBFileName);
        	if(uploadB != null){
        		File savefile = new File(new File(realpath), uploadBFileName);
                if (!savefile.getParentFile().exists())
                    savefile.getParentFile().mkdirs();
                FileUtils.copyFile(uploadB, savefile);
                answerB.setUpload(uploadBFileName);
        	}
        	contentHandle(answerB);
        	boolean isAnsAdded = answerService.addAnswers(answerB);
        	if(isAnsAdded) System.out.println("answerB is added successfully");
        }
        if(answerC != null) {
        	answerC.setQuestion_id(question_id);
        	uploadCFileName = getNo(uploadCFileName);
        	if(uploadC != null){
        		File savefile = new File(new File(realpath), uploadCFileName);
                if (!savefile.getParentFile().exists())
                    savefile.getParentFile().mkdirs();
                FileUtils.copyFile(uploadC, savefile);
                answerC.setUpload(uploadCFileName);
        	}
        	contentHandle(answerC);
        	boolean isAnsAdded = answerService.addAnswers(answerC);
        	if(isAnsAdded) System.out.println("answerC is added successfully");
        }
        if(answerD != null) {
        	answerD.setQuestion_id(question_id);
        	uploadDFileName = getNo(uploadDFileName);
        	if(uploadD != null){
        		File savefile = new File(new File(realpath), uploadDFileName);
                if (!savefile.getParentFile().exists())
                    savefile.getParentFile().mkdirs();
                FileUtils.copyFile(uploadD, savefile);
                answerD.setUpload(uploadDFileName);
        	}
        	contentHandle(answerD);
        	boolean isAnsAdded = answerService.addAnswers(answerD);
        	if(isAnsAdded) System.out.println("answerD is added successfully");
        }
        List<Question> questionList = questionService.getQuestionList();
	    this.request().getSession().setAttribute("questions", questionList);
		return SUCCESS;
		
    }
	//文件名处理,来解决中文名字无法得到的问题
	public static String getNo(String filename){
//		try {
//			return  URLEncoder.encode(filename, "GBK");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return filename;
//		}	
		if(filename != null){
			String suffix = filename.substring(filename.lastIndexOf("."));
			SimpleDateFormat sdf=new SimpleDateFormat("yyMMddhhmmss");  
			String nowDate=sdf.format(new java.util.Date());
			String iRandom = Math.round(Math.random()*900 )+100 +""; 
			return nowDate + iRandom + suffix;
		}
		return null;
		 
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
			break;
		case "answerB":
			answerB.setIsRight(1);
			break;
		case "answerC":
			answerC.setIsRight(1);
			break;
		case "answerD":
			answerD.setIsRight(1);
			break;
		default:
			break;
		}
	}
	public File getUpload() {
		return upload;
	}


	public void setUpload(File upload) {
		this.upload = upload;
	}


	public String getUploadFileName() {
		return uploadFileName;
	}


	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}


	public String getUploadContentType() {
		return uploadContentType;
	}


	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
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
    
	public Question getQuestion() {
		return question;
	}


	public void setQuestion(Question question) {
		this.question = question;
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
	public File getUploadA() {
		return uploadA;
	}
	public void setUploadA(File uploadA) {
		this.uploadA = uploadA;
	}
	public String getUploadAFileName() {
		return uploadAFileName;
	}
	public void setUploadAFileName(String uploadAFileName) {
		this.uploadAFileName = uploadAFileName;
	}
	public String getUploadAContentType() {
		return uploadAContentType;
	}
	public void setUploadAContentType(String uploadAContentType) {
		this.uploadAContentType = uploadAContentType;
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
