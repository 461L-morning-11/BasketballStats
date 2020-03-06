<%@ page import="java.net.URL" %>
<%@ page import="org.xml.sax.SAXException" %> 
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="org.json.simple.parser.JSONParser" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="java.util.Scanner" %>
<%@ page import="java.net.URI"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/docs/4.0/assets/img/favicons/favicon.ico">

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
              <a class="dropdown-item" href="/coaches">Coaches</a>
            </div>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/about">About</a>
          </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
          <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
      </div>
    </nav>

    <main role="main" class="container">

      <div class="main-content"></div>
      
    <% //pageContext.setAttribute("team_ID", request.getParameter("teamId"));
	URL url = new URL("https://www.balldontlie.io/api/v1/players/" + request.getParameter("playerId") );
	HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	conn.setRequestMethod("GET");
	conn.connect();
	int responsecode = conn.getResponseCode();
	String inline = "";
	if(responsecode != 200)
	    throw new RuntimeException("HttpResponseCode: " +responsecode);
	else
	{

	    Scanner sc = new Scanner(url.openStream());

	    while(sc.hasNext())
	    {
	        inline+=sc.nextLine();
	    }
	    System.out.println("\nJSON data in string format");
	    System.out.println(inline);
	    sc.close();
	}

	JSONParser parse = new JSONParser();

	JSONObject jobj = (JSONObject)parse.parse(inline);
	//System.out.println("Elements under data array");
	JSONObject playerTeam = (JSONObject) jobj.get("team");
    
    	pageContext.setAttribute("player_first_name", jobj.get("first_name"));

		pageContext.setAttribute("player_last_name", jobj.get("last_name"));
		
		pageContext.setAttribute("player_height_feet", jobj.get("height_feet"));
		
		pageContext.setAttribute("player_height_inches", jobj.get("height_inches"));
		
		pageContext.setAttribute("player_weight", jobj.get("weight_pounds"));
		
		
		
	    pageContext.setAttribute("player_team_name_short", playerTeam.get("name"));
		
		pageContext.setAttribute("player_team_name_long", playerTeam.get("full_name"));
		
		pageContext.setAttribute("player_team_conference", playerTeam.get("conference"));
		
		pageContext.setAttribute("team_logo", "../img/logos/" + playerTeam.get("name") + ".png");
		
		String short_position = (String) jobj.get("position");
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
		
	
	%>
	
	 <hr>
	   	<div class="container">
	<div class="row">
		<div>			
		<img src=${fn:escapeXml(team_logo)} class="img-fluid img-thumbnail" alt="Responsive image">
		<h1><b>${fn:escapeXml(player_first_name)} ${fn:escapeXml(player_last_name)}</b>
		<%
		if(jobj.get("height_feet") != null){
			%>
			- ${fn:escapeXml(player_height_feet)}'${fn:escapeXml(player_height_inches)}''
			<%
		}
		if(jobj.get("weight_pounds") != null){
			%> ${fn:escapeXml(player_weight)} lbs
			<%
		}
		%>
		</h1>
			<div>	
			<p>${fn:escapeXml(player_first_name)} is a ${fn:escapeXml(player_position)} for the ${fn:escapeXml(player_team_name_long)} in the ${fn:escapeXml(player_team_conference)}ern Conference</p>
			</div>
    	</div>
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
