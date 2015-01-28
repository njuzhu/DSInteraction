package model;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="filmschedule")
public class FilmSchedule {
	private int id;
	private int cinemaHall_id;
	private String name;
	private Date startTime;
	private Date endTime;
	private String period;
	
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getCinemaHall_id() {
		return cinemaHall_id;
	}
	public void setCinemaHall_id(int cinemaHall_id) {
		this.cinemaHall_id = cinemaHall_id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	
}
