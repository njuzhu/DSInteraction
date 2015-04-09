package service;

import java.util.List;

import model.Cinema;

public interface CinemaService {
	public List<Cinema> searchAllCinemas();
	
	public Cinema searchCinema(String cinemaName);
}
