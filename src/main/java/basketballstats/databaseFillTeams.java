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
public class databaseFillTeams extends HttpServlet {
		  Long id;
		  String division;
		  String abbreviation;
		  String city;
		  String short_name;
		  String long_name;
		  String conference;
		  
		
		
			
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
				  int[] total_champ = {1,17,0,0,6,1,1,0,3,6,2,0,0,16,0,3,1,0,0,2,1,0,3,0,1,1,5,1,0,1};
				  int[] total_tries = {4,21,2,0,6,5,2,0,7,11,4,1,0,31,0,5,2,0,0,8,4,2,9,2,3,1,6,1,2,4};
				  int[] recent_champ = {1958,2008,2003,0,1998,2016,2011,0,2004,2018,1995,2000,0,2010,0,2013,1971,0,0,1973,1979,2009,1983,1993,1977,1951,2014,2019,1998,1978};
				  for(int i=1;i<31;i++) {
						fetchAPI(i);
						
						PreparedStatement ps=null;
				  
				  
						String ins="INSERT INTO teams (id,division,abbreviation,city,full_name,short_name, conference, total_champ, total_tries, recent_champ) VALUE (?,?,?,?,?,?,?,?,?,?)";
						c.setAutoCommit(false);
				  
				  
					  ps=c.prepareStatement(ins);
					  ps.setLong(1, id);
					  ps.setString(2, division);
					  ps.setString(3,abbreviation);
					  ps.setString(4, city);
					  ps.setString(5,long_name);
					  ps.setString(6,short_name);
					  ps.setString(7, conference);
					  ps.setInt(8,total_champ[i-1]);
					  ps.setInt(9,total_tries[i-1]);
					  ps.setInt(10, recent_champ[i-1]);
					 
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
				URL url = new URL("https://www.balldontlie.io/api/v1/teams/" + pageNum);
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
					   
					   		id= (Long) jobj.get("id");
					   		System.out.println(id);
					   		division = (String) jobj.get("division");
					   		abbreviation = (String) jobj.get("abbreviation");
					   		city = (String) jobj.get("city");
					   		long_name = (String) jobj.get("full_name");
					   		short_name = (String) jobj.get("name");
					   		conference = (String) jobj.get("conference");
					   	}
					   		
					   	}
				conn.disconnect();
				   
				}catch(Exception e) {
				   		e.printStackTrace();
				   	}
				   
				}
				
			
				
				
			 }
		 
			
	    

