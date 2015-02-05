package service;

import java.util.List;

import dao.FilmScheduleDao;

public interface FilmScheduleService {
	public FilmScheduleDao getFilmScheduleDao();
	
	public void setFilmScheduleDao(FilmScheduleDao filmScheduleDao);
	
	public List<FilmScheduleDao> searchFilmSchedule(int cinemaHall_id);
}
