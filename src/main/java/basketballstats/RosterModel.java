package basketballstats;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

public class RosterModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<PlayerModel> players;
	private ArrayList<String> lastNames;
	private ArrayList<String> position;
	private ArrayList<String> id;
	private String teamId;
	private int rosterSize;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ArrayList<PlayerModel> getPlayers() {
		return players;
	}
	
	public String getTeamId() {
		return teamId;
	}
	
	public int getRosterSize() {
		return rosterSize;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getPosition(int i) {
		return position.get(i);
	}

	public String getLastName(int i) {
		return lastNames.get(i);
	}

	public void fillData() {

		String db = "basketball_web";
		String user = "root";
		String pass = "Sr4*8DNgZbvHqnee";
		String ip = "104.154.138.136";
		
		players = new ArrayList<PlayerModel>();
		
		try {

			System.out.println("trying to query from sql database;");
			Class.forName("com.mysql.cj.jdbc.Driver");
			String host = "jdbc:mysql://" + ip + ":3306/" + db;
			Connection c = DriverManager.getConnection(host, user, pass);

			Statement statement = c.createStatement();
			ResultSet rs2 = statement.executeQuery("SELECT * FROM players WHERE team_id = " + teamId);

			while (rs2.next()) {
				
				PlayerModel newPlayer = new PlayerModel();
				newPlayer.setId(rs2.getString("id"));
				newPlayer.setFirstName(rs2.getString("first_name"));
				newPlayer.setLastName(rs2.getString("last_name"));
				newPlayer.setPosition(rs2.getString("position"));
				players.add(newPlayer);
				

				System.out.println(rosterSize);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
