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
        <h1>List of Players</h1>
      </div>
      
     <%

    String pageNumber = request.getParameter("page");
    if(pageNumber == null) {
    	pageNumber = "1";
    }
    int pageInt = Integer.parseInt(pageNumber);
	pageContext.setAttribute("page", pageInt);
	
    URL url = new URL("https://www.balldontlie.io/api/v1/players?per_page=100&page=" + pageNumber);
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
    	
    	sc.close();
	}

	JSONParser parse = new JSONParser();

	JSONObject jobj = (JSONObject)parse.parse(inline);

	JSONArray jsonarr_1 = (JSONArray) jobj.get("data");

	%>
	
	<table  class="table table-striped table-hover">
		<thead>
			<tr>
				<th scope="col">First Name</th>
				<th scope="col">Last Name</th>
				<th scope="col">Position</th>
				<th scope="col">Team</th>
			</tr>
		</thead>
		<tbody>
			
	<%

	for(int i=0;i<jsonarr_1.size();i++)
	{
		JSONObject jsonobj_1 = (JSONObject)jsonarr_1.get(i);
	
	
    	//System.out.println("Elements under data array");
    
    	pageContext.setAttribute("player_first_name", jsonobj_1.get("first_name"));

		pageContext.setAttribute("player_last_name", jsonobj_1.get("last_name"));

		pageContext.setAttribute("player_id", jsonobj_1.get("id"));
		
		String short_position = (String) jsonobj_1.get("position");
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
		
		JSONObject player_team = (JSONObject) jsonobj_1.get("team"); 
		pageContext.setAttribute("player_team", player_team.get("full_name"));
	
	%>
    
    			<tr onclick="window.location='specificPlayer.jsp?playerId=${player_id}';">
				
				  	<td>${fn:escapeXml(player_first_name)}</td>
				  	<td>${fn:escapeXml(player_last_name)}</td>
				  	<td>${fn:escapeXml(player_position)}</td>
				  	<td>${fn:escapeXml(player_team)}</td>
					
				  
				</tr>
			<% } %>
		</tbody>
	</table>
	
	<nav aria-label="Page navigation">
		<ul class="pagination justify-content-center">
			
			<li class="page-item <% if(pageInt == 1){ %> disabled <% } %>">
				<a class="page-link" href="players.jsp?page=${page-1}" tabindex="-1" aria-disabled="false">Previous</a>
			</li>
			<% if(pageInt > 3){ %>
			<li class="page-item"><a class="page-link" href="players.jsp?page=${page-3}">${page-3}</a></li>
			<% } %>
			<% if(pageInt > 2){ %>
			<li class="page-item"><a class="page-link" href="players.jsp?page=${page-2}">${page-2}</a></li>
			<% } %>
			<% if(pageInt > 1){ %>
			<li class="page-item"><a class="page-link" href="players.jsp?page=${page-1}">${page-1}</a></li>
			<% } %>
	 		<li class="page-item disabled"><a class="page-link" href="#">${page}</a></li>
			</li>
			<% if(pageInt < 33){ %>
			<li class="page-item"><a class="page-link" href="players.jsp?page=${page+1}">${page+1}</a></li>
			<% } %>
			<% if(pageInt < 32){ %>
			<li class="page-item"><a class="page-link" href="players.jsp?page=${page+2}">${page+2}</a></li>
			<% } %>
			<% if(pageInt < 31){ %>
			<li class="page-item"><a class="page-link" href="players.jsp?page=${page+3}">${page+3}</a></li>
			<% } %>
			<li class="page-item">
				<a class="page-link" href="players.jsp?page=${page+1}">Next</a>
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
