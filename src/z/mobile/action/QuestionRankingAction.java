package z.mobile.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import z.mobile.model.TempInfo;

public class QuestionRankingAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	public static List<TempInfo> tempInfos = new ArrayList<TempInfo>();
	
	public synchronized void questionRank() {

		int uid = Integer.parseInt(request().getParameter("uid").toString());
		int score = Integer.parseInt(request().getParameter("score").toString());
		TempInfo tempInfo = new TempInfo(uid,score);

		tempInfos.add(tempInfo);
		Collections.sort(tempInfos);
		
		for(int i = 0; i < tempInfos.size(); i++) {
			TempInfo tempInfo2 = tempInfos.get(i);
			System.out.println("questionRank-tempInfo:"+tempInfo2.toString());
		}
	}
}
