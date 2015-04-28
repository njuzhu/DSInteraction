package z.mobile.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import z.mobile.model.TempInfo;

public class RankingAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static List<TempInfo> tempInfos;
	private static Timer timer;
	private static int counter = 0;
	
	// 假设当前只有一个电影院，一个电影厅
	public synchronized void rank() {
		// 某一用户结束游戏
		if(request().getParameter("over") != null 
				&& request().getParameter("over").toString().equals("1")) {
			System.out.println("over");
			counter--;
			System.out.println("counter:"+counter);
			// 所有用户均结束游戏
			if(counter == 0) {
				System.out.println("over-all");
				timer.cancel();
				tempInfos = null;
			}
			return;
		}
		
		// 某一用户传输当前数据
		int uid = Integer.parseInt(request().getParameter("uid").toString());
		String cinema = request().getParameter("cinema").toString();
		String hall = request().getParameter("hall").toString();
		String seat = request().getParameter("seat").toString();
		String startTime = request().getParameter("startTime").toString();
		int score = Integer.parseInt(request().getParameter("score").toString());
		TempInfo tempInfo = new TempInfo(uid, cinema, hall, seat, startTime, score);
		
		// 第一个用户第一次传输数据
		if(tempInfos == null) {
			tempInfos = new ArrayList<TempInfo>();
			// 计时器，在0秒后执行此任务,每次间隔4秒执行一次
			timer = new Timer();
			//timer.schedule(new MyTask(), 0, 4000);
			timer.scheduleAtFixedRate(new MyTask(), 0, 4000);
		}
		
		boolean exist = false;
		for(int i = 0; i<tempInfos.size(); i++) {
			TempInfo tempInfoT = tempInfos.get(i);
			if(tempInfoT.getSeat().equals(seat)) {
				tempInfoT.setScore(score);
				tempInfos.remove(i);
				tempInfos.add(i, tempInfoT);
				exist = true;
			}
		}
		if(!exist) {
			tempInfos.add(tempInfo);
			counter++;
			System.out.println("counter++:"+counter);
		}
	}
	
	// 按照score从大到小排序，每次间隔4秒执行一次
	static class MyTask extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			synchronized(tempInfos){
				Collections.sort(tempInfos);
			}
			
			for(int i = 0; i < tempInfos.size(); i++) {
				TempInfo tempInfo = tempInfos.get(i);
				System.out.println("MyTask-i:"+i);
				System.out.println("MyTask-tempInfo:"+tempInfo.toString());
			}
		}
		
	}
}
