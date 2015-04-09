package service.impl;

import dao.RaceDao;
import model.Race;
import service.RaceService;

public class RaceServiceImpl implements RaceService{
	private RaceDao raceDao;

	@Override
	public Race searchRace(int race_id) {
		Race race = raceDao.findRace(race_id);
		return race;
	}

	public RaceDao getRaceDao() {
		return raceDao;
	}

	public void setRaceDao(RaceDao raceDao) {
		this.raceDao = raceDao;
	}

}
