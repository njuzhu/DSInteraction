package action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.avro.io.JsonEncoder;
import org.json.JSONArray;

import bsh.Console;

import com.thoughtworks.xstream.converters.extended.CharsetConverter;

import model.Answer;
import model.Cinema;
import model.CinemaHall;
import model.FilmSchedule;
import model.PlayList;
import model.Question;
import model.Race;
import service.AnswerService;
import service.CinemaHallService;
import service.CinemaService;
import service.FilmScheduleService;
import service.PlayListService;
import service.QuestionService;
import service.RaceService;

public class PlayContentAction extends BaseAction{
	private CinemaService cinemaService;
	private CinemaHallService cinemaHallService;
	private FilmScheduleService filmScheduleService;
	private PlayListService playListService;
	private QuestionService questionService;
	private AnswerService answerService;
	private RaceService raceService;
	private String cinemaName;
	private String hallName;
	private String period;
	
	//查找所有电影院的名称
	public String searchAllCinemas(){
		List<Cinema> cinemas = cinemaService.searchAllCinemas();
		List<String> cinemaNames = new ArrayList<String>();	
		
		for (Cinema cinema : cinemas) {
			String cinemaName = cinema.getName();
			cinemaNames.add(cinemaName);
		}
		
		this.request().getSession().setAttribute("cinemaNames", cinemaNames);
		return SUCCESS;
		
	}
	
