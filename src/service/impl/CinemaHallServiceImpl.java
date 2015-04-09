package service.impl;

import java.util.List;

import model.CinemaHall;
import dao.CinemaHallDao;
import service.CinemaHallService;

public class CinemaHallServiceImpl implements CinemaHallService{
	private CinemaHallDao cinemaHallDao;
	
	@Override
	public List<CinemaHall> searchCinemaHall(int cinema_id) {
		List<CinemaHall> cinemaHalls = cinemaHallDao.find(cinema_id);
		return cinemaHalls;
	}

	public CinemaHallDao getCinemaHallDao() {
		return cinemaHallDao;
	}

	public void setCinemaHallDao(CinemaHallDao cinemaHallDao) {
		this.cinemaHallDao = cinemaHallDao;
	}

}
