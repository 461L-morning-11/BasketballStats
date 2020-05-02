package basketballstats;

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

@SuppressWarnings("serial")
public class databaseFillGames extends HttpServlet {
	
	java.util.Date date;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String instance = "basketball-db";
		String db = "basketball_web";
		String user = "root";
		String pass = "Sr4*8DNgZbvHqnee";
		String ip = "104.154.138.136";
		APIProperties game = new APIProperties();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String host = "jdbc:mysql://" + ip + ":3306/" + db;
			Connection c = DriverManager.getConnection(host, user, pass);

			Statement statement = c.createStatement();
			ResultSet resultSet = statement.executeQuery("SHOW TABLES");
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1));
			}

			for (int i = 1; i < 489; i++) {
				game.setFill(false);
				while (!game.isFill()) {
					game = API.fetchAPI(i, "games");
					System.out.println("waiting..");
				}

				PreparedStatement ps = null;

				String ins = "INSERT INTO games (id,date,home_team_score,visitor_team_score,season, period, status, time, postseason, home_team_id,visitor_team_id,home_name,visitor_name) VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?)";
				c.setAutoCommit(false);

				ps = c.prepareStatement(ins);
				ps.setLong(1, game.getId());
				java.sql.Date sd = new java.sql.Date(date.getTime());
				ps.setDate(2, sd);
				ps.setInt(3, game.getHome_score());
				ps.setInt(4, game.getVisitor_score());
				ps.setInt(5, game.getSeason());
				ps.setShort(6, game.getPeriod());
				ps.setString(7, game.getStatus());
				ps.setString(8, game.getTime());
				ps.setBoolean(9, game.isPostseason());
				ps.setShort(10, game.getHome_id());
				ps.setShort(11, game.getVisitor_id());
				ps.setString(12, game.getHome_name());
				ps.setString(13, game.getVisitor_name());
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ps.executeUpdate();
				c.commit();
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		resp.sendRedirect("/teams.jsp");

	}

	

}
