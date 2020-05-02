package basketballstats;

import java.io.Serializable;
import java.sql.*;

public class PlayerModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String position;
	private String heightFeet;
	private String heightInches;
	private String weight;
	private int teamId;
	private String id;
	private String teamName;
	private String teamConference;
	private String ast2019;
	private String rbs2019;
	private String ftPct2019;
	private int pts2018;
	private String ast2018;
	private String rbs2018;
	private String ftPct2018;
	private String ast2017;
	private String rbs2017;
	private String ftPct2017;
	private int pts2016;
	private String ast2016;
	private String rbs2016;
	private String ftPct2016;
	private int pts2019;
	private int pts2017;
	
	

	
	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	public String getFirstName() {
		return firstName;
	}




	public String getLastName() {
		return lastName;
	}




	public String getPosition() {
		return position;
	}




	public String getHeightFeet() {
		return heightFeet;
	}




	public String getHeightInches() {
		return heightInches;
	}




	public String getWeight() {
		return weight;
	}




	public int getTeamId() {
		return teamId;
	}




	public String getTeamName() {
		return teamName;
	}




	public String getTeamConference() {
		return teamConference;
	}




	public int getPts2019() {
		return pts2019;
	}




	public String getAst2019() {
		return ast2019;
	}




	public String getRbs2019() {
		return rbs2019;
	}




	public String getFtPct2019() {
		return ftPct2019;
	}




	public int getPts2018() {
		return pts2018;
	}




	public String getAst2018() {
		return ast2018;
	}




	public String getRbs2018() {
		return rbs2018;
	}




	public String getFtPct2018() {
		return ftPct2018;
	}




	public int getPts2017() {
		return pts2017;
	}




	public String getAst2017() {
		return ast2017;
	}




	public String getRbs2017() {
		return rbs2017;
	}




	public String getFtPct2017() {
		return ftPct2017;
	}




	public int getPts2016() {
		return pts2016;
	}




	public String getAst2016() {
		return ast2016;
	}




	public String getRbs2016() {
		return rbs2016;
	}




	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}




	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




	public void setPosition(String position) {
		this.position = position;
	}




	public void setHeightFeet(String heightFeet) {
		this.heightFeet = heightFeet;
	}




	public void setHeightInches(String heightInches) {
		this.heightInches = heightInches;
	}




	public void setWeight(String weight) {
		this.weight = weight;
	}




	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}




	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}




	public void setTeamConference(String teamConference) {
		this.teamConference = teamConference;
	}




	public void setAst2019(String ast2019) {
		this.ast2019 = ast2019;
	}




	public void setRbs2019(String rbs2019) {
		this.rbs2019 = rbs2019;
	}




	public void setFtPct2019(String ftPct2019) {
		this.ftPct2019 = ftPct2019;
	}




	public void setPts2018(int pts2018) {
		this.pts2018 = pts2018;
	}




	public void setAst2018(String ast2018) {
		this.ast2018 = ast2018;
	}




	public void setRbs2018(String rbs2018) {
		this.rbs2018 = rbs2018;
	}




	public void setFtPct2018(String ftPct2018) {
		this.ftPct2018 = ftPct2018;
	}




	public void setAst2017(String ast2017) {
		this.ast2017 = ast2017;
	}




	public void setRbs2017(String rbs2017) {
		this.rbs2017 = rbs2017;
	}




	public void setFtPct2017(String ftPct2017) {
		this.ftPct2017 = ftPct2017;
	}




	public void setPts2016(int pts2016) {
		this.pts2016 = pts2016;
	}




	public void setAst2016(String ast2016) {
		this.ast2016 = ast2016;
	}




	public void setRbs2016(String rbs2016) {
		this.rbs2016 = rbs2016;
	}




	public void setFtPct2016(String ftPct2016) {
		this.ftPct2016 = ftPct2016;
	}




	public void setPts2019(int pts2019) {
		this.pts2019 = pts2019;
	}




	public void setPts2017(int pts2017) {
		this.pts2017 = pts2017;
	}




	public String getFtPct2016() {
		return ftPct2016;
	}




	public void fillData() {
		
		String player_ID = id;
		heightFeet = null;
		weight = null;

		String db = "basketball_web";
		String user = "root";
		String pass = "Sr4*8DNgZbvHqnee";
		String ip = "104.154.138.136";

		try {

			System.out.println("trying to query from sql database;");
			Class.forName("com.mysql.cj.jdbc.Driver");
			String host = "jdbc:mysql://" + ip + ":3306/" + db;
			Connection c = DriverManager.getConnection(host, user, pass);

			Statement statement = c.createStatement();

			ResultSet rs = statement.executeQuery("SELECT * FROM players WHERE id = " + player_ID);

			rs.next();
			System.out.println("getting data for player " + rs.getString("id"));
			
			firstName = rs.getString("first_name");
			lastName = rs.getString("last_name");
			heightFeet = rs.getString("height_feet");
			heightInches = rs.getString("height_inches");
			weight = rs.getString("weight_pounds");
			teamName = rs.getString("team_name");
			teamId =rs.getInt("team_id");
			teamConference = rs.getString("team_conference");

			String short_position = (String) rs.getString("position");
			position = "";
			char[] position_letters = short_position.toCharArray();
			for (int j = 0; j < short_position.length(); j++) {
				try {
					if (position_letters[j] == 'C') {
						position += "Center";
					} else if (position_letters[j] == 'G') {
						position += "Guard";
					} else if (position_letters[j] == 'F') {
						position += "Forward";
					} else if (position_letters[j] == 'C') {
						position += "Center";
					} else if (position_letters[j] == '-') {
						position += "-";
					}
				} catch (Exception e) {
				}
			}

			

		} catch (Exception e) {
			e.printStackTrace();
		}
	
		int nine_pts = -1;
		int eight_pts = -1;
		int seven_pts = -1;
		int six_pts = -1;
		try {

		System.out.println("trying to query from sql database;");
		Class.forName("com.mysql.cj.jdbc.Driver");
		String host = "jdbc:mysql://" + ip + ":3306/" + db;
		Connection c = DriverManager.getConnection(host, user, pass);

		Statement statement = c.createStatement();

		ResultSet rs = statement.executeQuery("SELECT * FROM players WHERE id = " + player_ID);

		rs.next();

		if (rs.getFloat("2019_pts") != -1) {
			ast2019 = rs.getString("2019_ast");
			rbs2019 = rs.getString("2019_rbs");
			ftPct2019 = rs.getString("2019_ft_pct");
			pts2019 = rs.getInt("2019_pts");
		} else {
			pts2019 = -1;
			ast2019 = "0";
			rbs2019 = "0";
			ftPct2019 = "0";
		}

		if (rs.getFloat("2018_pts") != -1) {
			ast2018 = rs.getString("2018_ast");
			rbs2018 = rs.getString("2018_rbs");
			ftPct2018 = rs.getString("2018_ft_pct");
			pts2018 = rs.getInt("2018_pts");
		} else {
			pts2018 = -1;
			ast2018 = "0";
			rbs2018 = "0";
			ftPct2018 = "0";
		}

		if (rs.getFloat("2017_pts") != -1) {
			ast2017 = rs.getString("2017_ast");
			rbs2017 = rs.getString("2017_rbs");
			ftPct2017 = rs.getString("2017_ft_pct");
			pts2017 = rs.getInt("2017_pts");
		} else {
			pts2017 = -1;
			ast2017 = "0";
			rbs2017 = "0";
			ftPct2017 = "0";
		}

		if (rs.getFloat("2016_pts") != -1) {
			ast2016 = rs.getString("2016_ast");
			rbs2016 = rs.getString("2016_rbs");
			ftPct2016 = rs.getString("2016_ft_pct");
			pts2016 = rs.getInt("2016_pts");
		} else {
			pts2016 = -1;
			ast2016 = "0";
			rbs2016 = "0";
			ftPct2016 = "0";
		}
	
	}
	catch (Exception e) {
		e.printStackTrace();
	}	

}
}
