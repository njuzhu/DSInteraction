package service.impl;

import java.util.List;

import model.Cinema;
import dao.CinemaDao;
import service.CinemaService;

public class CinemaServiceImpl implements CinemaService{
	private CinemaDao cinemaDao;
	
	@Override
	public List<Cinema> searchAllCinemas() {
		List<Cinema> cinemas = cinemaDao.findAll();
		return cinemas;
	}
	
	@Override
	public Cinema searchCinema(String cinemaName) {
		Cinema cinema = cinemaDao.find("name", cinemaName);
		return cinema;
	}
	
	public CinemaDao getCinemaDao() {
		return cinemaDao;
	}

	public void setCinemaDao(CinemaDao cinemaDao) {
		this.cinemaDao = cinemaDao;
	}

}
