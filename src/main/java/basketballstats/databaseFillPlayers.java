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
		  int height_feet;
		  int height_inches;
		  int weight_pounds;
		  Long team_id;
		  String team_name;
		  String team_conference;
		
		
			
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
				  
				  for(int i=1;i<2000;i++) {	//only getting 2000 for now. There are more
						fetchAPI(i);
					
						PreparedStatement ps=null;
				  
				  
						String ins="INSERT INTO players (id,first_name, last_name, position, height_feet, height_inches, weight_pounds, team_id, team_name, team_conference;) VALUE (?,?,?,?,?,?,?,?,?,?)";
						c.setAutoCommit(false);
				  
				  
					  ps=c.prepareStatement(ins);
					  ps.setLong(1, id);
					  ps.setString(2, first_name);
					  ps.setString(3, last_name);
					  ps.setString(4, position);
					  ps.setInt(5, height_feet);
					  ps.setInt(6, height_inches);
					  ps.setInt(7, weight_pounds);
					  ps.setLong(8, team_id);
					  ps.setString(9,team_name);
					  ps.setString(10,team_conference);
					  try {
			            Thread.sleep(10);
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
				URL url = new URL("https://www.balldontlie.io/api/v1/players/" + pageNum);
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
					   JSONObject js = (JSONObject)parse.parse(inline);
					 
					   		
					   		id= (Long) js.get("id");
					   		System.out.println(id);
					   		first_name = (String) js.get("first_name");
					   		last_name = (String) js.get("last_name");
					   		height_feet = (int) js.get("height_feet");
					   		weight_pounds = (int) js.get("weight_pounds");
					   		height_inches = (int) js.get("height_inches");
					   		position = (String) js.get("position");
					   		JSONObject t =(JSONObject)js.get("team");
					   		team_id = (Long) t.get("id");
					   		team_name = (String) t.get("full_name");
					   		team_conference = (String) t.get("conference");
					   		
					   	}}
				conn.disconnect();
				   
				}catch(Exception e) {
				   		e.printStackTrace();
				   	}
				   
				}
				
			
				
				
			 }
		 
			
	    

