package dao;

import java.util.List;

import model.Race;

public interface RaceDao {
	public Race findRace(int race_id);

	public List<Race> getRaceList();

	public List<Race> getRaceSearchList(String name);

	public int addRace(Race race);

	public void deleteRace(Race race);

	public boolean updateRace(Race race);
}
