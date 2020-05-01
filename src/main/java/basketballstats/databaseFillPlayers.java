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
		player.count = 0;
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
				player.count++;
				player.fill = false;
				while (!player.fill) {
					player = API.fetchAPI(i, "players");
					System.out.println("waiting..!");
				}

				PreparedStatement ps = null;

				String ins = "INSERT INTO players (id,first_name, last_name, position, height_feet, height_inches, weight_pounds, team_id, team_name, team_conference,2019_pts,2019_ast,2019_rbs,2019_ft_pct,2018_pts,2018_ast,2018_rbs,2018_ft_pct,2017_pts,2017_ast,2017_rbs,2017_ft_pct,2016_pts,2016_ast,2016_rbs,2016_ft_pct) VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				c.setAutoCommit(false);

				ps = c.prepareStatement(ins);
				ps.setLong(1, player.id);
				ps.setString(2, player.first_name);
				ps.setString(3, player.last_name);
				ps.setString(4, player.position);
				ps.setLong(5, player.height_feet);
				ps.setLong(6, player.height_inches);
				ps.setLong(7, player.weight_pounds);
				ps.setLong(8, player.team_id);
				ps.setString(9, player.team_name);
				ps.setString(10, player.team_conference);
				ps.setDouble(11, player.nine_pts);
				ps.setDouble(12, player.nine_ast);
				ps.setDouble(13, player.nine_rbs);
				ps.setDouble(14, player.nine_ft_pct);
				ps.setDouble(15, player.eight_pts);
				ps.setDouble(16, player.eight_ast);
				ps.setDouble(17, player.eight_rbs);
				ps.setDouble(18, player.eight_ft_pct);
				ps.setDouble(19, player.seven_pts);
				ps.setDouble(20, player.seven_ast);
				ps.setDouble(21, player.seven_rbs);
				ps.setDouble(22, player.seven_ft_pct);
				ps.setDouble(23, player.six_pts);
				ps.setDouble(24, player.six_ast);
				ps.setDouble(25, player.six_rbs);
				ps.setDouble(26, player.six_ft_pct);
				/*
				 * try { Thread.sleep(10); } catch (InterruptedException e) {
				 * e.printStackTrace(); }
				 */
				ps.executeUpdate();
				c.commit();
				if (player.count == 11) {
					Thread.sleep(60000);
					player.count = 0;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.sendRedirect("/teams.jsp");

	}

	

}
