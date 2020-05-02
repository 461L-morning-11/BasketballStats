package basketballstats;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GameModel implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String date;
	private String homeScore;
	private String visitorScore;
	private String season;
	private String period;
	private String status;
	private String time;
	private String postseason;
	private String homeID;
	private String visitorID;
	private String homeName;
	private String visitorName;
	private String homeLogo;
	private String visitorLogo;
	private String winner;
	
	public GameModel() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getHomeLogo() {
		return homeLogo;
	}

	public void setHomeLogo(String homeLogo) {
		this.homeLogo = homeLogo;
	}

	public String getVisitorLogo() {
		return visitorLogo;
	}

	public void setVisitorLogo(String visitorLogo) {
		this.visitorLogo = visitorLogo;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setHomeScore(String homeScore) {
		this.homeScore = homeScore;
	}

	public void setVisitorScore(String visitorScore) {
		this.visitorScore = visitorScore;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setPostseason(String postseason) {
		this.postseason = postseason;
	}

	public void setHomeID(String homeID) {
		this.homeID = homeID;
	}

	public void setVisitorID(String visitorID) {
		this.visitorID = visitorID;
	}

	public void setHomeName(String homeName) {
		this.homeName = homeName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}

	public String getDate() {
		return date;
	}
	public String getHomeScore() {
		return homeScore;
	}
	public String getVisitorScore() {
		return visitorScore;
	}
	public String getSeason() {
		return season;
	}
	public String getPeriod() {
		return period;
	}
	public String getStatus() {
		return status;
	}
	public String getTime() {
		return time;
	}
	public String getPostseason() {
		return postseason;
	}
	public String getHomeID() {
		return homeID;
	}
	public String getVisitorID() {
		return visitorID;
	}
	public String getHomeName() {
		return homeName;
	}
	public String getVisitorName() {
		return visitorName;
	}
	
	public void fillData() {
		String db = "basketball_web";
		String user = "root";
		String pass = "Sr4*8DNgZbvHqnee";
		String ip = "104.154.138.136";

		try {

			System.out.println("trying to query from sql database;");
			Class.forName("com.mysql.cj.jdbc.Driver");
			String host = "jdbc:mysql://" + ip + ":3306/" + db;
			System.out.println("jdbc:mysql://" + ip + ":3306/" + db);
			Connection c = DriverManager.getConnection(host, user, pass);

			Statement statement = c.createStatement();
			System.out.println("SELECT * FROM teams WHERE id = " + id);
			ResultSet rs = statement.executeQuery("SELECT * FROM games WHERE id = " + id);

			rs.next();
			
			
			homeID = rs.getString("home_team_id");
			visitorID = rs.getString("visitor_team_id");
			homeName = rs.getString("home_name");
			visitorName = rs.getString("visitor_name");
			date =  rs.getString("date").substring(5,10);
			homeScore = rs.getString("home_team_score");
			visitorScore = rs.getString("visitor_team_score");
			homeLogo = "../img/logos/" + homeName + ".png";
			visitorLogo = "../img/logos/" + visitorName + ".png";
			int homeScoreInt = Integer.valueOf(homeScore);
			int visitorScoreInt = Integer.valueOf(visitorScore);
			if(homeScoreInt < visitorScoreInt) {
				winner = homeName;
			}else if(visitorScoreInt < homeScoreInt){
				winner = visitorName;
			} else {
				winner = "TIE";
			}
			
			
		} catch (Exception e) {
		}

	}
	
}
