package dao;

import java.util.List;

import model.FilmSchedule;

public interface FilmScheduleDao {
	public List<FilmSchedule> find(int cinemaHall_id);			//根据影厅id查找电影场次
}
