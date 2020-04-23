<%@ page import="java.net.URL" %>
<%@ page import="org.xml.sax.SAXException" %> 
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="org.json.simple.parser.JSONParser" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="java.util.Scanner" %>
<%@ page import="java.net.URI"%>
<%@ page import="java.time.*" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.*" %>
<%@ page import="com.google.cloud.sql.jdbc.Driver" %>
<%@ page import="java.sql.*" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" href="../img/basketball.png">

    <title>Game</title>

    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../css/custom-site.css" rel="stylesheet">
  </head>

  <body>

    <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
      <a class="navbar-brand" href="/">Basketball Stats</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="http://example.com" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Models</a>
            <div class="dropdown-menu" aria-labelledby="dropdown01">
              <a class="dropdown-item" href="/teams">Teams</a>
              <a class="dropdown-item" href="/players">Players</a>
              <a class="dropdown-item" href="/games">Games</a>
            </div>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/about">About</a>
          </li>
        </ul>
    	<form class="form-inline my-2 my-lg-0" method="post" action="search.jsp">
          <input class="form-control mr-sm-2" name="search" type="text" placeholder="Search" aria-label="Search">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
      </div>
    </nav>

    <main role="main" class="container">

 <div class="main-content">
		        
		<% 
			int visitor_id = -1;
			int home_id = -1;
		
			String game_ID = request.getParameter("gameId");
		
			
			String db="basketball_web";
			String user = "root";
			String pass="Sr4*8DNgZbvHqnee";
			String ip="104.154.138.136";
			
	   		try {
	   			
	   			System.out.println("trying to query from sql database;");
			   	Class.forName("com.mysql.cj.jdbc.Driver");
			   	String host = "jdbc:mysql://" + ip + ":3306/" + db;
			   	Connection c = DriverManager.getConnection(
		        	host,
		        	user,
		        	pass
		        );
			   	
		
		   		Statement stm = c.createStatement();
		   		Statement stm2 = c.createStatement();
		   		Statement stm3 = c.createStatement();
		   		ResultSet rs = stm.executeQuery("SELECT * FROM games WHERE id = " + game_ID);
		   		

	   			rs.next();
		   		System.out.println("getting data for game " + rs.getString("id"));


				pageContext.setAttribute("game_home_team", rs.getString("home_name"));
				pageContext.setAttribute("game_visitor_team", rs.getString("visitor_name"));
				
				//DB SEARCH FOR TEAM ID
				
				String homeTeam=rs.getString("home_name");
				String visitorTeam=rs.getString("visitor_name");
	
	    
	   	String Query2 = "SELECT * FROM teams WHERE short_name = '"+homeTeam+"' LIMIT 1";
	   	ResultSet rs2 = stm2.executeQuery(Query2);
	   	rs2.next();
	   		pageContext.setAttribute("home_team_ID", rs2.getString("id"));
	   		home_id = rs2.getInt("id");
	   		String Query3 = "SELECT * FROM teams WHERE short_name = '"+visitorTeam+"' LIMIT 1";
		   	ResultSet rs3 = stm3.executeQuery(Query3);
		   	rs3.next();
		   		pageContext.setAttribute("visitor_team_ID", rs3.getString("id"));
				visitor_id = rs3.getInt("id");
		   		
		        String shortDate = (String) rs.getString("date");
			    
			    pageContext.setAttribute("game_date", shortDate.substring(5, 10));

				pageContext.setAttribute("game_home_score", rs.getString("home_team_score"));
				
				pageContext.setAttribute("game_visitor_score", rs.getString("visitor_team_score"));
				pageContext.setAttribute("home_logo", "../img/logos/" + rs.getString("home_name") + ".png");
				pageContext.setAttribute("visitor_logo", "../img/logos/" + rs.getString("visitor_name") + ".png");
				int vscore=Integer.parseInt(rs.getString("visitor_team_score"));
				int hscore=Integer.parseInt(rs.getString("home_team_score"));
				if(vscore>hscore){
					pageContext.setAttribute("winner",rs.getString("visitor_name"));}
				else if(vscore<hscore){
					pageContext.setAttribute("winner",rs.getString("home_name"));}
				else{
					pageContext.setAttribute("winner","TIE");}

					
				
				
				
				
				
			   		
		   		
				
			%>
			<div class="row">
        <div class="col-md-4">
            <h1>${fn:escapeXml(game_home_team)}</h2>
            
								<%if(home_id == 21){ %>
							<img src="../img/Thun.png" class="img-fluid img-thumbnail" alt="Responsive image">
						<%}else{ %>
							<img src="${fn:escapeXml(home_logo)}" class="img-fluid img-thumbnail" alt="Responsive image">
							<%} %>
            <h3>Score: ${fn:escapeXml(game_home_score)}</h3>
            <p><a href="specificTeam.jsp?teamId=${home_team_ID}" class="btn btn-success">Team Info &raquo;</a></p>
        </div>
    
    <div class="col-md-4">
            <img src="../img/vs.png" class="img-fluid img-thumbnail" alt="Responsive image">
            </div>
        
        <div class="col-md-4">
            <h1>${fn:escapeXml(game_visitor_team)}</h2>
            <%if(visitor_id == 21){ %>
							<img src="../img/Thun.png" class="img-fluid img-thumbnail" alt="Responsive image">
						<%}else{ %>
							<img src="${fn:escapeXml(visitor_logo)}" class="img-fluid img-thumbnail" alt="Responsive image">
							<%} %>
			<h3>Score: ${fn:escapeXml(game_visitor_score)}</h3>
            <p><a href="specificTeam.jsp?teamId=${visitor_team_ID}" class="btn btn-success">Team Info &raquo;</a></p>
        </div>
    </div>
    
		
       	 	<br><hr><br>
       	 	<p>This was not a Postseason game.</p>
        
	
		
        
      </div>
      <br><br><br>
      <div>
        <h1 style="color:#FF0000;text-align:center;">WINNER:${fn:escapeXml(winner)} </h1>
     <!-- <audio controls autoplay>  
        <source src="../img/music.mp3" type="audio/mp3">   
    </audio>  
    -->
      
      <%   	
	   		}
	   		catch(Exception e) {
	   			e.printStackTrace();
	   		}%>
</div>

    </main><!-- /.container -->
		
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="../js/bootstrap.min.js"></script>
  </body>
</html>
