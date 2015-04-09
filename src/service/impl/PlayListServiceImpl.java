package service.impl;

import model.PlayList;
import dao.PlayListDao;
import service.PlayListService;

public class PlayListServiceImpl implements PlayListService{
	private PlayListDao playListDao;
	
	@Override
	public PlayList searchPlayList(int filmSchedule_id) {
		PlayList playList = playListDao.find(filmSchedule_id);
		return playList;
	}

	public PlayListDao getPlayListDao() {
		return playListDao;
	}

	public void setPlayListDao(PlayListDao platListDao) {
		this.playListDao = platListDao;
	}

}
