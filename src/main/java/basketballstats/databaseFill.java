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



public class databaseFill extends HttpServlet {
	  short id;
	  java.util.Date date;
	  short home_score;
	  short visitor_score;
	  short season;
	  short period;
	  String status;
	  String time;
	  boolean postseason;
	  short home_id;
	  short visitor_id;
	
		
	 public void doGet(HttpServletRequest req, HttpServletResponse resp)
         throws IOException {
		 
		 	String instance = "basketball-db";
		 	String db="basketball_web";
			String user = "root";
			String pass="Sr4*8DNgZbvHqnee";
			String ip="104.154.138.136";
			
				fetchAPI();
	   try {
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   String host = "jdbc:mysql://" + ip + ":3306/" + db;
		   Connection c = DriverManager.getConnection(
	                host,
	                user,
	                pass
	        );
	   
	

			Statement statement = c.createStatement();
			  ResultSet resultSet = statement.executeQuery("SHOW TABLES");
			  while (resultSet.next()) {
			    System.out.println(resultSet.getString(1));
			  }
			  PreparedStatement ps=null;
			  
			  
			  String ins="INSERT INTO games (id,date,home_team_score,visitor_team_score,season, period, status, time, postseason, home_team_id,visitor_team_id) VALUE (?,?,?,?,?,?,?,?,?,?,?)";
			  c.setAutoCommit(false);
			  
			  
			  ps=c.prepareStatement(ins);
			  ps.setShort(6, id);
			  java.sql.Date sd=new java.sql.Date(date.getTime());
			  ps.setDate(0, sd);
			  ps.setShort(7,home_score);
			  ps.setShort(4, visitor_score);
			  ps.setShort(3,season);
			  ps.setShort(2, period);
			  ps.setString(10, status);
			  ps.setString(8, time);
			  ps.setBoolean(1, postseason);
			  ps.setShort(9, home_id);
			  ps.setShort(5, visitor_id);
			  ps.executeUpdate();
			  c.commit();

	   
	   }catch(Exception e){
			  e.printStackTrace();
			}
	   
			

    
 	
 	resp.sendRedirect("/teams.jsp");

}
	 public void fetchAPI() {
			int page=1;
			JSONParser parse = new JSONParser();
			try {
			URL url = new URL("https://www.balldontlie.io/api/v1/games");
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			String inline = "";
			if(responsecode != 200)
			    throw new RuntimeException("HttpResponseCode: " +responsecode);
			else
			{
			
			    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			   while((inline=in.readLine())!=null) {
				   JSONObject jobj = (JSONObject)parse.parse(inline);
				   JSONArray jsonarr = (JSONArray) jobj.get("data");
				   
				   	for(Object o:jsonarr) {
				   		JSONObject js=(JSONObject) o;
				   		Long longtemp=(long)js.get("id");
				   		id=longtemp.shortValue();
				   		String dateTemp=js.get("date")+"";
				   		dateTemp=dateTemp.substring(0,10);
				   		date=new SimpleDateFormat("dd-MM-yyyy").parse(dateTemp);
				   		longtemp=(long)js.get("home_team_score");
				   		home_score=longtemp.shortValue();
				   		longtemp=(long)js.get("visitor_team_score");
				   		visitor_score=longtemp.shortValue();
				   		longtemp=(long)js.get("season");
						season=longtemp.shortValue();
						longtemp=(long)js.get("period");
						period=longtemp.shortValue();
				   		status=js.get("status")+"";
				   		time=js.get("time")+"";
				   		postseason=(boolean)js.get("postseason");
				   		JSONObject g=(JSONObject)js.get("home_team");
				   		longtemp=(long)g.get("id");
						home_id=longtemp.shortValue();
						JSONObject gg=(JSONObject)js.get("visitor_team");
				   		longtemp=(long)gg.get("id");
						visitor_id=longtemp.shortValue();
				   		
				   		
				   	}
			   }
			   in.close();
			}
			
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		 }
	 }
		
    

