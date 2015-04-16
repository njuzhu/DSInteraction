package service;

import java.util.List;

import model.Race;

public interface RaceService {
	public Race searchRace(int race_id);

	public List<Race> getRaceList();

	public List<Race> getRaceSearchList(String name);

	public int addRace(Race race);

	public void deleteRaceById(int race_id);

	public boolean updateRace(Race race);
}
