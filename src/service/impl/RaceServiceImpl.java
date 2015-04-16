package service.impl;

import java.util.List;

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

	@Override
	public List<Race> getRaceList() {
		// TODO Auto-generated method stub
		return raceDao.getRaceList();
	}

	@Override
	public List<Race> getRaceSearchList(String name) {
		// TODO Auto-generated method stub
		return raceDao.getRaceSearchList(name);
	}

	@Override
	public int addRace(Race race) {
		// TODO Auto-generated method stub
		return raceDao.addRace(race);
	}

	@Override
	public void deleteRaceById(int race_id) {
		// TODO Auto-generated method stub
		Race race = raceDao.findRace(race_id);
		raceDao.deleteRace(race);
	}

	@Override
	public boolean updateRace(Race race) {
		// TODO Auto-generated method stub
		return raceDao.updateRace(race);
	}

}
