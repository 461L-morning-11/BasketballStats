<%@ page import="java.net.URL" %>
<%@ page import="org.xml.sax.SAXException" %> 
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="org.json.simple.parser.JSONParser" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="java.util.Scanner" %>
<%@ page import="java.net.URI"%>
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
    <link rel="icon" href="/docs/4.0/assets/img/favicons/favicon.ico">

    <title>Teams</title>

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

	</div>

	
	
	<%
	pageContext.setAttribute("team_ID", request.getParameter("teamId"));
	String team_ID = request.getParameter("teamId");
	
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
	   	

			Statement statement = c.createStatement();
			
			ResultSet rs = statement.executeQuery("SELECT * FROM teams WHERE id = " + team_ID);

			rs.next();
					
		
		    pageContext.setAttribute("team_name_long", rs.getString("full_name"));
		
		    pageContext.setAttribute("team_name_short", rs.getString("short_name"));
		
			pageContext.setAttribute("team_city", rs.getString("city"));
		
			pageContext.setAttribute("team_abbreviation", rs.getString("abbreviation"));
			
			pageContext.setAttribute("team_division", rs.getString("division"));
			
			pageContext.setAttribute("team_conference", rs.getString("conference"));
		
			pageContext.setAttribute("team_ID", rs.getString("id"));
		
			pageContext.setAttribute("team_logo", "../img/logos/" + rs.getString("short_name") + ".png");
			%>


			
			<hr>
			<div class="row">
				<div class="specificTeam">
					<div class="center">
						<img src="${fn:escapeXml(team_logo)}" class="img-fluid img-thumbnail" align="middle" alt="Responsive image">
						<h1>${fn:escapeXml(team_name_long)} (${fn:escapeXml(team_abbreviation)})</h1>	
						<p>The ${fn:escapeXml(team_name_short)} from ${fn:escapeXml(team_city)} play in the ${fn:escapeXml(team_division)} division of the ${fn:escapeXml(team_conference)}ern Conference </p>
    				</div>
    			</div>
    		</div>
    		
    		<br>
    		<table  class="table table-hover">
	   		<thead>
	   			<tr>
	   				<th scope="col">Players</th>
	   			</tr>
	   		</thead>
	   		<tbody>
    		<% 
			
			ResultSet rs2 = statement.executeQuery("SELECT * FROM players WHERE team_id = " + team_ID);
    		while(rs2.next()){
    			pageContext.setAttribute("player_id", rs2.getString("id"));
    	   		%>
    	   			<tr onclick="window.location='specificPlayer.jsp?playerId=${player_id}';">
    	   				<td><%=rs2.getString("first_name") %></td>
    	   				<td><%=rs2.getString("last_name") %></td>
    	   				<%String short_position = (String) rs2.getString("position");
    	   				String position = "";
    	   				char[] position_letters = short_position.toCharArray();
    	   				for(int j =0; j <short_position.length(); j++){
    	   					try{
    	   					if(position_letters[j] == 'C'){
    	   						position += "Center";
    	   					} else if(position_letters[j] == 'G'){
    	   						position += "Guard";
    	   					} else if(position_letters[j] == 'F'){
    	   						position += "Forward";
    	   					} else if(position_letters[j] == 'C'){
    	   						position += "Center";
    	   					} else if(position_letters[j] == '-'){
    	   						position += "-";
    	   					}
    	   					}catch(Exception e){}
    	   				}
    	   				pageContext.setAttribute("player_position", position); %>
    	   				<td>${fn:escapeXml(player_position)}</td>   			
    	   			</tr>
    	   		<%} %>
    	   		</tbody>
    		</table>
    		<br>
    		<% 	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	%>

    
    

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
