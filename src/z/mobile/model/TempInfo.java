package z.mobile.model;

public class TempInfo implements Comparable<TempInfo> {
	private int uid;
	private String cinema;
	private String hall;
	private String seat;
	private String startTime;
	private int score;
	
	public TempInfo(int uid,int score) {
		super();
		this.uid = uid;
		this.score = score;
	}
	
	public TempInfo(int uid, String cinema, String hall, String seat,
			String startTime, int score) {
		super();
		this.uid = uid;
		this.cinema = cinema;
		this.hall = hall;
		this.seat = seat;
		this.startTime = startTime;
		this.score = score;
	}

	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getCinema() {
		return cinema;
	}
	public void setCinema(String cinema) {
		this.cinema = cinema;
	}
	public String getHall() {
		return hall;
	}
	public void setHall(String hall) {
		this.hall = hall;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "TempInfo [uid=" + uid + ", cinema=" + cinema + ", hall=" + hall
				+ ", seat=" + seat + ", startTime=" + startTime + ", score="
				+ score + "]";
	}

	@Override
	public int compareTo(TempInfo o) {
		// TODO Auto-generated method stub
		return o.getScore() - this.getScore();
	}
}
