package dao;

import java.util.List;
import model.CinemaHall;

public interface CinemaHallDao {
	public List<CinemaHall> find(int cinema_id);		//根据电影院id查找该影院的所有影厅
}
