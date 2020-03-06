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
        <form class="form-inline my-2 my-lg-0">
          <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
      </div>
    </nav>

    <main role="main" class="container">

	<div class="main-content">

	</div>

	
	
	<% //pageContext.setAttribute("team_ID", request.getParameter("teamId"));
	URL url = new URL("https://www.balldontlie.io/api/v1/teams/" + request.getParameter("teamId") );
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
    
    pageContext.setAttribute("team_name_long", jobj.get("full_name"));
    
    pageContext.setAttribute("team_name_short", jobj.get("name"));

	pageContext.setAttribute("team_city", jobj.get("city"));

	pageContext.setAttribute("team_abbreviation", jobj.get("abbreviation"));
	
	pageContext.setAttribute("team_division", jobj.get("division"));
	
	pageContext.setAttribute("team_conference", jobj.get("conference"));
	
	pageContext.setAttribute("team_ID", jobj.get("id"));
	
	pageContext.setAttribute("team_logo", "../img/logos/" + jobj.get("name") + ".png");
	%>
    
    <hr>
	   	<div class="container">
	<div class="row">
		<div>			
		<img src=${fn:escapeXml(team_logo)} class="img-fluid img-thumbnail" alt="Responsive image">
		<h1>${fn:escapeXml(team_name_long)} (${fn:escapeXml(team_abbreviation)})</h1>
			<div>	
			<p>The ${fn:escapeXml(team_name_short)} from ${fn:escapeXml(team_city)} play in the ${fn:escapeXml(team_division)} division of the ${fn:escapeXml(team_conference)}ern Conference </p>
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
