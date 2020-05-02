package basketballstats;

import java.io.Serializable;

public class GameModel implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String date;
	private int homeScore;
	private int visitorScore;
	private int season;
	private int period;
	private String status;
	private String time;
	private int postseason;
	private int homeID;
	private int visitorID;
	private String homeName;
	private String visitorName;
	
	public GameModel() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}
	public int getHomeScore() {
		return homeScore;
	}
	public int getVisitorScore() {
		return visitorScore;
	}
	public int getSeason() {
		return season;
	}
	public int getPeriod() {
		return period;
	}
	public String getStatus() {
		return status;
	}
	public String getTime() {
		return time;
	}
	public int getPostseason() {
		return postseason;
	}
	public int getHomeID() {
		return homeID;
	}
	public int getVisitorID() {
		return visitorID;
	}
	public String getHomeName() {
		return homeName;
	}
	public String getVisitorName() {
		return visitorName;
	}
	
	public void getData() {
		status = "yayyy";
	}
	
}
