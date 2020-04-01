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
	  int id;
	  java.util.Date date;
	  int home_score;
	  int visitor_score;
	  int season;
	  short period;
	  String status;
	  String time;
	  boolean postseason;
	  short home_id;
	  short visitor_id;
	  String home_name;
	  String visitor_name;
	
		
	 public void doGet(HttpServletRequest req, HttpServletResponse resp)
         throws IOException {
		 
		 	String instance = "basketball-db";
		 	String db="basketball_web";
			String user = "root";
			String pass="Sr4*8DNgZbvHqnee";
			String ip="104.154.138.136";
			
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
			  
			  for(int i=1;i<489;i++) {
					fetchAPI(i);
				
					PreparedStatement ps=null;
			  
			  
					String ins="INSERT INTO games (id,date,home_team_score,visitor_team_score,season, period, status, time, postseason, home_team_id,visitor_team_id) VALUE (?,?,?,?,?,?,?,?,?,?,?)";
					c.setAutoCommit(false);
			  
			  
				  ps=c.prepareStatement(ins);
				  ps.setInt(1, id);
				  java.sql.Date sd=new java.sql.Date(date.getTime());
				  ps.setDate(2, sd);
				  ps.setInt(3,home_score);
				  ps.setInt(4, visitor_score);
				  ps.setInt(5,season);
				  ps.setShort(6, period);
				  ps.setString(7, status);
				  ps.setString(8, time);
				  ps.setBoolean(9, postseason);
				  ps.setShort(10, home_id);
				  ps.setShort(11, visitor_id);
				  try {
		            Thread.sleep(50);
				  }
				  catch (InterruptedException e)
				  {
		            e.printStackTrace();
				  }
				  ps.executeUpdate();
				  c.commit();
			  
			  	  }
	   
	   }catch(Exception e){
			  e.printStackTrace();
			}
	   
			

    
 	
 	resp.sendRedirect("/teams.jsp");

}
	 public void fetchAPI(int pageNum) {
			JSONParser parse = new JSONParser();
			try {
			URL url = new URL("https://www.balldontlie.io/api/v1/games?per_page=100&page="+pageNum);
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
				   		Long longtemp;
				   		id=(int) ((js.get("id")));
				   		System.out.println(id);
				   		String dateTemp=js.get("date")+"";
				   		dateTemp=dateTemp.substring(0,10);
				   		date=new SimpleDateFormat("dd-MM-yyyy").parse(dateTemp);
				   		home_score=Integer.valueOf((js.get("home_team_score")+""));
				   		
				   		visitor_score=Integer.valueOf((js.get("visitor_team_score")+""));

				   		season=Integer.valueOf((js.get("season")+""));
						longtemp=(long)js.get("period");
						period=longtemp.shortValue();
				   		status=js.get("status")+"";
				   		time=js.get("time")+"";
				   		postseason=(boolean)js.get("postseason");
				   		JSONObject g=(JSONObject)js.get("home_team");
				   		longtemp=(long)g.get("id");
				   		home_name = (String) g.get("name");
						home_id=longtemp.shortValue();
						JSONObject gg=(JSONObject)js.get("visitor_team");
				   		longtemp=(long)gg.get("id");
				   		visitor_name = (String) gg.get("name");
						visitor_id=longtemp.shortValue();
				   	}
				   		
				   	}}
			conn.disconnect();
			   
			}catch(Exception e) {
			   		e.printStackTrace();
			   	}
			   
			}
			
		
			
			
		 }
	 
		
    

