package action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import net.sf.json.JSONArray;
import model.Answer;
import model.Cinema;
import model.CinemaHall;
import model.FilmSchedule;
import model.PlayList;
import model.Question;
import service.AnswerService;
import service.CinemaHallService;
import service.CinemaService;
import service.FilmScheduleService;
import service.PlayListService;
import service.QuestionService;
import z.mobile.model.MobileUser;

public class PlayChoiceAction extends BaseAction{

	private CinemaService cinemaService;
	private CinemaHallService cinemaHallService;
	private FilmScheduleService filmScheduleService;
	private PlayListService playListService;
	private QuestionService questionService;
	private AnswerService answerService;
	
	//从二维码中获得用户所在影院、影厅及游戏开始时间
	private String cinemaName;
	private String hallName;
	private String startTime;
	
	//该时间段的播放列表
	private PlayList playList;
	private List questions = new ArrayList();
	private List rightAnswers = new ArrayList();
	private List durations = new ArrayList();
	
	public void loadQuestions(){
		cinemaName = request().getParameter("cinema").toString();
		hallName = request().getParameter("hall").toString();
		startTime = request().getParameter("startTime").toString();
		
		if(isQuestionPlayList()){
			//加载选择题
			searchQuestions();

			Gson gson = new Gson();
			java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<HashMap>() {  
	        }.getType();
	        
			Map map = new HashMap<>();
			map.put("questions", questions);
			map.put("rightAnswers", rightAnswers);
			map.put("durations", durations);
				  
			String jArray = gson.toJson(map, type);

			try {
				this.response().setContentType("text/xml;charset=utf-8");
				System.out.println(jArray.toString());
				this.response().getWriter().write(jArray.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else{
			//是播放赛车游戏，不用加载游戏
		}
	}
	
	//判断该时间段是否是播放选择题
	public boolean isQuestionPlayList(){
		//由二维码获得的数据，暂时为假数据
		cinemaName = "dahua";
		hallName = "one";
		startTime = "2015-04-27 08:00:00";
		
		Cinema cinema = cinemaService.searchCinema(cinemaName);
		List<CinemaHall> halls = cinemaHallService.searchCinemaHall(cinema.getId());
		int hall_id = 0;
		
		for (CinemaHall cinemaHall : halls) {
			if(cinemaHall.getName().equals(hallName)){
				hall_id = cinemaHall.getId();
			}
		}
		
		List<FilmSchedule> filmSchedules = filmScheduleService.searchFilmSchedule(hall_id);
		int filmSchedule_id = 0;
		
		for (FilmSchedule filmSchedule : filmSchedules) {
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String startTime_tmp = sd.format(filmSchedule.getStartTime()).toString();
			
			if(startTime_tmp.equals(startTime) ){
				filmSchedule_id = filmSchedule.getId();
			}
		}
		
		playList = playListService.searchPlayList(filmSchedule_id);
		if(playList.getGameType() == 0){
			return true;
		}else{
			return false;
		}	
		
	}
	
	//[问题，选项][正确答案][时长]
	public void searchQuestions(){
		String question_ids = playList.getQuestion_ids();
		String[] ids = question_ids.split(",");		
		
		for (int i = 0; i < ids.length; i++) {
			
			int id = Integer.parseInt(ids[i]);				
			Question question = questionService.findQuestionById(id);
			List<Answer> answers = answerService.findAnswersByQid(id);
			
			List que_ans = new ArrayList<>();			
			String ques_content = question.getContent();
			que_ans.add(ques_content);
			
			for(int j = 0; j < answers.size(); j++){
				Answer ans = answers.get(j);
				String ans_content = ans.getContent();
				que_ans.add(ans_content);
				
				if(ans.getIsRight() == 1){
					int right_ans = j+1;
					rightAnswers.add(right_ans);
				}
			}			
			questions.add(que_ans);

			int duration = question.getDuration();
			durations.add(duration);
		}

	}

	public CinemaService getCinemaService() {
		return cinemaService;
	}

	public void setCinemaService(CinemaService cinemaService) {
		this.cinemaService = cinemaService;
	}

	public CinemaHallService getCinemaHallService() {
		return cinemaHallService;
	}

	public void setCinemaHallService(CinemaHallService cinemaHallService) {
		this.cinemaHallService = cinemaHallService;
	}

	public FilmScheduleService getFilmScheduleService() {
		return filmScheduleService;
	}

	public void setFilmScheduleService(FilmScheduleService filmScheduleService) {
		this.filmScheduleService = filmScheduleService;
	}

	public PlayListService getPlayListService() {
		return playListService;
	}

	public void setPlayListService(PlayListService playListService) {
		this.playListService = playListService;
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

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	public String getHallName() {
		return hallName;
	}

	public void setHallName(String hallName) {
		this.hallName = hallName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public PlayList getPlayList() {
		return playList;
	}

	public void setPlayList(PlayList playList) {
		this.playList = playList;
	}

	public List getQuestions() {
		return questions;
	}

	public void setQuestions(List questions) {
		this.questions = questions;
	}

	public List<Integer> getRightAnswers() {
		return rightAnswers;
	}

	public void setRightAnswers(List<Integer> rightAnswers) {
		this.rightAnswers = rightAnswers;
	}

	public List<Integer> getDurations() {
		return durations;
	}

	public void setDurations(List<Integer> durations) {
		this.durations = durations;
	}
	
}
