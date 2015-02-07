package service;

import java.util.List;

import model.FilmSchedule;

public interface FilmScheduleService {
	public List<FilmSchedule> searchFilmSchedule(int cinemaHall_id);
}
