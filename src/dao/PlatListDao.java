package dao;

import model.PlayList;

public interface PlatListDao {
	public PlayList find(int filmSchedule_id);			//根据电影院场次id查找播放列表
}
