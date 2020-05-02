package basketballstats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.cloud.sql.jdbc.*;
import com.google.cloud.sql.jdbc.Driver;
import java.util.Map;

@SuppressWarnings("serial")
public class databaseFillPlayers extends HttpServlet {
	

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		APIProperties player = new APIProperties();
		player.setCount(0);
		String instance = "basketball-db";
		String db = "basketball_web";
		String user = "root";
		String pass = "Sr4*8DNgZbvHqnee";
		String ip = "104.154.138.136";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String host = "jdbc:mysql://" + ip + ":3306/" + db;
			Connection c = DriverManager.getConnection(host, user, pass);

			Statement statement = c.createStatement();
			ResultSet resultSet = statement.executeQuery("SHOW TABLES");
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1));
			}

			for (int i = 1; i < 3092; i++) { // only getting 2000 for now. There are more
				player.setCount(player.getCount()+1);
				player.setFill(false); 
				while (!player.isFill()) {
					player = API.fetchAPI(i, "players");
					System.out.println("waiting..!");
				}

				PreparedStatement ps = null;

				String ins = "INSERT INTO players (id,first_name, last_name, position, height_feet, height_inches, weight_pounds, team_id, team_name, team_conference,2019_pts,2019_ast,2019_rbs,2019_ft_pct,2018_pts,2018_ast,2018_rbs,2018_ft_pct,2017_pts,2017_ast,2017_rbs,2017_ft_pct,2016_pts,2016_ast,2016_rbs,2016_ft_pct) VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				c.setAutoCommit(false);

				ps = c.prepareStatement(ins);
				ps.setLong(1, player.getId());
				ps.setString(2, player.getFirst_name());
				ps.setString(3, player.getLast_name());
				ps.setString(4, player.getPosition());
				ps.setLong(5, player.getHeight_feet());
				ps.setLong(6, player.getHeight_inches());
				ps.setLong(7, player.getWeight_pounds());
				ps.setLong(8, player.getTeam_id());
				ps.setString(9, player.getTeam_name());
				ps.setString(10, player.getTeam_conference());
				ps.setDouble(11, player.getNine_pts());
				ps.setDouble(12, player.getNine_ast());
				ps.setDouble(13, player.getNine_rbs());
				ps.setDouble(14, player.getNine_ft_pct());
				ps.setDouble(15, player.getEight_pts());
				ps.setDouble(16, player.getEight_ast());
				ps.setDouble(17, player.getEight_rbs());
				ps.setDouble(18, player.getEight_ft_pct());
				ps.setDouble(19, player.getSeven_pts());
				ps.setDouble(20, player.getSeven_ast());
				ps.setDouble(21, player.getSeven_rbs());
				ps.setDouble(22, player.getSeven_ft_pct());
				ps.setDouble(23, player.getSix_pts());
				ps.setDouble(24, player.getSix_ast());
				ps.setDouble(25, player.getSix_rbs());
				ps.setDouble(26, player.getSix_ft_pct());
				/*
				 * try { Thread.sleep(10); } catch (InterruptedException e) {
				 * e.printStackTrace(); }
				 */
				ps.executeUpdate();
				c.commit();
				if (player.getCount() == 11) {
					Thread.sleep(60000);
					player.setCount(0);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.sendRedirect("/teams.jsp");

	}

	

}
