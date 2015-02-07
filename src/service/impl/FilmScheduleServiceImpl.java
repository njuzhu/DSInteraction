package service.impl;

import java.util.List;

import model.FilmSchedule;
import dao.FilmScheduleDao;
import service.FilmScheduleService;

public class FilmScheduleServiceImpl implements FilmScheduleService{
	private FilmScheduleDao filmScheduleDao;
	
	@Override
	public List<FilmSchedule> searchFilmSchedule(int cinemaHall_id) {
		List<FilmSchedule> filmSchedules = filmScheduleDao.find(cinemaHall_id);
		return filmSchedules;
	}

	public FilmScheduleDao getFilmScheduleDao() {
		return filmScheduleDao;
	}

	public void setFilmScheduleDao(FilmScheduleDao filmScheduleDao) {
		this.filmScheduleDao = filmScheduleDao;
	}

}
