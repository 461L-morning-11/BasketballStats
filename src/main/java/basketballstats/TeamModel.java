package basketballstats;

import java.io.Serializable;
import java.sql.*;

public class TeamModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String abbreviation;
	private String city;
	private String conference;
	private String division;
	private String fullName;
	private String shortName;
	private int champsRecent;
	private int totalTries;
	private int champsCount;
	private String teamLogo;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public String getCity() {
		return city;
	}

	public String getConference() {
		return conference;
	}

	public String getDivision() {
		return division;
	}

	public String getFullName() {
		return fullName;
	}

	public String getShortName() {
		return shortName;
	}

	public int getChampsCount() {
		return champsCount;
	}

	public int getChampsRecent() {
		return champsRecent;
	}

	public int getTotalTries() {
		return totalTries;
	}

	public String getTeamLogo() {
		return teamLogo;
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
			ResultSet rs = statement.executeQuery("SELECT * FROM teams WHERE id = " + id);

			rs.next();

			fullName = rs.getString("full_name");
			shortName = rs.getString("short_name");
			city = rs.getString("city");
			abbreviation = rs.getString("abbreviation");
			division = rs.getString("division");
			conference = rs.getString("conference");
			champsCount = rs.getInt("total_champ");

			totalTries = rs.getInt("total_tries");
			champsRecent = rs.getInt("recent_champ");

			teamLogo = "../img/logos/" + shortName + ".png";

		} catch (Exception e) {
		}

	}
}
