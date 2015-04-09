package service;

import java.util.List;

import model.CinemaHall;

public interface CinemaHallService {
	public List<CinemaHall> searchCinemaHall(int cinema_id);
}
