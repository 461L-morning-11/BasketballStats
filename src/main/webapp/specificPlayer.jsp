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

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" href="../img/basketball.png">

    <title>Players</title>

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

      <div class="main-content"></div>
      
    <% //pageContext.setAttribute("team_ID", request.getParameter("teamId"));
    String height_feet = null;
    String weight_pounds = null;
   	int nine_pts = -1;
   	int eight_pts = -1;
   	int seven_pts = -1;
   	int six_pts = -1;
    String player_ID = request.getParameter("playerId");
    
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
   		
   		ResultSet rs = statement.executeQuery("SELECT * FROM players WHERE id = " + player_ID);
   		

			rs.next();
   		System.out.println("getting data for player " + rs.getString("id"));

    
    	pageContext.setAttribute("player_first_name", rs.getString("first_name"));

		pageContext.setAttribute("player_last_name", rs.getString("last_name"));
		
		pageContext.setAttribute("player_height_feet", rs.getString("height_feet"));
	 	height_feet = rs.getString("height_feet");
		pageContext.setAttribute("player_height_inches", rs.getString("height_inches"));
		
		pageContext.setAttribute("player_weight", rs.getString("weight_pounds"));
		weight_pounds = rs.getString("weight_pounds");
		
		
	    pageContext.setAttribute("player_team_name_short", rs.getString("team_name"));
		
		pageContext.setAttribute("player_team_name_long", rs.getString("team_name"));
		
		pageContext.setAttribute("player_team_conference", rs.getString("team_conference"));
		
		pageContext.setAttribute("team_logo", "../img/logos/" + rs.getString("team_name") + ".png");
		
		String short_position = (String) rs.getString("position");
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
		pageContext.setAttribute("player_position", position);
		
		pageContext.setAttribute("2019_pts", 0);
		pageContext.setAttribute("2019_ast", 0);
		pageContext.setAttribute("2019_rbs", 0);
		pageContext.setAttribute("2019_ft_pct", 0);
		
		if(rs.getFloat("2019_pts") != -1){
			pageContext.setAttribute("2019_pts", rs.getString("2019_pts"));
			pageContext.setAttribute("2019_ast", rs.getString("2019_ast"));
			pageContext.setAttribute("2019_rbs", rs.getString("2019_rbs"));
			pageContext.setAttribute("2019_ft_pct", rs.getString("2019_ft_pct"));	
		}
		
		if(rs.getFloat("2018_pts") != -1){
			pageContext.setAttribute("2018_pts", rs.getString("2018_pts"));
			pageContext.setAttribute("2018_ast", rs.getString("2018_ast"));
			pageContext.setAttribute("2018_rbs", rs.getString("2018_rbs"));
			pageContext.setAttribute("2018_ft_pct", rs.getString("2018_ft_pct"));	
		} else{
			pageContext.setAttribute("2018_pts", 0);
			pageContext.setAttribute("2018_ast", 0);
			pageContext.setAttribute("2018_rbs", 0);
			pageContext.setAttribute("2018_ft_pct", 0);
		}
		
		if(rs.getFloat("2017_pts") != -1){
			pageContext.setAttribute("2017_pts", rs.getString("2017_pts"));
			pageContext.setAttribute("2017_ast", rs.getString("2017_ast"));
			pageContext.setAttribute("2017_rbs", rs.getString("2017_rbs"));
			pageContext.setAttribute("2017_ft_pct", rs.getString("2017_ft_pct"));	
		} else{
			pageContext.setAttribute("2017_pts", 0);
			pageContext.setAttribute("2017_ast", 0);
			pageContext.setAttribute("2017_rbs", 0);
			pageContext.setAttribute("2017_ft_pct", 0);
		}
		
		if(rs.getFloat("2016_pts") != -1){
			pageContext.setAttribute("2016_pts", rs.getString("2016_pts"));
			pageContext.setAttribute("2016_ast", rs.getString("2016_ast"));
			pageContext.setAttribute("2016_rbs", rs.getString("2016_rbs"));
			pageContext.setAttribute("2016_ft_pct", rs.getString("2016_ft_pct"));	
		} else{
			pageContext.setAttribute("2016_pts", 0);
			pageContext.setAttribute("2016_ast", 0);
			pageContext.setAttribute("2016_rbs", 0);
			pageContext.setAttribute("2016_ft_pct", 0);
		}
		
		}
   		catch(Exception e) {
   			e.printStackTrace();
   		}
	
	%>
	<div class="row">
		<div class="specificTeam">
			<div class="center">
				<h1><b>${fn:escapeXml(player_first_name)} ${fn:escapeXml(player_last_name)}</b>
				<%
				if(height_feet != null){
					%><br>
					- ${fn:escapeXml(player_height_feet)}'${fn:escapeXml(player_height_inches)}''
					<%
				}
				if(weight_pounds != null){
					%> ${fn:escapeXml(player_weight)} lbs
					<%
				}
				%>
				</h1>
				<div class="center">	
					<p>${fn:escapeXml(player_first_name)} is a ${fn:escapeXml(player_position)} for the ${fn:escapeXml(player_team_name_long)} in the ${fn:escapeXml(player_team_conference)}ern Conference</p>
				</div>
   			</div>
    	</div>
    	
    </div>
    <br>
    <div id="wrapper">
    <% 
    
    
    db="basketball_web";
	user = "root";
	pass="Sr4*8DNgZbvHqnee";
	ip="104.154.138.136";
	
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
   		
   		ResultSet rs = statement.executeQuery("SELECT * FROM players WHERE id = " + player_ID);
   		

			rs.next();
			pageContext.setAttribute("nine_pts", rs.getString("2019_pts"));
			nine_pts =  rs.getInt("2019_pts");
			pageContext.setAttribute("nine_ast", rs.getString("2019_ast"));
			pageContext.setAttribute("nine_rbs", rs.getString("2019_rbs"));
			pageContext.setAttribute("nine_ft_pct", rs.getString("2019_ft_pct"));
			pageContext.setAttribute("eight_pts", rs.getString("2018_pts"));
			eight_pts =  rs.getInt("2018_pts");
			pageContext.setAttribute("eight_ast", rs.getString("2018_ast"));
			pageContext.setAttribute("eight_rbs", rs.getString("2018_rbs"));
			pageContext.setAttribute("eight_ft_pct", rs.getString("2018_ft_pct"));
			pageContext.setAttribute("seven_pts", rs.getString("2017_pts"));
			seven_pts =  rs.getInt("2017_pts");
			pageContext.setAttribute("seven_ast", rs.getString("2017_ast"));
			pageContext.setAttribute("seven_rbs", rs.getString("2017_rbs"));
			pageContext.setAttribute("seven_ft_pct", rs.getString("2017_ft_pct"));
			pageContext.setAttribute("six_pts", rs.getString("2016_pts"));
			six_pts =  rs.getInt("2016_pts");
			pageContext.setAttribute("six_ast", rs.getString("2016_ast"));
			pageContext.setAttribute("six_rbs", rs.getString("2016_rbs"));
			pageContext.setAttribute("six_ft_pct", rs.getString("2016_ft_pct"));
			%>
			<%if(nine_pts != -1 ){ %>
    <div class="seasonAverages" >
    	<header class="title">2019 Season Stats</header>
    	
   		<table style="width:100%">
		  <tr>
		    <td>Average Points:</td>
		    <td>${nine_pts}</td>
		  </tr>
		  <tr>
		    <td>Average Assists:</td>
		    <td>${nine_ast}</td>
		  </tr>
		  <tr>
		    <td>Average Rebounds:</td>
		    <td>${nine_rbs}</td>
		  </tr>
		  <tr>
		    <td>Free Throw Percent:</td>
		    <td>${nine_ft_pct}</td>
		  </tr>
		</table>
   	</div>
   	<%} %>
   	<%if(eight_pts != -1 ){ %>
    <div class="seasonAverages" >
    	<header class="title">2018 Season Stats</header>
    	
   		<table style="width:100%">
		  <tr>
		    <td>Average Points:</td>
		    <td>${eight_pts}</td>
		  </tr>
		  <tr>
		    <td>Average Assists:</td>
		    <td>${eight_ast}</td>
		  </tr>
		  <tr>
		    <td>Average Rebounds:</td>
		    <td>${eight_rbs}</td>
		  </tr>
		  <tr>
		    <td>Free Throw Percent:</td>
		    <td>${eight_ft_pct}</td>
		  </tr>
		</table>
   	</div>
   	<%} %>
   	<%if(seven_pts != -1 ){ %>
    <div class="seasonAverages" >
    	<header class="title">2017 Season Stats</header>
   		<table style="width:100%">
		  <tr>
		    <td>Average Points:</td>
		    <td>${seven_pts}</td>
		  </tr>
		  <tr>
		    <td>Average Assists:</td>
		    <td>${seven_ast}</td>
		  </tr>
		  <tr>
		    <td>Average Rebounds:</td>
		    <td>${seven_rbs}</td>
		  </tr>
		  <tr>
		    <td>Free Throw Percent:</td>
		    <td>${seven_ft_pct}</td>
		  </tr>
		</table>
   	</div>
   	<%} %>
   	<%if(six_pts != -1 ){ %>
    <div class="seasonAverages">
    	<header class="title">2016 Season Stats</header>
   		<table style="width:100%">
		  <tr>
		    <td>Average Points:</td>
		    <td>${six_pts}</td>
		  </tr>
		  <tr>
		    <td>Average Assists:</td>
		    <td>${six_ast}</td>
		  </tr>
		  <tr>
		    <td>Average Rebounds:</td>
		    <td>${six_rbs}</td>
		  </tr>
		  <tr>
		    <td>Free Throw Percent:</td>
		    <td>${six_ft_pct}</td>
		  </tr>
		</table>
   	</div>
   	<%} %>
   	<%if(six_pts == -1 && seven_pts == -1 && eight_pts == -1 && nine_pts == -1){ %>
	<p>We have no record of this player playing in the NBA since at least 2015</p>   	
   	<%} %>
    <% }
   		catch(Exception e) {
   			e.printStackTrace();
   		}
	   		%>
	
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
