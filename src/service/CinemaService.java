package service;

import dao.CinemaDao;

public interface CinemaService {
	public CinemaDao getCinemaDao();
	
	public void setCinemaDao(CinemaDao cinemaDao);
	
	public CinemaDao searchAllCinemas();
}
