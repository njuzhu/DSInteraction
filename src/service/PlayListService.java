package service;

import dao.PlatListDao;

public interface PlayListService {
	public PlatListDao getPlatListDao();
	
	public void setPlayListDao(PlatListDao platListDao);
	
	public void searchPlayList(int filmSchedule_id);
}
