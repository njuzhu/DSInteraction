package service;

import java.util.List;

import dao.CinemaHallDao;

public interface CinemaHallService {
	public CinemaHallDao getCinemaHallDao();
	
	public void setCinemaHallDao(CinemaHallDao cinemaHallDao);
	
	public List<CinemaHallDao> searchCinemaHall(int cinema_id);
}
