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
public class databaseFillPlayersAverages extends HttpServlet {
		  Long pts;
		  Long ast;
		  Long rbs;
		  Long ft_pct;
		  boolean fill;
		
		
			
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
					  fill = false;
					  while(!fill) {
						  fetchAPI(i);
						  System.out.println("waiting..");
					  }
						
						PreparedStatement ps=null;
				  
				  
						String ins="INSERT INTO players (2019_pts,2019_ast,2019_rbs,2019_ft_pct) VALUE (?,?,?,?)";
						c.setAutoCommit(false);
				  
				  
					  ps=c.prepareStatement(ins);
					  ps.setLong(1, pts);
					  ps.setLong(2, ast);
					  ps.setLong(3, rbs);
					  ps.setLong(4, ft_pct);
					  
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
				  
				  	  }
		   }catch(Exception e){
				  e.printStackTrace();
				}
		   
				

	    
	 	
	 	resp.sendRedirect("/teams.jsp");

	}
		 public void fetchAPI(int pageNum) {
				JSONParser parse = new JSONParser();
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
					 
					   		
					   	
					   		JSONObject t =(JSONObject)js.get("data");
					   		pts = (Long) t.get("pts");
					   		ast = (Long) t.get("ast");
					   		rbs = (Long) t.get("rbs");
					   		ft_pct = (Long) t.get("ft_pct");
					   		
					   	}}
				conn.disconnect();
				   
				}catch(Exception e) {
				   		e.printStackTrace();
				   	}
				   
				}
				
			
				
				
			 }
		 
			
	    

