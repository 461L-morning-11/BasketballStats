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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/docs/4.0/assets/img/favicons/favicon.ico">

    <title>Coaches</title>

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
        <form class="form-inline my-2 my-lg-0">
          <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
      </div>
    </nav>

    <main role="main" class="container">

      <div class="main-content">
        <h1>List of Games</h1>
        <br><hr><br>
        
		        
		<% 
		
		
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
		
		JSONArray jsonarr_1 = (JSONArray) jobj.get("data");
		
		
		%>
	
		<table class="table">
			<thead>
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Home Team</th>
					<th scope="col">Home Score</th>
					<th scope="col">Visitor Score</th>
					<th scope="col">Visitor Team</th>
				</tr>
			</thead>
			<tbody>
				
			<%
			for(int i=0;i<jsonarr_1.size();i++)
			{
				JSONObject jsonobj_1 = (JSONObject)jsonarr_1.get(i);
				
				
			    //System.out.println("Elements under data array");
			    
			    JSONObject homeObj = (JSONObject)jsonobj_1.get("home_team");
			    JSONObject visitorObj = (JSONObject)jsonobj_1.get("visitor_team");

		        String shortDate = (String) jsonobj_1.get("date");
			    
			    pageContext.setAttribute("game_date", shortDate.substring(0, 10));

				pageContext.setAttribute("game_home_team", homeObj.get("name"));

				pageContext.setAttribute("game_home_score", jsonobj_1.get("home_team_score"));
				
				pageContext.setAttribute("game_visitor_score", jsonobj_1.get("visitor_team_score"));
				
				pageContext.setAttribute("game_visitor_team", visitorObj.get("name"));
				
				pageContext.setAttribute("game_ID", jsonobj_1.get("id"));
				
			%>
				<tr onclick="window.location='specificGame.jsp?gameId=${game_ID}';">
				
				  	<td>${fn:escapeXml(game_date)}</td>
				  	<td>${fn:escapeXml(game_home_team)}</td>
				  	<td>${fn:escapeXml(game_home_score)}</td>
				  	<td>${fn:escapeXml(game_visitor_score)}</td>
				 	<td>${fn:escapeXml(game_visitor_team)}</td>
					
				  
				</tr>
				<% } %>
			</tbody>
		</table>
		
		
        
      </div>
      
      <nav aria-label="Page navigation">
			<ul class="pagination justify-content-center">
				<li class="page-item disabled">
					<a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
				</li>
				<li class="page-item"><a class="page-link" href="#">1</a></li>
		 		<li class="page-item"><a class="page-link" href="#">2</a></li>
				<li class="page-item"><a class="page-link" href="#">3</a></li>
				<li class="page-item"><a class="page-link" href="#">4</a></li>
		 		<li class="page-item"><a class="page-link" href="#">...</a></li>
				<li class="page-item">
					<a class="page-link" href="#">Next</a>
				</li>
			</ul>
		</nav>

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
