package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="playlist")
public class PlayList {
	private int id;
	private int filmSchedule_id;
	private String name;
	private int gameType;
	private int duration;
	private String question_ids;
	private String race_ids;
	
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getFilmSchedule_id() {
		return filmSchedule_id;
	}
	public void setFilmSchedule_id(int filmSchedule_id) {
		this.filmSchedule_id = filmSchedule_id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getGameType() {
		return gameType;
	}
	public void setGameType(int gameType) {
		this.gameType = gameType;
	}
	
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public String getQuestion_ids() {
		return question_ids;
	}
	public void setQuestion_ids(String question_ids) {
		this.question_ids = question_ids;
	}
	
	public String getRace_ids() {
		return race_ids;
	}
	public void setRace_ids(String race_ids) {
		this.race_ids = race_ids;
	}
	
}
