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
		  Long id;
		  String first_name;
		  String last_name;
		  String position;
		  long height_feet;
		  long height_inches;
		  long weight_pounds;
		  Long team_id;
		  String team_name;
		  String team_conference;
		  Double nine_pts;
		  Double nine_ast;
		  Double nine_rbs;
		  Double nine_ft_pct;
		  Double eight_pts;
		  Double eight_ast;
		  Double eight_rbs;
		  Double eight_ft_pct;
		  Double seven_pts;
		  Double seven_ast;
		  Double seven_rbs;
		  Double seven_ft_pct;
		  Double six_pts;
		  Double six_ast;
		  Double six_rbs;
		  Double six_ft_pct;
		  int count;
		  boolean fill;
		
		
			
		 public void doGet(HttpServletRequest req, HttpServletResponse resp)
	         throws IOException {
			 count = 0;
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
				  
				  for(int i=1;i<2000;i++) {	//only getting 2000 for now. There are more
					  count++;
					  fill = false;
					  while(!fill) {
						  fetchAPI(i);
						  System.out.println("waiting..!");
					  }
						
						PreparedStatement ps=null;
				  
				  
						String ins="INSERT INTO players (id,first_name, last_name, position, height_feet, height_inches, weight_pounds, team_id, team_name, team_conference,2019_pts,2019_ast,2019_rbs,2019_ft_pct,2018_pts,2018_ast,2018_rbs,2018_ft_pct,2017_pts,2017_ast,2017_rbs,2017_ft_pct,2016_pts,2016_ast,2016_rbs,2016_ft_pct) VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						c.setAutoCommit(false);
				  
				  
					  ps=c.prepareStatement(ins);
					  ps.setLong(1, id);
					  ps.setString(2, first_name);
					  ps.setString(3, last_name);
					  ps.setString(4, position);
					  ps.setLong(5, height_feet);
					  ps.setLong(6, height_inches);
					  ps.setLong(7, weight_pounds);
					  ps.setLong(8, team_id);
					  ps.setString(9,team_name);
					  ps.setString(10,team_conference);
					  ps.setDouble(11, nine_pts);
					  ps.setDouble(12, nine_ast);
					  ps.setDouble(13, nine_rbs);
					  ps.setDouble(14, nine_ft_pct);
					  ps.setDouble(15, eight_pts);
					  ps.setDouble(16, eight_ast);
					  ps.setDouble(17, eight_rbs);
					  ps.setDouble(18, eight_ft_pct);
					  ps.setDouble(19, seven_pts);
					  ps.setDouble(20, seven_ast);
					  ps.setDouble(21, seven_rbs);
					  ps.setDouble(22, seven_ft_pct);
					  ps.setDouble(23, six_pts);
					  ps.setDouble(24, six_ast);
					  ps.setDouble(25, six_rbs);
					  ps.setDouble(26, six_ft_pct);
					  /*
					  try {
			            Thread.sleep(10);
					  }
					  catch (InterruptedException e)
					  {
			            e.printStackTrace();
					  }
					  */
					  ps.executeUpdate();
					  c.commit();
					  if(count==11) {
						  Thread.sleep(60000);
						  count=0;
					  }
				  
				  	  }
		   }catch(Exception e){
				  e.printStackTrace();
				}
		   
				

	    
	 	
	 	resp.sendRedirect("/teams.jsp");

	}
		 public void fetchAPI(int pageNum) {
				JSONParser parse = new JSONParser();
				try {
				URL url = new URL("https://www.balldontlie.io/api/v1/players/" + pageNum);
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();
				int responsecode = conn.getResponseCode();
				String inline = "";
				if(responsecode != 200) {
					fill = false;
					throw new RuntimeException("HttpResponseCode: " +responsecode);
				}
				else
				{
					fill = true;
				    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				
				   while((inline=in.readLine())!=null) {
					   JSONObject js = (JSONObject)parse.parse(inline);
					 
					   		
					   		id= (Long) js.get("id");
					   		System.out.println(id);
					   		first_name = (String) js.get("first_name");
					   		last_name = (String) js.get("last_name");
					   		height_feet = (long) js.get("height_feet");
					   		weight_pounds = (long) js.get("weight_pounds");
					   		height_inches = (long) js.get("height_inches");
					   		position = (String) js.get("position");
					   		JSONObject t =(JSONObject)js.get("team");
					   		team_id = (Long) t.get("id");
					   		team_name = (String) t.get("full_name");
					   		team_conference = (String) t.get("conference");
					   		
					   	}}
				conn.disconnect();
				   
				}catch(Exception e0) {
				   		e0.printStackTrace();
				   	}
				   
				
		 
		 
		 
		 		//2019 stats
				parse = new JSONParser();
			try {
				URL url = new URL("https://www.balldontlie.io/api/v1/season_averages?season=2019&player_ids[]=" + pageNum);
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();
				int responsecode = conn.getResponseCode();
				String inline = "";
				if(responsecode != 200) {
					fill = false;
					throw new RuntimeException("HttpResponseCode: " +responsecode);
				}
				else
				{
					fill = true;
				    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				
				   while((inline=in.readLine())!=null) {
					   JSONObject js = (JSONObject)parse.parse(inline);
					 
					   		
					   	
					   JSONArray t =(JSONArray) js.get("data");
					   if(t.size()!=0) {
		                JSONObject g = (JSONObject) t.get(0);

		                nine_pts = (Double) g.get("pts");
		                nine_ast = (Double) g.get("ast");
		                nine_rbs = (Double) g.get("reb");
		                nine_ft_pct = (Double) g.get("ft_pct");
					   }
					   else {
						   nine_pts = -1.0;
					   		nine_ast = -1.0;
					   		nine_rbs = -1.0;
					   		nine_ft_pct = -1.0;
					   }
				   		
				   	}}
			conn.disconnect();
			   
		   	}catch(Exception e1) {
			   		e1.printStackTrace();
			   	}
			   
			
		 
		 
		//2018 stats
			parse = new JSONParser();
		try {
			URL url = new URL("https://www.balldontlie.io/api/v1/season_averages?season=2018&player_ids[]=" + pageNum);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			String inline = "";
			if(responsecode != 200) {
				fill = false;
				throw new RuntimeException("HttpResponseCode: " +responsecode);
			}
			else
			{
				fill = true;
			    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			   while((inline=in.readLine())!=null) {
				   JSONObject js = (JSONObject)parse.parse(inline);
				 
				   		
				   	
				   JSONArray t =(JSONArray) js.get("data");
				   if(t.size() != 0) {
	                JSONObject g = (JSONObject) t.get(0);

	                eight_pts = (Double) g.get("pts");
	                eight_ast = (Double) g.get("ast");
	                eight_rbs = (Double) g.get("reb");
	                eight_ft_pct = (Double) g.get("ft_pct");
				   }
				   else {
					   eight_pts = -1.0;
				   		eight_ast = -1.0;
				   		eight_rbs = -1.0;
				   		eight_ft_pct = -1.0;
				   }
			   		
			   	}
			   }
		conn.disconnect();
		   

	   	}catch(Exception e2) {
		   		e2.printStackTrace();
		   	}
		   
		



		
		//2017 stats
		parse = new JSONParser();
		try {
		URL url = new URL("https://www.balldontlie.io/api/v1/season_averages?season=2017&player_ids[]=" + pageNum);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		int responsecode = conn.getResponseCode();
		String inline = "";
		if(responsecode != 200) {
			fill = false;
			throw new RuntimeException("HttpResponseCode: " +responsecode);
		}
		else
		{
			fill = true;
		    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		   while((inline=in.readLine())!=null) {
			   JSONObject js = (JSONObject)parse.parse(inline);
			 
			   		
			   	
			   JSONArray t =(JSONArray) js.get("data");
			   if(t.size() != 0) {
               JSONObject g = (JSONObject) t.get(0);

               seven_pts = (Double) g.get("pts");
               seven_ast = (Double) g.get("ast");
               seven_rbs = (Double) g.get("reb");
               seven_ft_pct = (Double) g.get("ft_pct");
			   }
			   else {
				   seven_pts = -1.0;
			   		seven_ast = -1.0;
			   		seven_rbs = -1.0;
			   		seven_ft_pct = -1.0;
			   }
		   		
		   	}}
		conn.disconnect();
		
	   	}catch(Exception e3) {
				e3.printStackTrace();
			}
		
		
		
		//2016 stats
				parse = new JSONParser();
				try {
				URL url = new URL("https://www.balldontlie.io/api/v1/season_averages?season=2016&player_ids[]=" + pageNum);
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();
				int responsecode = conn.getResponseCode();
				String inline = "";
				if(responsecode != 200) {
					fill = false;
					throw new RuntimeException("HttpResponseCode: " +responsecode);
				}
				else
				{
					fill = true;
				    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				
				   while((inline=in.readLine())!=null) {
					   JSONObject js = (JSONObject)parse.parse(inline);
					 
					   		
					   	
					   JSONArray t =(JSONArray) js.get("data");
					   if(t.size()!=0) {
		                JSONObject g = (JSONObject) t.get(0);

		                six_pts = (Double) g.get("pts");
		                six_ast = (Double) g.get("ast");
		                six_rbs = (Double) g.get("reb");
		                six_ft_pct = (Double) g.get("ft_pct");
					   }
					   else {
						   six_pts = -1.0;
					   		six_ast = -1.0;
					   		six_rbs = -1.0;
					   		six_ft_pct = -1.0;
					   }
				   		
				   	}}
				conn.disconnect();
				
			   	}catch(Exception e4) {
						e4.printStackTrace();
					}
		
		}
					
				
				
			 }
		 
			
	    