	//根据选择的电影院，查找该影院的所有电影厅的名称
	public void searchAllCinemaHalls(){
		Cinema cinema = cinemaService.searchCinema(cinemaName);		
		int cinema_id = cinema.getId();
		
		List<CinemaHall> cinemaHalls = cinemaHallService.searchCinemaHall(cinema_id);		
		List<String> hallNames = new ArrayList<String>();
		
		for (CinemaHall cinemaHall : cinemaHalls) {
			String hallName = cinemaHall.getName();
			hallNames.add(hallName);
		}
		
		JSONArray jArray = new JSONArray();
		for (String hallName : hallNames) {
			jArray.put(hallName);
		}
		
		try {
			this.response().getWriter().write(jArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//根据选择的影厅，查找该影厅当日的所有电影放映时间段
	public void searchAllFilmPeriods(){
		Cinema cinema = cinemaService.searchCinema(cinemaName);
		int cinema_id = cinema.getId();
		List<CinemaHall> cinemaHalls = cinemaHallService.searchCinemaHall(cinema_id);
		
		int hall_id = 0;
		
		for (CinemaHall cinemaHall : cinemaHalls) {
			if(cinemaHall.getName().equals(hallName)){
				hall_id = cinemaHall.getId();
			}
		}
		
		List<FilmSchedule> filmSchedules = filmScheduleService.searchFilmSchedule(hall_id);
		List<String> periods = new ArrayList<String>();
		
		for (FilmSchedule filmSchedule : filmSchedules) {
			Date startDate = filmSchedule.getStartTime();
			
			if(isToday(startDate)){
				String period = filmSchedule.getPeriod();
				periods.add(period);
			}
						
		}
		
		JSONArray jArray = new JSONArray();
		for (String period : periods) {
			jArray.put(period);
		}
		
		try {
			this.response().getWriter().write(jArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//查找该时间段的播放列表，返回内容为类型、关键字和时长
	public void searchPlayList(){
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
			if(filmSchedule.getPeriod().equals(period) 
					&& isToday(filmSchedule.getStartTime())
					&& isToday(filmSchedule.getEndTime())){
				filmSchedule_id = filmSchedule.getId();
			}
		}
		
		PlayList playList = playListService.searchPlayList(filmSchedule_id);
		List dataList = new ArrayList<>();
		
		//编号、类型、关键字、时长
		switch (playList.getGameType()) {
		case 0:
			dataList = searchQuestions(playList);
			break;
		case 1:
			dataList = searchRaces(playList);
			break;
		case 2:
			List questionList = searchQuestions(playList);
			List raceList = searchRaces(playList);
			
			boolean isAdded = questionList.addAll(raceList);
			
			if(isAdded){
				dataList = questionList;
			}else{
				System.out.println("problems in merging race&que list");
			}
			break;
		default:
			break;
		}
		
		net.sf.json.JSONArray jArray = net.sf.json.JSONArray.fromObject(dataList); 
		
		try {
			this.response().getWriter().write(jArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//把播放选择题时需要的数据也一同传过去:questions,rightAnswers
	public List searchQuestions(PlayList playList){
		String question_ids = playList.getQuestion_ids();
		String[] ids = question_ids.split(",");		
		List dataList = new ArrayList<>();
		
		for (int i = 0; i < ids.length; i++) {
			int id = Integer.parseInt(ids[i]);				
			Question question = questionService.findQuestionById(id);
			String questionContent = question.getContent();
			String keyword = question.getKeyword();
			int duration = question.getDuration();
			String durationStr = formatDuration(duration);
			
			Map map = new HashMap<>();
			map.put("number", i+1);
			map.put("type", "选择题");
			map.put("keyword", keyword);
			map.put("duration", duration);
			map.put("formatDuration", durationStr);
			map.put("question", questionContent);
			
			//答案
			List<Answer> answers = answerService.findAnswersByQid(id);
			ArrayList answerContent = new ArrayList<>();
			int rightAnswer = 0;
			
			for(int j = 0;j < answers.size();j++){
				Answer answer = answers.get(j);
				ArrayList<String> ans_imgList = new ArrayList<String>();
				
				ans_imgList.add(answer.getContent());
				ans_imgList.add("../../DSInteraction/images/" + answer.getUpload());
				
				answerContent.add(ans_imgList);
				
				if(answer.getIsRight() == 1){
					rightAnswer = j;
				}
				
			}
			
			map.put("answers", answerContent);
			map.put("rightAnswer", rightAnswer);
			
			dataList.add(map);
		}
		return dataList;
	}
	
	public List searchRaces(PlayList playList){
		String race_ids = playList.getRace_ids();
		String[] ids = race_ids.split(",");		
		List dataList = new ArrayList<>();
		
		for (int i = 0; i < ids.length; i++) {
			int id = Integer.parseInt(ids[i]);				
			Race race = raceService.searchRace(id);
			String keyword = race.getName();
			int duration = race.getDuration();
			String durationStr = formatDuration(duration);

			Map map = new HashMap<>();
			map.put("number", i+1);
			map.put("type", "赛车视频");
			map.put("keyword", keyword);
			map.put("formatDuration", durationStr);
			
			dataList.add(map);
		}
		return dataList;
	}
	
	public String formatDuration(int duration){
		int minute = duration / 60;
		String durationStr;
		
		if(minute > 0){						
			int second = duration - minute *60;
			
			if(second > 0){
				durationStr = minute + "分" + second + "秒";
			}else {
				durationStr = minute + "分";
			}
		}else{
			durationStr = duration + "秒"; 
		}
		
		return durationStr;
	}
	
	public boolean isToday(Date date){
		Date currentDate = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		
		String dString = sd.format(date).toString();
		String today = sd.format(currentDate).toString();
		
		if(dString.equals(today)){
			return true;
		}else{
			return false;
		}
		
	}
	
	public CinemaService getCinemaService() {
		return cinemaService;
	}

	public void setCinemaService(CinemaService cinemaService) {
		System.out.println("set cinemaService");
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

	public String getCinemaName() {
		return cinemaName;
	}

	public QuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public RaceService getRaceService() {
		return raceService;
	}

	public void setRaceService(RaceService raceService) {
		this.raceService = raceService;
	}

	public void setCinemaName(String cinemaName) {
		try {
			cinemaName = URLDecoder.decode(cinemaName, "utf-8");
			this.cinemaName = cinemaName;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public String getHallName() {
		return hallName;
	}

	public void setHallName(String hallName) {
		try {
			hallName = URLDecoder.decode(hallName, "utf-8");
			this.hallName = hallName;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}		
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		try {
			period = URLDecoder.decode(period, "utf-8");
			this.period = period;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}		
	}

	public AnswerService getAnswerService() {
		return answerService;
	}

	public void setAnswerService(AnswerService answerService) {
		this.answerService = answerService;
	}

}
