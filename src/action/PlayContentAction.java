package action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;

import model.Answer;
import model.Cinema;
import model.CinemaHall;
import model.FilmSchedule;
import model.PlayList;
import model.Question;
import model.Race;
import model.User;
import service.AnswerService;
import service.CinemaHallService;
import service.CinemaService;
import service.FilmScheduleService;
import service.PlayListService;
import service.QuestionService;
import service.RaceService;
import service.UserService;
import z.mobile.action.QuestionRankingAction;
import z.mobile.action.RankingAction;
import z.mobile.model.TempInfo;

public class PlayContentAction extends BaseAction{
	private CinemaService cinemaService;
	private CinemaHallService cinemaHallService;
	private FilmScheduleService filmScheduleService;
	private PlayListService playListService;
	private QuestionService questionService;
	private AnswerService answerService;
	private RaceService raceService;
	private static UserService userService;
	
	private static RankingAction rankingAction;
	private static QuestionRankingAction questionRankingAction;
	private String cinemaName;
	private String hallName;
	private String period;
	
	private Timer timer = new Timer();
	public static List<TempInfo> tempInfoList;
	private int gametype = 0;
	private static List<String> imageString = new ArrayList<String>();
	//private static String imageString[] = {"","","","",""};
	//private static String imageString[] = {"icon4.jpg","icon2.jpg","icon3.jpg","icon.jpg"};
	//private static String imageString2[] = {"icon1.jpg","icon3.jpg","icon4.jpg","icon2.jpg"};
	
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
			gametype = 0;
			dataList = searchQuestions(playList);
			break;
		case 1:
			gametype = 1;
			dataList = searchRaces(playList);
			break;
		case 2:
			gametype = 2;
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
			String questionContent = question.getContent().substring(2);
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
	
	//获取排名,显示前5名
	public void rank(){
		List dataList = new ArrayList<>();		
		int rankLength = imageString.size();
		
		for(int i = 0; i < rankLength; i++){
			Map map = new HashMap<>();			
			String image = "../../DSInteraction/images/" + imageString.get(i);
			
			map.put("usr_img", image);
			
			dataList.add(map);
		}		
		
		//System.out.println(imageString[0] + imageString[1] + imageString[2] +imageString[3] +imageString[4]);	
		net.sf.json.JSONArray jArray = net.sf.json.JSONArray.fromObject(dataList); 
		
		try {
			this.response().getWriter().write(jArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//4秒后，更新数据
	public void startTimer(){
		//timer.schedule(new MyTask(),4000);
		timer.scheduleAtFixedRate(new MyTask2(), 0, 5000);
		System.out.println("start timer successfully!");
	}
	
	//获得最终排名
	public void finalRank(){
		timer.cancel();
		System.out.println("rank timer end!");
		
		List dataList = new ArrayList<>();	
		List<TempInfo> tempInfoList2 = new ArrayList<>();	

		if(gametype == 0){
			tempInfoList2 = questionRankingAction.tempInfos;
		}else if(gametype ==1){
			tempInfoList2 = tempInfoList;
		}
		
		int tempSize = tempInfoList2.size();
		int rankNum = 5;
		
		if(tempSize < rankNum){
			rankNum = tempSize;
		}
		
		if(rankNum > 0){
			for(int i = 0;i < rankNum;i++){
				Map map = new HashMap<>();
				TempInfo tmpInfo = tempInfoList2.get(i);
				
				int score = tmpInfo.getScore();
				int uid = tmpInfo.getUid();			
				User user = userService.getUserInfo(uid);
				String name = user.getName();
				String image = "../../DSInteraction/images/" + user.getImage();
				
				map.put("user_name", name);
				map.put("user_image", image);
				map.put("user_score", score);
				
				dataList.add(map);
				
			}
		}

		net.sf.json.JSONArray jArray = net.sf.json.JSONArray.fromObject(dataList); 
		
		try {
			this.response().getWriter().write(jArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
				
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

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	static class MyTask2 extends TimerTask {

		@Override
		public void run() {
//			imageString = imageString2;
			System.out.println("update rank data!");
			
			if(rankingAction.tempInfos != null){
				
				synchronized(tempInfoList = rankingAction.tempInfos){
					imageString.clear();
					
					int tempSize = tempInfoList.size();
					int rankNum = 5;
					
					if(tempSize < rankNum){
						rankNum = tempSize;
					}
					
					for(int i = 0;i < rankNum;i++){
						TempInfo tmpInfo = tempInfoList.get(i); 
						int uid = tmpInfo.getUid();
						
						User user = userService.getUserInfo(uid);
						String image = user.getImage();
						imageString.add(image);
					}
				}
			}
		
		}
		
	}
}
